<template>

    <div class="component">
        <div class="chart" v-if="operations.length > 0">
            <h2>{{titleProps}}</h2>
            <div class="bank-chart">
                <highcharts :options="chartOptions" ref="highcharts"/>
            </div>
        </div>
        <div class="no-chart bank-chart" v-else>
            <p>
                <img class="empty" src="../assets/empty.png" /> <br />
                <span>Aucune operation de {{ titleProps.toLowerCase() }} disponible pour ce mois.</span>
            </p>
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
                    width: '530'
                },
                title: this.titleProps,
                series: []
                        }
        }
    },
    watch: {
        operations: function(){
            this.createSeries();
        },
        categories: function(){
            this.createSeries();
        }
    },
    mounted() {
       this.createSeries();
    },
    methods: {
        createSeries: function(){

            if( this.operations && this.categories && this.$refs.highcharts){
                var _self = this;
                var serie = {
                    name: this.titleProps,
                    data:[]
                };

                this.operations.forEach( el => {
                    _self.groupOperationByCategory( serie.data , el );
                });

                //Suppression des series déjà ajouter sinon bug sur ajout multiple de serie.
                var size = this.$refs.highcharts.chart.series.length;
                for( var i = 0 ; i < size ; i++ ){
                    this.$refs.highcharts.chart.series[i].remove();   
                }
                this.$refs.highcharts.chart.addSeries( serie );
    
            }
        },

        groupOperationByCategory: function( operationsArray, operationToAdd ){
            var category = operationToAdd.category;
            var data = undefined;
            if( category ){
                operationsArray.forEach( function(el){
                    if( el.name == category.name ){
                        data = el;
                        el.y = el.y + Math.abs(operationToAdd.amount);
                    }
                });
                if( !data ){
                    data = {
                        name: category.name,
                        y: Math.abs(operationToAdd.amount),
                        color: category.color
                    }
                    operationsArray.push( data );
                }
            }
        }
    },

}
</script>

<style>

    .bank-chart{
        width: 510px;
        margin: auto;
        text-align:center;
    }

    .no-chart{
        padding: 50px;
        width: 400px;
    }

    img.empty{
        width: 50px;
    }

</style>