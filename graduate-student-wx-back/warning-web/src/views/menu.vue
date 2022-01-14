<template>
  <el-container>
    <!-- 头部区域 -->
    <el-header>
      <el-col :span="16" class="headerlogo">
        <div class="grid-content bg-purple" style="padding: 8px">
          <img
            src="../assets/title.png"
            alt="无法显示图片"
          />
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
                username:window.sessionStorage.username,
                collapse: false, //是否折叠
                list: [
                    {
                        name: "数据统计",
                        id: -1,
                        icon: "el-icon-pie-chart",
                        url: "/dataAnalysis",
                        children: []
                    },
                    {
                        name: "类别管理",
                        id: 20,
                        icon: "el-icon-collection-tag",
                        url: "/sysCategory",
                        children: []
                    },
                    {
                        id: 5,
                        parentid: 1,
                        name: "文物管理",
                        icon: "el-icon-folder-opened",
                        url: "/ztGoods",
                        children: []
                    },
                    {
                        id: 6,
                        parentid: 1,
                        name: "捐赠管理",
                        icon: "el-icon-s-check",
                        url: "/ztDonate",
                        children: []
                    },
                  /*  {
                        id: 7,
                        parentid: 1,
                        name: "收藏管理",
                        icon: "el-icon-star-on",
                        url: "/ztCollect",
                        children: []
                    },*/
                    {
                        id: 9,
                        parentid: 1,
                        name: "配置管理",
                        icon: "el-icon-s-tools",
                        url: "/sysSetting",
                        children: []
                    },
                    {
                        id: 8,
                        parentid: 1,
                        name: "建议反馈管理",
                        icon: "el-icon-s-comment",
                        url: "/ztAdvice",
                        children: []
                    }
                ], //当行菜单数据源
                backgroundColor: "#304156", //导航菜单背景颜色
                textColor: "#BFCBD9" //导航菜单文字颜色
            };
        },
        methods:{
            signout () {
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
  .userinfo-inner{
    color: beige;
    font-weight: bold;
  }

</style>
