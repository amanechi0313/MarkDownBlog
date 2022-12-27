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
    { path: '/markdownblog/', component: createComponent('./home.vue') },
    { path: '/markdownblog/login', component: createComponent('./login.vue') },
    { path: '/markdownblog/success', component: createComponent('./loginSuccess.vue') },
    { path: '/markdownblog/admin', component: createComponent('./admin.vue') },
    { path: '/markdownblog/modify', component: createComponent('./modify.vue') },
    { path: '/markdownblog/editor', component: createComponent('./editor.vue') },
]