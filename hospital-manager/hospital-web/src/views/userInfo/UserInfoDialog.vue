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
        <el-form-item label="头像地址" label-width="105px" prop="avatarUrl">
            <el-input placeholder="请输入头像地址" v-model="dialogFormData.avatarUrl"/>
        </el-form-item>
        <el-form-item label="微信名称" label-width="105px" prop="nickName">
            <el-input placeholder="请输入微信名称" v-model="dialogFormData.nickName"/>
        </el-form-item>
        <el-form-item label="电话号码" label-width="105px" prop="phoneNum">
            <el-input placeholder="请输入电话号码" v-model="dialogFormData.phoneNum"/>
        </el-form-item>
        <el-form-item label="医保卡" label-width="105px" prop="medicareCard">
            <el-input placeholder="请输入医保卡" v-model="dialogFormData.medicareCard"/>
        </el-form-item>
        <el-form-item label="性别1男 2女 0 未知" label-width="105px" prop="gender">
            <el-input placeholder="请输入性别1男 2女 0 未知" v-model="dialogFormData.gender"/>
        </el-form-item>
        <el-form-item label="身份证号码" label-width="105px" prop="idCard">
            <el-input placeholder="请输入身份证号码" v-model="dialogFormData.idCard"/>
        </el-form-item>
        <el-form-item label="年龄" label-width="105px" prop="age">
            <el-input type="number" placeholder="请输入年龄" v-model.number="dialogFormData.age"/>
        </el-form-item>
        <el-form-item label="详细地址" label-width="105px" prop="address">
            <el-input placeholder="请输入详细地址" v-model="dialogFormData.address"/>
        </el-form-item>
        <el-form-item label="" label-width="105px" prop="temp2">
            <el-input placeholder="请输入" v-model="dialogFormData.temp2"/>
        </el-form-item>
        <el-form-item label="真实年龄" label-width="105px" prop="trueName">
            <el-input placeholder="请输入真实年龄" v-model="dialogFormData.trueName"/>
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
        name: "UserInfoAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + 'userInfo',
                dialogFormData: {
                    avatarUrl: '',
                    nickName: '',
                    id: '',
                    phoneNum: '',
                    medicareCard: '',
                    gender: '',
                    idCard: '',
                    age: '',
                    address: '',
                    temp2: '',
                    trueName: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    avatarUrl: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nickName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    phoneNum: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    medicareCard: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    gender: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    idCard: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    age: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    address: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    temp2: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    trueName: [
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
                    avatarUrl: '',
                    nickName: '',
                    id: '',
                    phoneNum: '',
                    medicareCard: '',
                    gender: '',
                    idCard: '',
                    age: '',
                    address: '',
                    temp2: '',
                    trueName: '',
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
