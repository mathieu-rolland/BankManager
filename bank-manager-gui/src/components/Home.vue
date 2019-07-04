<template>

  <div class="content">

    <h1>{{ msg }}</h1>

    <div class="control month-nav text-center">

      <b-button-toolbar key-nav aria-label="Toolbar with button groups">
        
        <b-button-group class="mx-1">
         <b-button variant="primary" @click="previousMonth()">&lsaquo;</b-button>
        </b-button-group>

        <p class="current-month">
            {{ moment(current_month).format('MMMM YYYY') }}
        </p>

        <b-button-group class="mx-1">
          <b-button variant="primary" @click="nextMonth()">&rsaquo;</b-button>
        </b-button-group>
        
      </b-button-toolbar>

    </div>


    <b-container class="row">

      <b-row>

        <b-col class="chart-center-middle">
          <HomePieChart :operations="debitOperations" :categories="categoryHashmap" titleProps="Débit"/>
        </b-col>

        <b-col class="chart-center-middle">
          <HomePieChart :operations="creditOperations" :categories="categoryHashmap" titleProps="Crédit"/>
        </b-col>

      </b-row>

      <b-row>

        <b-col>
          <Operations v-on:operationschange="fetchData" :parentOperations="debitOperations"/>
        </b-col>

        <b-col>
          <OperationsUpload v-on:operationschange="fetchData"/>
        </b-col>

      </b-row>

    </b-container>

  </div>

</template>

<script>

import axios from 'axios'
import HomePieChart from '@/components/HomePieChart.vue'
import Operations from '@/components/Operation/OperationsComponent.vue'
import Historical from '@/components/HistoricalComponent.vue'
import OperationsUpload from '@/components/Operation/OperationsUploadComponent.vue'
import moment from 'moment'

export default {
  name: 'home',
  props: {
    msg: String
  },
  components:{
    HomePieChart, Operations, Historical, OperationsUpload
  },
  data: function(){
    return {
      message: "loading",
      debitOperations: [],
      creditOperations: [],
      categoryHashmap: [],
      current_month: moment(1, "DD")
    }
  },

  methods: {
    fetchData: function(){
      this.debitOperations = [];
      this.creditOperations = [];
      this.categoryHashmap = [];
        this.fetchCategories( );
        this.fetchOperations( );
    },
    fetchCategories: function( ){
        axios  
          .get('http://' + process.env.VUE_APP_API_URL + '/category/list')
          .then( response => (this.categoryToHashmap(response.data)) )
          .catch(function (error) {
              console.log(error);
          });
    },

    fetchOperations: function(  ){
      var _self = this;
      axios  
        .get('http://' + process.env.VUE_APP_API_URL + '/operations/month', {
          params: {
            //date: moment(_self.current_month).format('DD-MM-YYYYT00:00:00')
            date: moment(_self.current_month).format()
          }
        })
        .then( response => (this.sortOperations( response.data )) )
        .catch(function (error) {
            console.log(error);
        });
    },

    sortOperations: function( operationList ){
      operationList.forEach( el => {
                if( el.operationWay == 'DEBIT' ) this.debitOperations.push( el );
                else this.creditOperations.push( el );
            });
    },

    categoryToHashmap: function( categoriesList ){
      var map = [];
      categoriesList.forEach( el =>  {
        map[ el.id ] = el;
      }); 
      this.categoryHashmap = map;
    },

    nextMonth: function(){
      this.current_month = moment( this.current_month ).add( 1 , 'month' );
      this.fetchData();
    },
    moment,
    previousMonth: function(){
      this.current_month = moment( this.current_month ).add( -1 , 'month' );
      this.fetchData();
    }

  },

  mounted() {
    this.fetchData();
    console.log(process.env);
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .row{
    margin: auto;
  }
  
  .control{
    display: inline-block;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    padding-top: 15px;
    padding-bottom: 15px;
  }

  .mx-1{
    padding: 10px;
  }

  .current-month{
    margin: auto;
    font-size: larger;
    font-weight: bolder;
 }

  .col {
    min-width: 365px;
  }

  .chart-center-middle{
    margin: auto;
    vertical-align: middle;
  }

</style>
