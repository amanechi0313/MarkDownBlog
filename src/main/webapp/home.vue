<style scoped>

</style>

<template>
  <div class="container">
    <div>
      <h1>This is Landing Page.</h1>
      <h2 v-if="data.user.login">Welcome, {{data.user.userName}} !!</h2>
      <h2 v-else>Please login:(</h2>
      <div v-for="item in items" :key="item.name">
        <router-link :to="item.path">{{ item.name }}</router-link>
        <!--            <a href="#">Log Out</a>-->
      </div>
    </div>
  </div>
</template>

<script setup>

import {onMounted,reactive} from 'vue'

const data = reactive({
  user: {},
})

const items = [
  {name: 'Login', path: '/login'},
  {name: 'Register', path: '/register'},
  {name: 'Logout', path: '/logout'},
  {name: 'Already Login? Go to Admin page', path: '/admin'},
]

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