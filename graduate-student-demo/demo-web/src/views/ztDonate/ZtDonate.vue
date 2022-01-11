<template>
  <div class="app-container">
    <!-- 表头 查询与新增 -->
    <el-row>
      <el-col :span="24" class="filter-container">
        <el-input placeholder="捐赠用户过滤" type="text" v-model.number="listQuery.userName" size="small" class="filter-item"
                  @keyup.enter.native="handleFilter"/>
        <el-input placeholder="物品名称过滤" type="text" v-model.number="listQuery.name" size="small" class="filter-item"
                  @keyup.enter.native="handleFilter"/>
        <el-date-picker
          v-model="listQuery.donateTime"
          value-format="timestamp"
          type="datetime"
          placeholder="选择日期时间"
          size="small"
          class="filter-item">
        </el-date-picker>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="small"
          @click="handleFilter">搜索
        </el-button>
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
          <el-table-column label="捐赠用户" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.userName }}
            </template>
          </el-table-column>
          <el-table-column label="文物名称" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.name }}
            </template>
          </el-table-column>
          <el-table-column label="图片" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              <img
                :src='imageUrl+"?path="+ scope.row.photoUrl +"&name="+ scope.row.photoName'
                alt="无法显示图片"
                style="width: 100%; height: 100%"
                fit="contain"
                :preview-src-list="srcList"
              />
            </template>
          </el-table-column>
          <el-table-column label="简介" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.produce }}
            </template>
          </el-table-column>
          <el-table-column label="种类" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.typeChn }}
            </template>
          </el-table-column>
          <!--<el-table-column label="捐赠时间" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.donateTime}}
            </template>
          </el-table-column>-->
          <el-table-column label="状态" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.statusChn }}
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="180"
            class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <template v-if="scope.row.status === 0">
                <el-button size="small" @click="auditConfim(scope.row)">审批</el-button>
              </template>
              <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
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
    <handle-dialog ref="dlg" @refreshDataList="fetchData"></handle-dialog>
  </div>
</template>

<script>
    import request from '@/utils/request'
    import HandleDialog from './ZtDonateDialog'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
        components: {
            HandleDialog,
            Pagination
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
                tableKey: 0,//表格key值
                list: null, //表格对象
                listLoading: true, //表格加载框
                total: 0, //分页总数
                tableHeight: window.innerHeight - 240, //表格高度
                listQuery: { //表格查询对象
                    current: 1,
                    size: 10,
                    query: '',
                    userId: null,
                    goodsId: null,
                    donatePrice: null,
                    donateTime: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                srcList: [
                    'https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg',
                    'https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg'
                ],
                prefixUrl: this.GLOBAL.baseUrl + '/ztDonate',
                imageUrl: this.GLOBAL.baseUrl + 'image/showImg'
            }
        },
        created() {
            this.fetchData()
        },
        methods: {
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
            //点击审批按钮
            auditConfim(row) {
                this.$confirm('该捐赠文物是否通过?', '确认', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //发起请求更改文物状态
                    request({
                        url: `${this.GLOBAL.baseUrl}/ztGoods/auditGoods`,
                        method: 'get',
                        params: {goodId:row.id}
                    }).then(res => {
                        this.fetchData();
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                            duration: 1500,
                            onClose: () => {
                            }
                        })
                    }).catch(error => {

                    })
                }).catch(() => {
                });
            },

            /**
             * 显示修改编辑框
             */
            showDialog(data) {
                this.$refs.dlg.init(data)
            },
            /**
             * 搜索过滤
             */
            handleFilter() {
                this.listQuery.current = 1;
                //将值为空字符串的内容设为null
                for(var k in this.listQuery){
                    if(!this.listQuery[k]){
                        this.listQuery[k] = null
                    }
                }
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
