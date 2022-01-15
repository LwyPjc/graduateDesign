<template>
  <div ref="print">

    <div style="margin:0 auto;">
      <div style="text-align:center;margin-top:10px;">个人简历</div>
      <table class="table table-striped table-bordered" align="center" valign="center">
        <tr>
          <td class="column" colspan="6">基本信息</td>

        </tr>
        <tr>
          <td class="column">姓名</td>
          <td  class="value"></td>
          <td class="column">性别</td>
          <td class="value"></td>
          <td class="column">出生年月</td>
          <td class="value"></td>
        </tr>
        <tr>
          <td class="column">民族</td>
          <td class="value">汉</td>
          <td class="column">婚否</td>
          <td class="value"></td>
          <td class="column">政治面貌</td>
          <td class="value"></td>
        </tr>
        <tr>
          <td class="column">籍贯</td>
          <td class="value"></td>
          <td class="column">学历</td>
          <td class="value"></td>
          <td class="column">现所在地</td>
          <td class="value"></td>
        </tr>
        <tr>
          <td class="column">毕业院校</td>
          <td class="value" colspan="3"></td>
          <td class="column">所在专业</td>
          <td class="value" ></td>

        </tr>
        <tr>
          <td class="column">电子邮箱</td>
          <td class="value" colspan="3"></td>
          <td class="column">手机号码</td>
          <td class="value" ></td>

        </tr>
        <tr>
          <td class="column" colspan="6">教育经历</td>
        </tr>
        <tr>
          <td class="value" colspan="2">起止时间</td>
          <td class="value" colspan="2">毕业院校/教育机构</td>
          <td class="value" colspan="2">专业/课程</td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>

        <tr>
          <td class="column" colspan="6">工作经历</td>
        </tr>
        <tr>
          <td class="value" colspan="2">起止时间</td>
          <td class="value" colspan="2">公司名称</td>
          <td class="value" colspan="2">职位</td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>
        <tr>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
          <td class="value" colspan="2"></td>
        </tr>

        <tr>
          <td class="column">爱好</td>
          <td class="value" colspan="5"></td>
        </tr>
        <tr >
          <td class="column" >技能</td>
          <td class="value" colspan="5" style="text-align:left;">
            <br>
            <br>
            <br>
            <br>
          </td>
        </tr>
        <tr>
          <td class="column">自我评价</td>
          <td class="value" colspan="5">
            <br>
            <br>
            <br>
            <br>
          </td>
        </tr>


      </table>
    </div>
  </div>
</template>
<script>

    export default {

        data() {
            return {
            };
        },
        props: [''],
        watch:{
        },
        computed: {
        },
        created() {

        },
        mounted() {
            setTimeout( ()=>{
                //延迟0.5秒弹出打印
                //不延迟可能document还没有更新，因为数据会从后端读取出来没那么快渲染到页面
                this.$print(this.$refs.print)
            }, 500)

        },
    }
</script>

<style lang="less">
  .table{
    border-collapse: collapse;
    border-spacing: 0;
    background-color: transparent;
    display: table;
    width: 100%;
    max-width: 100%;
    width: 800px;
    margin:0 auto;
  }
  .table td{
    text-align:center;
    vertical-align:middle;
    font-size: 14px;
    font-family: 'Arial Normal', 'Arial';
    color: #333333;
    padding: 8px 12px;
  }
  .table-bordered {
    border: 1px solid #ddd;
  }
  *{
    margin: 0px;
    padding: 0px;
  }
  .column{
    width:30px;
    height:30px;
    border:1px solid #333;
    background: #f1f1f1;
  }
  .value{
    width:70px;
    height:30px;
    border:1px solid #333;
  }


</style>
