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
    { path: '/markdownblog/', component: createComponent('./home.vue') , sideMenuVisible: false},
    { path: '/markdownblog/login', component: createComponent('./login.vue') ,sideMenuVisible: false},
    { path: '/markdownblog/success', component: createComponent('./RegisterSuccess.vue') ,sideMenuVisible: false},
    { path: '/markdownblog/admin', component: createComponent('./admin.vue') ,sideMenuVisible: true},
    { path: '/markdownblog/modify', component: createComponent('./modify.vue') ,sideMenuVisible: true},
    { path: '/markdownblog/editor', component: createComponent('./editor.vue') ,sideMenuVisible: false},
    { path: '/markdownblog/register', component: createComponent('./register.vue') ,sideMenuVisible: false},
    { path: '/markdownblog/articles', component: createComponent('./articles.vue') ,sideMenuVisible: true},
]