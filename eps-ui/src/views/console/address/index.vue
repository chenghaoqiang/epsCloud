<template>
  <div class="app-container">

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['console:address:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['console:address:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['console:address:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['console:address:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="addressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="索引" align="center" type="index" />
      <el-table-column label="发件人" align="center" prop="fullName" />
      <el-table-column label="联系方式" align="center" prop="phone" />
      <el-table-column label="店铺名称" align="center" prop="shopName" />
      <el-table-column label="店铺旺旺号" align="center" prop="shopWangwang" />
      <el-table-column label="发货地址" align="center" prop="fullShippingAddress" />
      <el-table-column label="是否默认" align="center" prop="hasDefault" :formatter="hasDefaultAddressFormat" />

      <el-table-column label="创建时间" align="center" prop="createTime" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['console:address:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['console:address:remove']"
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

    <!-- 添加或修改发货地址对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发件人" prop="fullName">
          <el-input v-model="form.fullName" placeholder="请输入发件人" />
        </el-form-item>
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="form.shopName" placeholder="请输入店铺名称" />
        </el-form-item>
        <el-form-item label="店铺旺旺号" prop="shopWangwang">
          <el-input v-model="form.shopWangwang" placeholder="请输入店铺旺旺号" />
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="省/市/区" prop="area">
        <el-cascader
                   size="large"
                   :options="options"
                   v-model="selectedOptions"
                   @change="handleChange"
                   placeholder="请选择省/市/区">
        </el-cascader>
        </el-form-item>
        <el-form-item label="详细地址" prop="addressLine">
          <el-input v-model="form.addressLine" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="是否默认" prop="hasDefault">
          <el-radio-group v-model="form.hasDefault">
            <el-radio
              v-for="dict in hasDefaultAddressOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listAddress, getAddress, delAddress, addAddress, updateAddress } from "@/api/console/address";
import { regionData, CodeToText, TextToCode } from 'element-china-area-data'

export default {
  name: "Address",
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
      showSearch: false,
      // 总条数
      total: 0,
      // 发货地址表格数据
      addressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否默认地址字典
      hasDefaultAddressOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fullName: null,
        shopName: null,
        shopWangwang: null,
        phone: null,
        province: null,
        city: null,
        area: null,
        addressLine: null,
        hasDelete: null,
        hasDefault: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fullName: [
          { required: true, message: "发件人不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "联系方式不能为空", trigger: "blur" },
          {pattern: /^1[3|5|7|8|9]\d{9}$/ , message: "请输入正确的号码格式", trigger: "blur"}
        ],
        area: [
          { required: true, message: "省/市/区不能为空", trigger: "blur" },
          {trigger: "blur"}
        ],
        addressLine: [
          { required: true, message: "详细地址不能为空", trigger: "blur" }
        ]
      },
      options: regionData,
      selectedOptions: []
    };
  },
  created() {
    this.getList();
    this.getDicts("eps_default_address").then(response => {
      this.hasDefaultAddressOptions = response.data;
    });
  },
  methods: {

    /** 查询发货地址列表 */
    getList() {
      this.loading = true;
      listAddress(this.queryParams).then(response => {
        this.addressList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 菜单状态字典翻译
    hasDefaultAddressFormat(row, column) {
      return this.selectDictLabel(this.hasDefaultAddressOptions, row.hasDefault);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        fullName: null,
        shopName: null,
        shopWangwang: null,
        phone: null,
        province: null,
        city: null,
        area: null,
        addressLine: null,
        hasDelete: null,
        hasDefault: null,
        createTime: null,
        updateTime: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加发货地址";
      this.selectedOptions = [];
      this.form.hasDefault = "1";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAddress(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发货地址";

        console.log(this.selectedOptions)
        const provinces = TextToCode[this.form.province];
        if(provinces == null) {
          this.selectedOptions = [];
          return;
        }
        const provinceCode = provinces['code'];
        console.log(provinceCode)
        const citys = provinces[this.form.city];
        if(citys == null) {
          this.selectedOptions = [provinceCode];
          return;
        }
        const cityCode = citys['code'];
        console.log(cityCode)
        const areas = citys[this.form.area];
        if(areas == null) {
          this.selectedOptions = [provinceCode,cityCode,null];
          console.log(this.selectedOptions)
          return;
        }
        const areaCode = areas['code'];
        this.selectedOptions = [provinceCode,cityCode,areaCode];
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAddress(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAddress(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除发货地址编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAddress(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('../console/address/export', {
        ...this.queryParams
      }, `发货地址.xlsx`)
    },
     handleChange (value) {
     this.form.province = CodeToText[value[0]];
     this.form.city = CodeToText[value[1]];
     this.form.area = CodeToText[value[2]];
     }
  }
};
</script>
