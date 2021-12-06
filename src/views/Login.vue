<template>
  <div class="login-container">
    <div class="login-left">
      <img src="../assets/img/background.jpg" alt="The logo" style="width:500px; height: 450px; border-radius: 20px;">
    </div>
    <div class="login-box">
      <!-- 头像 -->
      <div class="avatar-box">
        <img src="../assets/img/login_logo.png" alt="">
      </div>
      <!-- 标题 -->
      <div class="title"> Log in</div>
      <!-- 登陆表单 -->
      <el-form ref="loginFormRef" class="login-form" :model="loginForm" :rules="loginFormRules" label-position="top">
        <el-form-item prop="username" label="Username">
          <el-input placeholder="Please input your username" prefix-icon="el-icon-user" v-model="loginForm.username"/>
        </el-form-item>
        <el-form-item prop="password" label="Password">
          <el-input placeholder="Please input your password" show-password prefix-icon="iconfont icon-3702mima"
                    v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item prop="verifyCode">
          <div class="verifyCode_box">
            <el-input v-model="loginForm.verifyCode" type="text" placeholder="please input the code"
                      prefix-icon="el-icon-mobile"
                      class="verifyCode"></el-input>
            <img src="../assets/img/code.gif" alt="" class="verifyCode_img" @click="newVerifyCode">
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" class="login-btn" @click="Login">login</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 表单的数据绑定对象
      loginForm:
        {
          username: '',
          password: ''
        },
      // 表单的校验规则
      loginFormRules: {
        username:
          [
            {required: true, message: '请输入登陆账户', trigger: 'blur'},
            {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
          ],
        password:
          [
            {required: true, message: '请输入登陆密码', trigger: 'blur'},
            {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
          ]
      }
    }
  },
  methods:
    {
      login() {
        // 判断是否校验成功
        this.$refs.loginFormRef.validate(async valid => {
          if (!valid) {
            return this.$message.error("You have an error try it again")
          }
          // 发送请求判断是否登陆成功
          const {data: res} = await this.$api.post('Login.vue', this.loginForm)

          if (res.meta.status !== 200) {
            return this.$message.error('登陆失败')
          }

          this.$message.success('登陆成功')
          // 将用户信息保存到sessionStorage中
          sessionStorage.setItem('user_session', JSON.stringify(res.data))
          await this.$router.push('/home')
        })
      }
    }
}
</script>

<style scoped>
.login-container {
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align-all: center;
  margin-top: 50px;
}

.login-left {
  margin-right: 100px;
}

.photo {
  border-radius: 10px;
}

.login-box {
  width: 500px;
  height: 400px;
  background: #fff;
  padding: 50px;
  border-radius: 6px;
  box-sizing: border-box;
  position: relative;
}

.avatar-box {
  width: 120px;
  height: 120px;
  border: 5px solid #93defe;
  border-radius: 100px;
  text-align: center;
  line-height: 110px;
  position: absolute;
  top: -60px;
  right: 190px;
  background: #fff;
}

.title {
  position: absolute;
  top: 100px;
  left: 50%;
  transform: translate(-50%);
  font-size: 18px;
  color: #444;
}

.login-form {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translate(-50%);
  padding: 0 20px;
  width: 80%;
  box-sizing: border-box;
}

.login-btn {
  width: 100%;
}

.verifyCode_box {
  display: flex;


}

.verifyCode {
  width: 70%;
  justify-content: left;
}

.verifyCode_img {
  padding-left: 5px;
  padding-top: 2px;
  width: 25%;
  height: 35px;
  justify-content: flex-end;
  cursor: pointer;
}

/*img {*/
/*  width: 100%;*/
/*  height: 100%;*/
/*  border-radius: 50%;*/
/*  background-color: #EEEEEE;*/
/*}*/

/*img[src=""], img:not([src]) {*/
/*  opacity: 0;*/
/*  border: none;*/
/*  visibility: hidden;*/
/*  max-width: none;*/
/*}*/
</style>
