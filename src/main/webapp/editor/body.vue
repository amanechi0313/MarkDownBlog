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

<template>
<!--  <form action="http://localhost:8080/markdownblog/articlehandling" method="get">-->
  <div class="row">
    <div class="col g-0 left">
      <div class="editor">
        <div class="line-numbers">
          <div v-for="i in 100">{{i}}</div>
        </div>
        <textarea placeholder="add multiple lines" @keyup.enter="newLine" name="article"></textarea>
      </div>
    </div>
    <div class="col g-0 right">
      {{message}}
    </div>
  </div>

  <div id="content"></div>
<!--  </form>-->
</template>
<script setup>
import { reactive } from 'vue'
import{ref} from 'vue'


//データが外に届いてない！！！
var linesNumbers = 0;

function newLine(){
  linesNumbers = event.target.value.split('\n').length;
  console.log(linesNumbers);
  return linesNumbers;
}





//Line-adding
// function newLine() {
//   const numberOfLines = event.target.value.split('\n').length;
//   console.log(numberOfLines);
//
//   for (let i = 1; i <= numberOfLines; i++) {
//
//     document.querySelector('.line-numbers').innerHTML = Array(numberOfLines)
//         .fill("<div>" + i + "</div>")
//         .join('')
//   }
// }



const title = 'Body'
</script>
