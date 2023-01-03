<style scoped>
.danger{
  color: red;
  float: right;
}

.user{
  color: cornflowerblue;
  text-shadow: #333333;
}
</style>

<template>

  <div class="container">
    <h1>Modify your User Name & Password below😈</h1>
    <h2>親愛的<span class="user">{{ data.user.userName }}</span>，userId是不能更改的喔✌</h2>
    <div>
      <div class="mb-3">
        <label class="form-label">UserId:</label>
        <input type="text"  class="form-control" :placeholder=data.user.userId disabled>
      </div>
      <div class="mb-3">
        <label class="form-label">New User Name: (Current User Name: {{ data.user.userName }})</label>
        <span v-if="data.checker.name" class="danger">只能輸入10個字元！</span>
        <input type="text" id="name" class="form-control" v-model="data.newuesrname" @blur="checkName" placeholder="只能輸入10個字元">
      </div>
      <div class="mb-3">
        <label class="form-label">Current Password:</label>
        <span v-if="data.pwCorrect" class="danger">你是不是打錯密碼了😑</span>
        <input type="password" id="pw_1" class="form-control" v-model="data.toCheck" @change="pwCheck">
      </div>
      <div class="mb-3">
        <label class="form-label">New User Password:</label>
        <span v-if="data.checker.password" class="danger">須為6位數以上，並且至少包含 大寫字母、小寫字母、數字、符號 各一！</span>
        <input type="password"  id="pw" class="form-control" v-model="data.newpassword" @blur="checkPW"
               placeholder="6位數以上，並且至少包含 大寫字母、小寫字母、數字、符號 各一">
      </div>
      <button type="button" class="btn btn-primary" @click="modify">Modify</button>
    </div>
  </div>


</template>

<script setup>
import {reactive, onMounted} from 'vue'

function pwCheck() {
  console.log('old pw checking')
  if (data.toCheck == data.user.userPassword) {
    data.pwCorrect = false
    document.getElementById("pw_1").classList.remove("is-invalid");
    document.getElementById("pw_1").classList.add("is-valid");
  } else {
    data.pwCorrect = true
    document.getElementById("pw_1").classList.remove("is-valid");
    document.getElementById("pw_1").classList.add("is-invalid");
  }
}

const data = reactive({
  user: {},
  toCheck: '',
  newpassword: '',
  newuesrname: '',
  pwCorrect: true,
  checker: {
    id: true,
    name: true,
    password: true,
    undone: false,
  }
})

function checkPW() {
  console.log('pw checking!')
  console.log(data.newpassword)
  let pwChecker = /^(?=.*[^a-zA-Z0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/;
  if (pwChecker.test(data.newpassword)) {
    data.checker.password = false;
    document.getElementById("pw").classList.remove("is-invalid");
    document.getElementById("pw").classList.add("is-valid");
  } else {
    data.checker.password = true;
    document.getElementById("pw").classList.remove("is-valid");
    document.getElementById("pw").classList.add("is-invalid");
  }
}

function checkName() {
  console.log('name checking!')
  console.log(data.newuesrname)
  if (data.newuesrname.length <= 10 ) {
    data.checker.name = false;
    document.getElementById("name").classList.remove("is-invalid");
    document.getElementById("name").classList.add("is-valid");
  } else {
    data.checker.name = true;
    document.getElementById("name").classList.remove("is-valid");
    document.getElementById("name").classList.add("is-invalid");
  }
}
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