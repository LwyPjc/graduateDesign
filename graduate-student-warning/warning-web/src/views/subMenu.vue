<template functional>
  <el-submenu :index="props.data.id.toString()">
    <template slot="title">
      <i :class="props.data.icon"></i>
      <span>{{props.data.name}}</span>
    </template>
    <template v-for="item in props.data.children">
      <router-link :to="item.url" :key="item.id" v-if="item.children.length===0">
        <el-menu-item class="subitem" :index="item.id.toString()">
          <i :class="item.icon"></i>
          <span slot="title">{{item.name}}</span>
        </el-menu-item>
      </router-link>
      <sub-menu v-else :data="item" :key="item.id"></sub-menu>
    </template>
  </el-submenu>
</template>

<script>
    export default {
        name: "submenu",
        props: {
            data: [Array, Object]
        }
    };
</script>

<style lang="scss" scoped>
  .el-submenu {
    .el-menu-item {
      padding: 0;
    }
  }
</style>
