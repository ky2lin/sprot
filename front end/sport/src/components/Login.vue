<template>
  <!-- login容器开始 -->
  <div class="login_container">
    <!-- 登录块开始 -->
    <div class="login_box">
      <!-- logon头像开始 -->
      <div class="avatar_box">
        <img src="../assets/logo.jpg" alt="logo" />
      </div>
      <!-- logon头像结束 -->

      <!-- 表单开始 -->
      <el-form ref="loginFormRef" :rules="LoginRules" :model="loginForm" class="login_form" label-width="0">
        <!-- 用户名开始 -->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icon-denglu"></el-input>
        </el-form-item>
        <!-- 用户名结束 -->

        <!-- 密码开始 -->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="iconfont icon-mima" type="password"></el-input>
        </el-form-item>
        <!-- 密码结束 -->

        <!-- 按钮开始 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login()">登录</el-button>
          <el-button type="info" @click="resetLoginForm()">重置</el-button>
        </el-form-item>
        <!-- 按钮结束 -->
      </el-form>
      <!-- 表单结束 -->
    </div>
    <!-- 登录块结束 -->
  </div>
  <!-- login容器结束 -->
</template>

<script>
export default {
  //数据
  data() {
    return {
      //表单数据对象
      loginForm: {
        username: 'admin',
        password: '123456'
      },
      // 表单数据对象校验
      LoginRules: {
        //校验用户名的规则
        username: [
          //required:是否必填  message:提示信息 trigger:触发条件,失去焦点
          //min:最小长度 max:最大长度
          { required: true, message: '用户名称为必填项', trigger: 'blur' },//必填项验证
          { min: 5, max: 12, message: '用户名长度在 5 ~ 12 个字符', trigger: 'blur' } //长度验证
        ],
        //校验密码的规则
        password: [
          { required: true, message: '用户密码为必填项', trigger: 'blur' },
          { min: 6, max: 10, message: '密码长度在 6 ~ 10 个字符', trigger: 'blur' }
        ],
      },
    }
  },
  //方法
  methods: {
    //重置表单按钮的事件方法
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields(); //resetFields:ui中的方法
    },
    //表单登录按钮的事件方法
    login() {
      //验证校验规则
      this.$refs.loginFormRef.validate(async valid => { //valid:ui中的方法
        if (valid) { //校验成功返回后端的数据
          const { data: res } = await this.$http.post("login", this.loginForm); //访问后台,传递参数
          if (res.flag == 'ok') {
            this.$message.success('操作成功!'); //成功提示
            window.sessionStorage.setItem("user", res.user); //存储user对象
            this.$router.push({ path: '/home' }); //页面路由跳转路径
            // console.log(res.user);

          } else {
            this.$message.error('操作失败!'); //失败提示
          }
        } else {//校验失败不返回数据
          return false;
        }

      })
    },
  },

}
</script>

<style lang="less" scoped>
@import '../assets/css/login/login.css';
</style>