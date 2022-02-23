<template>
  <el-container>
    <!-- 头部区域 -->
    <el-header>
      <el-col :span="16" class="headerlogo">
        <div class="grid-content bg-purple" style="padding: 8px">
          <p style="font-size: 22px ;color: white">
            在线咨询医院系统后台
          </p>
        </div>
      </el-col>
      <el-col :span="8" class="rightsection">
        <div class="grid-content bg-purple-light">
          <span class="el-dropdown-link userinfo-inner">欢迎您，{{username}}</span>&nbsp;&nbsp;&nbsp;
          <span class="userinfo-inner" style="cursor:pointer;" @click="signout">退出</span>
        </div>
      </el-col>
    </el-header>
    <el-container>
      <!-- 侧边栏区域 -->
      <el-aside style="width: 230px;">
        <el-menu
          :background-color="backgroundColor"
          :text-color="textColor"
          :default-active="$route.meta.pageId"
          :collapse="collapse"
        >
          <template v-for="item in list">
            <router-link :to="item.url" :key="item.id" v-if="item.children.length===0">
              <el-menu-item :index="item.id.toString()">
                <i :class="item.icon"></i>
                <span slot="title">{{item.name}}</span>
              </el-menu-item>
            </router-link>
            <subMenu v-else :data="item" :key="item.id"></subMenu>
          </template>
        </el-menu>
      </el-aside>
      <!-- 右侧主体区域 -->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
    import subMenu from "./subMenu";

    export default {
        name: "menuList",
        components: {
            subMenu
        },
        data() {
            return {
                username: window.sessionStorage.username,
                collapse: false, //是否折叠
                list: [

                ], //当行菜单数据源
                backgroundColor: "#304156", //导航菜单背景颜色
                textColor: "#BFCBD9" //导航菜单文字颜色
            };
        },
        created() {
            if (window.sessionStorage.role === '2') {
                this.list = [
                    {
                        name: "用户意见反馈",
                        id: 1,
                        icon: "el-icon-user-solid",
                        url: "/feedbackInfo",
                        children: []
                    },
                    {
                        name: "科室管理",
                        id: 2,
                        icon: "el-icon-s-custom",
                        url: "/department",
                        children: []
                    },
                    {
                        name: "医生管理",
                        id: 3,
                        icon: "el-icon-s-custom",
                        url: "/doctorInfo",
                        children: []
                    },
                    {
                      name: "患者管理",
                      id: 4,
                      icon: "el-icon-collection-tag",
                      url: "/userInfo",
                      children: []
                    },
                  {
                    name: "统计",
                    id: 5,
                    icon: "el-icon-collection-tag",
                    url: "/statistics",
                    children: []
                  },
                    {
                      name: "修改密码",
                      id: 5,
                      icon: "el-icon-collection-tag",
                      url: "/updatePwd",
                      children: []
                    },

                ]
            } else {
                this.list = [
                  {
                    name: "我的咨询",
                    id: 6,
                    icon: "el-icon-collection-tag",
                    url: "/patient",
                    children: []
                  },
                    {
                        name: "聊天",
                        id: 6,
                        icon: "el-icon-collection-tag",
                        url: "/chat",
                        children: []
                    },
                    {
                        name: "修改密码",
                        id: 5,
                        icon: "el-icon-collection-tag",
                        url: "/updatePwd",
                        children: []
                    }
                ]
            }

        },
        methods: {
            signout() {
                window.location.href = "/login";
            }
        }
    };
</script>

<style lang="scss" scoped>
  .el-header {
    background: #304159;
  }

  .el-aside {
    background: #304159;
    height: 893px;
  }

  .el-menu {
    border-right: none;

    a {
      text-decoration: none;
    }
  }

  .rightsection {
    line-height: 60px;
    text-align: right;
  }

  .userinfo-inner {
    color: beige;
    font-weight: bold;
  }

</style>
