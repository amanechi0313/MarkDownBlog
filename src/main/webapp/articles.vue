<style scoped>

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
</style>

<template>
  <!--  <div class="outter">-->

  <!--  <side></side>-->
  <div class="container bootstrap snippets bootdey">
    <div class="row">
      <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <div class="well search-result">
          <div class="input-group">
            <input type="text" class="form-control" placeholder="Search">
            <span class="input-group-btn">
              <button class="btn  btn-lg btn-primary" type="button" >
                        <i class="glyphicon glyphicon-search"></i>
                        Search
                    </button>
            </span>
          </div>
        </div>
        <button type="button" class="btn btn-primary  btn-block" @click="newarticle">
          <i class="glyphicon glyphicon-refresh"></i>New Article
        </button>
<!--        <p>TESTING HERE {{ data.user.userId }}</p>-->
        <div class="well search-result">
          <div class="row">
            <a href="#">
              <div class="col-xs-6 col-sm-3 col-md-3 col-lg-2">
                <!--                  <img class="img-responsive" src="https://www.bootdey.com/image/400x200/7B68EE/000000" alt="">-->
              </div>
              <div class="col-xs-6 col-sm-9 col-md-9 col-lg-10 title" v-for="article in data.articledata" @click="goto(article.articleId)">
                <h3>{{ article.articleTitle }}</h3>
                <p>{{ article.articleContent }}</p>
              </div>
            </a>
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
})

function newarticle(){
  router.push("/markdownblog/editor")
}

onMounted(() => {
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

})

function goto(articleId){
  router.push("/markdownblog/editor/"+articleId)
}


</script>