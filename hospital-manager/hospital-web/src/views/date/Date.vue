<el-date-picker
  v-model="value"
  type="dateStart"
  placeholder="选择起运日期"
  :picker-options="pickerOptionsStart">
</el-date-picker>
<el-date-picker
  v-model="value"
  type="dateEnd"
  placeholder="选择送达日期"
  :picker-options="pickerOptionsEnd">
</el-date-picker>
<script>
export default {
  data (){
    return {
      dateStart:'', // 起运日期
      dateEnd:'', // 送达日期
      pickerOptionsStart: {
        disabledDate(time) {
          if (this.dateEnd) {
            var endTime = new Date(this.dateEnd).valueOf();
          }
          var startTime =  Date.now() - 8.64e7;
          return time && (time.valueOf() < startTime) || time.valueOf() > endTime
        }
      },
      pickerOptionsEnd: {
        disabledDate(time) {
          if (this.dateStart) {
            // 让用户可以选择开始结束同一天
            var startTime = new Date(this.dateStart).valueOf() - 8.64e7 + 1;
          } else {
            var startTime = Date.now() - 8.64e7;
          }
          return time && time.valueOf() < startTime
        }
      },
    }
  }
}
</script>
