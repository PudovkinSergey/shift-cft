const React = require('react');
const PropTypes = require('prop-types');
const locale = require('core/locale');

const propTypes = { message: PropTypes.string };

const Message = ({ message }) => (
  <div className="message">
    {message || locale.messages.DEFAULT_ERROR}
  </div>
);

Message.propTypes = propTypes;

module.exports = Message;
