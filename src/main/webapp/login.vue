<style scoped>

</style>

<template>
  <div class="container">
    <h1>This is Login Page.<span v-if="data.passwordIncorrect">亲，您的密码错囉😎</span></h1>
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
  userPassword: '',
  passwordIncorrect: false
})

function login() {
  console.log("User login. ID: " + data.userId + " Password: " + data.userPassword);
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
        if (response.status === 200){
          router.push('/admin')
        } else if(response.status === 403){
          data.passwordIncorrect = true;
        }
      })
      .then(data => {
        console.log(data);
      });
}

</script>