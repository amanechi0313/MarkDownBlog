<style scoped>
/*div{*/
/*  display: flex;*/
/*}*/

#user {
  color: darkred;
}
</style>

<template>
  <!--  <div>-->
  <!--  <side></side>-->

  <p>This is admin page. Welcome <span id="user"></span></p>
  <!--  </div>-->
</template>

<script setup>
import Side from "./side.vue";
import {onMounted} from 'vue'

const user = {};

onMounted(() => {
  fetch('/markdownblog/api/auth/userdata', {
    method: 'get',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(response => {
    return response.json()
  }).then(data => {
    document.getElementById("user").innerText = data.userName;

    //onMountedの外に届かない...

  })
})




</script>