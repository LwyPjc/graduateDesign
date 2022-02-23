// pages/doctor/doctor.js
const { $Message } = require('../../iview/base/index');
const urlApi = require('../../utils/server.api.js')
const util = require('../../utils/util.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    title: "挂号记录",
    regRecords: [],
    sickName: "",
    sickCard: "",
    show: "hidden",
    userInfo: '',
  },

  //获取挂号记录
  getRegisterLog: function (refresh) {
    var userInfo = app.globalData.userInfo
    this.setData({
      userInfo
    })
    const that = this;
    wx.setNavigationBarTitle({
      title: this.data.title,
    })
   
    console.log('registerLog-useInfo-',userInfo)
    wx.request({
      url: urlApi.getRegisterLogUrl(),
      method: "get",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openid: userInfo.id,
      },
      success: function (res) {
        var data = res.data
        var arr = new Array();
        if (data != undefined && data.length > 0) {
          for (var i of data) {
            i.status = "挂号成功";
            i.name = "就诊人:" + userInfo.trueName;
            i.detail = "就诊卡：" + userInfo.medicareCard;
            i.date = i.aptTime;
            arr.push(i)
          }
          that.setData({
            regRecords: arr
          })
        } else {
          that.setData({
            show: "show",
          })
        }
        if(refresh){
          wx.hideLoading()
          wx.hideNavigationBarLoading()
          wx.stopPullDownRefresh()
        }
      }
    })
  },

  detailFun: function (e) {
    console.log('detailFun--ee',e)
    for(var i of this.data.regRecords){
      if(i.id===e.currentTarget.dataset.id){
        console.log('equals--i--',i)
        wx.navigateTo({
          url: '/pages/GHDetail/GHDetail?aptRecord='+JSON.stringify(i),
        })
      }
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getRegisterLog(false);
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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
    this.getRegisterLog(true);
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
})
