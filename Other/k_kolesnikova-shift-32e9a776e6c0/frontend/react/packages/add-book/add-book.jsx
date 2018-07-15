const React = require('react');
const PropTypes = require('prop-types');
const locale = require('core/locale').addBook;

const propTypes = { addBook: PropTypes.func.isRequired };

const AddBook = ({ addBook }) => {
  const elements = {};

  const onSubmit = (event) => {
    event.preventDefault();

    if (!elements.name.value.trim() || !elements.author.value.trim()
    || !elements.genre.value.trim() || !elements.pages.value.trim()) {
      return;
    }

    addBook({
      name: elements.name.value.trim(),
      author: elements.author.value.trim(),
      genre: elements.genre.value.split(',').map((item) => item.trim()),
      pages: Number(elements.pages.value),
    });

    elements.name.value = '';
    elements.author.value = '';
    elements.genre.value = '';
    elements.pages.value = '';
  };

  return (
    <form className="add-book" onSubmit={onSubmit}>
      {
        ['name', 'author', 'genre', 'pages'].map((key) => (
          <label className="add-book_field" key={key}>
            <div className="add-book_field_label">{locale[key]}</div>
            <input
              className="add-book_field_control"
              type="text"
              name={key}
              ref={(el) => { elements[key] = el; }}
            />
          </label>
        ))
      }

      <button className="add-book_button" type="submit">{locale.buttonLabel}</button>
    </form>
  );
};

AddBook.propTypes = propTypes;

module.exports = AddBook;
