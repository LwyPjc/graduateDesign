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
        <el-form-item label="学号" label-width="105px" prop="stuCode">
            <el-input placeholder="请输入学号" v-model="dialogFormData.stuCode"/>
        </el-form-item>
        <el-form-item label="姓名" label-width="105px" prop="stuName">
            <el-input placeholder="请输入姓名" v-model="dialogFormData.stuName"/>
        </el-form-item>
        <el-form-item label="曾用名" label-width="105px" prop="stuNameOld">
            <el-input placeholder="请输入曾用名" v-model="dialogFormData.stuNameOld"/>
        </el-form-item>
        <el-form-item label="姓名拼音" label-width="105px" prop="stuNamePy">
            <el-input placeholder="请输入姓名拼音" v-model="dialogFormData.stuNamePy"/>
        </el-form-item>
        <el-form-item label="性别" label-width="105px" prop="sex">
            <el-input type="number" placeholder="请输入性别" v-model.number="dialogFormData.sex"/>
        </el-form-item>
        <el-form-item label="证件类型" label-width="105px" prop="certificatType">
            <el-input placeholder="请输入证件类型" v-model="dialogFormData.certificatType"/>
        </el-form-item>
        <el-form-item label="证件号码" label-width="105px" prop="certificateNo">
            <el-input placeholder="请输入证件号码" v-model="dialogFormData.certificateNo"/>
        </el-form-item>
        <el-form-item label="国籍" label-width="105px" prop="nationality">
            <el-input placeholder="请输入国籍" v-model="dialogFormData.nationality"/>
        </el-form-item>
        <el-form-item label="籍贯" label-width="105px" prop="hometown">
            <el-input placeholder="请输入籍贯" v-model="dialogFormData.hometown"/>
        </el-form-item>
        <el-form-item label="民族" label-width="105px" prop="nation">
            <el-input placeholder="请输入民族" v-model="dialogFormData.nation"/>
        </el-form-item>
        <el-form-item label="婚姻" label-width="105px" prop="marriage">
            <el-input type="number" placeholder="请输入婚姻" v-model.number="dialogFormData.marriage"/>
        </el-form-item>
        <el-form-item label="生源地" label-width="105px" prop="birthplace">
            <el-input placeholder="请输入生源地" v-model="dialogFormData.birthplace"/>
        </el-form-item>
        <el-form-item label="出生日期" label-width="105px" prop="birthday">
            <el-date-picker
                v-model="dialogFormData.birthday"
                type="date"
                placeholder="选择出生日期">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="政治面貌" label-width="105px" prop="politicalStatus">
            <el-input placeholder="请输入政治面貌" v-model="dialogFormData.politicalStatus"/>
        </el-form-item>
        <el-form-item label="手机号" label-width="105px" prop="phone">
            <el-input placeholder="请输入手机号" v-model="dialogFormData.phone"/>
        </el-form-item>
        <el-form-item label="Email" label-width="105px" prop="email">
            <el-input placeholder="请输入Email" v-model="dialogFormData.email"/>
        </el-form-item>
        <el-form-item label="QQ" label-width="105px" prop="qq">
            <el-input placeholder="请输入QQ" v-model="dialogFormData.qq"/>
        </el-form-item>
        <el-form-item label="微信" label-width="105px" prop="weChat">
            <el-input placeholder="请输入微信" v-model="dialogFormData.weChat"/>
        </el-form-item>
        <el-form-item label="家庭电话" label-width="105px" prop="homePhone">
            <el-input placeholder="请输入家庭电话" v-model="dialogFormData.homePhone"/>
        </el-form-item>
        <el-form-item label="家庭地址" label-width="105px" prop="homeAddress">
            <el-input placeholder="请输入家庭地址" v-model="dialogFormData.homeAddress"/>
        </el-form-item>
        <el-form-item label="邮政编码" label-width="105px" prop="postCode">
            <el-input placeholder="请输入邮政编码" v-model="dialogFormData.postCode"/>
        </el-form-item>
        <el-form-item label="特长" label-width="105px" prop="skill">
            <el-input placeholder="请输入特长" v-model="dialogFormData.skill"/>
        </el-form-item>
        <el-form-item label="既往病史" label-width="105px" prop="sicknessHistory">
            <el-input placeholder="请输入既往病史" v-model="dialogFormData.sicknessHistory"/>
        </el-form-item>
        <el-form-item label="班级id" label-width="105px" prop="classId">
            <el-input type="number" placeholder="请输入班级id" v-model.number="dialogFormData.classId"/>
        </el-form-item>
        <el-form-item label="考生号" label-width="105px" prop="candidateNo">
            <el-input placeholder="请输入考生号" v-model="dialogFormData.candidateNo"/>
        </el-form-item>
        <el-form-item label="入学时间" label-width="105px" prop="admissionTime">
            <el-date-picker
                v-model="dialogFormData.admissionTime"
                type="date"
                placeholder="选择入学时间">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="入学方式" label-width="105px" prop="admissionMethord">
            <el-input placeholder="请输入入学方式" v-model="dialogFormData.admissionMethord"/>
        </el-form-item>
        <el-form-item label="学籍状态" label-width="105px" prop="stuStatus">
            <el-input placeholder="请输入学籍状态" v-model="dialogFormData.stuStatus"/>
        </el-form-item>
        <el-form-item label="培养层次" label-width="105px" prop="trainingLevel">
            <el-input placeholder="请输入培养层次" v-model="dialogFormData.trainingLevel"/>
        </el-form-item>
        <el-form-item label="学制" label-width="105px" prop="educationalSystem">
            <el-input placeholder="请输入学制" v-model="dialogFormData.educationalSystem"/>
        </el-form-item>
        <el-form-item label="辅导员" label-width="105px" prop="counselor">
            <el-input placeholder="请输入辅导员" v-model="dialogFormData.counselor"/>
        </el-form-item>
        <el-form-item label="奖惩情况" label-width="105px" prop="rewards">
            <el-input placeholder="请输入奖惩情况" v-model="dialogFormData.rewards"/>
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
        name: "EduStudentAddOrUpdate",
        data () {
            return {
                visible: false,
                prefixUrl: '/eduStudent',
                dialogFormData: {
                    id: '',
                    stuCode: '',
                    stuName: '',
                    stuNameOld: '',
                    stuNamePy: '',
                    sex: '',
                    certificatType: '',
                    certificateNo: '',
                    nationality: '',
                    hometown: '',
                    nation: '',
                    marriage: '',
                    birthplace: '',
                    birthday: '',
                    politicalStatus: '',
                    phone: '',
                    email: '',
                    qq: '',
                    weChat: '',
                    homePhone: '',
                    homeAddress: '',
                    postCode: '',
                    skill: '',
                    sicknessHistory: '',
                    classId: '',
                    candidateNo: '',
                    admissionTime: '',
                    admissionMethord: '',
                    stuStatus: '',
                    trainingLevel: '',
                    educationalSystem: '',
                    counselor: '',
                    rewards: '',
                },
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    stuCode: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    stuName: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    stuNameOld: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    stuNamePy: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    sex: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    certificatType: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    certificateNo: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nationality: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    hometown: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    nation: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    marriage: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    birthplace: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    birthday: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    politicalStatus: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    qq: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    weChat: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    homePhone: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    homeAddress: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    postCode: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    skill: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    sicknessHistory: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    classId: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    candidateNo: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    admissionTime: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    admissionMethord: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    stuStatus: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    trainingLevel: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    educationalSystem: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    counselor: [
                        { required: true, message: '参数不能为空', trigger: 'blur' }
                    ],
                    rewards: [
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
                    stuCode: '',
                    stuName: '',
                    stuNameOld: '',
                    stuNamePy: '',
                    sex: '',
                    certificatType: '',
                    certificateNo: '',
                    nationality: '',
                    hometown: '',
                    nation: '',
                    marriage: '',
                    birthplace: '',
                    birthday: '',
                    politicalStatus: '',
                    phone: '',
                    email: '',
                    qq: '',
                    weChat: '',
                    homePhone: '',
                    homeAddress: '',
                    postCode: '',
                    skill: '',
                    sicknessHistory: '',
                    classId: '',
                    candidateNo: '',
                    admissionTime: '',
                    admissionMethord: '',
                    stuStatus: '',
                    trainingLevel: '',
                    educationalSystem: '',
                    counselor: '',
                    rewards: '',
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
