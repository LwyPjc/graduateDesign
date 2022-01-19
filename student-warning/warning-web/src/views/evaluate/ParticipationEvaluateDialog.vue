<template>
<!-- 编辑弹窗 -->
  <el-dialog
    :title="dialogFormData.id == ''? '新增': '修改'"
    :visible.sync="dialogVisible"
    :close-on-click-modal="false"
    :before-close="handleClose"
    width="683px"
    >
    <el-form :model="dialogFormData"
             label-width="120px"
             :rules="dialogFormRules"
             :inline="true"
             size="small"
             ref="dialogForm"
             label-position="right">
        <el-form-item label="学生" label-width="105px" prop="studentId">
            <!--<el-input type="number" placeholder="请输入学生ID" v-model.number="dialogFormData.studentId"/>-->
          <el-select v-model="dialogFormData.studentId" placeholder="请选择学生" style="width: 220%">
            <el-option
              v-for="item in dialogFormData.students"
              :key="item.id"
              :label="item.stuName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程" label-width="105px" prop="openCourseId">
<!--            <el-input type="number" placeholder="请输入课程ID" v-model.number="dialogFormData.openCourseId"/>-->
          <el-select v-model="dialogFormData.openCourseId" placeholder="请选择课程" style="width: 220%">
            <el-option
              v-for="item in dialogFormData.courses"
              :key="item.id"
              :label="item.courseName"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="缺勤次数" label-width="105px" prop="count">
            <el-input type="number" placeholder="请输入缺勤次数" v-model.number="dialogFormData.count"/>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="dialogVisible = false">取消</el-button>
        <el-button
                type="primary"
                :loading="dialogLoading"
                size="small"
                @click.native="dialogSubmit"
        >
            提交
        </el-button>
    </div>
  </el-dialog>
</template>

<script>
  import request from '@/utils/request'
    export default {
        name: "ParticipationEvaluateAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/participationEvaluate',
                dialogFormData: {
                    id: '',
                    studentId: '',
                    teacherId: '',
                    openCourseId: '',
                    count: '',
                    studentName: '',
                    teacherName: '',
                    courseName: ''
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    count: [
                        { required: true, message: '次数不能为空', trigger: 'blur' }
                    ],
                  studentId: [
                      { required: true, message: '学生信息不能为空', trigger: 'blur' }
                    ],
                  openCourseId: [
                      { required: true, message: '课程信息不能为空', trigger: 'blur' }
                    ],
                }
            }
        },
        computed: {
            validSwitch: {
                // getter
                get: function () {
                    return this.dialogFormData.validFlag === '1'
                },
                // setter
                set: function (newValue) {
                    if (newValue) {
                        this.dialogFormData.validFlag = '1'
                    } else {
                        this.dialogFormData.validFlag = '0'
                    }
                }
            }
        },
        methods: {
            init(row) {
                this.resetModel()
                if (row) {
                    this.dialogFormData = Object.assign({}, row);
                }
                this.dialogVisible = true
            },
            resetModel() {
                this.dialogFormData = {
                    id: '',
                    studentId: '',
                    teacherId: '',
                    openCourseId: '',
                    count: '',
                    studentName: '',
                    teacherName: '',
                    courseName: '',
                }

            },
            /**
            * 关闭对话框操作
           */
           handleClose(done) {
             this.dialogVisible = false
           },
           dialogSubmit() {
                this.$refs.dialogForm.validate((valid) => {
                    if (!valid) {
                      return false
                    }
                  const apiName = `${!this.dialogFormData.id ? 'save' : 'edit'}`;
                    this.dialogFormData.students = null;
                    this.dialogFormData.courses = null;
                    this.dialogFormData.teacherName = window.sessionStorage.username;
                  request({
                    url: `${this.prefixUrl}/${apiName}`,
                    method: 'post',
                    params: this.dialogFormData
                  }).then(res => {
                    console.log('返回结果', res)
                    let status = res.status;
                    if(status === '500'){
                      this.$message({
                        message: res.msg,
                        type: 'error',
                        duration: 1500,
                        onClose: () => {
                        }
                      })
                      return;
                    }
                    this.$message({
                      message: '操作成功',
                      type: 'success',
                      duration: 1500,
                      onClose: () => {
                        this.dialogVisible = false
                        this.$emit('refreshDataList')
                      }
                    })
                  }).catch(error => {
                    console.log('错误信息',error)
                    this.$message({
                      message: error,
                      type: 'error',
                      duration: 1500,
                      onClose: () => {
                      }
                    })
                  })
                })
           }
        }
    }
</script>

<style scoped>

</style>
