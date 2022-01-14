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
        <el-form-item label="用户ID" label-width="105px" prop="userId">
            <el-input type="number" placeholder="请输入用户ID" v-model.number="dialogFormData.userId"/>
        </el-form-item>
        <el-form-item label="物品ID" label-width="105px" prop="goodsId">
            <el-input type="number" placeholder="请输入物品ID" v-model.number="dialogFormData.goodsId"/>
        </el-form-item>
        <el-form-item label="收藏时间" label-width="105px" prop="collectTime">
            <el-input placeholder="请输入收藏时间" v-model="dialogFormData.collectTime"/>
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
        name: "ZtCollectAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/ztCollect',
                dialogFormData: {
                    id: '',
                    userId: '',
                    goodsId: '',
                    collectTime: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    userId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    goodsId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    collectTime: [
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
                    userId: '',
                    goodsId: '',
                    collectTime: '',
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
