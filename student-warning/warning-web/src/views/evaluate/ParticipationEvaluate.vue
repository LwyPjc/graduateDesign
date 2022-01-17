<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <el-input placeholder="学生ID过滤" type="number" v-model.number="listQuery.studentId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="教师ID过滤" type="number" v-model.number="listQuery.teacherId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="课程ID过滤" type="number" v-model.number="listQuery.openCourseId" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="缺勤次数过滤" type="number" v-model.number="listQuery.count" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="学生姓名过滤" v-model="listQuery.studentName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="教师姓名过滤" v-model="listQuery.teacherName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="课程名称过滤" v-model="listQuery.courseName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
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
                    <el-table-column label="学生ID" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.studentId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="教师ID" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.teacherId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="课程ID" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.openCourseId }}
                        </template>
                    </el-table-column>
                    <el-table-column label="缺勤次数" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.count }}
                        </template>
                    </el-table-column>
                    <el-table-column label="学生姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.studentName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="教师姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.teacherName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="课程名称" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.courseName }}
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
    import HandleDialog from './ParticipationEvaluateDialog'
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
                    studentId: null,
                    teacherId: null,
                    openCourseId: null,
                    count: null,
                    studentName: null,
                    teacherName: null,
                    courseName: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '/participationEvaluate'
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
