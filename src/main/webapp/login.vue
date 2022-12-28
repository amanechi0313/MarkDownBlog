<style scoped>

</style>

<template>
  <!--	<div>-->
  <!--		<div>-->
  <!--			<label>User ID:</label>-->
  <!--			<input v-model="data.userId" type="text">-->
  <!--		</div>-->
  <!--		<div>-->
  <!--			<label>User Password:</label>-->
  <!--			<input v-model="data.userPassword" type="password">-->
  <!--		</div>-->
  <!--		<div>-->
  <!--			<button @click="login">送信</button>-->
  <!--		</div>-->
  <!--	</div>-->
  <!--  <form>-->
  <div class="container">
    <div class="mb-3">
      <label class="form-label">User ID:</label>
      <input type="text" class="form-control" v-model="data.userId">
    </div>
    <div class="mb-3">
      <label class="form-label">User Password:</label>
      <input type="password" class="form-control" v-model="data.userPassword">
    </div>
    <button type="button" class="btn btn-primary" @click="login">Submit</button>
  </div>
  <!--  </form>-->
</template>

<script setup>
import {reactive} from 'vue'

const data = reactive({
  userId: '',
  userPassword: ''
})

function login() {
  console.log(data.userId + " " + data.userPassword);
  fetch('/markdownblog/api/auth/login', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
    body: JSON.stringify({
      userId: data.userId,
      userPassword: data.userPassword,
      userName: '',

    }) // JSON形式のデータ
  })
      .then(response => {
        console.log('through here')
        if (response.status === 200) router.push('/markdownblog/admin')
      })
      .then(data => {
        console.log(data);
      });
}

</script>