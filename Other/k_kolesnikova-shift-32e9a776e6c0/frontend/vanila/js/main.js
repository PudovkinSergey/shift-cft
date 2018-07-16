// polyfills
require('whatwg-fetch');
require('babel-polyfill');
module.hot.accept();
// resources
const createRequest = require('./create-request');

const resultBlock = document.querySelector('.result');

createRequest('/api/v001/books').then((result) => {
  let template = '';

  if (result.data) {
    result.data.forEach((item) => {
      template += `<div>Название: ${item.name}</div><div>Автор: ${
        item.author
      }</div>`;
    });
  }

  resultBlock.innerHTML = template;
});
