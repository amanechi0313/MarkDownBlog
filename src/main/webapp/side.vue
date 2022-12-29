<template>

  <div class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px; height: 100vh;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"></use></svg>
      <span class="fs-4">Mark Down Blog</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item">
        <router-link to="/admin" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"></use></svg>
          Dashboard
        </router-link>
      </li>
      <li>
<!--        <a href="#" class="nav-link text-white">-->
          <router-link to="/articles" class="nav-link text-white">
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"></use></svg>
          Articles
          </router-link>
<!--        </a>-->
      </li>
      <li>
        <router-link to="/modify" class="nav-link text-white">
<!--        <a href="" class="nav-link text-white">-->
          <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"></use></svg>
          Settings
          </router-link>
<!--        </a>-->
      </li>
      <li>
        <button type="button" class="btn btn-primary" @click="logout">Logout</button>
      </li>

    </ul>
    <hr>

    </div>

</template>

<script setup>
// export default {
//   name: "side"
// }

function logout() {
  fetch('/markdownblog/api/auth/logout', { // 送信先URL
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
  })
      .then(response => {
        router.push('/')
        console.log('logout success')
      });
}
</script>

<style scoped>

</style>