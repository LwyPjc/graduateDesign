// pages/feedbackFun/feedbackFun.js
const utils = require('../../utils/util.js')
const apiUrl=require('../../utils/server.api.js');
const util = require('../../utils/util.js');
Page({

    /**
     * 页面的初始数据
     */
    data: {
      userInfo:'',
    },
    formSubmit(e){
        console.log('hhh',e);
        if(util.checkIsNull(this.data.userInfo)){
          wx.showToast({
            title: '请先登录',
            icon:'error',
            duration:1000,
            complete: function () {
              setTimeout(function () {
                wx.switchTab({
                  url: '/pages/personal/personal',
                });
              }, 1000)
            }
          })

          return;
          
        }
        let reason = e.detail.value.reason;
        if(utils.checkIsNull(reason)){
            wx.showToast({
              title: '内容不能为空',
              icon:'error',
              duration:2000
            })
            return;
        }
        wx.showToast({
          title: '感谢您的反馈',
          icon:'success',
          duration:2000
        })
        let userInfo = this.data.userInfo;
        console.log('feedback--userinfo-',userInfo)
        console.log('content-',reason)
        wx.request({
          url: apiUrl.saveFeedBackInfo(),
          method:'POST',
          data:{
            openid:userInfo.id,
            content:reason,
          },
          success(res){
            console.log("feedback success: ",res);
          }
        })
        setTimeout(function () {
          wx.navigateBack({
            delta:1
          })
        }, 2000)
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
      const { userInfo } = options;
        // console.log('userDetails--onload--userInfo: ',userInfo);
        if (userInfo != undefined) {
            //todo
            var obj = JSON.parse(userInfo);
            if(util.checkIsNull(obj)){
              return;
            }
            // var obj = '';
            console.log("userDetails-onLoad---", obj)
            var id = 'userInfo.id'
            this.setData({
                [id]: obj.id,
            })
            // console.log(this.data.userInfo);
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