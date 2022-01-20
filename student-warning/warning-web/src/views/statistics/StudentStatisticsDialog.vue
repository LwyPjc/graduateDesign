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
        <el-form-item label="学生姓名" label-width="105px" prop="studentName">
            <el-input placeholder="请输入学生姓名" v-model="dialogFormData.studentName"/>
        </el-form-item>
        <el-form-item label="课程名称" label-width="105px" prop="courseName">
            <el-input placeholder="请输入课程名称" v-model="dialogFormData.courseName"/>
        </el-form-item>
        <el-form-item label="类型:1:缺几节课程 2:缺几次作业 3:成绩不合格" label-width="105px" prop="type">
            <el-input placeholder="请输入类型:1:缺几节课程 2:缺几次作业 3:成绩不合格" v-model="dialogFormData.type"/>
        </el-form-item>
        <el-form-item label="预警等级" label-width="105px" prop="warningLevel">
            <el-input placeholder="请输入预警等级" v-model="dialogFormData.warningLevel"/>
        </el-form-item>
        <el-form-item label="预警值" label-width="105px" prop="value">
            <el-input placeholder="请输入预警值" v-model="dialogFormData.value"/>
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
        name: "StudentStatisticsAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/studentStatistics',
                dialogFormData: {
                    id: '',
                    studentName: '',
                    courseName: '',
                    type: '',
                    warningLevel: '',
                    value: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    studentName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    courseName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    type: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    warningLevel: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    value: [
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
                    studentName: '',
                    courseName: '',
                    type: '',
                    warningLevel: '',
                    value: '',
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
