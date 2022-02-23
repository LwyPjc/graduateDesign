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
        <el-form-item label="科室id" label-width="105px" prop="dptId">
            <el-input type="number" placeholder="请输入科室id" v-model.number="dialogFormData.dptId"/>
        </el-form-item>
        <el-form-item label="科室名称" label-width="105px" prop="dptName">
            <el-input placeholder="请输入科室名称" v-model="dialogFormData.dptName"/>
        </el-form-item>
        <el-form-item label="医生id" label-width="105px" prop="docId">
            <el-input type="number" placeholder="请输入医生id" v-model.number="dialogFormData.docId"/>
        </el-form-item>
        <el-form-item label="医生姓名" label-width="105px" prop="docName">
            <el-input placeholder="请输入医生姓名" v-model="dialogFormData.docName"/>
        </el-form-item>
        <el-form-item label="收藏时间" label-width="105px" prop="createTime">
            <el-date-picker
                v-model="dialogFormData.createTime"
                type="date"
                placeholder="选择收藏时间">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="假删除 0否 1删" label-width="105px" prop="deleteFlg">
            <el-input type="number" placeholder="请输入假删除 0否 1删" v-model.number="dialogFormData.deleteFlg"/>
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
        name: "CollectInfoAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/collectInfo',
                dialogFormData: {
                    id: '',
                    openid: '',
                    dptId: '',
                    dptName: '',
                    docId: '',
                    docName: '',
                    createTime: '',
                    deleteFlg: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    openid: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    dptId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    dptName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    docId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    docName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    createTime: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    deleteFlg: [
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
                    dptId: '',
                    dptName: '',
                    docId: '',
                    docName: '',
                    createTime: '',
                    deleteFlg: '',
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
