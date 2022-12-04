<template>
  <div class="app-container">
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>添加数据</span>
    </el-card>
    <el-card class="form-container" shadow="never">
      <el-form :model="detail"
               ref="detailFrom"
               label-width="150px">
        <el-form-item v-show="isEdit" style="width : 60%" label="养殖详情编号：" prop="detail" isEdit>
          <el-input v-model="detail.bdId" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item v-show="false" style="width : 60%" label="养殖信息编号：" prop="detail">
          <el-input v-model="detail.bid" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item style="width : 60%" label="养殖箱土壤温度：" prop="detail" isEdit>
          <el-input v-model="detail.soilTemperature"></el-input>
        </el-form-item>

        <el-form-item style="width : 60%" label="养殖箱土壤湿度：" prop="detail" isEdit>
          <el-input v-model="detail.soilHumidity"></el-input>
        </el-form-item>
        <el-form-item style="width : 60%" label="ph值：" prop="detail" isEdit>
          <el-input v-model="detail.ph"></el-input>
        </el-form-item>
        <el-form-item label="异常状态：">
          <el-select v-model="detail.abnormal" placeholder="请选择状态">
            <el-option
              v-for="item in stateList"
              :key="item.abnormal"
              :label="item.assess"
              :value="item.abnormal">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="width : 60%" label="异常说明：" prop="detail" isEdit>
          <el-input v-model="detail.description"></el-input>
        </el-form-item>
        <el-form-item label="检查图片：">
          <template slot-scope="scope">
            <single-upload v-model="detail.imgUrl"></single-upload>
          </template>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit('detailFrom')">提交</el-button>
          <el-button v-if="!isEdit" @click="resetForm('detailFrom')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import {fetchList, createBreedDetail, updateBreedDetail, getBreedDetailInfo} from '@/api/breedDetail';
import SingleUpload from '@/components/Upload/singleUpload';

const defaultDetail = {
  bdId: 0,
  bid: 0,
  img: '',
  status: 0,
  abnormal: 0
};
export default {
  name: "detail",
  components: {SingleUpload},
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }

  },
  data() {
    return {
      detail: Object.assign({}, defaultDetail),
      stateList: [{abnormal: 0, assess: '正常'}, {abnormal: 1, assess: '异常'}],
    }
  },
  created() {

    if (this.isEdit) {
      getBreedDetailInfo(this.$route.query.id).then(response => {
        this.detail = response.data;
      });
    } else {

      this.detail = Object.assign({}, defaultDetail);
      this.detail.bid = this.$route.query.bid;
    }

    this.getSelectDetailList(this.detail.bid);
  },
  methods: {
    getSelectDetailList(fid) {
      fetchList(fid, {pageSize: 100, pageNum: 1}).then(response => {
        this.selectDetailList = response.data;
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
            if (this.isEdit) {
              updateBreedDetail(this.detail).then(response => {
                this.$message({
                  message: '修改成功',
                  type: 'success',
                  duration: 1000
                });
                this.$router.back();
              });
            } else {
              createBreedDetail(this.detail).then(response => {
                this.$refs[formName].resetFields();
                this.resetForm(formName);
                this.$message({
                  message: '提交成功',
                  type: 'success',
                  duration: 1000
                });
                this.$router.back();
              });
            }
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
      this.detail = Object.assign({}, defaultDetail);
      this.getSelectDetailList(this.detail.bid);
    },
  }
}
</script>

<style scoped>

</style>
