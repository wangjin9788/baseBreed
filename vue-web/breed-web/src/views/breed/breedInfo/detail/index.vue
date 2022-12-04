<template xmlns="http://www.w3.org/1999/html"> 
  <div class="app-container">
    <el-card class="filter-container" shadow="never">
      <div>
        <i class="el-icon-search"></i>
        <span>筛选搜索</span>
        <el-button
          style="float:right"
          type="primary"
          @click="handleSearchList()"
          size="small">
          查询搜索
        </el-button>
        <el-button
          style="float:right;margin-right: 15px"
          @click="handleResetSearch()"
          size="small">
          重置
        </el-button>
      </div>
      <div style="margin-top: 15px">
        <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
          <el-form-item label="年：">
            <el-input v-model="listQuery.year" class="input-width" placeholder="年份" clearable></el-input>
          </el-form-item>
          <el-form-item label="月：">
            <el-input v-model="listQuery.month" class="input-width" placeholder="月份" clearable></el-input>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
    <el-card class="operate-container" shadow="never">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
      <el-button
        class="btn-add"
        @click="handleAddBreed()"
        size="mini">
        添加
      </el-button>
    </el-card>
    <div class="table-container">
      <el-table ref="revenueTable"
                :data="list"
                style="width: 100%;"
                v-loading="listLoading" border>
        <el-table-column label="编号" width="100" align="center">
          <template slot-scope="scope">{{ scope.row.bdId }}</template>
        </el-table-column>
        <el-table-column label="养殖信息编号" align="center">
          <template slot-scope="scope">{{ scope.row.bid }}</template>
        </el-table-column>
        <el-table-column label="检查时天气温度" align="center">
          <template slot-scope="scope">{{ scope.row.temperature }}℃</template>
        </el-table-column>
        <el-table-column label="检查时空气湿度" align="center">
          <template slot-scope="scope">{{ scope.row.humidity }}%</template>
        </el-table-column>
        <el-table-column label="养殖土壤温度" align="center">
          <template slot-scope="scope">{{ scope.row.soilTemperature }}℃</template>
        </el-table-column>
        <el-table-column label="养殖土壤湿度" align="center">
          <template slot-scope="scope">{{ scope.row.soilHumidity }}%</template>
        </el-table-column>
        <el-table-column label="ph值" align="center">
          <template slot-scope="scope">{{ scope.row.ph}}</template>
        </el-table-column>
        <el-table-column label="异常情况" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.abnormal >0"> 异常</span>
            <span v-else> 正常</span>
          </template>
        </el-table-column>
        <el-table-column label="异常说明" align="center">
          <template slot-scope="scope">{{ scope.row.description }}
          </template>
        </el-table-column>
        <el-table-column label="异常图片" align="center">
          <template slot-scope="scope">
            <img style="height:80px" v-image-preview :src="scope.row.imgUrl">
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center">
          <template slot-scope="scope">{{ scope.row.createTime }}</template>
        </el-table-column>
        <el-table-column label="操作" width="160" align="center">
          <template slot-scope="scope">
            <el-row>
              <el-button
                size="mini"
                type="text"
                @click="handleUpdate(scope.$index, scope.row)">编辑
              </el-button>
              <el-button size="mini"
                         type="text"
                         @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="pagination-container">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes,prev, pager, next,jumper"
        :current-page.sync="listQuery.pageNum"
        :page-size="listQuery.pageSize"
        :page-sizes="[5,10,15]"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>
<script>
import {fetchList, deleteBreedDetail} from '@/api/breedDetail';

const defaultListQuery = {
  pageNum: 1,
  pageSize: 5
};
export default {

  data() {
    return {
      listQuery: Object.assign({}, defaultListQuery),
      list: null,
      total: null,
      listLoading: false,
      dialogVisible: false,
      isEdit: false,

    }
  },
  created() {
    this.id = this.$route.query.bid;
    this.getList(this.id);
  },

  methods: {
    handleResetSearch() {

    },
    handleSearchList() {
      this.listQuery.pageNum = 1;
      this.getList(this.id);
    },
    handleSizeChange(val) {
      this.listQuery.pageNum = 1;
      this.listQuery.pageSize = val;

      this.getList(this.id);
    },
    handleCurrentChange(val) {
      this.listQuery.pageNum = val;
      this.getList(this.id);
    },
    handleAddBreed() {
      this.$router.push({path: '/breed/addBreedDetail', query: {bid: this.id}});
    },

    handleDelete(index, row) {
      this.$confirm('是否要删除该详情?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteBreedDetail(row.bdId).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.getList(this.id);
        });
      });
    },
    handleUpdate(index, row) {
      this.$router.push({path: '/breed/updateBreedDetail', query: {id: row.bdId,bid: this.id}});
    },
    /** 修改状态，并将数据进行总结 **/
    handleHiddenChange(index, row) {
      updateHidden(row.id, {hidden: row.hidden}).then(response => {
        this.$message({
          message: '修改成功',
          type: 'success',
          duration: 1000
        });
      });
    },
    getList(id) {

      this.listLoading = true;
      this.handleResetSearch();
      fetchList(id, this.listQuery).then(response => {
        this.listLoading = false;
        this.list = response.data;
        this.total = response.data.total;
      });

    }
  }
}
</script>
<style></style>


