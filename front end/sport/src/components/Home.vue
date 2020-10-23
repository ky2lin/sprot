<template>
  <!-- 引入container布局开始 -->
  <el-container class="home-container">
    <!-- 头部开始 -->
    <el-header>
      <!-- logo -->
      <div>
        <img class="img-logo" src="../assets/Naruto.png" alt="Naruto" />
      </div>
      <span>个人运动平台</span>
      <el-button type="info" @click="logout()">安全退出</el-button>
    </el-header>
    <!-- 头部结束 -->

    <!-- 容器主体部分开始 -->
    <el-container>
      <!-- 容器侧边栏开始 -->
      <el-aside width="200px">

        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff" :router="true" :default-active="activePath">
          <!-- 一级菜单开始 -->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <!-- 遍历menuList -->
            <template slot="title">
              <i :class="iconsObject[item.id]"></i>
              <span> {{item.title}}</span> <!-- 从循环中取出标题 -->
            </template>

            <!-- 二级菜单开始 -->
            <el-menu-item :index="it.path" v-for="it in item.sList" :key="it.id" @click="saveNavState(it.path)">
              <!-- 从menuList的sList中取出子标题 -->
              <template slot="title">
                <i :class="iconsObject[it.id]"></i>
                <span> {{it.title}}</span>
              </template>
            </el-menu-item>
            <!-- 二级菜单结束 -->
          </el-submenu>
          <!-- 一级菜单结束 -->
        </el-menu>
      </el-aside>
      <!-- 容器侧边栏结束 -->

      <!-- 主体内容开始 -->
      <el-main>
        <router-view></router-view>
      </el-main>
      <!-- 主体内容结束 -->

    </el-container>
    <!-- 容器主体部分结束 -->

  </el-container>
  <!-- 引入container布局结束 -->
</template>
<script>
export default {
  data() {
    return {
      //菜单列表
      menuList: [],
      //导航栏图标
      iconsObject: {
        '100': 'iconfont icon-guanli',
        '200': 'iconfont icon-yundong1',
        '101': 'iconfont icon-denglu',
        '102': 'iconfont icon-mima',
        '103': 'iconfont icon-yundong1', /*  */
        '104': 'iconfont icon-shangpin',
        '201': 'iconfont icon-shu',
        '202': 'iconfont icon-kaluli',
        '203': 'iconfont icon-shiwu',
        '204': 'iconfont icon-denglu',
      },
      //默认活跃路径
      activePath: '/welcome',
    }
  },
  //创建时执行
  created() {
    //查询menuList
    this.getMenuList();
    //取出存放的路径状态,动态修改activePath
    this.activePath = window.sessionStorage.getItem('activePath');

  },
  methods: {
    //安全退出方法
    logout() {
      window.sessionStorage.clear();//清除储存的user对象
      this.$router.push("/login"); //退出之后返回到登录页
    },
    //获取导航菜单menuList的方法
    async getMenuList() {
      const { data: res } = await this.$http.get("menus"); //请求后端的地址
      console.log(res);
      if (res.flag != 200) { //判断是否请求成功
        return this.$message.error("获取列表失败!");
      }
      this.menuList = res.menus; //赋值给menuList
    },
    //保存路径事件的方法
    saveNavState(activePath) {
      window.sessionStorage.setItem('activePath', activePath); //储存路径状态在session中，刷新了就不会掉
      this.activePath = activePath;
    },
  }

}
</script>

<style lang="less" scoped>
@import '../assets/css/home/home.css';
</style>