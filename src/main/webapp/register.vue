<style scoped>
.danger{
  color: red;
  float: right;
}
</style>

<template>
  <div class="container">
    <h1>This is Registration Page.</h1>
    <div class="mb-3">
      <label class="form-label">User ID:</label>
      <span v-if="data.checker.id" class="danger">須為6~24英文或數字！</span>
      <input type="text" class="form-control" id="id" v-model="data.userId" @blur="checkID"
             placeholder="6~24英文或數字">
    </div>
    <div class="mb-3">
      <label class="form-label">User Name:</label>
      <span v-if="data.checker.name" class="danger">只能輸入10個字元！</span>
      <input type="text" class="form-control" id="name" v-model="data.userName" @blur="checkName" placeholder="只能輸入10個字元">
    </div>
    <div class="mb-3">
      <label class="form-label">User Password:</label>
      <span v-if="data.checker.password" class="danger">須為6位數以上，並且至少包含 大寫字母、小寫字母、數字、符號 各一！</span>
      <input type="password" class="form-control" id="pw" v-model="data.userPassword" @blur="checkPW"
             placeholder="6位數以上，並且至少包含 大寫字母、小寫字母、數字、符號 各一">
    </div>
    <button type="button" class="btn btn-primary" @click="register">register</button>
    <h3 v-if="data.checker.undone">👎用戶資料已經被註冊過，或是資料庫爆了！</h3>
  </div>

</template>

<script setup>
import {reactive} from 'vue'
import Side from "./side.vue";

const data = reactive({
  userName: '',
  userPassword: '',
  userId: '',
  checker: {
    id: true,
    name: true,
    password: true,
    undone: false,
  }
})

function checkID() {
  let idChecker = /[A-Za-z0-9]{6,24}/;
  console.log('id checking!')
  if (idChecker.test(data.userId)) {
    data.checker.id = false;
    document.getElementById("id").classList.remove("is-invalid");
    document.getElementById("id").classList.add("is-valid");
  } else {
    data.checker.id = true;
    document.getElementById("id").classList.remove("is-valid");
    document.getElementById("id").classList.add("is-invalid");
  }
}

function checkPW() {
  console.log('pw checking!')
  console.log(data.userPassword)
  let pwChecker = /^(?=.*[^a-zA-Z0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*\d).{6,}$/;
  if (pwChecker.test(data.userPassword)) {
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
  if (data.userName.length <= 10 ) {
    data.checker.name = false;
    document.getElementById("name").classList.remove("is-invalid");
    document.getElementById("name").classList.add("is-valid");
  } else {
    data.checker.name = true;
    document.getElementById("name").classList.remove("is-valid");
    document.getElementById("name").classList.add("is-invalid");
  }
}


function register() {
  console.log(data.userId + " " + data.userPassword + " " + data.userName);
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
        if (response.status === 200) router.push('/success')
        if (response.status === 500) {
          data.checker.undone = true;
        }
      });
}


</script>