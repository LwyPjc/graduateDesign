// pages/device/add.js
const axios = require('../../utils/init.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tipContent: "",
    topTips: false,
    hide: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
   //打开提示窗 
   open: function (content) {
    this.setData({
      topTips: true,
      tipContent: content
    });
    setTimeout(() => {
      this.setData({
        topTips: false,
        hide: false,
      });
    }, 1000);
  },
  /**
   * 表单提交事件
   */
  onSubmit: function (e) {
    var data = e.detail.value
    data['userId'] = wx.getStorageSync('openId');
    if (!data.name) {
      this.open("请填写名称");
      return;
    } else if (!data.deviceId) {
      this.open("请填写设备ID");
      return;
    }
    //添加设备
    axios.panleAPI('ztDevice/save', data, "POST", function (res) {
      console.log(res);
      wx.navigateTo({
        url: '/pages/msg/msg_success?page=index&url=index&type=tab'
      })
    })
  }
})