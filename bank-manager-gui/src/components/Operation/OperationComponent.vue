<template>

    <tr v-if="operation && categories">
        <td>{{operation.label}}</td>
        <td>{{operation.amount}}</td>
        <td>
            <b-form-select v-bind:style="{ color: displayCategoryColor }" v-model="selected" :options="optionsCategory" @change="categoryChange" ></b-form-select>
        </td>
        <td>{{ displayDate(operation.date) }}</td>
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
import moment from 'moment'

export default {
    name: 'OperationComponent',

    components: {
        moment
    },

    props: [
        'operation',
        'categories'
    ],
    data: function(){
        return {
            selected_category: "",
            modal: undefined,
            optionsCategory: [],
            selected: null,
            displayCategoryColor: ""
        }
    },
    mounted: function(){
        if( this.operation ){
            this.preSelectCategoryForCurrentOperation();
        }
        if( this.categories ){
            this.formatCategoryArray();
        }
    },
    watch: {
        categories: function(){
            this.optionsCategory = [];
            this.formatCategoryArray();
        },
        operation: function(){
            this.preSelectCategoryForCurrentOperation();
        },

    },
    methods: {

        displayDate: function( date ){
            return moment(date).format( "DD/MM/YYYY" );
        },

        categoryChange: function( el ){
            var copy = JSON.parse(JSON.stringify(this.operation));
            copy.category = this.optionsCategory[this.selected - 1].obj;
            this.editOperation( copy );
        },

        formatCategoryArray: function(){
            var self = this;

            //remplissage de la liste des categories disponible
            if( this.categories ){
                self.categories.forEach( function(el){
                    self.optionsCategory.push({value:el.id,text:el.name,obj:el});
                });
            }
        },

        preSelectCategoryForCurrentOperation: function(){
            //selection de la categorie suivant l'operation
            if ( this.operation.category ){
                this.selected = this.operation.category.id;
                this.displayCategoryColor = this.operation.category.color;
            }
        },  

        deleteOperation: function(){
            axios.delete( "http://" + process.env.VUE_APP_API_URL + "/operations/delete" , {
                data: this.operation
            })
            .then( this.submited )
            .catch(function (error) {
                console.log(error);
            });
        },
        editOperation: function( operation ){
            axios.post( "http://" + process.env.VUE_APP_API_URL + "/operations/create" , operation )
            .then( this.submited )
            .catch(function (error) {
                console.log(error);
            });
        },
        submited: function(){
            this.$emit('operationschange' , this.operation );
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