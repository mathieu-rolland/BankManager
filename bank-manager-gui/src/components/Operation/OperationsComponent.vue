<template>

    <div class="component operations">

        <table class="table" v-if="operations.length > 0">
            <thead>
                <tr>
                    <th>Libellé</th>
                    <th>Montant</th>
                    <th>Category</th>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                    <Operation v-for="operation in operations"  v-bind:key="operation.id" :operation="operation" :categories="categories" v-on:operationschange="operationsChange"/>
            </tbody>
        </table>

        <CreateOperation :categories="categories" :callback="fetchData" />

    </div>

</template>

<script>

import Operation from '@/components/Operation/OperationComponent.vue'
import CreateOperation from '@/components/Operation/OperationCreateComponent.vue'

import axios from 'axios'

export default {
    
    name: 'OperationsComponents',

    components:{
        Operation, CreateOperation
    },

    data : function(){
        return {
            operations: [],
            categories: [],
        }
    },

    props: {
        parentOperations: undefined
    },

    mounted: function(){
        this.fetchData();
    },

    methods: {
        
        fetchData(){
            axios
                .get('http://' + process.env.VUE_APP_API_URL + '/category/list')
                .then( response => (this.categories = response.data) )
                .catch( error => (this.categories = error ));

            if( this.parentOperations ){
                this.operations = this.parentOperations;
            }else{

                axios  
                    .get('http://' + process.env.VUE_APP_API_URL + '/operations/list')
                    .then( reponse => (this.operations = reponse.data) )
                    .catch(function (error) {
                        console.log(error);
                    });
                this.$emit('operationschange' , this.operation );
            }
        },

        operationsChange(){
            this.fetchData();
            this.$emit( 'operationschange' );
        }
        
    },

    watch: {
        parentOperations: function(data){
            this.operations = data;
        }
    },

}
</script>

<style scoped>
    
    .component.operations {
        min-width: 400px;
    }


</style>