<template>
  <div style="width: 40%;text-align: center;justify-content: center;margin-left: 33%;">
    <el-form ref="form" :model="form" label-width="90px">
      <el-form-item label="旧密码">
        <el-input v-model="form.oldPassword"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="form.password"></el-input>
      </el-form-item>
      <el-form-item label="新密码确认">
        <el-input v-model="form.passwordConfirm"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
    import request from '@/utils/request'
    export default {
        data() {
            return {
                form: {
                    oldPassword: "",
                    password: "",
                    passwordConfirm: ""
                }
            }
        },
        methods: {
            onSubmit() {
                if (!this.form.oldPassword || !this.form.password || !this.form.passwordConfirm) {
                    this.$message({
                        type: 'info',
                        message: '请完整输入信息',
                        duration: 1500,
                    });
                    return false;
                }
                if (this.form.password !== this.form.passwordConfirm) {
                    this.$message({
                        type: 'info',
                        message: '两次输入的密码不一致',
                        duration: 1500,
                    });
                    return false;
                }
                request({
                    url: `${this.GLOBAL.baseUrl}sysUser/editPassword`,
                    method: 'post',
                    params: {stuCode:window.sessionStorage.username,password:this.form.password,oldPassword:this.form.oldPassword}
                }).then(res => {
                  let status = res.status;
                  if(status === '500'){
                    this.$message({
                      message: res.msg,
                      type: 'error',
                      duration: 1500,
                      onClose: () => {
                      }
                    });
                    return;
                  }
                    this.$message({
                        message: '操作成功',
                        type: 'success',
                        duration: 1500,
                        onClose: () => {
                            this.$emit('refreshDataList')
                        }
                    })
                    this.$router.push({path: '/login'});

                })
            }
        }
    }
</script>
