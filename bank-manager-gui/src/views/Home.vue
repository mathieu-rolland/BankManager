<template>
  <div class="home">
    <img class="bank-icon" alt="Vue logo" src="../assets/bank-main.png" />
    <HomeComponent msg="Bank Manager App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HomeComponent from '@/components/Home.vue'

export default {
  name: 'home',
  components: {
    HomeComponent
  }
}
</script>

<style>

  .bank-icon{
    width: 200px;

  }

</style>
