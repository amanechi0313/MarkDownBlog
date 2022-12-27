<style scoped>

</style>

<template>
  <div class="container">
	<div>
		<h1>ログインに成功しました。</h1>
    <button type="button" class="btn btn-primary" @click="logout">Logout</button>
  </div>
  </div>
</template>

<script setup>
function logout() {
  fetch('/markdownblog/api/auth/logout', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
  })
      .then(response => {
        router.push('/markdownblog/')
        console.log('logout success')
      });
}

</script>