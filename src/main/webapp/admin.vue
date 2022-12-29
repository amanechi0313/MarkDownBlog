<style scoped>

</style>

<template>
<div class="container>">
  <h1>This is admin page. Welcome !! {{data.user.userName}} </h1>
</div>
</template>

<script setup>
import {onMounted,reactive} from 'vue'
const data = reactive({
  user: {},
})

onMounted(() => {
  fetch('/markdownblog/api/auth/userdata', {
    method: 'get',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(response => {
    return response.json()
  }).then(d => {
    data.user = d;
  })
})




</script>