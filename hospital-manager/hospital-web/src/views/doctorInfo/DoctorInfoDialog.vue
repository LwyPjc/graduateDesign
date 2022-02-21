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
        <el-form-item label="医生姓名" label-width="105px" prop="name">
            <el-input placeholder="请输入医生姓名" v-model="dialogFormData.name"/>
        </el-form-item>
      <el-form-item label="科室" label-width="105px" prop="openCourseId">
        <el-select v-model="dialogFormData.dptId" placeholder="请选择科室" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.departmentList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
            @click.native="convert($event,item)"
          >
          </el-option>
        </el-select>
      </el-form-item>
        <el-form-item label="简介" label-width="105px" prop="descs">
            <el-input placeholder="请输入简介" v-model="dialogFormData.descs"/>
        </el-form-item>
        <el-form-item label="手机号" label-width="105px" prop="phone">
            <el-input placeholder="请输入手机号" v-model="dialogFormData.phone"/>
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
        name: "DoctorInfoAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + 'doctorInfo',
                dialogFormData: {
                    id: '',
                    name: '',
                    dptId: '',
                    deptName:'',
                    descs: '',
                    titleId: '',
                    phone: '',
                    temp1: '',
                    departmentList: [],
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    name: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    dptId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    descs: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    phone: [
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
          convert(event,item){
            console.log("信息", item);
            this.dialogFormData.dptName = item.name;
          },
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
                    dptId: '',
                    descs: '',
                    phone: '',
                    temp1: '',
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
                  this.dialogFormData.departmentList = null;
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
