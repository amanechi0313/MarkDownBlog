<style scoped>

</style>

<template>
    <div>
        <h1>Admin Page</h1>
        <div v-for="item in items" :key="item.name">
            <router-link :to="item.path">{{ item.name }}</router-link>
        </div>
    </div>
</template>

<script setup>
const items = [
    { name: 'Account Modify', path: '/markdownblog/modify' },
    { name: 'Logout', path: '/markdownblog/logout' },
]
</script>