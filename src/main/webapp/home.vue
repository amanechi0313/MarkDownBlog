<style scoped>

</style>

<template>
  <div class="container">
    <div>
        <h1>Landing Page</h1>
        <div v-for="item in items" :key="item.name">
            <router-link :to="item.path">{{ item.name }}</router-link>
<!--            <a href="#">Log Out</a>-->
        </div>
    </div>
  </div>
</template>

<script setup>
const items = [
    { name: 'Login', path: '/markdownblog/login' },
    { name: 'Register', path: '/markdownblog/register' },
    // { name: 'Logout', path: '/markdownblog/logout' },
]
</script>