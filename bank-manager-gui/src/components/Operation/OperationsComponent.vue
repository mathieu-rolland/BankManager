<template>

    <div>
        <Operation name="test"/>

       <!-- <b-container class="bv-example-row" v-for="operation in operations">
            <Operation :operation="operation" :categories="categories" />
        </b-container>-->


        <table class="table">
            <thead>
                <tr>
                    <th>Libellé</th>
                    <th>Montant</th>
                    <th>Category</th>
                </tr>
            </thead>
            <tbody>
                    <Operation v-for="operation in operations" :operation="operation" :categories="categories" />
            </tbody>
        </table>
    </div>

</template>

<script>

import Operation from '@/components/Operation/OperationComponent.vue'
import axios from 'axios'

export default {
    
    name: 'OperationsComponents',

    components:{
        Operation
    },

    data : function(){
        return {
            operations: [
                {name: 'op1', amount:1},
                {name: 'op2', amount:2},
            ],
            categories: [],
        }
    },

    mounted: function(){
        this.fetchCategories();
    },

    methods: {
        
        fetchCategories(){
            axios
                .get('http://localhost:8080/category/list')
                .then( response => (this.categories = response.data) )
                .catch( error => (this.categories = error ));
        }
        
    },

}
</script>