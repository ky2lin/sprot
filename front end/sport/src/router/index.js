import Vue from 'vue'
import VueRouter from 'vue-router'
//引入login组件
import Login from '../components/Login.vue'
//引入Home组件
import Home from '../components/Home.vue'
//引入Welcome组件
import Welcome from '../components/Welcome.vue'
//引入UserList组件
import UserList from '../components/admin/UserList.vue'

Vue.use(VueRouter)

//路由配置
const routes = [
  {
    path: '/',
    redirect: '/login',
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/user', component: UserList },
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

//出现Uncaught (in promise) undefined的解决办法
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject)
    return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch((err) => err)
}

//挂载路由导航守卫
//beforeEach在路由跳转之前
router.beforeEach((to, from, next) => {
  //从from到to
  //to 将要访问
  //from从哪访问
  //next接着做的事情：没参数，继续访问to的路径  next(url):有参数就重定向到url上

  if (to.path == '/login') {
    //如果访问的是首页，放行
    return next()
  }
  //获取user
  const userFlag = window.sessionStorage.getItem('user') //取出当前用户
  if (!userFlag) {
    //如果userFlag为空，返回登录页
    return next('/login')
  }

  //符合上述条件，放行
  next()
})

export default router
