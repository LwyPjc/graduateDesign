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
        <el-form-item label="学生ID" label-width="105px" prop="studentId">
            <el-input type="number" placeholder="请输入学生ID" v-model.number="dialogFormData.studentId"/>
        </el-form-item>
        <el-form-item label="教师ID" label-width="105px" prop="teacherId">
            <el-input type="number" placeholder="请输入教师ID" v-model.number="dialogFormData.teacherId"/>
        </el-form-item>
        <el-form-item label="课程ID" label-width="105px" prop="openCourseId">
            <el-input type="number" placeholder="请输入课程ID" v-model.number="dialogFormData.openCourseId"/>
        </el-form-item>
        <el-form-item label="分数" label-width="105px" prop="score">
            <el-input placeholder="请输入分数" v-model="dialogFormData.score"/>
        </el-form-item>
        <el-form-item label="学生姓名" label-width="105px" prop="studentName">
            <el-input placeholder="请输入学生姓名" v-model="dialogFormData.studentName"/>
        </el-form-item>
        <el-form-item label="教师姓名" label-width="105px" prop="teacherName">
            <el-input placeholder="请输入教师姓名" v-model="dialogFormData.teacherName"/>
        </el-form-item>
        <el-form-item label="课程名称" label-width="105px" prop="courseName">
            <el-input placeholder="请输入课程名称" v-model="dialogFormData.courseName"/>
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
        name: "ScoreEvaluateAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/scoreEvaluate',
                dialogFormData: {
                    id: '',
                    studentId: '',
                    teacherId: '',
                    openCourseId: '',
                    score: '',
                    studentName: '',
                    teacherName: '',
                    courseName: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    studentId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    teacherId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    openCourseId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    score: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    studentName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    teacherName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    courseName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
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
                    score: '',
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
                  const apiName = `${!this.dialogFormData.id ? 'save' : 'edit'}`
                  request({
                    url: `${this.prefixUrl}/${apiName}`,
                    method: 'post',
                    params: this.dialogFormData
                  }).then(res => {
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
