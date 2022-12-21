<%--
  Created by IntelliJ IDEA.
  User: 周汰南
  Date: 21/12/2022
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<%--    <script type="text/javascript" src="Scripts/jquery-2.1.1.min.js"></script>--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
<%--把文字在vue(app div)裡面處理好之後，送到markdown renderer裡面，再塞回content。--%>
<nav  class="navbar bg-light px-3 mb-3">
    <a class="navbar-brand" href="#">Navbar</a>
    <ul class="nav nav-pills">
        <li class="nav-item">
            <a class="nav-link" href="#scrollspyHeading1">First</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#scrollspyHeading2">Second</a>
        </li>
        <div class="dropdown">
            <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown link
            </a>

            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
        </div>
    </ul>
</nav>
<div id="app">
    <h1 v-if="false">Testing</h1>
    <div class="row">
        <div class="col">
            <div class="editor">
                <div class="line-numbers">
                    <span></span>
                </div>
                <textarea placeholder="add multiple lines" @keyup.enter="newLine"></textarea>
<%--                textareaのなかでv-modelが使用できない！v-modelでバインドして、もう一つのCOLに表示させたいです！--%>
            </div>
        </div>
        <div class="col">
            {{message}}
        </div>
    </div>

        <div id="content"></div>
</div>



<script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script>
    const {createApp} = Vue
    //DOM的寫法
    const textarea = document.querySelector('textarea')
    // var lineNumbers = document.querySelector('.line-numbers')
    //
    // textarea.addEventListener('keyup', event => {
    //     const numberOfLines = event.target.value.split('\n').length
    //
    //     lineNumbers.innerHTML = Array(numberOfLines)
    //         .fill('<span></span>')
    //         .join('')
    // })

    createApp({
        data() {
            return {
                message: 'Hello Vue!',

            }
        },
        methods:{
            newLine: function (){
                const numberOfLines = event.target.value.split('\n').length;
                console.log(numberOfLines);
                for(let i = 1; i <= numberOfLines; i++){

                    document.querySelector('.line-numbers').innerHTML = Array(numberOfLines)
                        .fill("<span>"+i+"</span>")
                        .join('')
                }

            }
        }
    }).mount('#app')

        //put markdown string in .parse(==here==)
        document.getElementById('content').innerHTML =
            marked.parse('# Marked in the browser\n\nRendered by **marked**.');

</script>
</body>

</html>
