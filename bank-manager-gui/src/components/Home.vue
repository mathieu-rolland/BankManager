<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <HomePieChart :operations="debitOperations" :categories="categoryHashmap" titleProps="Débit"/>
    <HomePieChart :operations="creditOperations" :categories="categoryHashmap" titleProps="Crédit"/>
  </div>
</template>

<script>

import axios from 'axios'
import HomePieChart from '@/components/HomePieChart.vue'

export default {
  name: 'home',
  props: {
    msg: String
  },
  components:{
    HomePieChart
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
      console.log(this.categoryHashmap);
    }
  },

  mounted() {
    this.fetchData();
  },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
