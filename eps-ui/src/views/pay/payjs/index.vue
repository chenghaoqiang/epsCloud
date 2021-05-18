<template>
  <el-card class="box-card">
    <h4 style="align-content: center">充值金额</h4>
    <el-radio-group v-model="amountVal" @change="amountChange">
      <el-radio border :label="'' + 0.01">充值0.01</el-radio>
      <el-radio border :label="'' + 200">充值200</el-radio>
      <el-radio border :label="'' + 500">充值500</el-radio>
      <el-radio border :label="'' + 1000">充值1000</el-radio>
      <el-radio border :label="'' + 5000">充值5000</el-radio>
    </el-radio-group>
    <h4 style="margin-bottom: 10px"></h4>
    <div class="info-box">
      <img :src="qrurl"  alt="" />
    </div>
    <img src="@/assets/images/WePayInfo.png" width="280" alt="" />
  </el-card>
</template>

<script>
  import { wxPayjsNative, wxPayjsCheck } from "@/api/pay/payjs"
  export default {
    name: "qecode",
    data() {
      return {
        amountVal: "100",
        qrurl: "",
        payjsOrderId:"",
        timer: null,
        setOrderTimeOut:1800,
      };
    },

    created() {
     this.amountChange(this.amountVal);
      this.startLoop();
    },

    methods: {
      //充值金额
      amountChange(amountVal) {
        this.setOrderTimeOut = 1800;
        wxPayjsNative(amountVal).then(response => {
          if(response.return_code == 1 && response.return_msg == "SUCCESS") {
            this.qrurl = response.qrcode;
            this.payjsOrderId = response.payjs_order_id;
          }
        });
      },

      startLoop() {
        this.timer = setInterval(() => {
          this.isPaySuccess();
        }, 3000);
      },

      async isPaySuccess() {
        const payjsOrderId = this.payjsOrderId;
        console.log(payjsOrderId)
        if(payjsOrderId == null) {
          return;
        }
        wxPayjsCheck(payjsOrderId).then(response => {
          if(response.return_code == 1 && response.status == 1) {
            clearInterval(this.timer);
            //跳转订单
            this.$router.push('/pay/order/index');
          }
          this.setOrderTimeOut -= 5;
          if(this.setOrderTimeOut <= 0) {
            clearInterval(this.timer);
            // 跳转首页
            this.$router.push('/index');
          }
        });
      },

    },
  };
</script>

</style>
