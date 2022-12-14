<template>
  <el-card >
    <el-form :model="cla"
             ref="claFrom"
             label-width="150px">
      <el-form-item label="操作">
        <div>
          <el-radio v-model="cla.oper" label="1" border>普通操作</el-radio>
          <el-radio v-model="cla.oper" label="2" border>治疗操作</el-radio>
        </div>
      </el-form-item>
      <el-row :gutter="20">
        <el-col :span="5">
          <el-form-item
            v-for="(medium, index) in cla.mediums"
            :label="'介质' + index"
            :key="medium.key"
            :prop="'domains.' + index + '.value'">
            <el-input v-model="medium.value"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item
            v-for="(domain, index) in cla.domains"
            :label="'千克' + index"
            :key="domain.key"
            :prop="'domains.' + index + '.value'">
            <el-input v-model="domain.value"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item
            v-for="(rate, index) in cla.rates"
            :label="'比例' + index"
            :key="rate.key"
            :prop="'rates.' + index + '.value'">
            <el-cascader :options="options" v-model="rate.value" clearable></el-cascader>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item
            v-for="(reusl, index) in cla.results"
            :label="'结果' + index"
            :key="reusl.key"
            :prop="'domains.' + index + '.value'">
            <el-input v-model="reusl.value"  readonly ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button @click="getResult(cla)">获得结果</el-button>
        <el-button @click="addDomain">新增</el-button>
        <el-button style="text-align: -moz-right" @click.prevent="removeDomain(domain)">删除</el-button>
        <el-button type="primary" @click="onSubmit('claFrom')">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { createNutritionCla} from '@/api/nutritionCla';

const defaultcla = {
  pageSize: 100,
  pageNum: 1,
  selectDay: '',
  domains: [{}],
  rates: [{}],
  results: [{}],
  mediums: [{}],
  oper:'1'
};
export default {
  name: "claDetail",
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      cla: Object.assign({}, defaultcla),
      options: [{value: '蛋白质', label: '蛋白质', children: [{value: 5, label: '5%'}]}, {
        value: '葡萄糖',
        label: '葡萄糖',
        children: [{value: 15, label: '15%'}]
      }]

    }
  },
  created() {
    this.cla.ffId = this.$route.query.id
  },
  methods: {
    removeDomain(item) {
      var index = this.cla.domains.indexOf(item)

      this.cla.domains.splice(index, 1)
      this.cla.rates.splice(index, 1)
      this.cla.results.splice(index, 1)
      this.cla.mediums.splice(index, 1)

    },
    addDomain() {
      this.cla.domains.push({
        value: '',
        key: Date.now()
      });
      this.cla.rates.push({
        value: '',
        key: Date.now()
      });
      this.cla.results.push({
        value: ''
      });
      this.cla.mediums.push({
        value: ''
      });
    },
    getSelectclaList() {
      fetchList(0, this.cla).then(response => {
        this.selectclaList = response.data;
      });

    },
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('是否提交数据', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            createNutritionCla(this.cla).then(response => {
                this.$refs[formName].resetFields();
                this.resetForm(formName);
                this.$message({
                  message: '提交成功',
                  type: 'success',
                  duration: 1000
                });

              cla: Object.assign({}, defaultcla)
              });

          });

        } else {
          this.$message({
            message: '验证失败',
            type: 'error',
            duration: 1000
          });
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.cla = Object.assign({}, defaultcla);
      this.getSelectclaList();
    },
    getResult(cla) {
      console.log(cla.domains)
      if (cla.domains != undefined && cla.rates != undefined ) {
        let res = 0;
        for (let i = 0; i < this.cla.domains.length; i++) {
          console.log(this.cla.domains[i].value)
          for (let j = 0; j < this.cla.rates[i].value.length; j++) {
            res = this.cla.domains[i].value * (this.cla.rates[i].value[1] / 100),
              this.cla.results[i] = res,
              this.$set(this.cla.results, i, {value: res.toFixed(1)})
          }
        }
        console.log(this.cla.results)
      }
    }
  }
}
</script>

