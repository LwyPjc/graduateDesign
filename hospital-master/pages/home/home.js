// pages/home/home.js
const urlApi = require('../../utils/server.api.js');
const util = require('../../utils/util.js');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pensorShowTop: "show",
    pensorShowBottom: "hidden",
    visible1: false,
    code: "",
    actions4: [
      {
        name: '确定'
      },
    ],
    userInfo: '',
  },
  /**
   * 添加就诊人
   */
  addVisitCard: function (e) {
    console.log()
    if (util.checkIsNull(app.globalData.userInfo)) {
      wx.showLoading({
        title: 'loading...',
      })
      var that = this;
      if (wx.canIUse('button.open-type.getUserInfo')) {
        wx.getUserProfile({
          desc: '获取您的用户头像和昵称',
          success: res => {
            var openid = 'userInfo.id';
            if(res.userInfo.gender===0){
              res.userInfo.gender=''
            }
            console.log('home-getUserinfo-res-',res)
            var userInfo = res.userInfo;
            console.log('home-getUserinfo-userinfo-',userInfo)

            
            this.setData({
              userInfo: userInfo,
              [openid]: app.globalData.openid,
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
                  that.myNavigate();
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
      }, 2000)
      return;
    }
    if (util.checkIsNull(this.data.userInfo.medicareCard)) {
      this.myNavigate();
    }
  },


  myNavigate() {
    if (util.checkIsNotNull(app.globalData.userInfo)) {
      wx.navigateTo({
        url: '../addcard/addcard',
      })
    }
  },

  /**
   * 就诊人列表
   */
  // personList: function () {
  //   wx.navigateTo({
  //     url: '../myperson/myperson'
  //   })
  // },
  personalInfo(e) {
    var info = JSON.stringify(this.data.userInfo);
    wx.navigateTo({
      url: '/pages/userDetails/userDetails?userInfo=' + info,
    })
  },

  goInfo: function () {
    wx.switchTab({
      url: '/pages/info/info',
    })
  },
  /**
   * 住院人列表
   */
  residentList: function () {
    wx.navigateTo({
      url: '../myresident/myresident'
    })
  },
  /**
   * 住院充值
   */
  gotoDeposit: function () {
    wx.navigateTo({
      url: '../deposit/deposit'
    })
  },

  //记录查询
  registerLog: function () {
    var info = JSON.stringify(this.data.userInfo);
    wx.navigateTo({
      url: '/pages/registerLog/registerLog?userInfo=' + info
    })
  },

  //去住院日清单页面
  goDayLog: function (e) {
    wx.navigateTo({
      url: '/pages/dayLog/dayLog'
    })
  },

  //住院报告
  report: function () {
    wx.navigateTo({
      url: '/pages/report/report'
    })
  },

  goBranchList: function (e) {
    wx.navigateTo({
      url: '/pages/department/department?type=' + e.currentTarget.dataset.type
    })
  },

  //获取二维码
  EWM: function () {
    this.setData({
      visible1: true
    });
  },

  //取消
  handleClose1() {
    this.setData({
      visible1: false
    });
  },

  //和changeMan,的通信函数
  onGetCode: function (e) {
    this.setData({
      code: e.detail.val
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('home-onload--', )
    var that = this;
    app.myLogin().then(res => {
      console.log("promise回调后的数据：");
      console.log(res);
      if (util.checkIsNotNull(res) && util.checkIsNotNull(res.medicareCard)) {
        //把首页需要请求的数据接口都提取到一个自定义方法中
        // that.GetData();
        that.setData({
          userInfo: res,
          pensorShowTop: "hidden",
          pensorShowBottom: "show",
          userInfo: res
        })
      }
    })
    // if (util.checkIsNotNull(app.globalData.userInfo)) {
    //   var info = app.globalData.userInfo;
    //   console.log('home---info--', info);
    //   if (util.checkIsNotNull(info.medicareCard)) {
    //     //已经添加过就诊卡
    //     this.setData({
    //       pensorShowTop: "hidden",
    //       pensorShowBottom: "show",
    //       medicareCard: info.medicareCard,
    //       pensorName: info.trueName,
    //     })
    //   }
    // }
    // var that = this;
    // app.globalData.sickList = [];
    // app.globalData.changeMan = {
    //   changeMan:function(data){
    //     console.log("切换人",data)
    //     that.setData({
    //       pensorName: data.NAME,
    //       // pensorNumber: data.PATIENT_ID
    //     })
    //     app.globalData.sickName = data.NAME;
    //     app.globalData.BRID= data.BR_ID;
    //     app.globalData.SFZH = data.IDCARD;
    //     app.globalData.sickCard = data.PATIENT_ID;
    //   }
    // }
    // app.globalData.getPonsorInfo = {
    //   getPonsorInfo: function(){
    //     that.getPonsorInfo()
    //   }
    // }
    // app.globalData.getPonsorInfo = {
    //   getPonsorInfo:function(){
    //     console.log("这个函数执行了的");
    //     that.getPonsorInfo();
    //   }
    // }  
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
    // var updateShow = wx.getStorageSync('updateShow');

    // if(util.checkIsNull(updateShow)&&!updateShow){
    //   return;
    // }
    // this.setData({
    //   userInfo:app.globalData.userInfo
    // })
    // console.log('onshow--',app.globalData.userInfo)
    // console.log('onshow--',this.data.userInfo)

    // if(util.checkIsNotNull(this.data.userInfo)&&util.checkIsNotNull(this.data.medicareCard)){
    //   this.setData({
    //     pensorShowTop: "hidden",
    // pensorShowBottom: "show",
    //   })
    // }

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
    console.log('onPullDownRefresh--home--')
    app.myLogin().then((res) => {
      console.log('home--onPullFresh--res-', res)
      if(util.checkIsNotNull(res)){
        this.setData({
          userInfo: res,
          pensorShowTop: "hidden",
          pensorShowBottom: "show",
        })
      }else{
        this.setData({
          userInfo:res,
          pensorShowTop: "show",
          pensorShowBottom: "hidden",
        })
      }
      wx.hideLoading()
      wx.hideNavigationBarLoading() //在标题栏中隐藏加载
      wx.stopPullDownRefresh()
      console.log('--home-onPullDownRefresh--res--1', res)
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