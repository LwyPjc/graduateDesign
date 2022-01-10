// pages/device/myDevice.js
const axios = require('../../utils/init.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    deviceList:[],
    targetUrl: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var targetUrl = "";
    if(options.type === "edit"){
      targetUrl = "/cmd/cmd"
    }else{
      targetUrl = "/device/controll"
    }
    this.setData({
      targetUrl: targetUrl
    })
    var that = this
    //获取我的设备
    axios.panleAPI('ztDevice/findList', {userId:wx.getStorageSync('openId')}, "GET", function (res) {
      that.setData({
        deviceList: res
      })
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  /**
   * 新设备接入页面
   */
  toAdd: function(){
    wx.navigateTo({
      url: '/pages/device/add'
    })
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

  }
})