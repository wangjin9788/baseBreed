<template>

  <el-card class="form-container" shadow="never">
    <el-form :model="breed"
             ref="breedFrom"
             label-width="150px">
      <el-form-item v-show="false"   label="id：" prop="breed" isEdit>
        <el-input v-model.trim="breed.bid"></el-input>
      </el-form-item>
      <el-form-item  label="栏位编号：" prop="breed" >
        <el-input v-model.trim="breed.number"></el-input>
      </el-form-item>
      <el-form-item label="投入时间：" prop="breed" >
        <el-date-picker  style="width: 100%"
          v-model="breed.inputTime"
          type="datetime"
          format="yyyy-MM-dd hh:mm"
          value-format="yyyy-MM-dd hh:mm"
          placeholder="选择日期时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item  label="投入重量：" prop="breed" isEdit>
        <el-input v-model.trim="breed.inputWeight"></el-input>
      </el-form-item>
      <el-form-item label="模型：" >
        <el-select v-model="breed.pid" placeholder="请选择模型" style="display: block">
          <el-option
            v-for="item in selectBreedLists"
            :key="item.pid"
            :label="item.evaluate"
            :value="item.pid">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('breedFrom')">提交</el-button>
        <el-button v-if="!isEdit" @click="resetForm('breedFrom')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import {
  fetchList,
  createBreed,
  updateBreed,
  getBreedInfo,
  getModelList
} from '@/api/breedInfo';


const defaultBreed = {
  status: 0
};
export default {
  name: "BreedDetail",
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      breed: Object.assign({}, defaultBreed),
      selectBreedLists: getModelList().then(response => {
        this.selectBreedLists = response.data;
      })
    }
  },
  created() {
    if (this.isEdit) {
      getBreedInfo(this.$route.query.id).then(response => {
        this.breed = response.data;
      });
    } else {
      this.breed = Object.assign({}, defaultBreed);
    }
    this.getSelectBreedList();
  },
  methods: {
    getSelectBreedList() {
      fetchList(0, {pageSize: 100, pageNum: 1}).then(response => {
        this.selectBreedList = response.data;
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
              updateBreed(this.breed).then(response => {
                this.$message({
                  message: '修改成功',
                  type: 'success',
                  duration: 1000
                });
                this.$router.back();
              });
            } else {
              createBreed(this.breed).then(response => {
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
      this.breed = Object.assign({}, defaultBreed);
      this.getSelectBreedList();
    },
  }
}
</script>

<style scoped>

</style>
