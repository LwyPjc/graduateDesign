var app = getApp();
const defaultAvatarUrl = 'https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0';
var urlApi = require("../../utils/server.api.js");
const util = require("../../utils/util.js");
Page({
  data: {
    firstLogin: true,
    userInfo: '',
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: false,
    avatarUrl: defaultAvatarUrl,
    //Anti duplicate Click
    btnClicked: false,
    firstShow: true,
  },
  //todo test
  clickToDelete(e) {
    console.log(e)
    let index = e.currentTarget.dataset.index;
    var splice = this.data.testArray.splice(index, 1)
    console.log('splice--', splice);
    this.setData({
      testArray: this.data.testArray
    })
  },
  handleContact(e) {
    console.log(e.detail.path)
    console.log(e.detail.query)
  },

  bindGetUserInfo(e) {
    //防重复点击
    this.setData({
      btnClicked: true,
    })
    wx.showLoading({
      title: 'loading...',
    })
    var that = this;
    // console.log('openid---',openid);
    if (this.data.canIUse) {
      wx.getUserProfile({
        desc: '获取您的用户头像和昵称',
        success: res => {
          var openid = 'userInfo.id';
          var userInfo = res.userInfo;
          this.setData({
            userInfo: userInfo,
            [openid]: app.globalData.openid,
            firstLogin: false
          });
          app.globalData.userInfo = userInfo;
          var info = this.data.userInfo;
          //存入数据库中
          wx.request({
            url: urlApi.saveUserInfo(),
            method: 'POST',
            data: {
              id: info.id,
              nickName: info.nickName,
              avatarUrl: info.avatarUrl,
            },
            success(res) {
              let respCode = res.statusCode.toString();
              console.log('res: ', res);
              if (respCode.startsWith('2')) {
                console.log("success save userInfo.")
              } else {
                console.log('save failed, res: ', res);
              }
            }
          })
        },
        fail: res => {
          console.log("用户拒绝授权...");
        }

      })
    }
    setTimeout(function () {
      wx.hideLoading();
      that.setData({
        btnClicked: false,
      });
    }, 2000)
  },
  //个人信息
  userDetails(e) {
    var userInfo = this.data.userInfo;
    var gender = 'userInfowx.gender';
    if (util.checkIsNotNull(userInfo) && userInfo.gender === 0) {
      this.setData({
        [gender]: ''
      })
    }
    var info = JSON.stringify(this.data.userInfo);
    wx.navigateTo({
      url: '/pages/userDetails/userDetails?userInfo=' + info,
    })
  },
  //反馈功能
  feedbackFun(e) {
    var info = JSON.stringify(this.data.userInfo);
    wx.navigateTo({
      url: '/pages/feedbackFun/feedbackFun?userInfo=' + info,
    })
  },

  //记录查询
  registerLog: function (e) {
    wx.navigateTo({
      url: '/pages/registerLog/registerLog?type=' + e.currentTarget.dataset.type
    })
  },
  myContact(e){
    wx.navigateTo({
      url: '../myContact/myContact',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //如果数据库中已经有该用户的信息，不需要再授权
    console.log('personal-userinfo--', app.globalData.userInfo);
    if (util.checkIsNotNull(app.globalData.userInfo)) {
      console.log('personal-onload-isNotNull-userinfo-', app.globalData.userInfo)
      this.setData({
        userInfo: app.globalData.userInfo,
        firstLogin: false
      })
    } else {
      console.log('personal--onload--else-', app.globalData.userInfo)
      app.myLogin().then(res => {
        console.log('personal-onload-mylogin-', res)
        if (util.checkIsNotNull(res))
          this.setData({
            userInfo: res,
            firstLogin: false
          })
      })
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
    wx.showLoading({
      title: '刷新中...'
    })
    console.log('onPullDownRefresh--personal-info--')
    app.myLogin().then((res) => {
      console.log('personal--onPullFresh--res-', res)
      if(util.checkIsNotNull(res)){
        this.setData({
          userInfo: res,
          firstLogin:false,
        })
      }else{
        this.setData({
          userInfo:res,
          firstLogin:true
        })
      }
      wx.hideLoading()
      wx.hideNavigationBarLoading() //在标题栏中隐藏加载
      wx.stopPullDownRefresh()
      console.log('--personal-onPullDownRefresh--res--1', res)
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