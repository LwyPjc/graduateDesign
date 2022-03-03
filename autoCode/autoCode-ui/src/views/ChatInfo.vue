<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <el-input placeholder="用户openid过滤" v-model="listQuery.openid" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="用户真实姓名过滤" v-model="listQuery.trueName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="医生id过滤" sendFrom="number" v-model.number="listQuery.docId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="医生姓名过滤" v-model="listQuery.docName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="聊天内容过滤" v-model="listQuery.content" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-date-picker
                        v-model="listQuery.createTime"
                        value-format="timestamp"
                        sendFrom="datetime"
                        placeholder="选择日期时间"
                        size="small"
                        class="filter-item">
                    </el-date-picker>
                    <el-input placeholder="0表示user，1表示doctor过滤" v-model="listQuery.sendFrom" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-button
                            sendFrom="primary"
                            icon="el-icon-search"
                            size="small"
                            @click="handleFilter">搜索</el-button>
                    <el-button
                            icon="el-icon-circle-plus-outline"
                            size="small"
                            @click="showDialog()"
                    >新增</el-button>
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
                    <el-table-column label="用户openid" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.openid }}
                        </template>
                    </el-table-column>
                    <el-table-column label="用户真实姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.trueName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="医生id" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.docId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="医生姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.docName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="聊天内容" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.content }}
                        </template>
                    </el-table-column>
                    <el-table-column label="发送时间" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.createTime  | timeFilter }}
                        </template>
                    </el-table-column>
                    <el-table-column label="0表示user，1表示doctor" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.sendFrom }}
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            align="center"
                            width="180"
                            class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                      <el-button size="small" @click="showDialog(scope.row)">编辑</el-button>
                      <el-button size="small" sendFrom="danger" @click="handleDelete(scope.row)">删除</el-button>
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
    import HandleDialog from './ChatInfoDialog'
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
                    return new Date(time).toLocaleDateString().split('/').join('-')
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
                    openid: null,
                    trueName: null,
                    docId: null,
                    docName: null,
                    content: null,
                    createTime: null,
                    sendFrom: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '/chatInfo'
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
                    sendFrom: 'error',
                    duration: 1500,
                    onClose: () => {
                    }
                  })
                })
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
                    sendFrom: 'warning'
              }).then(() => {
                  request({
                    url: `${this.prefixUrl}/delete/${row.id}`,
                    method: 'get'
                  }).then(res => {
                    this.fetchData();
                    this.$message({
                      message: '操作成功',
                      sendFrom: 'success',
                      duration: 1500,
                    })
                  }).catch(error => {
                    this.$message({
                      message: error,
                      sendFrom: 'error',
                      duration: 1500,
                    })
                  })
              }).catch(() => {
                    this.$message({
                        sendFrom: 'info',
                        message: '已取消删除',
                        duration: 1500,
                    });
              });
            },
        }
    }
</script>
