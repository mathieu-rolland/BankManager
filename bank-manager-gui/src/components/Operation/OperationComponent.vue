<template>

    <tr v-if="operation && categories">
        <td>{{operation.label}}</td>
        <td>{{operation.amount}}</td>
        <td>
            <b-form-select v-model="selected" :options="optionsCategory" ></b-form-select>
        </td>
        <td>{{operation.operationWay}}</td>
        <td>
            <img v-b-modal.modal-operation
                variant="primary" 
                src="../../assets/edit.png"
                class="edit button"/>
            <img 
                class="delete button" alt="delete.png" src="../../assets/delete.png"
                v-on:click="deleteOperation()"/>
        </td>
    </tr>

</template>

<script>

import axios from 'axios'

export default {
    name: 'OperationComponent',
    props: [
        'operation',
        'categories',
        'callback'
    ],
    data: function(){
        return {
            selected_category: "",
            modal: undefined,
            optionsCategory: [],
            selected: undefined
        }
    },
    mounted: function(){
        if( this.operation ){
            this.selected_category = this.operation.category;
        }
        if( this.categories ){
            this.formatCategoryArray();
        }
    },
    watch: {
        categories: function(){
            this.optionsCategory = [];
            this.formatCategoryArray();
        }
    },
    methods: {
        formatCategoryArray: function(){
            var self = this;
            if( this.categories ){
                self.categories.forEach( function(el){
                    self.optionsCategory.push({value:el.id,text:el.name,obj:el});
                });
                if ( self.operation.category ){
                    self.selected = self.operation.category.id;
                }
            }
        },
        deleteOperation: function(){
            axios.delete( "http://localhost:8080/operations/delete" , {
                data: this.operation
            })
            .then( this.callback )
            .catch(function (error) {
                console.log(error);
            });
        },
        editOperation: function(){
            this.operation.category = this.selected_category;
            axios.post( "http://localhost:8080/operations/create" , this.operation )
            .then( this.callback )
            .catch(function (error) {
                console.log(error);
            });
        }
    },
}

</script>

<style>

.edit.button  {
    width: 20px;
    cursor: pointer;
    margin-left: 10px;
}

.delete.button {
    width: 20px;
    cursor: pointer;
    margin-left: 10px;
}

</style>