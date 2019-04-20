<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <Category/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import Category from '@/components/CategoryComponent.vue'

export default {
  name: 'home',
  components: {
    HelloWorld, Category
  }
}
</script>
