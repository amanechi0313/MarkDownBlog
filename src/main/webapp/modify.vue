<style scoped>
/*.outter{*/
/*  display: flex;*/
/*}*/
</style>

<template>
  <!--  <div class="outter">-->

  <!--  <side></side>-->

  <!--    <div class="mb-3">-->
  <!--      <label class="form-label">User ID:</label>-->
  <!--      <input type="text" class="form-control" v-model="data.userId">-->
  <!--    </div>-->
  <div class="container">
    <div>
      <div class="mb-3">
        <label class="form-label">New User Name:</label>
        <input type="text" class="form-control" v-model="data.userName">
      </div>
      <div class="mb-3">
        <label class="form-label">New User Password:</label>
        <input type="password" class="form-control" v-model="data.userPassword">
      </div>
      <button type="button" class="btn btn-primary" @click="modify">Modify</button>
    </div>
  </div>
  <!--  </div>-->

</template>

<script setup>
import {reactive} from 'vue'
import Side from "./side.vue";

const data = reactive({
  // userId: '',
  userName: '',
  userPassword: ''
})

function modify() {
  fetch('/markdownblog/api/auth/modify', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
    body: JSON.stringify({
      userName: data.userName,
      userPassword: data.userPassword

    }) // JSON形式のデータ
  })
      .then(response => {
        if (response.status === 200) router.push('/markdownblog/admin')
      })
      .then(data => {
        console.log(data);
      });
}


</script>