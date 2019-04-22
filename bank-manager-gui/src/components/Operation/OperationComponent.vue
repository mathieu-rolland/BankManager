<template>

    <tr v-if="operation && categories">
        <td>{{operation.label}}</td>
        <td>{{operation.amount}}</td>
        <td>
            <select v-model="selected_category" :options="categories" @change="changeSelected">
                <option v-for="category in categories" v-bind:value="category.id">
                    {{category.name}}/{{operation.category == category.id}}
                </option>
            </select>
        </td>
        <td>
            <img v-b-modal="'collapse-modify-category'" 
                variant="primary" 
                v-on:click="editOperation()" 
                src="../../assets/edit.png"
                class="edit button" />

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
            selected_category: ""
        }
    },
    mounted: function(){
        if( this.operation ){
            this.selected_category = this.operation.category;
        }
    },
    methods: {
        changeSelected: function(){
            this.operation.category = this.selected_category;
            this.editOperation();
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
            axios.post( "http://localhost:8080/operations/create" , this.operation
            )
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