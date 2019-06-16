<template>

    <div class="create-operation-modal">

        <b-button v-b-modal="'collapse-create-operation'" variant="primary">Nouvelle opération</b-button>

        <b-modal id="collapse-create-operation" class="mt-2" ref="modal" @ok="createOperation" title="Créer une opération">
            <form method="post" id="category-create-form">
                <label for="label">Libellé</label>
                <b-form-input id="label" v-model="label" type="text" name="label" placeholder="Entrer le libellé" />
                <label for="amount">Montant</label>
                <b-form-input id="amount" v-model="amount" type="number" name="amount" placeholder="Entrer le montant" />
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

export default {

    name: 'CreateOperationComponent',

    props: {
        categories: Array,
        callback: Function
    },

    data: function(){
       return {
            label: "",
            amount: 0,
            selected_way: "",
            selected_category: ""
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
                    operationWay: this.selected_way
                })
                .then( this.callback )
                .catch(function (error) {
                    console.log(error);
                });

        }
    },

}
</script>