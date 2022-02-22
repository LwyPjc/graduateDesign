var urlApi = require("../../utils/server.api.js")
var util = require("../../utils/util.js")
Page({

    /**
     * 页面的初始数据
     */
    data: {
        userInfo: '',
        isUserLogin: false,
    },
    backToPrePage(e) {
        wx.navigateBack({
            delta: 1,
        })
    },
    
    formSubmit(e) {
        let info = e.detail.value;
        console.log("e----", e);
        var nickName = 'userInfo.nickName';
        var idCard = 'userInfo.idCard';
        var phoneNum = 'userInfo.phoneNum';
        var trueName = 'userInfo.trueName';

        this.setData({
            [nickName]: info.nickName,
            [phoneNum]: info.phoneNum,
            [trueName]: info.trueName,
            [idCard]: info.idCard,
        })
        //更新数据库
        var userInfo = this.data.userInfo;
        wx.request({
            url: urlApi.updateUserInfo(),
            method: 'POST',
            data: {
                id: userInfo.id,
                nickName: userInfo.nickName,
                idCard: userInfo.idCard,
                trueName: userInfo.trueName,
                avatarUrl: userInfo.avatarUrl,
            },
            success(res) {
                let resp_code = res.statusCode.toString();
                if (resp_code.startsWith('2')) {
                    console.log('修改成功--', res);
                } else {
                    console.log('修改失败--', res);
                }
            }
        })
        let pages = getCurrentPages()
        let prevPages = pages[pages.length - 2]
        prevPages.setData({
            userInfo:this.data.userInfo
        })
        wx.navigateBack({
            delta: 1
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        const { userInfo } = options;
        // console.log('userDetails--onload--userInfo: ',userInfo);
        if (userInfo != undefined) {
            var obj = JSON.parse(userInfo);
            console.log('obj: ', obj)
            if (util.checkIsNotNull(obj)&&util.checkIsNotNull(obj.id)) {
                // var obj = '';
                var id = 'userInfo.id'
                var avatarUrl = 'userInfo.avatarUrl'
                var nickName = 'userInfo.nickName'
                var gender = 'userInfo.gender'
                var age = 'userInfo.age'
                var trueName = 'userInfo.trueName'
                var phoneNum = 'userInfo.phoneNum'
                var idCard = 'userInfo.idCard'
                var medicareCard = 'userInfo.medicareCard'
                var address = 'userInfo.address'
                this.setData({
                    [id]: obj.id,
                    [avatarUrl]: obj.avatarUrl,
                    [nickName]: obj.nickName,
                    [gender]: obj.gender,
                    [age]: obj.age,
                    [trueName]: obj.trueName,
                    [phoneNum]: obj.phoneNum,
                    [idCard]: obj.idCard,
                    [medicareCard]: obj.medicareCard,
                    [address]:obj.address,
                    isUserLogin: true,
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
        wx.showLoading({
            title: '刷新中...'
          })
          app.myLogin().then((res) => {
            if(util.checkIsNotNull(res)){
              this.setData({
                userInfo: res,
              })
            }else{
              this.setData({
                userInfo:res,
              })
            }
            wx.hideLoading()
            wx.hideNavigationBarLoading()
            wx.stopPullDownRefresh()
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