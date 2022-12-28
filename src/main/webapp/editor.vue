<template>

  <div>
    <nav class="navbar bg-light px-3 ">
      <a class="navbar-brand" href="#">MarkDownBlog</a>
      <!--      ここ-->
      <ul class="nav nav-pills">
        <li class="nav-item">
          <a class="nav-link" href="#" @click="save">Save</a>
        </li>
        <!--        なぜかリンクがズレてしまう！！-->
        <li class="btn-group">
          <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown"
                  aria-expanded="false">
            Account
          </button>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" href="#">Menu item</a></li>
            <li><a class="dropdown-item" href="#">Menu item</a></li>
            <li><a class="dropdown-item" href="#">Menu item</a></li>
          </ul>
        </li>
      </ul>
      <input placeholder="title" v-model="data.article.articleTitle">
      <input placeholder="tags" v-model="data.article.tags">
    </nav>
  </div>
  <div class="row">
    <div class="col g-0 left">
      <div class="editor">
        <div class="line-numbers">
          <div v-for="i in linesNumbers">{{ i }}</div>
        </div>
        <textarea placeholder="add multiple lines" name="article" v-model="data.article.articleContent"></textarea>
      </div>
    </div>
    <div class="col g-0 right">
      <div id="content" v-html="markdown"></div>
    </div>
  </div>

</template>

<script setup>
import {reactive, computed, onMounted} from 'vue'

const data = reactive({
  article: {
    articleTitle: '',
    // computed({
    //   get: () => data.article.articleContent.split('\n')[0].replace("# ","")
    // }),
    articleContent: '',
    tags: '',
  },
  article_newed: {},
})

const linesNumbers = computed({
  get: () => data.article.articleContent.split('\n').length + 1
})

const markdown = computed({
  get: () => marked.parse(data.article.articleContent)
})

function save() {
  fetch('/markdownblog/api/markdown/save', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      articleId: data.article_newed.articleId,
      articleTitle: data.article.articleTitle,
      articleContent: data.article.articleContent,
      tags: data.article.tags,
    })
  })
      .then(response => {
        if (response.status === 200)
          console.log("article saved")

      })
}

onMounted(() => {
  fetch('/markdownblog/api/markdown/articles', {
    method: 'post', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
    body: JSON.stringify({
      articleTitle: data.article.articleTitle,
      articleContent: data.article.articleContent,
      tags: data.article.tags,
    }) // JSON形式のデータ

}).then(response => {
  return response.json()
}).then(d => {
  data.article_newed = d;
  console.log(d);
  console.log(data.article_newed);
})
})

</script>

<style scoped>
textarea {
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

.col {
  height: 100vh;
}

.row {
  width: 100%;
}

.left {
  background-color: #252525;
}

.right {
  background-color: white;
}

body {
  background-color: #252525;
}

.editor {
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