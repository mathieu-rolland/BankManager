<template>

  <div class="hello">
    <h1>{{ msg }}</h1>

    <b-container class="row">

      <b-row>

        <b-col>
          <HomePieChart :operations="debitOperations" :categories="categoryHashmap" titleProps="Débit"/>
        </b-col>

        <b-col>
          <HomePieChart :operations="creditOperations" :categories="categoryHashmap" titleProps="Crédit"/>
        </b-col>

      </b-row>

      <b-row>

        <b-col>
          <Operations v-on:operationschange="fetchData"/>
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
      categoryHashmap: []
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
          .get('http://localhost:8080/category/list')
          .then( response => (this.categoryToHashmap(response.data)) )
          .catch(function (error) {
              console.log(error);
          });
    },

    fetchOperations: function(  ){
      axios  
        .get('http://localhost:8080/operations/list')
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
    }
  },

  mounted() {
    this.fetchData();
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .row{
    margin: auto;
  }
</style>
