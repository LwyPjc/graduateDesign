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
        <el-form-item label="学期" label-width="105px" prop="term">
            <el-input placeholder="请输入学期" v-model="dialogFormData.term"/>
        </el-form-item>
        <el-form-item label="开课学院" label-width="105px" prop="college">
            <el-input placeholder="请输入开课学院" v-model="dialogFormData.college"/>
        </el-form-item>
        <el-form-item label="班级id" label-width="105px" prop="classId">
            <el-input type="number" placeholder="请输入班级id" v-model.number="dialogFormData.classId"/>
        </el-form-item>
        <el-form-item label="课程id" label-width="105px" prop="courseId">
            <el-input type="number" placeholder="请输入课程id" v-model.number="dialogFormData.courseId"/>
        </el-form-item>
        <el-form-item label="学分" label-width="105px" prop="score">
            <el-input placeholder="请输入学分" v-model="dialogFormData.score"/>
        </el-form-item>
        <el-form-item label="学时" label-width="105px" prop="hour">
            <el-input type="number" placeholder="请输入学时" v-model.number="dialogFormData.hour"/>
        </el-form-item>
        <el-form-item label="人数" label-width="105px" prop="number">
            <el-input type="number" placeholder="请输入人数" v-model.number="dialogFormData.number"/>
        </el-form-item>
        <el-form-item label="开始周" label-width="105px" prop="weekStart">
            <el-input type="number" placeholder="请输入开始周" v-model.number="dialogFormData.weekStart"/>
        </el-form-item>
        <el-form-item label="结束周" label-width="105px" prop="weekEnd">
            <el-input type="number" placeholder="请输入结束周" v-model.number="dialogFormData.weekEnd"/>
        </el-form-item>
        <el-form-item label="开始节" label-width="105px" prop="nodeStart">
            <el-input type="number" placeholder="请输入开始节" v-model.number="dialogFormData.nodeStart"/>
        </el-form-item>
        <el-form-item label="结束节" label-width="105px" prop="nodeEnd">
            <el-input type="number" placeholder="请输入结束节" v-model.number="dialogFormData.nodeEnd"/>
        </el-form-item>
        <el-form-item label="教师id" label-width="105px" prop="teacherId">
            <el-input type="number" placeholder="请输入教师id" v-model.number="dialogFormData.teacherId"/>
        </el-form-item>
        <el-form-item label="教室id" label-width="105px" prop="classroomId">
            <el-input type="number" placeholder="请输入教室id" v-model.number="dialogFormData.classroomId"/>
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
        name: "EduOpenCourseAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: '/openCourse',
                dialogFormData: {
                    id: '',
                    term: '',
                    college: '',
                    classId: '',
                    courseId: '',
                    score: '',
                    hour: '',
                    number: '',
                    weekStart: '',
                    weekEnd: '',
                    nodeStart: '',
                    nodeEnd: '',
                    teacherId: '',
                    classroomId: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    term: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    college: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    classId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    courseId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    score: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    hour: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    number: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    weekStart: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    weekEnd: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nodeStart: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nodeEnd: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    teacherId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    classroomId: [
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
                    term: '',
                    college: '',
                    classId: '',
                    courseId: '',
                    score: '',
                    hour: '',
                    number: '',
                    weekStart: '',
                    weekEnd: '',
                    nodeStart: '',
                    nodeEnd: '',
                    teacherId: '',
                    classroomId: '',
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
