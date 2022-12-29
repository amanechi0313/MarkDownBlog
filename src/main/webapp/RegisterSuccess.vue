<style scoped>

</style>

<template>
  <div class="container">
    <div>
      <h1>User had been registered. Going back to Landing Page in 3 seconds...</h1>
    </div>
  </div>
</template>

<script setup>
import {onMounted} from 'vue'

onMounted(() => {
  setTimeout(() => {
    router.push('/')
  }, 3000)

})

</script>