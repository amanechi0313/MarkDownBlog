<style scoped>

</style>

<template>
  <side></side>
  <div class="container">
    <div class="mb-3">
      <label class="form-label">User ID:</label>
      <input type="text" class="form-control" v-model="data.userId">
    </div>
    <div class="mb-3">
      <label class="form-label">User Name:</label>
      <input type="text" class="form-control" v-model="data.userName">
    </div>
    <div class="mb-3">
      <label  class="form-label">User Password:</label>
      <input type="password" class="form-control" v-model="data.userPassword" >
    </div>
    <button type="button" class="btn btn-primary" @click="register">register</button>
    <button type="button" class="btn btn-primary" @click="register">Test</button>
  </div>


</template>

<script setup>
import { reactive } from 'vue'
import Side from "./side.vue";

const data = reactive({
  userName: '',
  userPassword: '',
  userId: '',
})


function register() {
  console.log(data.userId+" "+data.userPassword+" "+data.userName);
  fetch('/markdownblog/api/auth/register', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
    body: JSON.stringify({
      userName: data.userName,
      userPassword: data.userPassword,
      userId: data.userId

    }) // JSON形式のデータ
  })
      .then(response => {
        if (response.status === 200) router.push('/markdownblog/success')
      });
}



</script>