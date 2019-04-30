<template>

    <highcharts :options="chartOptions" ref="highchart"/>

</template>

<script>

import {Chart} from 'highcharts-vue'
import Highcharts from 'highcharts-vue'

export default {

    name: 'Historical View',
    props: {
        creditOperations: Array,
        debitOperations: Array
    },
    mounted: function(){

    },
    watch: {
        creditOperations: function(){
            this.updateChart();
        },
        debitOperations: function(){
            this.updateChart();
        }
    },
    data: function(){
        return {
            highchart: undefined,
        }
    },
    methods: {

        updateChart: function(){

            var self = this;
            var creditSerie = {
                name: 'Crédit',
                data: []
            };
            var debitSerie = {
                name: 'Débit',
                data: []
            };

            self.creditOperations.forEach( function(el){
                
            });

        }

    }


}
</script>