<template>
  <!-- 编辑弹窗 -->
  <el-dialog
    :title="dialogFormData.id == ''? '新增': '修改'"
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
      <el-form-item label="朝代" label-width="105px" prop="dynasty">
        <el-input placeholder="请输入朝代" v-model="dialogFormData.dynasty"/>
      </el-form-item>
      <el-form-item label="名称" label-width="105px" prop="name">
        <el-input placeholder="请输入名称" v-model="dialogFormData.name"/>
      </el-form-item>
  <!--    <el-form-item label="图片地址" label-width="105px" prop="photoUrl">
        <el-input placeholder="请输入图片地址" v-model="dialogFormData.photoUrl"/>
      </el-form-item>-->
      <el-form-item label="简介" label-width="105px" prop="produce">
        <el-input style="width: 520px"
          type="textarea"
          :rows="3"
          placeholder="请输入简介"
          v-model="dialogFormData.produce">
        </el-input>
      </el-form-item>
      <el-form-item label="种类" label-width="105px" prop="type">
        <el-select v-model="dialogFormData.type" placeholder="请选择类别"  style="width: 93%">
          <el-option
            v-for="item in dialogFormData.options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <!--            <el-input type="number" placeholder="请输入种类" v-model.number="dialogFormData.type"/>-->
      </el-form-item>
      <el-col style="padding-left: 4%">
        <el-form-item label="图片地址" label-width="105px" prop="photoUrl">
          <el-upload
            class="upload-demo"
            ref="upload"
            :action="uploadPath"
            :on-remove="handleRemove"
            :on-exceed="handleExceed"
            :on-success="handleSucess"
            :on-error="handleError"
            :file-list="fileList"
            :limit="1"
            :data="dialogFormData"
            :before-upload="handleUploadBefore"
            :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取图片</el-button>
            <!--          <el-button style="margin-left: 10px;" size="small" type="success" @click="dialogSubmit">上传到服务器</el-button>-->
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
          </el-upload>
        </el-form-item>
      </el-col>
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
        name: "ZtGoodsAddOrUpdate",
        data() {
            return {
                visible: false,
                prefixUrl: this.GLOBAL.baseUrl + '/ztGoods',
                options: [],
                dialogFormData: {
                    id: '',
                    dynasty: '',
                    name: '',
                    photoUrl: '',
                    produce: '',
                    type: '',
                },
                fileList:[],
                uploadPath: this.GLOBAL.baseUrl + "ztGoods/upload",
                dialogVisible: false,
                dialogLoading: false,
                dialogFormRules: {
                    dynasty: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    name: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    produce: [
                        {required: true, message: '参数不能为空', trigger: 'blur'}
                    ],
                    type: [
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
                    this.$delete(row,"price");
                    this.dialogFormData = Object.assign({}, row);
                }
                this.dialogVisible = true
            },
            resetModel() {
                this.dialogFormData = {
                    id: '',
                    dynasty: '',
                    name: '',
                    photoUrl: '',
                    produce: '',
                    type: '',
                }

            },
            /**
             * 处理文件移除事件
             */
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            /**
             * 处理文件移除事件
             */
            handleUploadBefore(file) {
                console.log(file);
            },
            /**
             * 上传文件超出限制的事件
             */
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 1 个文件`);
            },
            /**
             *  处理文件上传成功后的事件
             */
            handleSucess(files, fileList) {
                this.$message({
                    message: '操作成功',
                    type: 'success',
                    duration: 1500,
                    onClose: () => {
                        this.dialogVisible = false
                        this.$emit('refreshDataList')
                    }
                })
                this.$refs.upload.clearFiles();
            },
            /**
             * 处理文件上传失败的事件
             */
            handleError(files, fileList) {
                this.$message({
                    message: "操作失败",
                    type: 'error',
                    duration: 1500,
                    onClose: () => {
                    }
                })
            },
            /**
             * 文件删除前的事件
             */
            beforeRemove(file, fileList) {
                return this.$confirm(`确定移除 ${file.name}？`);
            },
            /**
             * 关闭对话框操作
             */
            handleClose(done) {
                this.dialogVisible = false
            },
            dialogSubmit() {
                var that = this;
                var isFileListEmpty = that.$refs.upload.uploadFiles.length === 0;
                //管理员上传的文物直接审核通过
                this.$refs.dialogForm['status'] = 1;
                this.$refs.dialogForm.validate((valid) => {
                    if (!valid) {
                        return false
                    }
                    if(!isFileListEmpty){
                        that.$refs.upload.submit();
                    }else {
                        const apiName = `${!this.dialogFormData.id ? 'save' : 'edit'}`;
                        this.$delete(that.dialogFormData,"options");
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
                    }

                })
            }
        }
    }
</script>

<style scoped>

</style>
