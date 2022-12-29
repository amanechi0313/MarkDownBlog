<style scoped>
.incorrect {
  padding-left: 50px;
  color: red;
}
</style>

<template>

  <div class="container">
    <h1>Modify your User Name & Password below😈{{ data.user.userName }}</h1>
    <div>
      <div class="mb-3">
        <label class="form-label">UserId:</label>
        <input type="text" class="form-control" :placeholder=data.user.userId disabled>
      </div>
      <div class="mb-3">
        <label class="form-label">New User Name: (Current User Name: {{ data.user.userName }})</label>
        <input type="text" class="form-control" v-model="data.newuesrname">
      </div>
      <div class="mb-3">
        <label class="form-label">Current Password:</label><span class="incorrect" v-if="data.pwCorrect">Password Incorrect</span>
        <input type="password" class="form-control" v-model="data.toCheck" @change="pwCheck">
      </div>
      <div class="mb-3">
        <label class="form-label">New User Password:</label>
        <input type="password" class="form-control" v-model="data.newpassword">
      </div>
      <button type="button" class="btn btn-primary" @click="modify">Modify</button>
    </div>
  </div>


</template>

<script setup>
import {reactive, onMounted} from 'vue'

function pwCheck() {
  if (data.toCheck == data.user.userPassword) {
    data.pwCorrect = false
  } else {
    data.pwCorrect = true
  }
}

const data = reactive({
  user: {},
  toCheck: '',
  newpassword: '',
  newuesrname: '',
  pwCorrect: true,

})

function modify() {
  fetch('/markdownblog/api/auth/modify', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
    body: JSON.stringify({
      userName: data.newuesrname,
      userPassword: data.newpassword

    }) // JSON形式のデータ
  })
      .then(response => {
        if (response.status === 200) router.push('/admin')
      })
      .then(data => {
        console.log(data);
      });
}

onMounted(() => {
  fetch('/markdownblog/api/auth/userdata', {
    method: 'get',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(response => {
    return response.json()
  }).then(d => {
    console.log(d)
    data.user = d;
  })
})


</script>