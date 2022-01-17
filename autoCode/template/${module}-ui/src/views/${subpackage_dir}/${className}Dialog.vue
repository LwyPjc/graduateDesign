<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign table_chn = table.remarks>
<#if table.primaryKeyColumns??>
<#assign pkColumn=table.pkColumn>
<#assign pkColumnName=table.pkColumn.columnName>
<#assign pkColumnNameLower=table.pkColumn.columnNameLower>
</#if>
<template>
<!-- 编辑弹窗 -->
  <el-dialog
    :title="dialogFormData.${pkColumnNameLower} == ''? '新增': '修改'"
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
      <#list table.columns as column>
      <#assign show_index=1>
      <#if !column.pk>
        <el-form-item label="${column.remarks!}" label-width="105px" prop="${column.columnNameLower}">
        <#if column.columnNameLower != "validFlag">
            <#if column.javaType == 'Date'>
            <el-date-picker
                v-model="dialogFormData.${column.columnNameLower}"
                type="date"
                placeholder="选择${column.remarks!}">
            </el-date-picker>
            <#elseif column.javaType == "Integer" || column.javaType == "Double" || column.javaType == "Float">
            <el-input type="number" placeholder="请输入${column.remarks!}" v-model.number="dialogFormData.${column.columnNameLower}"/>
            <#else>
            <el-input placeholder="请输入${column.remarks!}" v-model="dialogFormData.${column.columnNameLower}"/>
            </#if>
        <#else>
            <el-select
                v-model="dialogFormData.${column.columnNameLower}"
                placeholder="有效标志"
                clearable
                size="small"
            >
                <el-option label="有效" value="1"></el-option>
                <el-option label="无效" value="0"></el-option>
            </el-select>
        </#if>
        </el-form-item>
      </#if>
      </#list>
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
        name: "${className}AddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '<#if subpackage != "">/${subpackage}</#if>/${classNameLower}',
                dialogFormData: {
                    <#list table.columns as column>
                    ${column.columnNameLower}: '',
                    </#list>
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    <#list table.columns as column>
                    <#if !column.pk>
                    ${column.columnNameLower}: ${"["}
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ${"]"},
                    </#if>
                    </#list>
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
                    <#list table.columns as column>
                    <#if column.columnNameLower != "validFlag">
                    ${column.columnNameLower}: '',
                    </#if>
                    </#list>
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
                  const apiName = `${'$'}{!this.dialogFormData.${pkColumnNameLower} ? 'save' : 'edit'}`
                  request({
                    url: `${'$'}{this.prefixUrl}/${'$'}{apiName}`,
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
