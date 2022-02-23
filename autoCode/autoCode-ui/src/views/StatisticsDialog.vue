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
        <el-form-item label="医生姓名" label-width="105px" prop="doctorName">
            <el-input placeholder="请输入医生姓名" v-model="dialogFormData.doctorName"/>
        </el-form-item>
        <el-form-item label="患者姓名" label-width="105px" prop="patientName">
            <el-input placeholder="请输入患者姓名" v-model="dialogFormData.patientName"/>
        </el-form-item>
        <el-form-item label="医生id" label-width="105px" prop="doctorId">
            <el-input type="number" placeholder="请输入医生id" v-model.number="dialogFormData.doctorId"/>
        </el-form-item>
        <el-form-item label="患者ids" label-width="105px" prop="patientIds">
            <el-input placeholder="请输入患者ids" v-model="dialogFormData.patientIds"/>
        </el-form-item>
        <el-form-item label="次数" label-width="105px" prop="cnt">
            <el-input type="number" placeholder="请输入次数" v-model.number="dialogFormData.cnt"/>
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
        name: "StatisticsAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/statistics',
                dialogFormData: {
                    id: '',
                    doctorName: '',
                    patientName: '',
                    doctorId: '',
                    patientIds: '',
                    cnt: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    doctorName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    patientName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    doctorId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    patientIds: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    cnt: [
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
                    doctorName: '',
                    patientName: '',
                    doctorId: '',
                    patientIds: '',
                    cnt: '',
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
