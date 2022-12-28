<style scoped>
.flex-box{
  display: flex;
}
</style>

<template>
  <div :class="{'flex-box': sideMenuVisible}">
    <side v-if="sideMenuVisible"></side>
    <router-view></router-view>
  </div>

</template>

<script setup>
import {computed} from 'vue'
import Side from "./side.vue";
const sideMenuVisible = computed({
  get: () => {
    const route = routes.find(r => r.path === router.currentRoute.value.path)
    if (!route) return false
    return route.sideMenuVisible
  }
})
</script>