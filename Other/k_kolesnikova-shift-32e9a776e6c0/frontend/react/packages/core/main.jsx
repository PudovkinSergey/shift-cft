const React = require('react');
const Router = require('react-router-dom').BrowserRouter;
const { Route } = require('react-router-dom');
const Books = require('books/books');
const About = require('about/about');
const Menu = require('menu/menu');

const routes = [
  { path: '/', component: Books, exact: true },
  { path: '/about', component: About },
];

const Main = () => (
  <Router>
    <div className="main">
      {
        routes.map((route) => (
          <Route
            path={route.path}
            exact={route.exact || false}
            component={route.component}
            key={route.path}
          />
        ))
      }
      <Menu />
    </div>
  </Router>
);

module.exports = Main;
