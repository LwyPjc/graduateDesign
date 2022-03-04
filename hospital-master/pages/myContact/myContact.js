// pages/myContact/myContact.js
var urlApi = require("../../utils/server.api.js");
const util = require("../../utils/util.js");
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        current: 'tab1',
        current_scroll: 'tab1',
        show: "hidden",
        doctors:'',
        actions: [ 
          {
            name: '返回',
            width: 100,
            color: '#80848f',
            fontsize: '20',
            icon: 'undo'
          }
        ],
        userInfo:'',
    },
    chatDetails(e){
        wx.navigateTo({
          url: '../chat/chat?doctorInfo='+JSON.stringify(e.currentTarget.dataset.doctor)
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.setData({
            userInfo:app.globalData.userInfo
        })
        this.getMyContact();
    },
    getMyContact(){
       var userInfo = app.globalData.userInfo
       var that = this;
        wx.request({
          url: urlApi.getMyContact(),
          method:'GET',
          data:{
              openid:userInfo.id
          },
          success(res){
              console.log('myContact--res--',res)
              that.setData({
                  doctors:res.data
              })
          }
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