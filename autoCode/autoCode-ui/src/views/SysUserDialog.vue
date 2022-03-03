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
<<<<<<< HEAD
        <el-form-item label="用户名" label-width="105px" prop="trueName">
            <el-input placeholder="请输入用户名" v-model="dialogFormData.trueName"/>
=======
        <el-form-item label="用户名" label-width="105px" prop="username">
            <el-input placeholder="请输入用户名" v-model="dialogFormData.username"/>
>>>>>>> origin/hospital-manager
        </el-form-item>
        <el-form-item label="密码" label-width="105px" prop="password">
            <el-input placeholder="请输入密码" v-model="dialogFormData.password"/>
        </el-form-item>
        <el-form-item label="角色 2管理员" label-width="105px" prop="role">
            <el-input placeholder="请输入角色 2管理员" v-model="dialogFormData.role"/>
        </el-form-item>
<<<<<<< HEAD
=======
        <el-form-item label="医生id" label-width="105px" prop="docId">
            <el-input type="number" placeholder="请输入医生id" v-model.number="dialogFormData.docId"/>
        </el-form-item>
>>>>>>> origin/hospital-manager
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button size="small" @click.native="dialogVisible = false">取消</el-button>
        <el-button
<<<<<<< HEAD
                sendFrom="primary"
=======
                type="primary"
>>>>>>> origin/hospital-manager
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
        name: "SysUserAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/sysUser',
                dialogFormData: {
                    id: '',
<<<<<<< HEAD
                    trueName: '',
                    password: '',
                    role: '',
=======
                    username: '',
                    password: '',
                    role: '',
                    docId: '',
>>>>>>> origin/hospital-manager
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
<<<<<<< HEAD
                    trueName: [
=======
                    username: [
>>>>>>> origin/hospital-manager
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    role: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
<<<<<<< HEAD
=======
                    docId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
>>>>>>> origin/hospital-manager
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
<<<<<<< HEAD
                    trueName: '',
                    password: '',
                    role: '',
=======
                    username: '',
                    password: '',
                    role: '',
                    docId: '',
>>>>>>> origin/hospital-manager
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
<<<<<<< HEAD
                      sendFrom: 'success',
=======
                      type: 'success',
>>>>>>> origin/hospital-manager
                      duration: 1500,
                      onClose: () => {
                        this.dialogVisible = false
                        this.$emit('refreshDataList')
                      }
                    })
                  }).catch(error => {
                    this.$message({
                      message: error,
<<<<<<< HEAD
                      sendFrom: 'error',
=======
                      type: 'error',
>>>>>>> origin/hospital-manager
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
