const apiUrl = "http://localhost:8083/";
// const apiUrl = "http://192.168.0.102:8083/";
const wsUrl = "ws://localhost:8083/ws";


const type = {
  user: "UserManager/",
  data: "Information/",
  MZ: "OutPatient/",
  YJ: "Hospitalization/",
  MT: "MedicalTechnology/"
}


//获取openid
const getOpenidUrl = function () {
  return 'https://api.weixin.qq.com/sns/jscode2session';
}

//获取个人信息
const getPensorInfoUrl = function () {
  return apiUrl + 'userInfo/';
}
//首次登录保存用户信息
const saveUserInfo = function () {
  return apiUrl + 'userInfo/save';
}
//更新用户信息
const updateUserInfo = function () {
  return apiUrl + 'userInfo/edit'
}
//添加反馈信息
const saveFeedBackInfo = function () {
  return apiUrl + 'feedbackInfo/save'
}
//获取医院的简介
const getHospitalInfo = function () {
  return apiUrl + 'hospitalInfo/getInfo';
}
const checkIdCardIsExist = function () {
  return apiUrl + 'userInfo/getByIdCard/'
}
const getDepartmentInfo = function () {
  return apiUrl + 'department/findAll'
}
const getDepartmentInfoById = function () {
  return apiUrl + 'department/'
}
const getdoctorListByDptId = function () {
  return apiUrl + 'doctorInfo/getByDptId/'
}
const saveRegisterInfo = function () {
  return apiUrl + 'aptRecord/save'
}
//收藏功能
const saveCollect = function () {
  return apiUrl + "collectInfo/save";
}
//我的收藏
const getCollectInfos = function(){
  return apiUrl+'collectInfo/findByOpenid';
}
const getSingleCollectInfo = function(){
  return apiUrl+'collectInfo/findByDoubleIds'
}
//
const getHistoryChatInfo = function () {
  return apiUrl + "chatInfo/findByDoubleIds"
}
const connectWs = function(){
  return wsUrl;
}
const updateCollectInfo = function(){
  return apiUrl+'collectInfo/edit'
}
//my contact 
const getMyContact = function(){
  return apiUrl+'doctorInfo/findByOpenid'
}
// todo
module.exports = {
  getPensorInfoUrl: getPensorInfoUrl,
  saveUserInfo: saveUserInfo,
  updateUserInfo: updateUserInfo,
  saveFeedBackInfo: saveFeedBackInfo,
  getHospitalInfo: getHospitalInfo,
  checkIdCardIsExist: checkIdCardIsExist,
  getDepartmentInfo: getDepartmentInfo,
  getDepartmentInfoById: getDepartmentInfoById,
  getdoctorListByDptId: getdoctorListByDptId,
  saveRegisterInfo: saveRegisterInfo,
  saveCollect: saveCollect,
  getHistoryChatInfo: getHistoryChatInfo,
  connectWs:connectWs,
  getCollectInfos:getCollectInfos,
  getSingleCollectInfo:getSingleCollectInfo,
  updateCollectInfo:updateCollectInfo,
  getMyContact:getMyContact,
  getOpenidUrl: getOpenidUrl,

  
}