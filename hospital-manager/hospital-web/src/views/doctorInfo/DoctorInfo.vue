<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <el-input placeholder="医生姓名过滤" v-model="listQuery.name" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="科室id过滤" type="number" v-model.number="listQuery.dptId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="简介过滤" v-model="listQuery.desc" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="头衔id过滤" type="number" v-model.number="listQuery.titleId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="保留字段过滤" v-model="listQuery.temp1" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-button
                            type="primary"
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
                    <el-table-column label="医生姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.name }}
                        </template>
                    </el-table-column>
                    <el-table-column label="科室id" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.dptId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="简介" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.desc }}
                        </template>
                    </el-table-column>
                    <el-table-column label="头衔id" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.titleId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="保留字段" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.temp1 }}
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            align="center"
                            width="180"
                            class-name="small-padding fixed-width">
                        <div slot-scope="scope" class="table-operate-box">
                            <i class="zoeIconfont z_modifyEI_normal"
                               @click="showDialog(scope.row)"></i>
                            <i class="zoeIconfont z_delete_normal danger"
                               @click="handleDelete(scope.row)"></i>
                        </div>
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
    import HandleDialog from './DoctorInfoDialog'
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
                    name: null,
                    dptId: null,
                    desc: null,
                    titleId: null,
                    temp1: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '/doctorInfo'
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
