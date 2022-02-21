<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign table_chn = table.remarks>
<#if table.primaryKeyColumns??>
<#assign pkColumn = table.pkColumn>
</#if>
<template>
    <div class="app-container">
        <!-- 表头 查询与新增 -->
        <el-row>
            <el-col :span="24" class="filter-container">
                    <#list table.columns as column>
                    <#if !column.pk>
                    <#if column.columnNameLower != "validFlag">
                    <#if column.javaType == "Integer">
                    <el-input placeholder="${column.remarks!}过滤" type="number" v-model.number="listQuery.${column.columnNameLower}" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    <#elseif column.javaType == "Date">
                    <el-date-picker
                        v-model="listQuery.${column.columnNameLower}"
                        value-format="timestamp"
                        type="datetime"
                        placeholder="选择日期时间"
                        size="small"
                        class="filter-item">
                    </el-date-picker>
                    <#else>
                    <el-input placeholder="${column.remarks!}过滤" v-model="listQuery.${column.columnNameLower}" size="small" class="filter-item" @keyup.enter.native="handleFilter"/>
                    </#if>
                    <#else>
                    <#assign hasValidFlag = true>
                    <el-select v-model="listQuery.validFlag" placeholder="有效标志" clearable class="filter-item" size="small"
                               @change="handleFilter">
                        <el-option v-for="(value, key) in statusOptions" :key="key" :label="value+'('+key+')'" :value="key"/>
                    </el-select>
                    </#if>
                    </#if>
                    </#list>
                    <el-button
                            type="primary"
                            icon="el-icon-search"
                            size="small"
                            @click="handleFilter">搜索</el-button>
                    <el-button
                            icon="el-icon-circle-plus-outline"
                            size="small"
                            @click="showDialog()"
                    >新增</el-button>
            </el-col>
        </el-row>
        <!-- 表格list -->
        <el-row>
            <el-col :span="24">
                <el-table
                        v-loading="listLoading"
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
                    <#list table.columns as column>
                    <#if !column.pk>
                    <#if column.columnNameLower != "validFlag">
                    <el-table-column label="${column.remarks!}" show-overflow-tooltip style="width: 10%" align="center">
                        <template slot-scope="scope">
                            {{ scope.row.${column.columnNameLower} <#if column.javaType == 'Date'> | timeFilter </#if>}}
                        </template>
                    </el-table-column>
                    <#else>
                    <el-table-column class-name="status-col" label="有效标志" style="width: 10%" align="center">
                        <template slot-scope="scope">
                            <el-tag :type="scope.row.validFlag | statusFilter">{{ scope.row.validFlag | validFilter}}</el-tag>
                        </template>
                    </el-table-column>
                    </#if>
                    </#if>
                    </#list>
                    <el-table-column
                            label="操作"
                            align="center"
                            width="180"
                            class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                      <el-button size="small" @click="showDialog(scope.row)">编辑</el-button>
                      <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                    </el-table-column>
                </el-table>

                <pagination v-show="total>0" :total="total"
                    :page.sync="listQuery.current"
                    :limit.sync="listQuery.size"
                    layout="total, sizes, prev, pager, next"
                    @pagination="fetchData"/>
            </el-col>
        </el-row>
        <handle-dialog ref="dlg" @refreshDataList="fetchData"></handle-dialog>
</div>
</template>

<script>
    import request from '@/utils/request'
    import HandleDialog from './${className}Dialog'
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
                    return new Date(time).toLocaleDateString().split('/').join('-')
                } else {
                    return ''
                }
            },
        },
        data() {
            return {
                tableKey: 0,//表格key值
                list: null, //表格对象
                listLoading: true, //表格加载框
                total: 0, //分页总数
                tableHeight: window.innerHeight - 240, //表格高度
                listQuery: { //表格查询对象
                    current: 1,
                    size: 10,
                    query: '',
                    <#list table.columns as column>
                    <#if !column.pk>
                    <#if column.columnNameLower != "validFlag">
                    ${column.columnNameLower}: null,
                    <#else>
                    <#assign hasValidFlag = true>
                    validFlag: '1',
                    </#if>
                    </#if>
                    </#list>
                },
                statusOptions: { //有效无效下拉框
                    '1': '有效',
                    '0': '无效'
                },
                prefixUrl: this.GLOBAL.baseUrl + '<#if subpackage != "">/${subpackage}</#if>/${classNameLower}'
            }
        },
        created() {
            this.fetchData()
        },
        methods: {
            /**
             * 获取表格数据
             */
            fetchData() {
                this.listLoading = true
                request({
                    url: `${'$'}{this.prefixUrl}/findListByPage`,
                    method: 'get',
                    params: this.listQuery
                }).then(res => {
                  this.list = res.records
                  this.total = res.total
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
              this.$confirm('确定删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
              }).then(() => {
                  request({
                    url: `${'$'}{this.prefixUrl}/delete/${'$'}{row.${pkColumn.columnNameLower}}`,
                    method: 'get'
                  }).then(res => {
                    this.fetchData();
                    this.$message({
                      message: '操作成功',
                      type: 'success',
                      duration: 1500,
                    })
                  }).catch(error => {
                    this.$message({
                      message: error,
                      type: 'error',
                      duration: 1500,
                    })
                  })
              }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除',
                        duration: 1500,
                    });
              });
            },
        }
    }
</script>
