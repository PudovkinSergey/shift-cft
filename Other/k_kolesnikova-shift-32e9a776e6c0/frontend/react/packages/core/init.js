// polyfills
require('whatwg-fetch');
require('babel-polyfill');

const { createElement } = require('react');
const { render } = require('react-dom');
const Main = require('./main');

render(createElement(Main), document.querySelector('.root'));

module.hot.accept();
