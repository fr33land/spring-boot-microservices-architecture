var path = require('path');
module.exports = {
    entry:
            {
                app: './src/main/js/App.js',
                usersecurity: './src/main/js/admin/usersecurity/UserSecurityRouter.js',
            },
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/[name].js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                        loader: 'babel-loader',
                        options: {
                            presets: ["@babel/preset-env", "@babel/preset-react"]
                        }
                    }]
            },
            {
                test:/\.css$/,
                use:['style-loader','css-loader']
            }
        ]
    }
};