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
        <el-form-item label="科室名称" label-width="105px" prop="name">
            <el-input placeholder="请输入科室名称" v-model="dialogFormData.name"/>
        </el-form-item>
        <el-form-item label="科室简介" label-width="105px" prop="desc">
            <el-input placeholder="请输入科室简介" v-model="dialogFormData.desc"/>
        </el-form-item>
        <el-form-item label="父科室id" label-width="105px" prop="parentId">
            <el-input type="number" placeholder="请输入父科室id" v-model.number="dialogFormData.parentId"/>
        </el-form-item>
        <el-form-item label="假删除 1删除，0否" label-width="105px" prop="deleteFlg">
            <el-input placeholder="请输入假删除 1删除，0否" v-model="dialogFormData.deleteFlg"/>
        </el-form-item>
        <el-form-item label="创建时间" label-width="105px" prop="createTime">
            <el-date-picker
                v-model="dialogFormData.createTime"
                type="date"
                placeholder="选择创建时间">
            </el-date-picker>
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
        name: "DepartmentAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/department',
                dialogFormData: {
                    id: '',
                    name: '',
                    desc: '',
                    parentId: '',
                    deleteFlg: '',
                    createTime: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    name: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    desc: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    parentId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    deleteFlg: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    createTime: [
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
                    name: '',
                    desc: '',
                    parentId: '',
                    deleteFlg: '',
                    createTime: '',
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
