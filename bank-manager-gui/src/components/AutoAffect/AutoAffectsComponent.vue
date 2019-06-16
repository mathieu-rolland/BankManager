<template>
    
    <div>
        <div class="auto-affect list">
            <p>Auto affects</p>  
            <AutoAffectComponent v-for="(autoAffect, i) in autoAffectList" :key="i" :autoAffect="autoAffect" :categories="categories" v-on:autoaffectdeleted="refreshAutoAffect"/>
        </div>

        <div class="auto-affect create">
            <AutoAffectCreateComponent :categories="categories" v-on:autoaffectlistchange="refreshAutoAffect"/>
        </div>
    
    </div>

</template>

<script>

import AutoAffectComponent from '@/components/AutoAffect/AutoAffectComponent'
import AutoAffectCreateComponent from '@/components/AutoAffect/AutoAffectCreateComponent'

import axios from "axios"

export default {
    name: 'AutoAffectsComponent',

    components: {
        AutoAffectComponent, AutoAffectCreateComponent
    },

    data: function(){
        return {
            autoAffectList: [],
            categories: []
        }
    },

    mounted: function(){
        var _self = this;
        _self.fetchAutoAffectList();
        axios
            .get('http://' + process.env.VUE_APP_API_URL + '/category/list')
            .then( response => (this.categories = response.data) )
            .catch( error => (this.categories = error ));

    },

    methods: {
        
        refreshAutoAffect: function(){
            this.fetchAutoAffectList();
        },

        fetchAutoAffectList: function(){
            var _self = this;
            axios.get( 'http://' + process.env.VUE_APP_API_URL + '/auto-affect/list')
            .then( function(response){
                console.log(response);
                _self.autoAffectList = response.data;
            })
            .catch(function(error){
                console.log(error);
            });
        }
    },

}
</script>