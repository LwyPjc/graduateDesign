<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <el-input placeholder="电话号码过滤" v-model="listQuery.phoneNum" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="姓名过滤" v-model="listQuery.trueName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-button
                            type="primary"
                            icon="el-icon-search"
                            size="small"
                            @click="handleFilter">搜索</el-button>
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
                  <el-table-column label="姓名" show-overflow-tooltip style="width: 10%" align="center">
                    <template slot-scope="scope">
                      {{ scope.row.trueName }}
                    </template>
                  </el-table-column>
                    <el-table-column label="电话号码" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.phoneNum }}
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
               :sourceAvatar="sourceAvatar" :targetAvatar="targetAvatar"
               :loadHistory="loadHistory" :sendMessage="sendMessage" />
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
                sourceAvatar: 'https://gitee.com/run27017/assets/raw/master/avatars/bear.jpg',
                targetAvatar: '王五',
                tableKey: 0,//表格key值
                list: null, //表格对象
                listLoading: true, //表格加载框
                total: 0, //分页总数
                tableHeight: window.innerHeight - 240, //表格高度
                listQuery: { //表格查询对象
                    current: 1,
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
                prefixUrl: this.GLOBAL.baseUrl + 'userInfo'
            }
        },
        created() {
            this.fetchData()
        },
        methods: {
          openWs(){
            let ws = new websocket("ws://localhost:8083/ws");
            ws.onopen = function(evt) {
              console.log("Connection open ...");
              ws.send("Hello WebSockets!");
            };
          },

          // 定义加载历史消息的方式，该函数应该返回一个对象（`{ messages, hasMore }`），或者是返回该对象的 Promise （异步）。
          loadHistory () {
            //创建websocket对象
            // let ws = new websocket("ws://localhost:8083/ws");

            //开启websocket连接
            // ws.onopen = function(evt) {
            //   console.log("Connection open ...");

            //主动发送消息
            //   ws.send("Hello WebSockets!");
            // };
            //

            //接收服务端推送的消息
            // ws.onmessage = function(evt) {
            //   console.log( "Received Message: " + evt.data);

            //
            //   ws.close();
            // };
            //
            // ws.onclose = function(evt) {
            //   console.log("Connection closed.");
            // };
            this.listLoading = true
            request({
              url: `${this.prefixUrl}/findListByPage`,
              method: 'get',
              params: this.listQuery
            }).then(res => {
              this.list = res.records
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
            return {
              // 消息数据，字段如下，应以时间的倒序给出。
              messages: [
                { text: "Really cute!", time: new Date(2020, 8, 4), direction: 'sent' },
                { text: "Hey, I'm a bear!", time: new Date(2020, 7, 4), direction: 'received' },
                { text: 'Hello, who are you?', time: new Date(2020, 7, 4), direction: 'sent' },
              ],
              // 定义是否还有历史消息，如果为 false，将停止加载。读者可将其改为 true 演示一下自动滚动更新的效果。
              hasMore: false
            }
          },

          // 定义发送消息的方式。如果发送成功，应该返回成功发送的消息数据，或者 Promise.
          sendMessage ({ text }) {
            return {
              text,
              time: new Date(),
              direction: 'sent'
            }
          },

          // 该函数演示如何加载新消息（一般通过 WebSocket 实时收取）
          receiveMessage (message) {
            this.$refs.chat.appendNew(message)
          },
            /**
             * 获取表格数据
             */
            fetchData() {
                this.listLoading = true
                request({
                    url: `${this.prefixUrl}/findListByPage`,
                    method: 'get',
                    params: this.listQuery
                }).then(res => {
                  this.list = res.records

                  this.list.forEach(l=>{
                    if(0 === l.gender){
                      l.gender = "未知"
                    }else if(l.gender === 1){
                      l.gender = "男"
                    }else {
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
              this.targetAvatar = row.avatarUrl;
                this.$refs.chat.init()
            },
            /**
             * 搜索过滤
             */
            handleFilter() {
                this.listQuery.current = 1
                this.fetchData()
            },
            /**
             * 删除操作
             */
            handleDelete(row) {
              this.$confirm('确定删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
              }).then(() => {
                  request({
                    url: `${this.prefixUrl}/delete/${row.id}`,
                    method: 'get'
                  }).then(res => {
                    this.fetchData();
                    this.$message({
                      message: '操作成功',
                      type: 'success',
                      duration: 1500,
                    })
                  }).catch(error => {
                    this.$message({
                      message: error,
                      type: 'error',
                      duration: 1500,
                    })
                  })
              }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除',
                        duration: 1500,
                    });
              });
            },
        }

    }
</script>
