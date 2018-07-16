const React = require('react');
const PropTypes = require('prop-types');
const className = require('class-name/class-name');
const locale = require('core/locale').book;

const propTypes = {
  book: PropTypes.object.isRequired,
  toggleBookAvailable: PropTypes.func.isRequired,
  deleteBook: PropTypes.func.isRequired,
};

const Book = ({ book, toggleBookAvailable, deleteBook }) => (
  <li className={className({ name: 'book', mods: { unavailable: !book.isAvailable } })}>
    <div className="book_name">{locale.name} {book.name}</div>
    <div className="book_author">{locale.author} {book.author}</div>
    <div className="book_genres">{locale.genre} {book.genre.join(', ')}</div>
    <div className="book_pages">{locale.pages} {book.pages}</div>
    <div className="book_available" onClick={toggleBookAvailable} data-book-id={book.id}>
      {book.isAvailable ? locale.hold : locale.unhold}
    </div>
    <div className="book_delete" onClick={deleteBook} data-book-id={book.id}>X</div>
  </li>
);

Book.propTypes = propTypes;

module.exports = Book;
