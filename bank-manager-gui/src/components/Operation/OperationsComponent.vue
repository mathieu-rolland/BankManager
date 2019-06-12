<template>

    <div>

        <table class="table">
            <thead>
                <tr>
                    <th>Libellé</th>
                    <th>Montant</th>
                    <th>Category</th>
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
            operations: [
                {label: 'op1', amount:1},
                {label: 'op2', amount:2},
            ],
            categories: [],
        }
    },

    mounted: function(){
        this.fetchData();
    },

    methods: {
        
        fetchData(){
            axios
                .get('http://localhost:8080/category/list')
                .then( response => (this.categories = response.data) )
                .catch( error => (this.categories = error ));

            axios  
                .get('http://localhost:8080/operations/list')
                .then( reponse => (this.operations = reponse.data) )
                .catch(function (error) {
                    console.log(error);
                });
            this.$emit('operationschange' , this.operation );
        },

        operationsChange(){
            this.fetchData();
            this.$emit( 'operationschange' );
        }
        
    },

}
</script>