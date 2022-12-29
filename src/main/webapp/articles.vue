<style scoped>

.ellipsis {
  width: 700px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}


.search-result .title h3 {
  margin: 0 0 15px;
  color: #333;
}

.search-result .title p {
  font-size: 12px;
  color: #333;
}

.well {
  border: 0;
  padding: 20px;
  min-height: 63px;
  background: #fff;
  box-shadow: none;
  border-radius: 3px;
  position: relative;
  max-height: 100000px;
  border-bottom: 2px solid #ccc;
  transition: max-height 0.5s ease;
  -o-transition: max-height 0.5s ease;
  -ms-transition: max-height 0.5s ease;
  -moz-transition: max-height 0.5s ease;
  -webkit-transition: max-height 0.5s ease;
}

.form-control {
  height: 45px;
  padding: 10px;
  font-size: 16px;
  box-shadow: none;
  border-radius: 0;
  position: relative;
}

.inner_row {
  padding: 10px;
  margin: 3px;
  background-color: #f1ecec;
}

.inner_row:hover {
  opacity: 0.8;
}

.button_wrap {
  margin: 5px;
  justify-content: center;
  display: flex;
}

.button_wrap button {
  margin-right: 20px;
  margin-left: 20px;
}


</style>

<template>
  <div class="container bootstrap snippets bootdey " style="height: 100vh">
    <div class="row">
      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="well search-result">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Search" @focus="onFocus" @blur="onBlur"
                   v-model="data.keyword">
            <span class="input-group-btn">
              <button class="btn  btn-lg btn-primary" type="button" @click="search">
                        <i class="glyphicon glyphicon-search"></i>
                        Search
                    </button>
            </span>
          </div>
          <div>
            <h3 v-if="data.input">What are you searching for?</h3>
          </div>
        </div>

        <!--        <p>TESTING HERE {{ data.user.userId }}</p>-->
        <div class="well search-result " style="overflow: auto; height: 75vh">
          <div class="button_wrap">
            <button type="button" class="btn btn-success  btn-block" @click="newarticle">
              <i class="glyphicon glyphicon-refresh"></i>New Article
            </button>
            <button type="button" class="btn btn-success  btn-block" @click="getarticles">
              <i class="glyphicon glyphicon-refresh"></i>Show All Article
            </button>
          </div>

          <div class="row inner_row" v-for="article in data.articledata">
            <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title">
              <div>
                <h3 @click="goto(article.articleId)">{{ article.articleTitle }}</h3>
                <p class="ellipsis">{{ article.articleContent }}</p>
                <button type="button" class="btn btn-danger  btn-block" @click="deleteArticle(article.articleId)">Delete
                </button>
              </div>

            </div>
          </div>
        </div>
        <div class="row">
          <button type="button" class="btn btn-primary  btn-block">
            <i class="glyphicon glyphicon-refresh"></i>Load more...
          </button>
        </div>
      </div>
    </div>
  </div>
  <!--  </div>-->
</template>

<script setup>
import {reactive, onMounted} from 'vue'
import Side from "./side.vue";

const data = reactive({
  articledata: [],
  input: false,
  keyword: '',
})

function onBlur() {
  data.input = false
}

function onFocus() {
  data.input = true
}

function newarticle() {
  router.push("/editor")
}


function deleteArticle(articleId) {
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
        getarticles();
      })
}

function getarticles() {
  fetch('/markdownblog/api/markdown/articles', {
    method: 'get',
    headers: {
      'Content-Type': 'application/json'
    },
  }).then(response => {
    return response.json()
  }).then(d => {
    // data.user.userId = d.userId;
    data.articledata = d;
    console.log(d);

  })
}

function search() {
  fetch('/markdownblog/api/markdown/search', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      keyword: data.keyword,
    })
  })
      .then(response => {
        return response.json()
      }).then(d => {
    // data.user.userId = d.userId;
    data.articledata = d;
    console.log(d);
  })

  function goto(articleId) {
    // router.push({ path:'/markdownblog/editor',params: {articleId}})
    router.push('/editor/' + articleId)
  }

  onMounted(() => {
    getarticles();
  })
}


</script>