const webpack = require('webpack');
const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  context: path.join(__dirname),
  entry: ['./js/main.js', './styles/main.scss'],
  output: {
    filename: 'bundle.js',
    path: path.join(__dirname, '/dist'),
  },
  devtool: 'source-map',
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: { presets: ['env', 'react'] },
        },
      },
      {
        test: /\.(sass|scss)$/,
        use: ['css-hot-loader'].concat(ExtractTextPlugin.extract({
          use: [
            {
              loader: 'css-loader',
              options: { sourceMap: true, minimize: true },
            },
            { loader: 'resolve-url-loader' },
            {
              loader: 'sass-loader',
              options: { sourceMap: true },
            },
          ],
        })),
      },
      {
        test: /\.(png|jpg|gif)$/,
        use: [
          {
            loader: 'file-loader',
            options: {},
          },
        ],
      },
    ],
  },
  resolve: {
    extensions: ['*', '.js', '.jsx'],
    modules: [path.join(__dirname, 'js'), 'node_modules'],
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new CleanWebpackPlugin(['dist']),
    new HtmlWebpackPlugin({
      template: './index.html',
      hash: true,
      minify: {
        // removeAttributeQuotes: true,
        collapseWhitespace: true,
        html5: true,
        removeComments: true,
        removeEmptyAttributes: true,
      },
    }),
    new ExtractTextPlugin({
      filename: 'bundle.css',
      allChunks: true,
    }),
  ],
  devServer: {
    contentBase: './dist',
    historyApiFallback: true,
    hot: true,
    proxy: { '/api': 'http://localhost:3000' },
  },
};
