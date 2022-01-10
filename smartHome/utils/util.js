const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function getLocation(callback) { //位置信息
  wx.chooseLocation({
    success: function (res) {
      callback(true, res.latitude, res.longitude);
    },
    fail: function () {
      callback(false);
    }
  })
}

function getWeather(latitude, longitude, callback) {  //天气信息
  var ak = "WjFhio84FVTSbFqAns28uCdC3n9jLpyc";//换成自己的ak
  var url = "https://api.map.baidu.com/telematics/v3/weather?location=" + longitude + "," + latitude + "&output=json&ak=" + ak; //接口请求和参数传递

  wx.request({
    url: url,
    success: function (res) {
      console.log("天气请求结果", res.data); //在打开应用即可看到
      callback(res.data);
    }

  });
}

function loadWeatherData(callback) {
  getLocation(function (success, latitude, longitude) {
    getWeather(latitude, longitude, function (weatherData) {
      callback(weatherData);
    });
  });
}

function loadWeatherData2(callback) {
  getWeather('26.908320', '118.985710', function (weatherData) {
    callback(weatherData);
  });
}

module.exports = {
  formatTime: formatTime,
  loadWeatherData: loadWeatherData,
  loadWeatherData2: loadWeatherData2
}
