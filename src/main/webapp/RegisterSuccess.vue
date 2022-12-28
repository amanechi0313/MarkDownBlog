<style scoped>

</style>

<template>
  <div class="container">
	<div>
		<h1>User had been registered. Please login.</h1>
    <button type="button" class="btn btn-primary" href="/login">Login</button>
<!--    <button type="button" class="btn btn-primary" @click="logout">Logout</button>-->
  </div>
  </div>
</template>

<script setup>
import Login from "./login.vue";

// function logout() {
//   fetch('/markdownblog/api/auth/logout', { // 送信先URL
//     method: 'post', // 通信メソッド
//     headers: {
//       'Content-Type': 'application/json' // JSON形式のデータのヘッダー
//     },
//   })
//       .then(response => {
//         router.push('/markdownblog/')
//         console.log('logout success')
//       });
// }

</script>