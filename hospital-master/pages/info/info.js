// pages/info/info.js
const urlApi = require('../../utils/server.api.js')
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    infoImgURL: '../../images/2.jpg',
    name: "",
    address: "",
    phone: "",
    intro: "",
    info: '',
  },

  //去医院简介页面
  goHospitalInfo: function (e) {
    var intro = JSON.stringify(this.data.intro);
    wx.navigateTo({
      url: '/pages/hospitalInfo/hospitalInfo?intro=' + intro
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('onload--hospital-info--', options)
    this.getHospitalInfo().then(res => {
      console.log('onload-回调--res', res);
    });
  },

  getHospitalInfo() {
    var that = this;
    return new Promise(function (resolve, reject) {
      wx.request({
        url: urlApi.getHospitalInfo(),
        method: 'GET',
        success(res) {
          console.log('res---', res);
          let info = res.data;
          that.setData({
            name: info.name,
            address: info.location,
            phone: info.contactPhone,
            intro: info.introInfo,
            info: info
          })
          resolve(info)
        },
        fail(res) {
          reject('error')
        }
      })
    })
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
   *  app.myLogin().then(res => {
      console.log("promise回调后的数据：");
      console.log(res);
      if 
   */
  onPullDownRefresh: function () {
    wx.showLoading({
      title: '刷新中...'
    })
    console.log('onPullDownRefresh--hospital-info--')
    this.getHospitalInfo().then((res) => {
      wx.hideLoading()
      wx.hideNavigationBarLoading() //在标题栏中隐藏加载
      wx.stopPullDownRefresh()
      console.log('--onPullDownRefresh--res--1', res)
    },
      () => {
        wx.hideLoading()
        wx.showToast({
          title: '服务器错误，请稍后重试'
        })
      })
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