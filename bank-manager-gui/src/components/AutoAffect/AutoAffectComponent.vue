<template>
    <b-row class="auto-affect element" v-if="autoAffect">
        <b-col> {{autoAffect.regex}} </b-col>
        <b-col> {{autoAffect.category.name}} </b-col>
        <b-col>
            <img v-b-modal.modal-operation
                variant="primary" 
                src="../../assets/edit.png"
                class="edit button"/>
            <img 
                class="delete button" alt="delete.png" src="../../assets/delete.png"
                v-on:click="deleteOperation()"/>
        </b-col>
    </b-row>
</template>

<script>

import axios from "axios"

export default {
    name: 'AutoAffect',
    props: [
            'autoAffect',
    ],

    methods: {
        deleteOperation: function(){
            var _self = this;
            axios.delete( "http://" + process.env.VUE_APP_API_URL + "/auto-affect/delete" , {
                data: this.autoAffect
            })
            .then( function(){
                _self.$emit('autoaffectdeleted')
            } )
            .catch(function (error) {
                console.log(error);
            });
        },
    },

}
</script>