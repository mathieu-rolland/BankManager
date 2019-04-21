<template>

    <tr v-if="operation && categories">
        <td>{{operation.name}}</td>
        <td>{{operation.amount}}</td>
        <td>
            <select v-model="selected_category" :options="categories" @change="changeSelected">
                <option v-for="category in categories" v-bind:value="category.id">{{category.name}}</option>
            </select>
        </td>
    </tr>

</template>

<script>

export default {
    name: 'OperationComponent',
    props: [
        'operation',
        'categories'
    ],
    data: function(){
        return {
            selected_category: ""
        }
    },
    methods: {
        changeSelected: function(){
            console.log( this.selected_category );
        }
    },
}

</script>