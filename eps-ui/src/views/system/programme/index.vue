<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计划编号" prop="planNo">
        <el-input
          v-model="queryParams.planNo"
          placeholder="请输入计划编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划类型" prop="planType">
        <el-select v-model="queryParams.planType" placeholder="请选择计划类型" clearable size="small">
          <el-option
            v-for="dict in planTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="勘察编号" prop="surveyNo">
        <el-input
          v-model="queryParams.surveyNo"
          placeholder="请输入勘察编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:programme:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:programme:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:programme:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:programme:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="programmeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="标识" align="center" prop="objId" />
      <el-table-column label="计划编号" align="center" prop="planNo" />
      <el-table-column label="计划类型" align="center" prop="planType" :formatter="planTypeFormat" />
      <el-table-column label="计划版本" align="center" prop="planVersion" />
      <el-table-column label="勘察编号" align="center" prop="surveyNo" />
      <el-table-column label="检修范围" align="center" prop="repairRange" />
      <el-table-column label="检修开始时间" align="center" prop="repairStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.repairStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检修结束时间" align="center" prop="repairEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.repairEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="停电开始时间" align="center" prop="powercutStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.powercutStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="停电结束时间" align="center" prop="powercutEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.powercutEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="列行检修项目" align="center" prop="ordinaryRepairItems" />
      <el-table-column label="特殊性检修项目" align="center" prop="specialRepairItems" />
      <el-table-column label="技改项目" align="center" prop="technicalItems" />
      <el-table-column label="主要消缺项目" align="center" prop="defectTreatmentItems" />
      <el-table-column label="组织措施" align="center" prop="organizationMeasure" />
      <el-table-column label="安全措施" align="center" prop="securityMeasure" />
      <el-table-column label="技术措施" align="center" prop="technologyMeasure" />
      <el-table-column label="进度控制措施" align="center" prop="progressControlMeasure" />
      <el-table-column label="标准卡数量" align="center" prop="standardWorkNumber" />
      <el-table-column label="验收卡数量" align="center" prop="repairCheckNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:programme:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:programme:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改作业方案信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="计划编号" prop="planNo">
          <el-input v-model="form.planNo" placeholder="请输入计划编号" />
        </el-form-item>
        <el-form-item label="计划类型" prop="planType">
          <el-select v-model="form.planType" placeholder="请选择计划类型">
            <el-option
              v-for="dict in planTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="计划版本" prop="planVersion">
          <el-input v-model="form.planVersion" placeholder="请输入计划版本" />
        </el-form-item>
        <el-form-item label="勘察编号" prop="surveyNo">
          <el-input v-model="form.surveyNo" placeholder="请输入勘察编号" />
        </el-form-item>
        <el-form-item label="检修范围" prop="repairRange">
          <el-input v-model="form.repairRange" placeholder="请输入检修范围" />
        </el-form-item>
        <el-form-item label="检修开始时间" prop="repairStartTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.repairStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检修开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检修结束时间" prop="repairEndTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.repairEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检修结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="停电开始时间" prop="powercutStartTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.powercutStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择停电开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="停电结束时间" prop="powercutEndTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.powercutEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择停电结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="列行检修项目" prop="ordinaryRepairItems">
          <el-input v-model="form.ordinaryRepairItems" placeholder="请输入列行检修项目" />
        </el-form-item>
        <el-form-item label="特殊性检修项目" prop="specialRepairItems">
          <el-input v-model="form.specialRepairItems" placeholder="请输入特殊性检修项目" />
        </el-form-item>
        <el-form-item label="技改项目" prop="technicalItems">
          <el-input v-model="form.technicalItems" placeholder="请输入技改项目" />
        </el-form-item>
        <el-form-item label="主要消缺项目" prop="defectTreatmentItems">
          <el-input v-model="form.defectTreatmentItems" placeholder="请输入主要消缺项目" />
        </el-form-item>
        <el-form-item label="组织措施" prop="organizationMeasure">
          <el-input v-model="form.organizationMeasure" placeholder="请输入组织措施" />
        </el-form-item>
        <el-form-item label="安全措施" prop="securityMeasure">
          <el-input v-model="form.securityMeasure" placeholder="请输入安全措施" />
        </el-form-item>
        <el-form-item label="技术措施" prop="technologyMeasure">
          <el-input v-model="form.technologyMeasure" placeholder="请输入技术措施" />
        </el-form-item>
        <el-form-item label="进度控制措施" prop="progressControlMeasure">
          <el-input v-model="form.progressControlMeasure" placeholder="请输入进度控制措施" />
        </el-form-item>
        <el-form-item label="标准卡数量" prop="standardWorkNumber">
          <el-input v-model="form.standardWorkNumber" placeholder="请输入标准卡数量" />
        </el-form-item>
        <el-form-item label="验收卡数量" prop="repairCheckNumber">
          <el-input v-model="form.repairCheckNumber" placeholder="请输入验收卡数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProgramme, getProgramme, delProgramme, addProgramme, updateProgramme } from "@/api/system/programme";

export default {
  name: "Programme",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作业方案信息表格数据
      programmeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 计划类型字典
      planTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planNo: null,
        planType: null,
        surveyNo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.planTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询作业方案信息列表 */
    getList() {
      this.loading = true;
      listProgramme(this.queryParams).then(response => {
        this.programmeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 计划类型字典翻译
    planTypeFormat(row, column) {
      return this.selectDictLabel(this.planTypeOptions, row.planType);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        objId: null,
        planNo: null,
        planType: null,
        planVersion: null,
        surveyNo: null,
        repairRange: null,
        repairStartTime: null,
        repairEndTime: null,
        powercutStartTime: null,
        powercutEndTime: null,
        ordinaryRepairItems: null,
        specialRepairItems: null,
        technicalItems: null,
        defectTreatmentItems: null,
        organizationMeasure: null,
        securityMeasure: null,
        technologyMeasure: null,
        progressControlMeasure: null,
        standardWorkNumber: null,
        repairCheckNumber: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.objId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加作业方案信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const objId = row.objId || this.ids
      getProgramme(objId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作业方案信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.objId != null) {
            updateProgramme(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProgramme(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const objIds = row.objId || this.ids;
      this.$confirm('是否确认删除作业方案信息编号为"' + objIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProgramme(objIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/programme/export', {
        ...this.queryParams
      }, `system_programme.xlsx`)
    }
  }
};
</script>