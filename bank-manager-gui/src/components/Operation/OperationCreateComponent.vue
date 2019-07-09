<template>

    <div class="create-operation-modal">

        <b-button v-b-modal="'collapse-create-operation'" variant="primary">Nouvelle opération</b-button>

        <b-modal id="collapse-create-operation" class="mt-2" ref="modal" @ok="createOperation" title="Créer une opération">
            
            <form method="post" id="category-create-form">
            
                <label for="label">Libellé</label>
                <b-form-input id="label" v-model="label" type="text" name="label" placeholder="Entrer le libellé" />
            
                <label for="amount">Montant</label>
                <b-form-input id="amount" v-model="amount" type="number" name="amount" placeholder="Entrer le montant" />
                    
                <label for="way">Date</label>
                <datepicker :value="operationDate" :input-class="'datepicker-input'"></datepicker>

                <label for="way">Sens</label>
                <b-form-select name="way" v-model="selected_way">
                    <option value="DEBIT">Débit</option>
                    <option value="CREDIT">Crédit</option>
                </b-form-select>

                <label for="category">Catégorie</label>
                <b-form-select name="category" v-model="selected_category">
                    <option v-for="category in categories" v-bind:value="category.id">{{category.name}}</option>
                </b-form-select>

            </form>
            
        </b-modal>
    </div>

</template>

<script>

import axios from 'axios';
import Datepicker from 'vuejs-datepicker';
import moment from 'moment';

export default {

    name: 'CreateOperationComponent',

    components:{
        Datepicker
    },

    props: {
        categories: Array,
        callback: Function
    },

    data: function(){
       return {
            label: "",
            amount: 0,
            selected_way: "",
            selected_category: "",
            operationDate: new Date(),
       } 
    },

    methods: {


        createOperation: function(){
            var self = this;
            var selectedCategoryObject = undefined;
            this.categories.forEach( function(el){
                if( el.id == self.selected_category ){
                    selectedCategoryObject = el;
                    return;
                }
            });

            axios  
                .post('http://' + process.env.VUE_APP_API_URL + '/operations/create' , {
                    label: this.label,
                    amount: this.amount,
                    category: selectedCategoryObject,
                    operationWay: this.selected_way,
                    date: moment(this.operationDate).set({hour:0,minute:0,second:0,millisecond:0}).format()
                })
                .then( this.callback )
                .catch(function (error) {
                    console.log(error);
                });

        }
    },

}
</script>

<style>

    .datepicker-input{
        display: block;
        width: 100%;
        height: calc(1.5em + .75rem + 2px);
        padding: .375rem .75rem;
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
    }
</style>