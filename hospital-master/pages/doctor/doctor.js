// pages/doctor/doctor.js
import initCalendar from '../component/calendar/calendar/index';
import { setTodoLabels } from '../component/calendar/calendar/index';
import { switchView } from '../component/calendar/calendar/index';
const urlApi = require('../../utils/server.api.js')
const util = require("../../utils/util.js")
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    current: 'tab1',
    introduce: "hidden",
    registration: "show",
    date: util.formatTime2(new Date()),
    doctor: "",
    userInfo: "",
    dateType: "week",
    collectSuccess: false,
    isCollected: '',

  },

  handleOpen2: function () {
    this.setData({
      visible2: true,
    });
  },

  handleCancel2() {
    this.setData({
      visible2: false
    });
  },
  handleChange: function ({ detail }) {
    this.setData({
      current: detail.key
    });
  },
  switchView: function () {
    console.log("1111111")
    if (this.data.dateType == "week") {
      this.setData({
        dateType: "month"
      })
      switchView('month');
    } else {
      this.setData({
        dateType: "week"
      })
      switchView('week');
    }

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this;
    var doctor = JSON.parse(options.doctor);
    if (util.checkIsNotNull(options.collectSuccess)) {
      this.setData({
        collectSuccess: JSON.parse(options.collectSuccess)
      })
    }
    console.log('--doctor--', options)
    app.globalData.selectDoctor = doctor;
    var userInfo = app.globalData.userInfo
    this.setData({
      doctor,
      userInfo,
    })

    this.checkIsCollected().then(res => {
      console.log('doctor--onload--res--', res);
      if (util.checkIsNotNull(res)) {
        this.setData({
          isCollected: res.id
        })
        if (res.deleteFlg != 1) {
          this.setData({
            collectSuccess: true
          })
        }
      }
    });
    app.globalData.dayFun = {
      dayFun: function (data) {
        var idx = data.idx
        var month = data.days[idx].month;
        var day = data.days[idx].day;
        that.setData({
          date: data.days[idx].year.toString() + "-" + util.formatNumber(month) + '-' + util.formatNumber(day)
        })
      }
    }
    console.log('onload--date--', this.data.date)
  },
  checkIsCollected() {
    var userInfo = this.data.userInfo
    var doctor = this.data.doctor;
    var that = this;
    return new Promise(function (resolve, reject) {
      wx.request({
        url: urlApi.getSingleCollectInfo(),
        method: "get",
        header: {
          'content-type': 'application/x-www-form-urlencoded'
        },
        data: {
          openid: userInfo.id,
          docId: doctor.id,
        },
        success(res) {
          var collectInfo = res.data;
          if (util.checkIsNotNull(collectInfo)) {
            resolve(collectInfo)
            // that.setData({
            //   isCollected:collectInfo
            // })
          }
          console.log('checkIsCollected--', res)
        }
      })
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    const conf = {
      multi: false, // 是否开启多选,
      disablePastDay: true, // 是否禁选过去的日期
      /**
       * 初始化日历时指定默认选中日期，如：'2018-3-6' 或 '2018-03-06'
       * 注意：若想初始化时不默认选中当天，则将该值配置为除 undefined 以外的其他非值即可，如：空字符串, 0 ,false等。
      */
      noDefault: true, // 初始化后是否自动选中当天日期，优先级高于defaultDay配置，两者请勿一起配置
      /**
       * 选择日期后执行的事件
       * @param { object } currentSelect 当前点击的日期
       * @param { array } allSelectedDays 选择的所有日期（当mulit为true时，才有allSelectedDays参数）
       */
      afterTapDay: (currentSelect, allSelectedDays) => { },
      /**
       * 当改变月份时触发
       * @param { object } current 当前年月
       * @param { object } next 切换后的年月
       */
      whenChangeMonth: (current, next) => { },
      /**
       * 日期点击事件（此事件会完全接管点击事件）
       * @param { object } currentSelect 当前点击的日期
       * @param { object } event 日期点击事件对象
       */
      onTapDay(currentSelect, event) { },
      /**
       * 日历初次渲染完成后触发事件，如设置事件标记
       * @param { object } ctx 当前页面
       */
      afterCalendarRender(ctx) {
        setTodoLabels({
          pos: 'bottom',
          dotColor: '#40',
          days: [{
            year: 2019,
            month: 1,
            day: 1,
            todoText: '待办'
          }, {
            year: 2019,
            month: 1,
            day: 15,
          }],
        });
      },
    }
    initCalendar(conf); // 使用默认配置初始化日历
    switchView('week');

  },

  //tabs切换
  handleChange({ detail }) {
    var type = detail.key;
    if (type === "0") {
      this.setData({
        introduce: "hidden",
        registration: "show"
      });
    } else if (type === "1") {
      this.setData({
        introduce: "show",
        registration: "hidden"
      });
    }
  },


  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  collecting: function () {
    if (this.checkUserIsNotLogin()) {
      return;
    }
    // this.checkIsCollected().then(res => {
    // })

    var collectSuccess = this.data.collectSuccess
    this.setData({
      collectSuccess: !collectSuccess
    })

    var title;
    var deleteFlg;
    //如果点击收藏
    if (this.data.collectSuccess) {
      deleteFlg = '0'
      title = '收藏成功'
    } else {
      deleteFlg = '1'
      title = '取消收藏';
    }
    console.log('collecting--collectSuccess--', this.data.collectSuccess)
    var that = this
    var userInfo = this.data.userInfo
    var doctor = this.data.doctor
    console.log('collecting---isCollected--', this.data.isCollected)
    var isCollected = this.data.isCollected;
    if (util.checkIsNull(isCollected)) {
      wx.request({
        url: urlApi.saveCollect(),
        method: "post",
        data: {
          openid: userInfo.id,
          dptName: doctor.dptName,
          dptId: doctor.dptId,
          docId: doctor.id,
          docName: doctor.name,
        },
        success: function (res) {
          let statusCode = res.statusCode.toString();
          console.log('collecting--res--', res)
          if (statusCode.startsWith('2')) {
            wx.showToast({
              title: '收藏成功',
              icon: "none",
              duration: 2000
            })
            that.setData({
              isCollected: res.data
            })
          }
        }
      })
    } else {
      wx.request({
        url: urlApi.updateCollectInfo(),
        method: 'POST',
        data: {
          id: isCollected,
          deleteFlg: deleteFlg
        },
        success(res) {
          let statusCode = res.statusCode.toString();
          console.log('else--res--', res)
          if (statusCode.startsWith('2')) {
            wx.showToast({
              title: title,
              icon: 'none',
              duration: 2000
            })
          }
        }
      })
    }

  },
  checkUserIsNotLogin() {
    var userInfo = app.globalData.userInfo
    if (util.checkIsNull(userInfo) || util.checkIsNull(userInfo.medicareCard)) {
      wx.showToast({
        title: '请先绑定信息',
        icon: 'error',
        duration: 2000
      })
      return true;
    }
    return false;
  },
  //页面跳转
  confirmReg: function (e) {
    if (this.checkUserIsNotLogin()) {
      this.setData({
        visible2: false
      });
      return;
    }
    var doctorInfo = this.data.doctor
    var userInfo = this.data.userInfo
    console.log('doctor--confirmReg--date-', this.data.date)
    var that = this
    wx.request({
      url: urlApi.saveRegisterInfo(),
      method: 'POST',
      data: {
        openid: userInfo.id,
        dptId: doctorInfo.dptId,
        dptName: doctorInfo.dptName,
        docName: doctorInfo.name,
        docId: doctorInfo.id,
        aptTime: this.data.date
      },
      success(res) {
        let statusCode = res.statusCode.toString();
        console.log('doctor--confirm--res-', res)
        if (statusCode.startsWith('2')) {
          wx.navigateTo({
            url: '../registerSuccess/registerSuccess',
          })
        } else {
          wx.showToast({
            title: '预约失败,重试',
            icon: 'error',
            duration: 2000
          })
          that.setData({
            visible2: false
          });
        }
      }
    })
    // this.lockModify();

  }
})
