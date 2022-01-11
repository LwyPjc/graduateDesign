<template>
  <div>
    <el-row style="margin-bottom: 20px;">
      <el-col :span="5">
        <el-card style="width:350px; height: 160px;">
          <div slot="header" class="clearfix">
            <span slot="title">今日访问量</span>
          </div>
          <div class="card-list">
            <p><span class="card-big-font">{{todayLoginCnt}}</span></p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5" :offset="1">
        <el-card style="width:350px; height: 160px;">
          <div slot="header" class="clearfix">
            <span slot="title">总访问量</span>
          </div>
          <p><span class="card-big-font">{{loginCnt}}</span></p>
        </el-card>
      </el-col>
      <el-col :span="5" :offset="1">
        <el-card style="width:350px; height: 160px;">
          <div slot="header" class="clearfix">
            <span slot="title">文物数量</span>
          </div>
          <p><span class="card-big-font">{{goodsCnt}}</span></p>
        </el-card>
      </el-col>
      <el-col :span="5" :offset="1">
        <el-card style="width:350px; height: 160px;">
          <div slot="header" class="clearfix">
            <span slot="title">用户反馈数</span>
          </div>
          <p><span class="card-big-font">{{adviceCnt}}</span></p>
        </el-card>
      </el-col>

    </el-row>
    <el-row>
      <el-col :span="11">
        <el-card style="width:580px; height: 350px;">
          <div id="echarts" :style="{width:'500px', height: '300px'}" style="float: left;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    import echarts from 'echarts'
    import request from '@/utils/request'
    export default {
        data() {
            return {
                isCollapsed: false,
                charts: '',
                todayLoginCnt:0,
                loginCnt:0,
                goodsCnt:0,
                adviceCnt:0,
                monthsCnt:{},
                prefixUrl: this.GLOBAL.baseUrl + '/data'
            };
        },
        created () {
            this.init()
        },
        methods: {
            //请求分析结果
            init (row) {
                request({
                    url: `${this.prefixUrl}/total`,
                    method: 'get'
                }).then(res => {
                    this.loginCnt = res.loginCnt;
                    this.goodsCnt = res.goodsCnt;
                    this.adviceCnt = res.adviceCnt;
                    this.todayLoginCnt = res.todayLoginCnt;
                    this.monthsCnt = res.monthsCnt;
                    var data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
                    for(var i = 0;i<res.monthsCnt.length;i++){
                        var month = parseInt(res.monthsCnt[i].month);
                        data[month] = res.monthsCnt[i].cnt;
                    }
                    this.drawPie('echarts',data)
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error',
                        duration: 1500,
                        onClose: () => {
                        }
                    })
                })
            },
            drawPie(id,data) {
                this.charts = echarts.init(document.getElementById(id))
                this.charts.setOption({
                    title: {
                        text: '每月访问量'
                    },
                    xAxis: {
                        type: 'category',
                        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            formatter: '{value} '
                        }
                    },
                    series: [{
                        name: '访问量',
                        type: 'line',
                        stack: '总量',
                        itemStyle: {
                            normal: {
                                color: '#99CCFF',
                                lineStyle: {
                                    color: '#99CCFF'
                                }
                            }
                        },
                        data: data
                    }]
                })
            }
        },
        //调用
        mounted() {
            this.$nextTick(function() {

            })
        },
        computed: {
            menuitemClasses: function() {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        }
    }
</script>

<style scoped="scoped">
  .card-big-font {
    font-size: 36px;
    color: #666;
    line-height: 36px;
    padding: 5px 0 10px;
    text-overflow: ellipsis;
    word-break: break-all;
    white-space: nowrap;
    margin-bottom: 5px;
  }

  .card-span-color {
    position: absolute;
    right: 15px;
  }
</style>
