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
        <el-form-item label="用户openid" label-width="105px" prop="openid">
            <el-input placeholder="请输入用户openid" v-model="dialogFormData.openid"/>
        </el-form-item>
        <el-form-item label="用户真实姓名" label-width="105px" prop="trueName">
            <el-input placeholder="请输入用户真实姓名" v-model="dialogFormData.trueName"/>
        </el-form-item>
        <el-form-item label="医生id" label-width="105px" prop="docId">
<<<<<<< HEAD
            <el-input sendFrom="number" placeholder="请输入医生id" v-model.number="dialogFormData.docId"/>
=======
            <el-input type="number" placeholder="请输入医生id" v-model.number="dialogFormData.docId"/>
>>>>>>> origin/hospital-manager
        </el-form-item>
        <el-form-item label="医生姓名" label-width="105px" prop="docName">
            <el-input placeholder="请输入医生姓名" v-model="dialogFormData.docName"/>
        </el-form-item>
        <el-form-item label="聊天内容" label-width="105px" prop="content">
            <el-input placeholder="请输入聊天内容" v-model="dialogFormData.content"/>
        </el-form-item>
        <el-form-item label="发送时间" label-width="105px" prop="createTime">
            <el-date-picker
                v-model="dialogFormData.createTime"
<<<<<<< HEAD
                sendFrom="date"
=======
                type="date"
>>>>>>> origin/hospital-manager
                placeholder="选择发送时间">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="0表示user，1表示doctor" label-width="105px" prop="sendFrom">
            <el-input placeholder="请输入0表示user，1表示doctor" v-model="dialogFormData.sendFrom"/>
        </el-form-item>
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
        name: "ChatInfoAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/chatInfo',
                dialogFormData: {
                    id: '',
                    openid: '',
                    trueName: '',
                    docId: '',
                    docName: '',
                    content: '',
                    createTime: '',
                    sendFrom: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    openid: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    trueName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    docId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    docName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    content: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    createTime: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    sendFrom: [
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
                    openid: '',
                    trueName: '',
                    docId: '',
                    docName: '',
                    content: '',
                    createTime: '',
                    sendFrom: '',
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
