<template>
<!--  <div style="width: 100px;height: 20px;"></div>-->
<!--  <div>-->
    <div style="width: 50%;height: 800px;padding-top: 20px" id="main1"/>
    <div style="width: 50%;height: 800px;padding-top: 20px" id="main"/>
<!--  </div>-->
</template>

<script>
import * as echarts from 'echarts'
import request from "../../utils/request";

export default {
  name: "echarts",
  props: ['user'],
  data() {
    return {
      tableData: [],
    }
  },

  mounted() {
    this.echartsInit()
  },
  methods: {
    echartsInit() {
      request.get("/show/showall").then(res => {

        this.tableData = res.data
        echarts.init(document.getElementById('main1')).setOption({
          title: {
            text: '酒店各房型总床数',
            left: 'center',
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '10%',
            left: 'center'
          },

          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'left'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 15,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [
                {value: this.tableData[0], name: '优享大床房'},
                {value: this.tableData[1], name: '优享双床房'},
                {value: this.tableData[2], name: '舒适大床房'},
                {value: this.tableData[3], name: '舒适双床房'},
                {value: this.tableData[4], name: '轻享大床房'},
                {value: this.tableData[5], name: '轻享套房'},
                {value: this.tableData[6], name: '舒适套房'}
              ],
            }
          ]
        })


      })
      request.get("/show").then(res => {

        this.tableData = res.data
        echarts.init(document.getElementById('main')).setOption({
          title: {
            text: '目前各房型剩余床数',
            left: 'center'
          },
          tooltip: {
            trigger: 'item'
          },
          legend: {
            top: '10%',
            left: 'center'
          },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: ['40%', '70%'],
              avoidLabelOverlap: false,
              label: {
                show: false,
                position: 'left'
              },
              emphasis: {
                label: {
                  show: true,
                  fontSize: 15,
                  fontWeight: 'bold'
                }
              },
              labelLine: {
                show: false
              },
              data: [
                {value: this.tableData[0], name: '优享大床房'},
                {value: this.tableData[1], name: '优享双床房'},
                {value: this.tableData[2], name: '舒适大床房'},
                {value: this.tableData[3], name: '舒适双床房'},
                {value: this.tableData[4], name: '轻享大床房'},
                {value: this.tableData[5], name: '轻享套房'},
                {value: this.tableData[6], name: '舒适套房'}
              ],
            }
          ]
        })


      })


    }
  }
}
</script>