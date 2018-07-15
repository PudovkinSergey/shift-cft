const React = require('react');
const { Link } = require('react-router-dom');

const Menu = () => (
  <div className="menu">
    <Link className="menu_item" to="/">Книги</Link>
    <Link className="menu_item" to="/about">О программе</Link>
  </div>
);

module.exports = Menu;
