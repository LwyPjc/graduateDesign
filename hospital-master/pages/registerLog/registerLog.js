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
    totalObj:[],
    title:"挂号记录",
    totalPrice:0,
    notPayData:[],
    sickName:"",
    sickCard:"",
    show:"hidden",
    userInfo:'',
  },

  //获取待付款列表
  getRegisterLog:function(type){
    const that = this;
    this.setData({
      type:type
    })
    console.log("类类型类型类型型",type)
    var url = urlApi.getRegisterLogUrl();              
    var title = "挂号记录"          
    wx.setNavigationBarTitle({
      title: title,
    })
    var userInfo = this.data.userInfo
    console.log('registerLog--userinfo--',userInfo)
    this.setData({
      title: title
    })
    wx.request({
      url: url,
      method: "get",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openid:userInfo.id,
      },
      success: function (reponse) {
        var data = reponse.data;
        console.log('regi=--data--',data,',reponse: ',reponse)
        var arr = new Array();
        if (data != undefined && data.length > 0){
          for (var i of data) {
            console.log('for--i--',i)
              i.status =  "挂号成功" ;
              i.name = userInfo.trueName;
              i.detail = "就诊卡：" + userInfo.medicareCard;
              // i.price = i.MONEY / 100;
              i.date = util.format(i.createTime);
              arr.push(i)
          }
          that.setData({
            notPayData: arr
          })
        }else{
          that.setData({
            show: "show",
          })
        }
      }
    })
  },

  detailFun:function(){
    if(this.data.type == 0){
      wx.navigateTo({
        url: '/pages/GHDetail/GHDetail',
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var type = options.type;
    console.log('register--onload--',options)
    
    var userInfo = JSON.parse(options.userInfo);
    this.setData({
      sickName: userInfo.trueName,
      sickCard: userInfo.medicareCard,
      userInfo
    })
    this.getRegisterLog(Number(type));
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
