<template>
  <div>
    <!-- 面包屑导航开始 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <!-- <el-breadcrumb-item>权限管理</el-breadcrumb-item> -->
      <!-- <el-breadcrumb-item>用户列表</el-breadcrumb-item> -->
    </el-breadcrumb>
    <!-- 面包屑导航开始 -->

    <!-- 用户列表主体部分开始 -->
    <el-card>
      <el-row :gutter="25">
        <!-- 间隙 -->
        <!-- 搜索区域开始 -->
        <el-col :span="10">
          <!-- 列宽 -->
          <el-input placeholder="请输入搜索内容" v-model="queryInfo.username" clearable @clear="getUserList">
            <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <!-- 搜索按钮 -->
          <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
        </el-col>
        <!-- 搜索区域结束 -->
      </el-row>

      <!-- 用户列表表格开始  border:边框 ，stripe：隔行变色-->
      <el-table :data="userList" border stripe>
        <el-table-column type="index">
          <!-- 索引列 -->
        </el-table-column>
        <el-table-column label="用户名" prop="username">
          <!--  -->
        </el-table-column>
        <el-table-column label="邮箱" prop="email">
          <!--  -->
        </el-table-column>
        <el-table-column label="密码" prop="password">
          <!--  -->
        </el-table-column>
        <el-table-column label="角色" prop="role">
          <!--  -->
        </el-table-column>
        <el-table-column label="状态" prop="state">
          <!-- 作用域插槽 -->
          <template slot-scope="scope">
            <!-- {{scope.row}} 每一行封存的数据 -->
            <el-switch v-model="scope.row.state" @change="userStateChange(scope.row)"></el-switch>
          </template>

        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <!-- 修改 -->
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <!-- 删除   -->
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="deleteUser(scope.row.id)"></el-button>
            <!-- 权限 -->
            <el-tooltip effect="dark" content="分配权限" placement="top-start" :enterable="false">
              <!-- 文字提示 enterable隐藏-->
              <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      <!-- 用户列表表格结束 -->

      <!-- 分页组件开始 -->
      <!-- size-change:每页变化最大数 ， current-change：当前页面变化数 -->
      <div>
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="queryInfo.pageNum" :page-sizes="[1, 2, 5, 100]" :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
      <!-- 分页组件结束 -->
    </el-card>
    <!-- 用户列表主体部分结束 -->

    <!-- 添加用户区域开始 -->
    <el-dialog title="添加用户" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible=false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </span>

    </el-dialog>
    <!-- 添加用户区域结束 -->

    <!-- 修改用户开始 -->
    <el-dialog title="修改用户信息" :visible.sync="editDialogVisible" width="50%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" disabled></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
      </el-form>
      <!-- 内容底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible=false">取 消</el-button>
        <el-button type="primary" @click="editUserInfo">确 定</el-button>
      </span>

    </el-dialog>
    <!-- 修改用户结束 -->
  </div>
</template>

<script>

export default {
  created() {
    //创建用户列表菜单时调用
    this.getUserList();
  },
  data() {
    return {
      //向后端发送请求的查询信息实体
      queryInfo: {
        username: '',  //查询的用户名
        pageNum: 1,   //当前页
        pageSize: 5,  //每页的条数
      },
      userList: [], //用户列表
      total: 0, //初始总条数为0  
      addDialogVisible: false,//对话框显示隐藏状态
      //添加表单信息
      addForm: {
        username: '',
        password: '',
        email: '',
      },
      //修改用户的信息
      editForm: {

      },
      editDialogVisible: false, //显示和隐藏修改用户栏
      //添加用户表单验证
      addFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 5, max: 12, message: '长度在 5 ~ 12 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 8, message: '长度在 6 ~ 10 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { min: 5, max: 20, message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
      },
      //修改用户表单验证
      editFormRules: {
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 8, message: '长度在 6 ~ 10 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur' },
          { min: 5, max: 20, message: '请输入正确的邮箱地址', trigger: 'blur' }
        ],
      },


    }
  },
  methods: {
    //获取所有用户
    async getUserList() {
      //访问后端allUser地址
      const { data: res } = await this.$http.get("allUser", { params: this.queryInfo });
      // console.log(res);
      this.userList = res.users;  //用户列表数据
      this.total = res.counts; //总用户数
    },
    //获取分页最大数刷新数据的方法
    handleSizeChange(newSize) {
      this.queryInfo.pageSize = newSize; //把最新传入的查询条数赋值给pageSize
      this.getUserList(); //重新查询数据
    },
    // 上下页的触发事件
    handleCurrentChange(newPage) {
      this.queryInfo.pageNum = newPage; //把最新传入的当前页数赋值给pageNum
      this.getUserList(); //重新查询数据
    },
    //修改用户状态
    async userStateChange(userInfo) {
      const { data: res } = await this.$http.post(`userState?id=${userInfo.id}&state=${userInfo.state}`); //提交参数到后台
      if (res != "success") {
        userInfo.id = !userInfo.id;
        return this.$message.error("操作失败！");
      }
      this.$message.success("操作成功！");
    },
    //监听添加用户的事件，监听是否关闭
    addDialogClosed() {
      this.$refs.addFormRef.resetFields(); //关闭窗口重置信息
    },
    //添加用户的事件
    addUser() {
      this.$refs.addFormRef.validate(async valid => {
        //console.log(valid); //里面是true-false
        if (!valid) {
          return;
        }
        const { data: res } = await this.$http.post("addUser", this.addForm); //提交表单的信息到后台
        if (res != "success") {
          return this.$message.error("操作失败！");
        }
        this.$message.success("操作成功!");
        this.addDialogVisible = false;
        this.getUserList();
      });
    },
    //根据id删除用户
    async deleteUser(id) {
      const confirmResult = await this.$confirm('此操作将永久删除用户，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err)
      if (confirmResult != 'confirm') { //判断是否取消删除
        return this.$message.info("已取消删除");
      }
      const { data: res } = await this.$http.post("deleteUser?id=" + id);
      if (res != "success") {
        return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功!");
      this.getUserList();
    },
    //显示或者隐藏编辑输入框
    async showEditDialog(id) {
      const { data: res } = await this.$http.get("getUpdate?id=" + id);  //访问后台查询出用户
      this.editForm = res; //查询出的用户信息赋值给editForm
      this.editDialogVisible = true; //开启编辑输入框
    },
    //关闭编辑窗口事件
    editDialogClosed() {
      // console.log("哈哈");
      this.$refs.editFormRef.resetFields(); //关闭窗口重置信息
    },

    //确认修改
    editUserInfo() { //表单验证
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {//验证失败，返回
          return;
        }
        //验证成功发起请求
        const { data: res } = await this.$http.post("editUser", this.editForm); //提交表单的数据到后台
        if (res != "success") {
          return this.$message.error("操作失败!");
        }
        this.$message.success("操作成功!");
        //操作完成隐藏修改的输入框
        this.editDialogVisible = false;
        this.getUserList(); //重新查询数据

      })
    },


  },

}
</script>

<style lang="less" scoped>
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 17px;
}
</style>