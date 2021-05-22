module.exports = {
    // devServer: {
    //     overlay: {
    //         warnings: false, //不显示警告
    //         errors: false	//不显示错误
    //     }
    // },
    lintOnSave: false, //关闭eslint检查
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    // If you are using less-loader@5 please spread the lessOptions to options directly
                    modifyVars: {
                        'primary-color': '#1DA57A',
                        'link-color': '#1DA57A',
                        'border-radius-base': '2px',
                    },
                    javascriptEnabled: true,
                },
            },
        },
    },
}