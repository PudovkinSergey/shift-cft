module.exports = {
  fetchBooks: {
    path: '/api/v001/books',
    method: 'GET',
  },

  fetchBook: {
    path: '/api/v001/books/:id',
    method: 'GET',
  },

  addBook: {
    path: '/api/v001/books',
    method: 'POST',
  },

  updateBook: {
    path: '/api/v001/books/:id',
    method: 'PATCH',
  },

  deleteBook: {
    path: '/api/v001/books/:id',
    method: 'DELETE',
  },
};
