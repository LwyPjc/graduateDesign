<template>
  <div class="login-container">
    <el-form :model="ruleForm2" :rules="rules2"
             status-icon
             ref="ruleForm2"
             label-position="left"
             label-width="0px"
             class="demo-ruleForm login-page">
      <h3 class="title" style="text-align: center;">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm2.username"
                  auto-complete="off"
                  placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm2.password"
                  auto-complete="off"
                  placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        data() {
            return {
                logining: false,
                ruleForm2: {
                    username: '',
                    password: '',
                },
                rules2: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}]
                },
                checked: false,
                prefixUrl: this.GLOBAL.baseUrl + '/login'
            }
        },
        methods: {
            handleSubmit(event) {
                this.$refs.ruleForm2.validate((valid) => {
                    if (!valid) {
                        return false;
                    }
                    this.logining = true;
                    request({
                        url: `${this.prefixUrl}/loginCheck`,
                        method: 'post',
                        params: this.ruleForm2
                    }).then(res => {
                        if (res.status === "200") {
                            sessionStorage.setItem('username', res.msg.username);
                            sessionStorage.setItem('role', res.msg.role);
                            // sessionStorage.setItem('name', res.msg.name);
                            // 管理员
                            if (res.msg.role === "2") {
                                this.$router.push({path: '/student'});
                            } else {
                              // 教师
                              this.$router.push({path: '/studentEvaluate'});
                            }
                        } else {
                            this.$message({message: res.msg, type: 'error', duration: 1500});
                            this.logining = false;
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
                })
            }
        }
    };
</script>

<style scoped>
  .login-container {
    width: 100%;
    height: 100%;
  }

  .login-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  label.el-checkbox.rememberme {
    margin: 0px 0px 15px;
    text-align: left;
  }
</style>
