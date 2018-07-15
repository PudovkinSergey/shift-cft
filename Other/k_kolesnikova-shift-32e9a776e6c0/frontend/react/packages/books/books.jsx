const React = require('react');
const Book = require('./book.jsx');
const AddBook = require('add-book/add-book.jsx');
const className = require('class-name/class-name');
const locale = require('core/locale').books;
const { visibilityFilters, responseStatuses } = require('core/constants');
const createRequest = require('core/create-request');
const Message = require('message/message');

class Books extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      books: [],
      isLoading: true,
      activeFilter: visibilityFilters.ALL,
      message: null,
    };

    this.addBook = this.addBook.bind(this);
    this.toggleBookAvailable = this.toggleBookAvailable.bind(this);
    this.deleteBook = this.deleteBook.bind(this);
    this.changeFilter = this.changeFilter.bind(this);
  }

  componentDidMount() {
    createRequest('fetchBooks').then((response) => {
      this.setState({ books: response.data || [], isLoading: false, message: response.message });
    });
  }

  addBook(data) {
    const { books } = this.state;

    this.setState({ isLoading: true });
    createRequest('addBook', {}, data).then((response) => {
      if (response.status === responseStatuses.OK) {
        books.push(response.data);
        this.setState({ books, isLoading: false, message: response.message });
      } else {
        this.setState({ isLoading: false, message: response.message });
      }
    });
  }

  toggleBookAvailable(event) {
    const id = event.currentTarget.dataset.bookId;
    const { books } = this.state;
    let book = books.find((item) => item.id === id);

    this.setState({ isLoading: true });
    createRequest('updateBook', { id }, { isAvailable: !book.isAvailable }).then((response) => {
      if (response.status === responseStatuses.OK) {
        book = Object.assign(book, response.data);
        this.setState({ books, isLoading: false, message: response.message });
      } else {
        this.setState({ isLoading: false, message: response.message });
      }
    });
  }

  deleteBook(event) {
    event.preventDefault();
    event.stopPropagation();
    const id = event.currentTarget.dataset.bookId;

    let { books } = this.state;

    this.setState({ isLoading: true });
    createRequest('deleteBook', { id }).then((response) => {
      if (response.status === responseStatuses.OK) {
        books = books.filter((item)=> item.id !== id);
        this.setState({ books, isLoading: false, message: response.message });
      } else {
        this.setState({ isLoading: false, message: response.message });
      }
    });
  }

  changeFilter(event) {
    const newFilter = event.currentTarget.dataset.filterCode;
    this.setState({ activeFilter: newFilter });
  }

  render() {
    const { activeFilter, books, isLoading, message } = this.state;

    console.log(this.state);

    const filteredBooks = books.filter((book) => {
      switch (activeFilter) {
        case visibilityFilters.AVAILABLE:
          return book.isAvailable;

        case visibilityFilters.NOT_AVAILABLE:
          return !book.isAvailable;

        case visibilityFilters.ALL:
        default:
          return true;
      }
    });

    return (
      <div className={className({ name: 'books', mods: { loading: isLoading } })}>
        <div className="books_filters">
          {['ALL', 'AVAILABLE', 'NOT_AVAILABLE'].map((item) => (
            <div
              className={className({
                name: 'books_filters_item',
                mods: { active: activeFilter === visibilityFilters[item] },
              })}
              onClick={this.changeFilter}
              data-filter-code={visibilityFilters[item]}
              key={item}
            >
              {locale.filters[visibilityFilters[item]]}
            </div>
          ))}
        </div>
        {
          filteredBooks.length > 0
          && (
          <ul className="books_list">
            {filteredBooks.map((book) => (
              <Book book={book} toggleBookAvailable={this.toggleBookAvailable} deleteBook={this.deleteBook} key={book.id} />
            ))}
          </ul>
          )
        }
        {
          !message && !isLoading && filteredBooks.length === 0
          && <div className="books_empty">{locale.empty}</div>
        }
        {
          !isLoading && message
          && <Message message={message} />
        }
        <AddBook addBook={this.addBook} />
      </div>
    );
  }
}

module.exports = Books;
