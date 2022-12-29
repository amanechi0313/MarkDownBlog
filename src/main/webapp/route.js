function createComponent(path) {
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
    return Vue.defineAsyncComponent(() => loadModule(path, options))
}

const routes = [
    { path: '/', component: createComponent('./home.vue') , sideMenuVisible: false},
    { path: '/login', component: createComponent('./login.vue') ,sideMenuVisible: false},
    { path: '/success', component: createComponent('./RegisterSuccess.vue') ,sideMenuVisible: false},
    { path: '/admin', component: createComponent('./admin.vue') ,sideMenuVisible: true},
    { path: '/modify', component: createComponent('./modify.vue') ,sideMenuVisible: true},
    { path: '/editor', component: createComponent('./editor.vue') ,sideMenuVisible: false},
    { path: '/editor/:articleId', component: createComponent('./editor.vue') ,sideMenuVisible: false},
    { path: '/register', component: createComponent('./register.vue') ,sideMenuVisible: false},
    { path: '/articles', component: createComponent('./articles.vue') ,sideMenuVisible: true},
]