<template>
  <div>
    <nav  class="navbar bg-light px-3 ">
      <a class="navbar-brand" href="#">MarkDownBlog</a>
      <!--      ここ-->
      <ul class="nav nav-pills">
        <li class="nav-item">
          <a class="nav-link" href="#" >Save</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" >Send</a>
        </li>
        <!--        なぜかリンクがズレてしまう！！-->
        <li class="btn-group">
          <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
            Account
          </button>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href="#">Menu item</a></li>
            <li><a class="dropdown-item" href="#">Menu item</a></li>
            <li><a class="dropdown-item" href="#">Menu item</a></li>
          </ul>
        </li>
      </ul>
    </nav>
  </div>
  <div class="row">
    <div class="col g-0 left">
      <div class="editor">
        <div class="line-numbers">
          <div v-for="i in linesNumbers">{{i}}</div>
        </div>
        <textarea placeholder="add multiple lines" name="article" v-model="data.article"></textarea>
      </div>
    </div>
    <div class="col g-0 right">
      <div id="content" v-html="markdown"></div>
    </div>
  </div>
</template>

<script setup>
import { reactive, computed } from 'vue'

const data = reactive({
  article: '',
  // linesNumbers:  10,

})

const linesNumbers = computed({
  get: () => data.article.split('\n').length + 1
})

const markdown = computed({
  get:() => marked.parse(data.article)
})



// function newLine(){
//   data.linesNumbers = event.target.value.split('\n').length;
//   // console.log(linesNumbers);
//   // console.log(data.article);
//   return linesNumbers;
// }
</script>

<style scoped>
textarea{
  border: none;
  outline: none;
  background: #252525;
  width: 95%;
  height: 100%;
  resize: none;
  display: flex;
  overflow: hidden;
  color: whitesmoke;
}
.col{
  height: 100vh;
}

.row{
  width: 100%;
}

.left{
  background-color: #252525;
}

.right{
  background-color: white;
}

body{
  background-color: #252525;
}
.editor{
  display: flex;
  height: 100vh;
}
.line-numbers {
  width: 5%;
  color: whitesmoke;
  padding-left: 23px;
  padding-right: 58px;
  padding-top: 5px;
}
</style>