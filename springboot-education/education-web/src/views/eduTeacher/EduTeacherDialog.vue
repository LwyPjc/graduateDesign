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
      <el-form-item label="工号" label-width="105px" prop="code">
        <el-input placeholder="请输入工号" v-model="dialogFormData.code"/>
      </el-form-item>
      <el-form-item label="姓名" label-width="105px" prop="name">
        <el-input placeholder="请输入姓名" v-model="dialogFormData.name"/>
      </el-form-item>
      <el-form-item label="关联班级" label-width="105px" prop="relateClassIds">
        <el-select v-model="relateClassIdList" multiple placeholder="请选择关联班级" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.classInfo"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
        name: "EduTeacherAddOrUpdate",
        data() {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/eduTeacher',
                dialogFormData: {
                    id: '',
                    name: '',
                    code: '',
                    relateClassIds: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    code: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],name: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    relateClassIds: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ]
                },
                relateClassIdList: []
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
                    this.relateClassIdList = row.relateClassIdList;
                }
                this.dialogVisible = true
            },
            resetModel() {
                this.dialogFormData = {
                    id: '',
                    name: '',
                    code: '',
                    relateClassIds: '',
                }

            },
            /**
             * 关闭对话框操作
             */
            handleClose(done) {
                this.dialogVisible = false
            },
            dialogSubmit() {
                if(this.relateClassIdList){
                    this.dialogFormData.relateClassIds = this.relateClassIdList.join(",");
                }
                this.$refs.dialogForm.validate((valid) => {
                    var that = this;
                    if (!valid) {
                        return false
                    }
                    const apiName = `${!this.dialogFormData.id ? 'save' : 'edit'}`
                    this.$delete(that.dialogFormData, "relateClassIdList");
                    this.$delete(that.dialogFormData, "relateClassName");
                    this.$delete(that.dialogFormData, "classInfo");
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
