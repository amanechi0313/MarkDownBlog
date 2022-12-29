<template>

  <div>
    <nav class="navbar bg-light px-3 ">
      <a class="navbar-brand" href="#">MarkDownBlog</a>
      <ul class="nav nav-pills">
        <li class="nav-item">
          <label>Title: </label>
          <input placeholder="title" v-model="data.article.articleTitle">
        </li>
        <li class="nav-item">
          <label>Tags: </label>
          <input placeholder="tags" v-model="data.article.tags">
        </li>
        <li class="nav-item">
          <a class="nav-link" @click="save">Save</a>
        </li>
        <li class="btn-group">
          <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown"
                  aria-expanded="false">
            Account
          </button>
          <ul class="dropdown-menu dropdown-menu-end">
            <li><a class="dropdown-item" @click="logout">
              登出
            </a></li>
            <li><a class="dropdown-item" @click="deleteArticle(data.article_newId)">刪除此篇文章</a></li>
            <li><a class="dropdown-item" @click="gotoArticles">回到文章列表</a></li>
          </ul>
        </li>
      </ul>

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


const route = VueRouter.useRoute()


const data = reactive({
  article: {
    articleTitle: '',
    articleContent: '',
    tags: '',
  },
  article_newId: 0
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

      articleId: data.article_newId,
      articleTitle: data.article.articleTitle,
      articleContent: data.article.articleContent,
      tags: data.article.tags,
    })
  })
      .then(response => {
        if (response.status === 200){
          alert('文章已儲存😘')
        }else if(response.status === 500){
          alert('標題跟tag只能50個字內😰')
        }


      })
}

function gotoArticles(){
  router.push('/articles')
}

onMounted(() => {
  console.log(route.params.articleId)

  if (route.params.articleId != null) {
    data.article_newId = route.params.articleId;
    editarticle(route.params.articleId);
  } else {
    newarticle()
  }


})

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

function editarticle(articleId) {
  fetch('/markdownblog/api/markdown/articles/' + articleId, {
    method: 'get', // 通信メソッド
    headers: {
      'Content-Type': 'application/json' // JSON形式のデータのヘッダー
    },
  }).then(response => {
    return response.json()
  }).then(d => {
    data.article.articleTitle = d.articleTitle;
    data.article.tag = d.tags;
    data.article.articleContent = d.articleContent;
  })

}

function deleteArticle(articleId) {
  if (confirm('確定要刪除嗎？')) {
    fetch('/markdownblog/api/markdown/delete', {
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        articleId: articleId,
      })
    })
        .then(response => {
          if (response.status === 200)
            console.log("article deleted");
          alert('文章已刪除，將回到文章列表');
          router.push('/articles');
        })
  }

}

function newarticle() {
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
    data.article_newId = d;
    console.log(d);
    console.log(data.article_newId);
    // console.log("typeof data.article_newed.articleId is " + typeof data.article_newed.articleId)
  })
}



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

.nav-link, li {
  cursor: pointer;
}

li:hover {
  opacity: 0.8;
}
</style>