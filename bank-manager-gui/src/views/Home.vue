<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <Category/>
    <Operations/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Category from '@/components/CategoryComponent.vue'
import Operations from '@/components/Operation/OperationsComponent.vue'

export default {
  name: 'home',
  components: {
    HelloWorld, Category, Operations
  }
}
</script>
