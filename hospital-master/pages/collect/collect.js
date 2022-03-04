// pages/reserve/reserve.js
import initCalendar from '../component/calendar/calendar/index';
import { setTodoLabels } from '../component/calendar/calendar/index';
import { switchView } from '../component/calendar/calendar/index';
const { $Message } = require('../../iview/base/index');
const urlApi = require('../../utils/server.api.js')
const util = require("../../utils/util.js");
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    department: {},
    doctors: [],
    current: 'tab1',
    current_scroll: 'tab1',
    show: "hidden",
    actions: [ 
      {
        name: '返回',
        width: 100,
        color: '#80848f',
        fontsize: '20',
        icon: 'undo'
      }
    ]
  },

  handlerCloseButton(e) {
    var type = e.detail.index;
    var id = e.currentTarget.dataset.id;
    console.log("类型", type, "医生ID", id)
    if (type == 0) {
      this.deleteDoctor(id);
    } else {
      console.log("返回")
      this.setData({
        toggle2: this.data.toggle2 ? false : true
      });
    }
  },

  

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var userInfo = app.globalData.userInfo
    if (util.checkIsNull(userInfo)) {
      wx.showToast({
        title: '您还未登录，请先登录',
        duration: 2000
      })
      return;
    }

    this.getDoctorList();
    this.setData({
      userInfo: app.globalData.userInfo,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },


  getDoctorList: function () {
    var userInfo = app.globalData.userInfo
    var that = this;
    console.log('getDoctorList--userinfo-', userInfo.id)
    wx.request({
      url: urlApi.getCollectInfos(),
      method: "get",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openid: userInfo.id
      },
      success: function (res) {
        var doctorList = res.data
        console.log('collect--getDoctorList--res-', res)
        that.data.doctors = []
        doctorList.map(item => {
          that.data.doctors.push({
            imageurl: "../../images/doctor2.jpg",
            color: "color",
            name: item.docName,
            dptName: item.dptName,
            id:item.docId

          })
        })
        that.setData({
          doctors: that.data.doctors,
          show: "show",
        });
      }
    })
  },

  handleChange({ detail }) {
    var type = detail.key;
    if (type === "1") {
      this.doctorList();
    } else if (type === "0") {
      this.doctorList();
      //this.doctorListDate();
    }

    this.setData({
      current: detail.key
    });
  },

  handleChangeScroll({ detail }) {
    this.setData({
      current_scroll: detail.key
    });
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
    wx.showLoading({
      title: '刷新中...'
    })
    this.getDoctorList();

    wx.hideLoading()
    wx.hideNavigationBarLoading() 
    wx.stopPullDownRefresh()
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
  //页面跳转
  toShowDoctorDetails: function (e) {
    console.log("选中的医生", e);
    wx.navigateTo({
      url: '/pages/doctor/doctor?doctor=' + JSON.stringify(e.currentTarget.dataset.doctor)
        +'&collectSuccess='+true
    })
  }
})