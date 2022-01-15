<template>
  <div id="coursesTable"></div>
</template>

<script>
    import request from '@/utils/request'
    import Timetables from 'Timetables'
    export default {
        data() {
            return {
                timetables : [
                    ['大学英语(Ⅳ)@10203','大学英语(Ⅳ)@10203','','','','','毛概@14208','毛概@14208','','','','选修'],
                    ['','','信号与系统@11302','信号与系统@11302','模拟电子技术基础@16204','模拟电子技术基础@16204','','','','','',''],
                    ['大学体育(Ⅳ)','大学体育(Ⅳ)','形势与政策(Ⅳ)@15208','形势与政策(Ⅳ)@15208','','','电路、信号与系统实验','电路、信号与系统实验','','','',''],
                    ['','','','','电装实习@11301','电装实习@11301','','','','大学体育','大学体育',''],
                    ['','','数据结构与算法分析','数据结构与算法分析','','','','','信号与系统','信号与系统','',''],
                ],
                timetableType : [
                    [{index: '1',name: ''}, 1],
                    [{index: '2',name: ''}, 1],
                    [{index: '3',name: ''}, 1],
                    [{index: '4',name: ''}, 1],
                    [{index: '5',name: ''}, 1],
                    [{index: '6',name: ''}, 1],
                    [{index: '7',name: ''}, 1],
                    [{index: '8',name: ''}, 1],
                    [{index: '9',name: ''}, 1],
                    [{index: '10',name: ''}, 1]
                ],
                week :  ['周一', '周二', '周三', '周四', '周五'],
                highlightWeek : new Date().getDay(),
                styles : {
                    Gheight: 50,
                    leftHandWidth: 100,
                    palette: ['#ff6633', '#eeeeee']
                },
                timetable: null,
                prefixUrl: this.GLOBAL.baseUrl + '/studentCourse'
            }
        },
        created() {
            this.initData();

            setTimeout(function (){

            }, 1000);
        },
        methods: {
            /**
             * 获取课程表信息
             */
            initData(){
                var that = this;
                request({
                    url: `${this.prefixUrl}/timetable`,
                    method: 'get',
                    params: {userName:window.sessionStorage.username}
                }).then(res => {
                    this.timetables = res;
                    this.timetable = new Timetables({
                        el: '#coursesTable',
                        timetables: that.timetables,
                        week: that.week,
                        timetableType: that.timetableType,
                        highlightWeek: that.highlightWeek,
                      /*  gridOnClick: function (e) {
                            alert(e.name + '  ' + e.week +', 第' + e.index + '节课, 课长' + e.length +'节')
                            console.log(e)
                        },*/
                        styles: this.styles
                    });
                }).catch(error => {
                    this.$message({
                        message: error,
                        type: 'error',
                        duration: 1500,
                    })
                })
            },
            onChange() {
                this.timetable.setOption({
                    timetables: this.timetables,
                    week: ['一', '二', '三', '四', '五', '六', '日'],
                    styles:{
                        palette: ['#dedcda', '#ff4081']
                    },
                    timetableType:this.timetableType,
                    gridOnClick: function (e) {
                        console.log(e)
                    }})
            }
        }
    }
</script>

<style>
  #coursesTable {
    padding: 15px 10px;
  }

  .Courses-head {
    background-color: #edffff;
  }

  .Courses-head > div {
    text-align: center;
    font-size: 14px;
    line-height: 28px;
  }

  .left-hand-TextDom, .Courses-head {
    background-color: #f2f6f7;
  }

  .Courses-leftHand {
    background-color: #f2f6f7;
    font-size: 12px;
  }

  .Courses-leftHand .left-hand-index {
    color: #9c9c9c;
    margin-bottom: 4px !important;
  }

  .Courses-leftHand .left-hand-name {
    color: #666;
  }

  .Courses-leftHand p {
    text-align: center;
    font-weight: 900;
  }

  .Courses-head > div {
    border-left: none !important;
  }

  .Courses-leftHand > div {
    padding-top: 5px;
    border-bottom: 1px dashed rgb(219, 219, 219);
  }

  .Courses-leftHand > div:last-child {
    border-bottom: none !important;
  }

  .left-hand-TextDom, .Courses-head {
    border-bottom: 1px solid rgba(0, 0, 0, 0.1) !important;
  }

  .Courses-content > ul {
    border-bottom: 1px dashed rgb(219, 219, 219);
    box-sizing: border-box;
  }

  .Courses-content > ul:last-child {
    border-bottom: none !important;
  }

  .highlight-week {
    color: #02a9f5 !important;
  }

  .Courses-content li {
    text-align: center;
    color: #666666;
    font-size: 14px;
    line-height: 50px;
  }

  .Courses-content li span {
    padding: 6px 2px;
    box-sizing: border-box;
    line-height: 18px;
    border-radius: 4px;
    white-space: normal;
    word-break: break-all;
    cursor: pointer;
  }

  .grid-active {
    z-index: 9999;
  }

  .grid-active span {
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
  }
</style>
