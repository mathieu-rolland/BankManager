<template>

    <div class="chart">
        <h2>{{titleProps}}</h2>
        <div class="bank-chart">
            <highcharts :options="chartOptions" ref="highcharts"/>
        </div>
    </div>

</template>

<script>

import {Chart} from 'highcharts-vue'
import Highcharts from 'highcharts-vue'
export default {
    
    name:"pieChartComponent",
    components:{
        highcharts: Chart
    },
    props:{
        operations: Array,
        titleProps: String,
        categories: Array
    },
    data: function(){
        return {
            chart: undefined,
            chartOptions: {
                chart: {
                    type: "pie",
                },
                title: this.titleProps,
                series: []
                        }
        }
    },
    watch: {
        operations: function(){
            console.log("operations changed");
            this.createSeries();
        },
        categories: function(){
            console.log("categories changed");
            this.createSeries();
        }
    },
    mounted() {
       this.createSeries();
    },
    methods: {
        createSeries: function(){
            if( this.operations && this.categories ){

                var serie = {
                    name: this.titleProps,
                    data:[]
                };
                
                this.operations.forEach( el => {
                    console.log(this.categories);
                    var opCategory = this.categories[ el.category ];
                    if( opCategory == undefined ){
                        console.log("category undefined", opCategory);
                        opCategory = {
                            name : 'undefined',
                            color : 'RED'
                        };
                    }
                    serie.data.push( { 
                        name: opCategory.name,
                        y: el.amount,
                        color: opCategory.color
                    } );
                });
                var size = this.$refs.highcharts.chart.series.length;
                for( var i = 0 ; i < size ; i++ ){
                    this.$refs.highcharts.chart.series[i].remove();   
                }
                this.$refs.highcharts.chart.addSeries( serie );
                console.log(serie);
                
            }
        }
    },


}
</script>

<style>

    .bank-chart{
        /*width: 500px;
        margin: auto;
        text-align:center;*/
    }

</style>