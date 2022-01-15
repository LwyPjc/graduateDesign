<template>
  <div style="width: 50%;text-align: -webkit-right;padding-left: 10%">
    <el-form ref="form" :model="dialogFormData" label-width="120px" :inline="true">
      <el-form-item label="学号" label-width="105px" prop="stuCode">
        <el-input placeholder="请输入学号" v-model="dialogFormData.stuCode" v-bind:disabled="true"/>
      </el-form-item>
      <el-form-item label="姓名" label-width="105px" prop="stuName">
        <el-input placeholder="请输入姓名" v-model="dialogFormData.stuName" v-bind:disabled="true"/>
      </el-form-item>
      <el-form-item label="曾用名" label-width="105px" prop="stuNameOld">
        <el-input placeholder="请输入曾用名" v-model="dialogFormData.stuNameOld"/>
      </el-form-item>
      <el-form-item label="姓名拼音" label-width="105px" prop="stuNamePy">
        <el-input placeholder="请输入姓名拼音" v-model="dialogFormData.stuNamePy"/>
      </el-form-item>
      <el-form-item label="性别" label-width="105px" prop="sex">
        <el-select v-model="dialogFormData.sex" placeholder="请选择性别" style="width: 95%" disabled="disabled">
          <el-option
            v-for="item in sexSelect"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="婚姻" label-width="90px" prop="marriage">
        <el-select v-model="dialogFormData.marriage" placeholder="请选择婚姻" style="width: 95%" disabled="disabled">
          <el-option
            v-for="item in marraySelect"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="证件号码" label-width="105px" prop="certificateNo">
        <el-input placeholder="请输入证件号码" v-model="dialogFormData.certificateNo"/>
      </el-form-item>
      <el-form-item label="出生日期" label-width="87px" prop="birthday">
        <el-date-picker
          v-model="dialogFormData.birthday"
          type="date"
          placeholder="选择出生日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="籍贯" label-width="105px" prop="hometown">
        <el-input placeholder="请输入籍贯" v-model="dialogFormData.hometown"/>
      </el-form-item>
      <el-form-item label="民族" label-width="105px" prop="nation">
        <el-input placeholder="请输入民族" v-model="dialogFormData.nation"/>
      </el-form-item>
      <!--       <el-form-item label="婚姻" label-width="105px" prop="marriage">
                 <el-input type="number" placeholder="请输入婚姻" v-model.number="dialogFormData.marriage"/>
             </el-form-item>-->
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
      <el-form-item label="特长" label-width="105px" prop="skill">
        <el-input placeholder="请输入特长" v-model="dialogFormData.skill"/>
      </el-form-item>
      <el-form-item label="既往病史" label-width="105px" prop="sicknessHistory">
        <el-input placeholder="请输入既往病史" v-model="dialogFormData.sicknessHistory"/>
      </el-form-item>
      <el-form-item label="班级" label-width="105px" prop="classId"
                    style="display: block;text-align: left;padding-left: 21%;">
        <el-select v-model="dialogFormData.classId" placeholder="请选择班级" style="width: 220%" disabled="disabled">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
    </el-form>

    <div slot="footer" class="dialog-footer" style="text-align: center;padding-left: 20%;">
      <el-button
        type="primary"
        size="small"
        @click.native="dialogSubmit"
      >
        确认
      </el-button>
    </div>
  </div>

</template>
<script>
    import request from '@/utils/request'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
        components: {
            Pagination
        },
        data() {
            return {
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
                    counselorId: '',
                    rewards: '',
                },
                options: [],
                sexSelect: [
                    {value: 0, label: "女"},
                    {value: 1, label: "男"}
                ],
                marraySelect: [
                    {value: 0, label: "否"},
                    {value: 1, label: "是"}
                ],
                statusSelect: ["在学", "毕业"],
                prefixUrl: this.GLOBAL.baseUrl + '/student'
            }
        },
        created() {
            this.fetchData();
            this.getClasses();
        },
        methods: {
            /**
             * 获取个人信息数据
             */
            fetchData() {
                request({
                    url: `${this.prefixUrl}/findList`,
                    method: 'get',
                    params: {stuCode: window.sessionStorage.username}
                }).then(res => {
                    this.dialogFormData = res[0];
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error',
                        duration: 1500,
                        onClose: () => {
                        }
                    })
                })
            },
            /**
             * 请求所有班级
             */
            getClasses() {
                request({
                    url: `${this.GLOBAL.baseUrl}aClass/findList`,
                    method: 'get'
                }).then(res => {
                    for (var i = 0; i < res.length; i++) {
                        var obj = {
                            value: res[i].id, label: res[i].grade + "-" + res[i].college + "-"
                                + res[i].subject + "-" + res[i].classNo + "（辅导员：" + res[i].counselorName + "）"
                        };
                        this.options.push(obj);
                    }
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error',
                        duration: 1500,
                        onClose: () => {
                        }
                    })
                })
            },
            dialogSubmit() {
                var that = this;
                this.$delete(that.dialogFormData, "options");
                request({
                    url: `${this.prefixUrl}/edit`,
                    method: 'post',
                    params: this.dialogFormData
                }).then(res => {
                    if (res.status === "200") {
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                            duration: 1500,
                            onClose: () => {
                                this.dialogVisible = false
                                this.$emit('refreshDataList')
                            }
                        })
                    } else {
                        this.$message({
                            message: res.msg,
                            type: 'error',
                            duration: 1500,
                            onClose: () => {
                            }
                        })
                    }

                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error',
                        duration: 1500,
                        onClose: () => {
                        }
                    })
                })
            }
        }
    }
</script>
