<template>
  <div class="app-container">
    <!-- 表头 查询与新增 -->
    <el-row>
      <el-col :span="24" class="filter-container">
        <el-input placeholder="姓名过滤" v-model="listQuery.trueName" size="small" class="filter-item"
                  @keyup.enter.native="handleFilter"/>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="small"
          @click="handleFilter">搜索
        </el-button>
        <!--                    <el-button-->
        <!--                            icon="el-icon-circle-plus-outline"-->
        <!--                            size="small"-->
        <!--                            @click="showDialog()"-->
        <!--                    >新增</el-button>-->
      </el-col>
    </el-row>
    <!-- 表格list -->
    <el-row>
      <el-col :span="24">
        <el-table
          v-loading="listLoading"
          :data="list"
          element-loading-text="Loading"
          border
          fit
          :height="tableHeight"
          style="width: 100%;"
          highlight-current-row>
          <el-table-column align="center" label="序号" width="80">
            <template slot-scope="scope">
              {{ scope.$index }}
            </template>
          </el-table-column>
          <el-table-column label="聊天对象" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.name }}
            </template>
          </el-table-column>
          <el-table-column label="聊天" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              <el-button size="medium" @click="showDialog(scope.row)" type="danger">聊天</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total"
                    :page.sync="listQuery.current"
                    :limit.sync="listQuery.size"
                    layout="total, sizes, prev, pager, next"
                    @pagination="fetchData"/>
      </el-col>
    </el-row>
    <ChatBox ref="chat"
             :sourceAvatar="sourceAvatar" :targetAvatar="targetAvatar" :people="openId"
             :loadHistory="loadHistory" :sendMessage="sendMessage"/>
  </div>
</template>

<script>
  import '@/utils/chat.css'
  import ChatBox from '@/views/chat/ChatBox'
  import request from '@/utils/request'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  export default {
    components: {
      Pagination,
      ChatBox
    },
    filters: {
      statusFilter(status) {
        const statusMap = {
          1: 'success',
          2: 'gray',
          0: 'danger'
        }
        return statusMap[status]
      },
      validFilter(status) {
        const statusMap = {
          1: '有效',
          0: '无效'
        }
        return statusMap[status]
      },
      timeFilter(time) {
        if (time) {
          return new Date(time).Format('yyyy-MM-dd hh:mm:ss')
        } else {
          return ''
        }
      },
    },
    data() {
      return {
        sourceAvatar: '',
        targetAvatar: '',
        tableKey: 0,//表格key值
        list: null, //表格对象
        listLoading: true, //表格加载框
        total: 0, //分页总数
        tableHeight: window.innerHeight - 240, //表格高度
        openId: null,
        docId: null,
        people: null,
        messagess: [],
        url: this.GLOBAL.baseUrl,
        listQuery: { //表格查询对象
          current: 1,
          docId: null,
          size: 10,
          query: '',
          avatarUrl: null,
          nickName: null,
          phoneNum: null,
          medicareCard: null,
          gender: null,
          idCard: null,
          age: null,
          address: null,
          temp2: null,
          trueName: null,
        },
        statusOptions: { //有效无效下拉框
          '1': '有效',
          '0': '无效'
        },
        prefixUrl: this.GLOBAL.baseUrl + 'chatRecord',
        ws: null,
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      destroyed() {
        this.ws.close();
      },
      // 定义加载历史消息的方式，该函数应该返回一个对象（`{ messages, hasMore }`），或者是返回该对象的 Promise （异步）。
      loadHistory() {
        return request({
          url: `${this.GLOBAL.baseUrl}chatInfo/findByDoubleIds`,
          method: 'get',
          params: {docId: this.docId, openid: this.openId},
          async: false, // 同步
        }).then(res => {
          let dd = {
            // 消息数据，字段如下，应以时间的倒序给出。
            messages: [],
            // 定义是否还有历史消息，如果为 false，将停止加载。读者可将其改为 true 演示一下自动滚动更新的效果。
            hasMore: false
          };
          res.reverse().forEach(a => {
            let aa = Object.assign({
              text: a.content,
              time: a.createTime,
              direction: a.sendFrom === '0' ? 'received' : 'sent'
            });

            dd.messages.push(aa);
          });
          return dd;
        }).catch(error => {
          this.$message({
            message: error,
            type: 'error',
            duration: 1500,
            onClose: () => {
            }
          })
        });
      },

      // 定义发送消息的方式。如果发送成功，应该返回成功发送的消息数据，或者 Promise.
      sendMessage({text}) {
        //主动发送消息
        console.log('sendMessage--text-',text)
        let mm = {
          openid:this.openId,
          trueName:this.trueName,
          content:text,
          sendFrom:"1",
          docId:this.docId,
          docName:this.docName
        };
        this.ws.send(JSON.stringify(mm));
        //
        return {
          text,
          time: new Date(),
          direction: 'sent'
        }
      },

      /**
       * 获取表格数据
       */
      fetchData() {
        this.listLoading = true
        if('1'===window.sessionStorage.getItem("role")) {
          this.listQuery.docId = window.sessionStorage.getItem("docId");
        }
        request({
          url: `${this.prefixUrl}/findListByPage`,
          method: 'get',
          params: this.listQuery
        }).then(res => {
          this.list = res.records
            this.list.forEach(re=>{
              let name = '患者:'+re.trueName+' 医生:'+re.docName;
              re.name = name;
            })
          this.list.forEach(l => {
            if (0 === l.gender) {
              l.gender = "未知"
            } else if (l.gender === 1) {
              l.gender = "男"
            } else {
              l.gender = "女";
            }
          })

          this.total = res.total
          this.listLoading = false
        }).catch(error => {
          this.$message({
            message: error,
            type: 'error',
            duration: 1500,
            onClose: () => {
            }
          })
        })
      },
      /**
       * 显示修改编辑框
       */
      showDialog(row) {
        console.log('data is', row);
        this.targetAvatar = row.trueName;
        this.sourceAvatar = row.docName;
        this.openId = row.openid;
        this.trueName = row.trueName;
        this.docName = row.docName;
        this.docId = row.docId;
        this.people = row.trueName;
        this.$refs.chat.init(row.trueName);
        if(window.sessionStorage.getItem("role") === '1'){
          this.conenctWs();
        }
      },
      conenctWs() {
        //创建websocket对象
        //  this.ws = new Websocket("ws://localhost:8083/ws");
        var that = this;
        this.ws = new WebSocket("ws://localhost:8083/ws");

        //开启websocket连接
        this.ws.onopen = function (evt) {
          console.log("Connection open ...");
        }

        // 接收患者的消息
        this.ws.onmessage = function(evt) {
          let a = evt.data;
          let b = JSON.parse(a);
          // 过滤消息
          // 医生 患者对应，且不是医生发的消息才更新
         if(that.docId==b.docId && that.openId === b.openid && b.sendFrom === '0') {
           console.log("Received Message: " + evt.data);
           let newmsg = Object.assign({
             text: b.content,
             time: new Date(),
             direction: b.sendFrom === '0' ? 'received' : 'sent'
           });
           that.$refs.chat.appendNew(newmsg);
         }
        }
      },
      /**
       * 搜索过滤
       */
      handleFilter() {
        this.listQuery.current = 1
        this.fetchData()
      },
    }

  }
</script>
