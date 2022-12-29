<style scoped>
.router{
  padding: 10px;
  font-weight: 500;
  font-size: 30px;
}

#myVideo {
  position: fixed;
  right: 0;
  bottom: 0;
  min-width: 100%;
  min-height: 100%;
  z-index: -1;
}
h1,h2{
  color: white;
}

</style>

<template>
  <div class="container outer">
    <video autoplay muted loop id="myVideo">
      <source src="Pretend%20You're%20Working%20-%20Hacker%20Typer.mp4" type="video/mp4">
    </video>
    <div>
      <h1>👉This is Landing Page. Welcome to MARKDOWN BLOG.</h1>
      <h2 v-if="data.user.login">Hi, {{data.user.userName}} !!</h2>
      <h2 v-else>Please login:(</h2>
      <div v-for="item in items" :key="item.name">
        <router-link class="router" :to="item.path">{{ item.name }}</router-link>
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