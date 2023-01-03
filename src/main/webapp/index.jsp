<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!--
<!DOCTYPE html>
<html>
<head>
    <title>Landing Page</title>
</head>
<body>
<h1>Landing Page</h1>
<br/>
<a href="login.jsp">Login</a><br>
<a href="register.jsp">Register</a>

</body>
</html>
-->

<!DOCTYPE html>
<html>
<head>
    <title>Mark Down Blog</title>
    <base href="/markdownblog/">
    <style>
        #content.markdown_wrap h1{
            padding: 1rem 2rem;
            color: #fff;
            background: #094;
            -webkit-box-shadow: 5px 5px 0 #007032;
            box-shadow: 5px 5px 0 #007032;
        }

        #content.markdown_wrap h2{
            position: relative;
            padding: 1rem 2rem;
            border-bottom: 6px solid #094;
        }

        #content.markdown_wrap h3{
            position: relative;
            padding: 1rem .5rem;
        }

        #content.markdown_wrap h3:after{
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 6px;
            content: '';
            border-radius: 3px;
            background-image: -webkit-gradient(linear, right top, left top, from(#2af598), to(#009efd));
            background-image: -webkit-linear-gradient(right, #2af598 0%, #009efd 100%);
            background-image: linear-gradient(to left, #2af598 0%, #009efd 100%);
        }</style>
</head>
<body>

    <div id="app"></div>

    <script src="https://unpkg.com/vue@3.2.45"></script>
    <script src="https://unpkg.com/vue-router@4.1.6/dist/vue-router.global.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue3-sfc-loader"></script>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/marked/4.2.5/marked.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    <script src="./route.js"></script>
    <script>
        const options = {
            moduleCache: {
                vue: Vue,
            },
            getFile(url) {
                return fetch(url).then(response => response.ok ? response.text() : Promise.reject(response));
            },
            addStyle(styleStr) {
                const style = document.createElement('style');
                style.textContent = styleStr;
                const ref = document.head.getElementsByTagName('style')[0] || null;
                document.head.insertBefore(style, ref);
            },
            log(type, ...args) {
                console.log(type, ...args);
            }
        }
        const { loadModule, version } = window["vue3-sfc-loader"];
        const router = VueRouter.createRouter({
            // Provide the history implementation to use. We are using the hash history for simplicity here.
            base: "/markdownblog/",
            history: VueRouter.createWebHistory(),
            routes, // short for `routes: routes`
        })
        const app = Vue.createApp({
            components: {
                'vue-app': Vue.defineAsyncComponent(() => loadModule('./index.vue', options)),
            },
            template: `<vue-app></vue-app>`
        });
        app.use(router)
        app.use(marked)
        app.mount('#app');
    </script>
</body>
</html>