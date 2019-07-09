<template>

    <div class="component">
        <div class="chart" v-show="hasToBeDeplayed">
            <h2>{{titleProps}} - {{hasToBeDeplayed}}</h2>
            <div class="bank-chart">
                <highcharts :options="chartOptions" ref="highcharts"/>
            </div>
        </div>
        <div class="no-chart bank-chart" v-show="!hasToBeDeplayed">
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
            hasToBeDeplayed: true,
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
            var _self = this;
             _self.hasToBeDeplayed = true;
            if( this.operations && this.categories){
                var _self = this;
                var serie = {
                    name: this.titleProps,
                    data:[]
                };

                this.operations.forEach( el => {
                    _self.groupOperationByCategory( serie.data , el );
                });

                //Suppression des series déjà ajouter sinon bug sur ajout multiple de serie.
                if( this.$refs.highcharts && serie.data.length > 0){
                    var size = this.$refs.highcharts.chart.series.length;
                    for( var i = 0 ; i < size ; i++ ){
                        this.$refs.highcharts.chart.series[i].remove();   
                    }
                    this.$refs.highcharts.chart.addSeries( serie );
                    _self.hasToBeDeplayed = serie.data.length > 0;
                }else{
                    _self.hasToBeDeplayed = false;
                }
            }
        },

        groupOperationByCategory: function( operationsArray, operationToAdd ){
            var category = operationToAdd.category;
            var data = undefined;
            if( category ){
                this.hasToBeDeplayed = true;
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
    }

    img.empty{
        width: 50px;
    }

</style>