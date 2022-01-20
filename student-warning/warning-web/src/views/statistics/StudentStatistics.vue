<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <el-input placeholder="学生姓名过滤" v-model="listQuery.studentName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <el-input placeholder="课程名称过滤" v-model="listQuery.courseName" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
<!--                    <el-input placeholder="类型" v-model="listQuery.type" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>-->
                    <el-input placeholder="预警等级过滤" v-model="listQuery.warningLevel" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
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
                    <el-table-column label="学生姓名" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.studentName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="课程名称" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.courseName }}
                        </template>
                    </el-table-column>
                    <el-table-column label="类型" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.type }}
                        </template>
                    </el-table-column>
                    <el-table-column label="预警等级" show-overflow-tooltip  align="center">
                        <template slot-scope="scope" >
                          <el-tag :color="getColour(scope.row.warningLevel)">
                            {{ scope.row.warningLevel }}
                          </el-tag>
                        </template>
                    </el-table-column>
<!--                    <el-table-column label="预警值" show-overflow-tooltip style="width: 10%" align="center">-->
<!--                        <template slot-scope="scope">-->
<!--                            {{ scope.row.value }}-->
<!--                        </template>-->
<!--                    </el-table-column>-->
<!--                    <el-table-column-->
<!--                            label="操作"-->
<!--                            align="center"-->
<!--                            width="180"-->
<!--                            class-name="small-padding fixed-width">-->
<!--                        <div slot-scope="scope" class="table-operate-box">-->
<!--                            <i class="zoeIconfont z_modifyEI_normal"-->
<!--                               @click="showDialog(scope.row)"></i>-->
<!--                            <i class="zoeIconfont z_delete_normal danger"-->
<!--                               @click="handleDelete(scope.row)"></i>-->
<!--                        </div>-->
<!--                    </el-table-column>-->
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
    import HandleDialog from './StudentStatisticsDialog'
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
                    studentName: null,
                    courseName: null,
                    type: null,
                    warningLevel: null,
                    value: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '/studentStatistics'
            }
        },
        created() {
            this.fetchData()
        },
        methods: {
          getColour(s) {
            console.log("拿到东西", s);
            if(s === '警告') {
              return 'yellow';
            } else if(s === '严重警告') {
              return 'green'
            } else if(s === '记过') {
              return 'blue';
            }
            return "red";
          },
            /**
             * 获取表格数据
             */
            fetchData() {
                this.listLoading = true;
              this.listQuery.teacherName = window.sessionStorage.username;
                request({
                    url: `${this.prefixUrl}/findListByPage`,
                    method: 'get',
                    params: this.listQuery
                }).then(res => {
                  this.list = res.records
                  this.list.forEach(l=>{
                    console.log('内容',l);
                    if(l.type ==='1'){
                      l.type = '缺课程(节)';
                    }else if(l.type === '2'){
                      l.type = '缺作业(次)';
                    }else {
                      l.type = '成绩不合格';
                    }
                  });
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
