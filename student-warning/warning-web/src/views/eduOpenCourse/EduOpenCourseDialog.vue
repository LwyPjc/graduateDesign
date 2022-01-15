<template>
  <!-- 编辑弹窗 -->
  <el-dialog
    :title="dialogFormData.id == null? '新增': '修改'"
    :visible.sync="dialogVisible"
    :close-on-click-modal="false"
    :before-close="handleClose"
    width="683px"
  >
    <el-form :model="dialogFormData"
             label-width="120px"
             :rules="dialogFormRules"
             :inline="true"
             size="small"
             ref="dialogForm"
             label-position="right">
      <el-form-item label="学期" label-width="105px" prop="term">
        <el-select v-model="dialogFormData.term" placeholder="请选择期" style="width: 95%">
          <el-option
            v-for="item in termSelect"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="班级" label-width="105px" prop="classId">
        <el-select v-model="dialogFormData.classId" placeholder="请选择班级" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.classes"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="课程" label-width="105px" prop="courseId">
        <el-select v-model="dialogFormData.courseId" placeholder="请选择课程" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.courses"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学分" label-width="105px" prop="score">
        <el-input placeholder="请输入学分" v-model="dialogFormData.score"/>
      </el-form-item>
      <el-form-item label="学时" label-width="105px" prop="hour">
        <el-input type="number" placeholder="请输入学时" v-model.number="dialogFormData.hour"/>
      </el-form-item>
      <el-form-item label="人数" label-width="105px" prop="number">
        <el-input type="number" placeholder="请输入人数" v-model.number="dialogFormData.number"/>
      </el-form-item>
      <el-form-item label="开始周" label-width="105px" prop="weekStart">
        <el-input type="number" placeholder="请输入开始周" v-model.number="dialogFormData.weekStart"/>
      </el-form-item>
      <el-form-item label="结束周" label-width="105px" prop="weekEnd">
        <el-input type="number" placeholder="请输入结束周" v-model.number="dialogFormData.weekEnd"/>
      </el-form-item>
      <el-form-item label="周次" label-width="105px" prop="weekTime">
        <el-input type="number" placeholder="请输入结束周(一周多节按英文逗号隔开)" v-model.number="dialogFormData.weekTime"/>
      </el-form-item>
      <el-form-item label="开始节" label-width="105px" prop="nodeStart">
        <el-input type="number" placeholder="请输入开始节" v-model.number="dialogFormData.nodeStart"/>
      </el-form-item>
      <el-form-item label="结束节" label-width="105px" prop="nodeEnd">
        <el-input type="number" placeholder="请输入结束节" v-model.number="dialogFormData.nodeEnd"/>
      </el-form-item>
      <el-form-item label="教师" label-width="105px" prop="teacherId">
        <el-select v-model="dialogFormData.teacherId" placeholder="请选择教师" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.teacheres"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="教室" label-width="105px" prop="classroomId">
        <el-select v-model="dialogFormData.classroomId" placeholder="请选择教室" style="width: 220%">
          <el-option
            v-for="item in dialogFormData.classrooms"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选课开始时间" label-width="105px" prop="chooseStart">
        <el-date-picker
          v-model="dialogFormData.chooseStart"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="选课结束时间" label-width="105px" prop="chooseEnd">
        <el-date-picker
          v-model="dialogFormData.chooseEnd"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择结束时间">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button size="small" @click.native="dialogVisible = false">取消</el-button>
      <el-button
        type="primary"
        :loading="dialogLoading"
        size="small"
        @click.native="dialogSubmit"
      >
        提交
      </el-button>
    </div>
  </el-dialog>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "EduOpenCourseAddOrUpdate",
        data() {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/openCourse',
                dialogFormData: {
                    id: '',
                    term: '',
                    college: '',
                    classId: '',
                    courseId: '',
                    score: '',
                    hour: '',
                    number: '',
                    weekStart: '',
                    weekEnd: '',
                    weekTime:'',
                    nodeStart: '',
                    nodeEnd: '',
                    teacherId: '',
                    classroomId: '',
                    chooseStart:'',
                    chooseEnd:''
                },
                termSelect: ["202101", "202102", "202201", "202202"],
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    term: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    classId: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    courseId: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    score: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    hour: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    number: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    weekStart: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    weekEnd: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    nodeStart: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    nodeEnd: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    teacherId: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    classroomId: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    weekTime: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                }
            }
        },
        computed: {
            validSwitch: {
                // getter
                get: function () {
                    return this.dialogFormData.validFlag === '1'
                },
                // setter
                set: function (newValue) {
                    if (newValue) {
                        this.dialogFormData.validFlag = '1'
                    } else {
                        this.dialogFormData.validFlag = '0'
                    }
                }
            }
        },
        methods: {
            init(row) {
                this.resetModel()
                if (row) {
                    this.dialogFormData = Object.assign({}, row);
                }
                this.dialogVisible = true
            },
            resetModel() {
                this.dialogFormData = {
                    id: '',
                    term: '',
                    college: '',
                    classId: '',
                    courseId: '',
                    score: '',
                    hour: '',
                    number: '',
                    weekStart: '',
                    weekEnd: '',
                    weekTime: '',
                    nodeStart: '',
                    nodeEnd: '',
                    teacherId: '',
                    classroomId: '',
                    chooseStart:'',
                    chooseEnd:''
                }

            },

            /**
             * 关闭对话框操作
             */
            handleClose(done) {
                this.dialogVisible = false
            },
            dialogSubmit() {
                this.$refs.dialogForm.validate((valid) => {
                    var that = this;
                    if (!valid) {
                        return false
                    }
                    const apiName = `${!this.dialogFormData.id ? 'save' : 'edit'}`
                    this.$delete(that.dialogFormData, "classes");
                    this.$delete(that.dialogFormData, "courses");
                    this.$delete(that.dialogFormData, "teacheres");
                    this.$delete(that.dialogFormData, "classrooms");

                    request({
                        url: `${this.prefixUrl}/${apiName}`,
                        method: 'post',
                        params: this.dialogFormData
                    }).then(res => {
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                            duration: 1500,
                            onClose: () => {
                                this.dialogVisible = false
                                this.$emit('refreshDataList')
                            }
                        })
                    }).catch(error => {
                        this.$message({
                            message: error,
                            type: 'error',
                            duration: 1500,
                            onClose: () => {
                            }
                        })
                    })
                })
            }
        }
    }
</script>

<style scoped>

</style>
