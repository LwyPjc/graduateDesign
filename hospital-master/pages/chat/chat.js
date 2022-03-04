// pages/chat/chat.js
const urlApi = require('../../utils/server.api.js');
const app = getApp();
const util = require("../../utils/util.js");
var ws;
//判断是否连接socket
var socketOpen = false;
Page({

    /**
     * 页面的初始数据
     */
    data: {
        receivebaseInfo: "",
        userInfo: '',
        doctorInfo: '',
        historyList: [],
        newsList: [],
        input: '',
    },
    formSubmit(e) {
        var content = e.detail.value.input;
        var userInfo = app.globalData.userInfo;
        var doctorInfo = this.data.doctorInfo;
        var that = this
        wx.sendSocketMessage({
            data: JSON.stringify({
                openid: userInfo.id,
                trueName: userInfo.trueName,
                docId: doctorInfo.id,
                docName: doctorInfo.name,
                content: content,
                sendFrom: '0',
                createTime: util.formatTime(new Date())
            }),
            success(res) {
                console.log('formSubmit--res--', res)
                that.setData({
                    input: ''
                })
            }
        })

    },
    sendSocketMessage() {
        wx.sendSocketMessage({
            data: JSON.stringify({
                id:111,
            }),
        })
        setTimeout(() => {
            this.sendSocketMessage();
        }, 5000);
       
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

        const docInfo = options.doctorInfo;
        var doctorInfo;
        if (docInfo != undefined) {
            doctorInfo = JSON.parse(docInfo);
            this.setData({
                doctorInfo
            })
        }
        this.setData({
            userInfo: app.globalData.userInfo
        })
        wx.setNavigationBarTitle({
            title: '在线咨询-' + doctorInfo.name
        })
        this.connectWs(3);

        this.getHistoryInfo();
        console.log('historyList--', this.data.historyList)
        //   this.resMsg(1);
    },
    connectWs(times) {
        var userInfo = this.data.userInfo;
        var doctorInfo = this.data.doctorInfo
        var that = this
        wx.connectSocket({
            url: urlApi.connectWs(),
            data: JSON.stringify({
                id: userInfo.id,
                trueName: userInfo.trueName
            }),
            success(res) {
                socketOpen = true
                console.log('connectWs--', res)
            },
            fail: (err) => {
                wx.showToast({
                    title: '网络异常！请刷新重试',
                })
                console.log(err)
            },
        })
        wx.onSocketOpen((res) => {
            console.log('WebSocket 成功连接', res)
            // that.listener();
            //todo
            //开启循环
            // heartBeat();
        })

        wx.onSocketError((err) => {
            if (times > 0) {
                console.log('正在尝试第', times, '次连接')
                that.connectWs(--times)
            }
        })
        // wx.onSocketMessage((res) => {
        //     console.log('wx.onSocketMessage---res--',res)
        // })
        //  接收服务器的消息事件
        wx.onSocketMessage(function(res){
            var res = JSON.parse(res.data);
            console.log('wx.onSocketMessage---res--',res)
            if (res.openid == userInfo.id && res.docId == doctorInfo.id) {
                that.data.newsList.push(res);
                that.setData({
                    newsList: that.data.newsList
                })
                console.log('this.data.newsList--', that.data.newsList)
            }
            },
        )
        //  监听连接关闭
        wx.onSocketClose(function(){
            console.log('监听 WebSocket 连接关闭事件')
        })
    },
   
    listener() {
        ws.onOpen(res => {
            socketOpen = true;
            console.log('监听 WebSocket 连接打开事件。', res)
        })
        
        //ws.onMessage
        this.resMsg(2);
        ws.onMessage(msg=>{
            console.log('onMessage--',msg)
        })
        ws.onClose(onClose => {
            console.log('监听 WebSocket 连接关闭事件。', onClose)
            socketOpen = false;
        })
        ws.onError(onError => {
            console.log('监听 WebSocket 错误。错误信息', onError)
            socketOpen = false
        })
        // setTimeout(() => {
        //     console.log('oooooooo')
        //     this.sendSocketMessage();
        // }, 5000);

    },
    resMsg(code) {
        var userInfo = app.globalData.userInfo
        var doctorInfo = this.data.doctorInfo
        console.log('resMsg--ws--', ws)
        ws.onMessage(onMessage => {
            var res = JSON.parse(onMessage.data);
            if (res.openid == userInfo.id && res.docId == doctorInfo.id) {
                this.data.newsList.push(res);
                this.setData({
                    newsList: this.data.newsList
                })
                console.log('this.data.newsList--', this.data.newsList)
            }
            console.log('onMessage---res--', res, code)
        })

    },
    getHistoryInfo() {
        var userInfo = app.globalData.userInfo;
        var that = this
        var doctorInfo = this.data.doctorInfo
        wx.request({
            url: urlApi.getHistoryChatInfo(),
            data: {
                openid: userInfo.id,
                docId: doctorInfo.id
            },
            success(res) {
                let statusCode = res.statusCode.toString();
                if (statusCode.startsWith('2')) {
                    var history = res.data;
                    that.data.historyList = [];
                    history.map(item => {
                        that.data.historyList.push({
                            id: item.id,
                            openid: item.openid,
                            content: item.content,
                            sendFrom: parseInt(item.sendFrom),
                            createTime: item.createTime
                        })
                    });
                    that.setData({
                        historyList: that.data.historyList
                    })
                    console.log("getHistoryInfo--res-", res)
                }
                console.log('historyList--', that.data.historyList)
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
        //离开页面关闭socket
        wx.closeSocket({
            code: 1000,
        })
        socketOpen = false
    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {
        wx.showLoading({
            title: '刷新中...'
        })
        this.setData({
            userInfo: app.globalData.userInfo
        })
        console.log('fresh--socketOpen--', socketOpen)
        if (!socketOpen)
            this.connectWs(3);
        this.getHistoryInfo();
        this.setData({
            newsList: []
        })

        wx.hideLoading()
        wx.hideNavigationBarLoading() //在标题栏中隐藏加载
        wx.stopPullDownRefresh()
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