<template>

    <div class="category-components">

        <b-spinner variant="primary" label="Spinning" v-if="!page_loaded"></b-spinner>

        <table class="table">
            <thead>
                <tr>
                <th scope="col">Id technique</th>
                <th scope="col">Nom</th>
                <th scope="col">Couleur</th>
                <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="category in categories">
                    <th scope="row">{{category.id}}</th>
                    <td>{{category.name}}</td>
                    <td><span class="color" v-bind:style="{color: category.color}">{{ category.color }}</span></td>
                    <td>
                        <img v-b-modal="'collapse-modify-category'" 
                            variant="primary" 
                            v-on:click="editCategory(category)" 
                            src="../assets/edit.png"
                            class="edit button" />

                        <img 
                            class="delete button" alt="delete.png" src="../assets/delete.png"
                            v-on:click="deleteCategory(category)"/>
                    </td>
                </tr>
            </tbody>
        </table>


        <b-button v-b-modal="'collapse-create-category'" variant="primary">Nouvelle catégorie</b-button>

        <b-modal id="collapse-create-category" class="mt-2" ref="modal" @ok="checkForm" title="Nouvelle catégory">
            <form method="post" id="category-create-form">
                <label for="name">Nom</label>
                <b-form-input id="name" v-model="name" type="text" name="name" placeholder="Entrer le nom de la catégorie" />
                <br />
                <label for="color">Couleur</label>
                <b-form-input id="color" 
                    v-model="color" 
                    type="text" 
                    name="color" 
                    placeholder="Entrer la couleur de la catégorie"
                    v-bind:style="{color: color}"/>
            </form>
        </b-modal>

        <b-modal id="collapse-modify-category" class="mt-2" ref="modal" @ok="modifyCategorySubmit" title="Modifier la catégory">
            <form method="post" id="category-create-form">
                <label for="name">Nom</label>
                <b-form-input id="name" v-model="modify_name" type="text" name="name" placeholder="Modifier le nom de la catégorie" />
                <br />
                <label for="color">Couleur</label>
                <b-form-input id="modify_color" 
                    v-model="modify_color" 
                    type="text" 
                    name="color" 
                    placeholder="Modifier la couleur de la catégorie"
                    v-bind:style="{color: modify_color}"/>
            </form>
        </b-modal>

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
            color: "",
            modify_name: "",
            modify_color: "",
            modify_id: "",
            page_loaded: false
        }
    },
    
    methods:{
        checkForm: function(e){
            e.preventDefault();
            axios.post( "http://" + process.env.VUE_APP_API_URL + "/category/create" , {
                color: this.color,
                name: this.name
            })
            .then( this.fetchCategory )
            .catch(function (error) {
                console.log(error);
            });
        },

        deleteCategory: function(category){
             axios.delete( "http://" + process.env.VUE_APP_API_URL + "/category/delete" , {
                data: { id: category.id }
            })
            .then( this.fetchCategory )
            .catch(function (error) {
                console.log(error);
            });
        },

        editCategory(category){
            this.modify_name  = category.name;
            this.modify_color = category.color;
            this.modify_id    = category.id;
        },

        modifyCategorySubmit(){
            axios.post( "http://" + process.env.VUE_APP_API_URL + "/category/create" , {
                color: this.modify_color,
                name: this.modify_name,
                id: this.modify_id
            })
            .then( this.fetchCategory )
            .catch(function (error) {
                console.log(error);
            });
        },

        fetchCategory(){
             axios
            .get('http://' + process.env.VUE_APP_API_URL + '/category/list')
            .then( response => (this.categories = response.data) )
            .catch( error => (this.categories = error ));
            this.$nextTick(() => {
                // Wrapped in $nextTick to ensure DOM is rendered before closing
                this.$refs.modal.hide()
            });
            this.page_loaded = true;
        }

    },

    mounted(){
        this.fetchCategory()
    },
}
</script>

<style scoped>

.category-components{
    width: 800px;
    margin:auto;
}

.category-list{
    width: 350px;
    margin: auto;
    padding-top: 20px;
    padding-bottom: 20px;
}

.category-element{
    width: 350px;
    text-align: left;
}

.name{
    margin-right: 10px;
}

.color {
    margin-right: 10px;
}

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