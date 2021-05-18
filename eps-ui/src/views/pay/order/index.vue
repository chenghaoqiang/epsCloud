<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="金额" prop="totalFee">
        <el-input
          v-model="queryParams.totalFee"
          placeholder="请输入金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信单号" prop="transactionId">
        <el-input
          v-model="queryParams.transactionId"
          placeholder="请输入微信订单号"
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
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['console:order:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column label="索引" align="center" type="index" />
      <el-table-column label="订单号" align="center" prop="payjsOrderId" />
      <el-table-column label="微信订单号" align="center" prop="transactionId" />
      <el-table-column label="金额" align="center" prop="totalFee" />
      <el-table-column label="支付状态" align="center" prop="status" :formatter="rechargeStatusFormat" />
      <el-table-column label="支付成功时间" align="center" prop="paidTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paidTime) }}</span>
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
  </div>
</template>

<script>
import { listOrder} from "@/api/pay/order";

export default {
  name: "Order",
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
      // 发货地址表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 充值状态字典
      rechargeStatusOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        returnCode: null,
        totalFee: null,
        outTradeNo: null,
        payjsOrderId: null,
        transactionId: null,
        timeEnd: null,
        userId: null,
      },
      // 表单参数
      form: {},
    };
  },
  created() {
    this.getList();
    this.getDicts("eps_recharge_status").then(response => {
      this.rechargeStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询发货地址列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        returnCode: null,
        totalFee: null,
        outTradeNo: null,
        payjsOrderId: null,
        transactionId: null,
        timeEnd: null,
        userId: null,
        createTime: null
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
    /** 导出按钮操作 */
    handleExport() {
      this.download('../pay/order/export', {
        ...this.queryParams
      }, `console_order.xlsx`)
    },
    // 充值状态字典翻译
    rechargeStatusFormat(row, column) {
      return this.selectDictLabel(this.rechargeStatusOptions, row.status);
    },
  }
};
</script>
