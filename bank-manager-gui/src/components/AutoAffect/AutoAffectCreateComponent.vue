<template>
    <div>
        <form>
            <b-form-input id="regex" v-model="regex" type="text" name="regex" placeholder="Saisir une regex" />
            <b-form-select v-model="selected_category" :options="optionsCategory" v-on:change="updateSelected"></b-form-select>
            <b-button variant="primary" @click="create">Créer</b-button>
        </form >
    </div>
</template>

<script>

import axios from "axios"

export default {
    name:'AutoAffectCreateComponent',

    props: [
        "categories"
    ],

    data: function(){
        return {
            regex: "",
            optionsCategory: [],
            selected_category: "",
            selectedCategoryObject: undefined
        };
    },

    watch: {
        categories: function(){
            this.optionsCategory = [];
            this.formatCategoryArray();
        }
    },

    methods: {

        updateSelected: function(){
            var _self = this;
            this.categories.forEach( function(el){
                if( el.id == self.selected_category ){
                    _self.selectedCategoryObject = el;
                    return;
                }
            });
        },

        formatCategoryArray: function(){
            var _self = this;

            //remplissage de la liste des categories disponible
            if( this.categories ){
                _self.categories.forEach( function(el){
                    _self.optionsCategory.push({value:el.id,text:el.name,obj:el});
                });
            }
        },

        create: function(){
            var _self = this;
            var selectedCategoryObject = undefined;
            this.categories.forEach( function(el){
                if( el.id == _self.selected_category ){
                    selectedCategoryObject = el;
                    return;
                }
            });

            axios.post( 'http://localhost:8080/auto-affect/create' , 
                {
                    regex: _self.regex,
                    category: selectedCategoryObject
                })
                .then( function(response){
                    console.log("emit",response);
                    _self.$emit('autoaffectlistchange');
                })
                .catch(function(error){
                    console.log(error);
                });
        }

    },

}
</script>