<template>

    <highcharts :options="chartOptions" ref="highchart"/>

</template>

<script>

import {Chart} from 'highcharts-vue'
import Highcharts from 'highcharts-vue'

export default {

    name: 'Historical View',
    props: {
        operations: Array,
    },
    mounted: function(){

    },
    data: function(){
        return {
            highchart: undefined,
        }
    },
    methods: function(){

    }


}
</script>