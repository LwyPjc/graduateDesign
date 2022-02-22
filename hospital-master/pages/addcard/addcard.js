// pages/addcard/addcard.js
const { $Message } = require('../../iview/base/index');
const urlApi = require('../../utils/server.api.js');
const util = require("../../utils/util.js")
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    name: '',
    card: '',
    phone: '',
    addrs: '',
    isIdcardExits: false,
    gender: [{
      id: 1,
      name: '女',
    }, {
      id: 2,
      name: '男'
    }],
    position: 'left',
    sex: "",
    checked: false,
    disabled: false,
    yesButton: "立即添加",
    current: '',
  },

  //姓名验证发放
  nameVerifyFun: function ({ detail }) {
    var name = detail.detail.value
    console.log('nameVerifyFun: ', name)
    if (util.checkIsNull(name)) {
      $Message({
        content: "名字不能为空"
      });
    } else {
      this.setData({
        name
      })
    }
  },

  //身份证验证发放
  cardVerifyFun: function ({ detail }) {
    var card = detail.detail.value
    if (util.checkIsNull(card)) {
      $Message({
        content: "身份证不能为空"
      });
      return;
    } else {
      this.setData({
        card
      })
    }
    var that = this;
    wx.request({
      url: urlApi.checkIdCardIsExist() + card,
      method: 'GET',
      success(res) {
        let openId = res.data;
        let bool = false;
        if (util.checkIsNotNull(openId)) {
          bool = true;
        }
        that.checkCardIsExist(bool);
        that.setData({
          isIdcardExits: bool
        })
      }
    })
  },

  checkCardIsExist(bool) {
    if (bool) {
      $Message({
        content: "身份证号码已经绑定了就诊卡",
        duration: 5
      });
    }
  },
  //电话验证发放
  phoneVerifyFun: function ({ detail }) {
    var phone = detail.detail.value
    if (util.checkIsNull(phone)) {
      $Message({
        content: "电话不能为空"
      });
    } else {
      this.setData({
        phone
      })
    }
  },

  //地址验证发放
  addrVerifyFun: function ({ detail }) {
    var addrs = detail.detail.value
    console.log('addrVerifyFun--',addrs)
    if (util.checkIsNull(addrs)) {
      $Message({
        content: "详细地址不能为空"
      });
    } else {
      this.setData({
        addrs
      })
    }
  },

  //获取性别
  handleGenderChange({ detail = {} }) {
    //男 女
    var gender = detail.value
    console.log('gender：', gender);
    if (gender === '女') {
      this.setData({
        sex: 1
      })
    } else if (gender === '男') {
      this.setData({
        sex: 2
      })
    }
    this.setData({
      current: gender
    });

  },

  handleDisabled() {
    this.setData({
      disabled: !this.data.disabled
    });
  },

  handleClick: function () {
    if (this.isIdcardExits) {
      wx.showToast({
        title: '身份证号已经绑定就诊卡',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    if (util.checkIsNull(this.data.name) || util.checkIsNull(this.data.card) || util.checkIsNull(this.data.phone) || util.checkIsNull(this.data.sex) || util.checkIsNull(this.data.addrs)) {
      console.log('name: ', this.data.name, ',card: ', this.data.card, ' ,phone: ', this.data.phone, ', sex: ', this.data.sex,', addrs: ',this.data.addrs);
      wx.showToast({
        title: '请填写完整信息',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    var openid = app.globalData.openid;
    wx.request({
      url: urlApi.updateUserInfo(),
      method: "post",
      data: {
        id: openid,
        trueName: this.data.name,
        phoneNum: this.data.phone,
        idCard: this.data.card,
        address: this.data.addrs,
        gender: this.data.sex,
      },
      success: function (reponse) {
        let statusCode = reponse.statusCode.toString();
        if (statusCode.startsWith('2')) {
          app.myLogin().then(res => {
            console.log("promise回调后的数据：");
            console.log(res);
            if (util.checkIsNotNull(res) && util.checkIsNotNull(res.medicareCard)) {
              app.globalData.userInfo = res
            }
          })
          console.log('addCard--reponse--', reponse)
          let pages = getCurrentPages();
          let prevPages = pages[pages.length - 2];
          if (util.checkIsNotNull(prevPages)) {
            prevPages.onLoad();
          }
          wx.showToast({
            title: '成功',
            icon: 'success',
            complete: function () {
              setTimeout(function () {
                wx.switchTab({
                  url: '/pages/home/home',
                });
              }, 1000)
            }
          })
          
        } else {
          wx.showToast({
            title: '失败',
            icon: 'none',
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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