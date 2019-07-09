<template>

    <div>
        <h1>Catégories</h1>

        <Category/>
    </div>

</template>

<script>

import Category from '@/components/CategoryComponent.vue'

export default {
    
    name: 'CategoryView',
    components:{
        Category
    }

}
</script>