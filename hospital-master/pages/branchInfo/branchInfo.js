// pages/branchInfo/branchInfo.js
const urlApi = require('../../utils/server.api.js');
const util = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: "",
    address: "",
    phone: "",
    info: "",
    id: '',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const { dptInfo } = options
    console.log('branchInfo--onload-options-', dptInfo)
    if (dptInfo != undefined) {
      var obj = JSON.parse(dptInfo);
      if (util.checkIsNotNull(obj)) {
        this.setData({
          phone: obj.phone,
          info: obj.descs,
          name: obj.name,
          id: obj.id,
        })
      }
    }

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
    //根据id查找科室
    var that = this
    if (util.checkIsNotNull(this.data.id)) {
      wx.request({
        url: urlApi.getDepartmentInfoById() + this.data.id,
        method: 'GET',
        success(res){
          var info = res.data
          that.setData({
            name:info.name,
            info:info.descs,
            phone:info.phone
          })
        }
      })
    }
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