<template>
    <div class="component">
        <h1 class="component-title">Opérations</h1>
        <Operations />
    </div>
</template>

<script>

import Operations from '@/components/Operation/OperationsComponent.vue'

export default {
    
    name: 'OperationView',
    components: {
        Operations
    }

}
</script>

<style scoped>

.component{
    width: 800px;
    margin: auto;
    padding-top: 30px;
}

.component-title{
    padding-bottom: 20px;
}

</style>