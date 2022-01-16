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
        <el-form-item label="类型" style="width: 100%" prop="type">
          <el-select v-model="dialogFormData.type"  placeholder="请选择预警类型" style="width: 100%">
            <el-option
              v-for="item in warningLevelTypes"
              :key="item.value"
              :label="item.type"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      <el-form-item label="请选择预警等级" label-width="125px" style="width: 100%" prop="warningLevel">
        <el-select v-model="dialogFormData.warningLevel"  placeholder="请选择预警等级" style="width: 90%">
          <el-option
            v-for="item in warningLevels"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
        <el-form-item label="预警值" style="width: 100%" prop="value">
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
        name: "WarningRuleAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/warningRule',
                dialogFormData: {
                    id: '',
                    type: '',
                    warningLevel: '',
                    value: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    type: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    warningLevel: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    value: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                },
                warningLevels: ['黄色','绿色','红色','黑色','蓝色'],
                warningLevelTypes: [
                    {
                        type: '缺课(节)',
                        value: 1
                    },
                    {
                        type: '缺作业(次)',
                        value: 2
                    },
                    {
                        type: '成绩不合格(分)',
                        value: 3
                    },
                ]
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
