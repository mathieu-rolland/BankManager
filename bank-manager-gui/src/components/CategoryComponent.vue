<template>

    <div class="category-components">

        <h2> Mes categories</h2>

        <div class="category-list">
                   
                <div class="category-element" v-for="category in categories">
                    <p>
                        <span class="name">{{ category.name }} : </span>
                        <span class="color" v-bind:style="{color: category.color}">{{ category.color }}</span>
                        <img 
                            class="delete button" alt="delete.png" src="../assets/delete.png"
                            v-on:click="deleteCategory(category)"/>
                    </p>
                </div>
            
        </div>

        <div class="category-create">
            <form method="post"
                id="category-create-form"
                @submit="checkForm">

                <label for="name">Nom</label>
                <input id="name" v-model="name" type="text" name="name" />
                <br />
                <label for="color">Couleur</label>
                <input id="color" v-model="color" type="text" name="color" />
                <br />
                <input type="submit" value="Créer" />
            </form>
        </div>
    </div>



</template>

<script>

import axios from 'axios'

export default {
    
    name: 'category',

    data: function(){
        return {
            categories: {},
            name: "",
            color: ""
        }
    },
    
    methods:{
        checkForm: function(e){
            e.preventDefault();
            console.log( "Création de la category : " + this.name + ' / ' + this.color );
            axios.post( "http://localhost:8080/category/create" , {
                color: this.color,
                name: this.name
            })
            .then( this.fetchCategory )
            .catch(function (error) {
                console.log(error);
            });
        },

        deleteCategory: function(category){
             axios.delete( "http://localhost:8080/category/delete" , {
                data: { id: category.id }
            })
            .then( this.fetchCategory )
            .catch(function (error) {
                console.log(error);
            });
        },

        fetchCategory: function(){
             axios
            .get('http://localhost:8080/category/list')
            .then( response => (this.categories = response.data) )
            .catch( error => (this.categories = error ))
        }

    },

    mounted(){
        this.fetchCategory()
    },
}
</script>

<style scoped>

.category-list{
    
}

.name{

}

.delete.button {
    width: 20px;
    cursor: pointer;
    margin-left: 10px;
}

</style>