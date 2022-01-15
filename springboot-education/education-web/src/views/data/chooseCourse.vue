<template>
  <div class="app-container">
    <el-input placeholder="学期过滤" v-model="listQuery.term" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
    <el-select v-model="listQuery.courseId" placeholder="请选择课程">
      <el-option
        v-for="item in courses"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
    <!-- 表头 查询与新增 -->
    <el-row>
      <el-col :span="24" class="filter-container">
        <el-button
          type="primary"
          icon="el-icon-search"
          size="small"
          @click="handleFilter">搜索</el-button>
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
          <el-table-column label="学期" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.term }}
            </template>
          </el-table-column>
          <el-table-column label="课程名称" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.courseName }}
            </template>
          </el-table-column>
          <el-table-column label="学分" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.score }}
            </template>
          </el-table-column>
          <el-table-column label="学时" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.hour }}
            </template>
          </el-table-column>
          <el-table-column label="开始周" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.weekStart }}
            </template>
          </el-table-column>
          <el-table-column label="结束周" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.weekEnd }}
            </template>
          </el-table-column>
          <el-table-column label="周次" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.weekTime }}
            </template>
          </el-table-column>
          <el-table-column label="教师" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.teacherName }}
            </template>
          </el-table-column>
          <el-table-column label="教室" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.roomName }}
            </template>
          </el-table-column>
          <el-table-column label="选择人数" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.chooseNum }}
            </template>
          </el-table-column>
          <el-table-column label="课程人数" show-overflow-tooltip style="width: 10%" align="center">
            <template slot-scope="scope">
              {{ scope.row.number }}
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="180"
            class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="small" @click="choose(scope.row.id,scope.row.courseName)">选择</el-button>
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
  </div>
</template>

<script>
    import request from '@/utils/request'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
        components: {
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
                    term: null,
                    college: null,
                    classId: null,
                    courseId: null,
                    score: null,
                    hour: null,
                    number: null,
                    weekStart: null,
                    weekEnd: null,
                    nodeStart: null,
                    nodeEnd: null,
                    teacherId: null,
                    classroomId: null,
                    chooseNum: null,
                    userName: window.sessionStorage.username
                },
                classes:[],
                courses:[],
                teacheres:[],
                classrooms:[],
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '/openCourse'
            }
        },
        created() {
            this.fetchData();
            this.getInitData();
        },
        methods: {
            /**
             * 获取表格数据
             */
            fetchData() {
                this.listLoading = true
                request({
                    url: `${this.prefixUrl}/stuFindListByPage`,
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
             * 请求所有初始化数据
             */
            getInitData() {
                //请求所有课程
                request({
                    url: `${this.GLOBAL.baseUrl}course/findList`,
                    method: 'get'
                }).then(res => {
                    this.courses.push({value:"",label:"全部"});
                    for (var i = 0; i < res.length; i++) {
                        var obj = {
                            value: res[i].id, label: res[i].name
                        };
                        this.courses.push(obj);
                    }
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
             * 搜索过滤
             */
            handleFilter() {
                this.listQuery.current = 1
                //将值为空字符串的内容设为null
                for(var k in this.listQuery){
                    if(!this.listQuery[k]){
                        this.listQuery[k] = null
                    }
                }
                this.fetchData()
            },
            /**
             * 选课
             */
            choose(id,name){
                this.$confirm('是否确定选择' + name + '?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    request({
                        url: `${this.GLOBAL.baseUrl}studentCourse/save`,
                        method: 'post',
                        params: {userName:window.sessionStorage.username,openCourseId:id}
                    }).then(res => {
                        if (res.status === "200") {
                            this.fetchData();
                            this.$message({
                                message: '选课成功',
                                type: 'success',
                                duration: 1500,
                            })
                        }else {
                            this.$message({
                                message: res.msg,
                                type: 'error',
                                duration: 1500,
                            })
                        }


                    }).catch(error => {
                        this.$message({
                            message: error,
                            type: 'error',
                            duration: 1500,
                        })
                    })
                }).catch(() => {

                });
            }
        }
    }
</script>
