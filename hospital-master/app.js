//app.js
const urlApi = require('./utils/server.api.js')
const util = require('./utils/util.js')
App({

  onLaunch: function () {
    // 展示本地存储能力s
    // var logs = wx.getStorageSync('logs') || []
    // logs.unshift(Date.now())
    // wx.setStorageSync('logs', logs)
    this.myLogin();
  },
  //use openid to find in db 
  getPensorInfoUrl(id, resolve, reject) {
    var that = this;
    wx.request({
      url: urlApi.getPensorInfoUrl() + id,
      method: "get",
      success: function (data) {
        let resp_code = data.statusCode.toString();
        console.log('getuserinfo--', data);
        if (util.checkIsNotNull(data) && resp_code.startsWith('2')) {
          that.globalData.userInfo = data.data
          resolve(that.globalData.userInfo);
          console.log('mylogin()--userinfo--', that.globalData.userInfo);
        }
      },
      fail(res) {
        console.log("未获取用户详细信息", res)
        reject(res)
      }
    })
  },
  getOpenId() {
    var that = this
    return new Promise(function (resolve, reject) {
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          console.log("app.js-getOpenid-login-res:", res);
          wx.request({
            //获取openid接口  
            url: urlApi.getOpenidUrl(),//'https://api.weixin.qq.com/sns/jscode2session',
            data: {
              //todo 修改成自己的 appid，secret,其他不变
              appid: "wxf0a6a8614da75e3d",
              secret: "0513263fe482cb7177a86237bd72efb4",
              JS_CODE: res.code,
              grant_type: 'authorization_code'
            },
            method: 'GET',
            success: function (res) {
              console.log("openId获取成", res)
              let statusCode = res.statusCode.toString();
              if (statusCode.startsWith('2')) {
                that.globalData.openid = res.data.openid;
                resolve(res.data.openid);
              } else {
                reject('error');
              }
            },
            fail(res) {
              console.log("openId获取失败");
              reject(res);
            }
          })
        }
      })
    });
  },

  globalData: {
    userInfo: null,
    openid: '',
    //only one thread run 
    synFlag:true,
    selectDoctor:'',
  },

  // myLaunch(resolve,reject){
  //   var openid = this.globalData.openid;
  //     if (util.checkIsNull(openid)) {
  //       this.getOpenId().then(res => {
  //         openid = res;
  //         console.log('app.js--this.mylogin--openid-', res)
  //       }).then(() => {
  //         this.getPensorInfoUrl(openid, resolve, reject)
  //       })
  //     } else {
  //       console.log('app.js--mylogin--openid is not null-,',openid)
  //       this.getPensorInfoUrl(openid, resolve, reject)
  //     }
  // },

  
  
  myLogin: function () {
    var that = this;
    console.log('app.js-mylogin--before-return-')
    return new Promise(function (resolve, reject) {
      var openid = that.globalData.openid;
      if (util.checkIsNull(openid)) {
        that.getOpenId().then(res => {
          openid = res;
          console.log('app.js--this.mylogin--openid-', res)
        }).then(() => {
          that.getPensorInfoUrl(openid, resolve, reject)
        })
      } else {  
        console.log('app.js--mylogin--openid is not null-,',openid)
        that.getPensorInfoUrl(openid, resolve, reject)
      }
    });


    // var that = this;
    // return new Promise(function (resolve, reject) {
    //   wx.request({
    //     url: urlApi.getPensorInfoUrl() + openid,
    //     method: "get",
    //     success: function (data) {
    //       let resp_code = data.statusCode.toString();
    //       console.log('getuserinfo--', data);
    //       if (util.checkIsNotNull(data) && resp_code.startsWith('2')) {
    //         that.globalData.userInfo = data.data
    //         resolve(that.globalData.userInfo);
    //         console.log('mylogin()--userinfo--', that.globalData.userInfo);
    //       }
    //     },
    //     fail(res) {
    //       console.log("未获取用户详细信息", res)
    //       reject(res)
    //     }
    //   })
    // });

    //get userInfo by openid

    // 登录
    // return new Promise(function (resolve, reject) {
    //   wx.login({
    //     success: res => {
    //       // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //       console.log("login-res:", res);
    //       wx.request({
    //         //获取openid接口  
    //         url: urlApi.getOpenidUrl(),//'https://api.weixin.qq.com/sns/jscode2session',
    //         data: {
    //           //todo 修改成自己的 appid，secret,其他不变
    //           appid: "wxf0a6a8614da75e3d",
    //           secret: "0513263fe482cb7177a86237bd72efb4",
    //           JS_CODE: res.code,
    //           grant_type: 'authorization_code'
    //         },
    //         method: 'GET',
    //         success: function (res) {
    //           console.log("openId获取成", res)
    //           let statusCode = res.statusCode.toString();
    //           if (statusCode.startsWith('2')) {
    //             that.getPensorInfoUrl(res.data.openid,resolve);
    //             that.globalData.openid = res.data.openid;                
    //           } else {
    //             reject('error');
    //           }
    //           //that.getPonsorInfo(res.data.openid);
    //         },
    //         fail(res) {
    //           console.log("openId获取失败");
    //           reject(res);
    //         }
    //       })
    //     }
    //   })
    // });
  },

  //添加住院人
  addResident() {
    wx.navigateTo({
      url: '../addresident/addresident',
    })
  }
})