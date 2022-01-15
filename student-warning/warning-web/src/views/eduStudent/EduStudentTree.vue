<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row :gutter="24">
            <el-col :span="4">
                <el-input
                        placeholder="输入关键字进行过滤"
                        size="small"
                        v-model="treefilterText">
                </el-input>
                <div class="left-tree-box" :style="{height:tableHeight + 82 + 'px'}" >
                    <el-tree
                            ref="tree2"
                            class="filter-tree"
                            :data="classData"
                            :props="defaultProps"
                            :default-expand-all="false"
                            :highlight-current="true"
                            :expand-on-click-node="false"
                            :filter-node-method="filterNode"
                            @node-click="nodeClick">
                    </el-tree>
                </div>
            </el-col>

            <el-col :span="20">
                <el-row>
                    <el-col :span="24" class="filter-container">
                        <el-input
                                placeholder="学号过滤"
                                v-model="listQuery.stuCode"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="姓名过滤"
                                v-model="listQuery.stuName"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="曾用名过滤"
                                v-model="listQuery.stuNameOld"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="姓名拼音过滤"
                                v-model="listQuery.stuNamePy"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="性别过滤"
                                type="number"
                                v-model.number="listQuery.sex "
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="证件类型过滤"
                                v-model="listQuery.certificatType"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="证件号码过滤"
                                v-model="listQuery.certificateNo"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="国籍过滤"
                                v-model="listQuery.nationality"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="籍贯过滤"
                                v-model="listQuery.hometown"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="民族过滤"
                                v-model="listQuery.nation"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="婚姻过滤"
                                type="number"
                                v-model.number="listQuery.marriage "
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="生源地过滤"
                                v-model="listQuery.birthplace"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="出生日期过滤"
                                v-model="listQuery.birthday"
                                value-format="timestamp"
                                type="datetime"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="政治面貌过滤"
                                v-model="listQuery.politicalStatus"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="手机号过滤"
                                v-model="listQuery.phone"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="Email过滤"
                                v-model="listQuery.email"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="QQ过滤"
                                v-model="listQuery.qq"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="微信过滤"
                                v-model="listQuery.weChat"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="家庭电话过滤"
                                v-model="listQuery.homePhone"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="家庭地址过滤"
                                v-model="listQuery.homeAddress"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="邮政编码过滤"
                                v-model="listQuery.postCode"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="特长过滤"
                                v-model="listQuery.skill"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="既往病史过滤"
                                v-model="listQuery.sicknessHistory"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="班级id过滤"
                                type="number"
                                v-model.number="listQuery.classId "
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="考生号过滤"
                                v-model="listQuery.candidateNo"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="入学时间过滤"
                                v-model="listQuery.admissionTime"
                                value-format="timestamp"
                                type="datetime"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="入学方式过滤"
                                v-model="listQuery.admissionMethord"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="学籍状态过滤"
                                v-model="listQuery.stuStatus"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="培养层次过滤"
                                v-model="listQuery.trainingLevel"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="学制过滤"
                                v-model="listQuery.educationalSystem"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="辅导员过滤"
                                v-model="listQuery.counselor"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-input
                                placeholder="奖惩情况过滤"
                                v-model="listQuery.rewards"
                                size="small"
                                class="filter-item"
                                @keyup.enter.native="handleFilter"/>
                        <el-button
                            type="primary"
                            icon="el-icon-search"
                            size="small"
                            @click="handleFilter"
                        >搜索</el-button>
                        <el-button
                            icon="el-icon-circle-plus-outline"
                            size="small"
                            @click="showDialog()"
                        >新增</el-button>
                    </el-col>
                </el-row>
        <!-- 表格list -->
                <el-row>
                    <el-col :span="24" :gutter="24">
                        <el-table
                                v-loading="listLoading"
                                :key="tableKey"
                                :data="list"
                                element-loading-text="Loading"
                                border
                                fit
                                :height="tableHeight"
                                style="width: 100%;"
                                highlight-current-row>
                            <el-table-column align="center" label="序号" width="80">
                                <template slot-scope="scope">
                                    {{ scope.$index }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="学号"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.stuCode }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="姓名"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.stuName }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="曾用名"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.stuNameOld }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="姓名拼音"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.stuNamePy }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="性别"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.sex }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="证件类型"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.certificatType }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="证件号码"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.certificateNo }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="国籍"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.nationality }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="籍贯"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.hometown }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="民族"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.nation }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="婚姻"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.marriage }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="生源地"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.birthplace }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="出生日期"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.birthday  | timeFilter }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="政治面貌"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.politicalStatus }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="手机号"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.phone }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="Email"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.email }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="QQ"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.qq }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="微信"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.weChat }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="家庭电话"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.homePhone }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="家庭地址"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.homeAddress }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="邮政编码"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.postCode }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="特长"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.skill }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="既往病史"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.sicknessHistory }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="班级id"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.classId }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="考生号"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.candidateNo }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="入学时间"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.admissionTime  | timeFilter }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="入学方式"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.admissionMethord }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="学籍状态"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.stuStatus }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="培养层次"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.trainingLevel }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="学制"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.educationalSystem }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="辅导员"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.counselor }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="奖惩情况"
                                    show-overflow-tooltip
                                    style="width: 10%"
                                    align="center">
                                <template slot-scope="scope">
                                    {{ scope.row.rewards }}
                                </template>
                            </el-table-column>
                            <el-table-column
                                    label="操作"
                                    align="center"
                                    width="180"
                                    class-name="small-padding fixed-width">
                                <div slot-scope="scope" class="table-operate-box">
                                    <i class="zoeIconfont z_modifyEI_normal" @click="showDialog(scope.row)"></i>
                                    <i class="zoeIconfont z_delete_normal danger" @click="handleDelete(scope.row)"></i>
                                </div>
                            </el-table-column>
                        </el-table>

                        <pagination v-show="total>0" :total="total"
                            :page.sync="listQuery.current"
                            :limit.sync="listQuery.size"
                            layout="total, sizes, prev, pager, next"
                            @pagination="fetchData"/>
                    </el-col>
                </el-row>

            </el-col>

        </el-row>

        <handle-dialog ref="dlg" @refreshDataList="fetchData"></handle-dialog>
</div>
</template>

<script>
    import request from '@/utils/request';
    import HandleDialog from './EduStudentDialog'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
    export default {
        components: {
            HandleDialog,
            Pagination
        },
        filters: {
            statusFilter(status) {
                const statusMap = {
                    1: 'success',
                    2: 'gray',
                    0: 'danger'
                }
                return statusMap[status]
            },
            validFilter(status) {
                const statusMap = {
                    1: '有效',
                    0: '无效'
                }
                return statusMap[status]
            },
            timeFilter(time) {
                if (time) {
                    return new Date(time).Format('yyyy-MM-dd hh:mm:ss')
                } else {
                    return ''
                }
            },
        },
        data() {
            return {
                classData: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                treefilterText: '',
                currentClass: '',
                tableKey: 0,//表格key值
                list: null, //表格对象
                listLoading: true, //表格加载框
                total: 0, //分页总数
                tableHeight: window.innerHeight - 240, //表格高度
                listQuery: { //表格查询对象
                    current: 1,
                    size: 10,
                    query: '',
                    stuCode: null,
                    stuName: null,
                    stuNameOld: null,
                    stuNamePy: null,
                    sex: null,
                    certificatType: null,
                    certificateNo: null,
                    nationality: null,
                    hometown: null,
                    nation: null,
                    marriage: null,
                    birthplace: null,
                    birthday: null,
                    politicalStatus: null,
                    phone: null,
                    email: null,
                    qq: null,
                    weChat: null,
                    homePhone: null,
                    homeAddress: null,
                    postCode: null,
                    skill: null,
                    sicknessHistory: null,
                    classId: null,
                    candidateNo: null,
                    admissionTime: null,
                    admissionMethord: null,
                    stuStatus: null,
                    trainingLevel: null,
                    educationalSystem: null,
                    counselor: null,
                    rewards: null,
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl:this.GLOBAL.baseUrl +  '/student'
            }
        },
        watch: {
            treefilterText(val) {
                this.$refs.tree2.filter(val.toUpperCase());
            }
        },
        created() {
            this.fetchData()
        },
        methods: {
            /**
            * 模块类别过滤
            * */
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            /**
            * 获取模块类别树
            */
            getClassTree() {

            },

            /**
            * 设置模块类别点击事件
            **/
            nodeClick(data, data1, data2) {
                this.currentClass = data.id !== '##' ? data.id : '';
                this.fetchData();
            },
            /**
             * 获取表格数据
             */
            fetchData() {
                this.listLoading = true
              request({
                url: `${this.prefixUrl}/findListByPage`,
                method: 'get',
                params: this.listQuery
              }).then(res => {
                this.list = res.data.records
                this.total = res.data.total
                this.listLoading = false
              }).catch(error => {
                this.$message({
                  message: error,
                  type: 'error',
                  duration: 1500,
                  onClose: () => {
                  }
                })
              })
            },
            /**
             * 显示修改编辑框
             */
            showDialog(data) {
                this.$refs.dlg.init(data)
            },
            /**
             * 搜索过滤
             */
            handleFilter() {
                this.listQuery.current = 1
                this.fetchData()
            },
            /**
             * 删除操作
             */
            handleDelete(row) {
              request({
                url: `${this.prefixUrl}/delete/${row.id}`,
                method: 'get'
              }).then(res => {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.fetchData()
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
            },
        }
    }
</script>
