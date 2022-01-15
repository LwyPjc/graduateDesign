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
        <el-form-item label="开课学院" label-width="105px" prop="college">
            <el-input placeholder="请输入开课学院" v-model="dialogFormData.college"/>
        </el-form-item>
        <el-form-item label="课程编码" label-width="105px" prop="code">
            <el-input placeholder="请输入课程编码" v-model="dialogFormData.code"/>
        </el-form-item>
        <el-form-item label="中文名称" label-width="105px" prop="name">
            <el-input placeholder="请输入中文名称" v-model="dialogFormData.name"/>
        </el-form-item>
        <el-form-item label="英文名称" label-width="105px" prop="nameEng">
            <el-input placeholder="请输入英文名称" v-model="dialogFormData.nameEng"/>
        </el-form-item>
        <el-form-item label="课程类别" label-width="105px" prop="type">
            <el-input placeholder="请输入课程类别" v-model="dialogFormData.type"/>
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
        name: "EduCourseAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: '/course',
                dialogFormData: {
                    id: '',
                    college: '',
                    code: '',
                    name: '',
                    nameEng: '',
                    type: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    college: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    code: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nameEng: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    type: [
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
                    college: '',
                    code: '',
                    name: '',
                    nameEng: '',
                    type: '',
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
