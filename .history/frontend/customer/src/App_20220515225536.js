<<<<<<< HEAD
import React from "react";
import "./App.css";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/bootstrap/dist/js/bootstrap.min.js";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Rooms from "./pages/Rooms";
import SingleRoom from "./pages/SingleRoom";
import Error from "./pages/Error";
import Navbar from "./components/Navbar";
import Booknow from "./pages/Booknow";
import SignUp from "./pages/SignUp";
import FormCopy from "./pages/FormCopy";
import BookingList from "./pages/BookingList";
=======
import logo from './logo.svg';
import './App.css';
>>>>>>> main

function App() {
  return (
    <div className="App">
<<<<<<< HEAD
      <BrowserRouter>
        <Navbar />
        <Switch>
          <Route exact path="/" component={Rooms} />
          <Route exact path="/rooms/" component={Rooms} />
          <Route exact path="/SignUp/" component={SignUp} />
          <Route exact path="/login/" component={FormCopy} />

          <Route exact path="/rooms/:slug" component={SingleRoom} />
          <Route exact path="/booknow/:slug" component={Booknow} />
          <Route exact path="/bookings/" component={BookingList} />
          <Route component={Error} />
        </Switch>
      </BrowserRouter>
=======
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
>>>>>>> main
    </div>
  );
}

export default App;
