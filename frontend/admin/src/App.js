import React, { Suspense } from "react";
import Header from "./Header";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import AdminRegister from "./AdminRegister";
import Reloader from "./Reloader";
import { useStateValue } from "./StateProvider";
import "./Bookings.css";

const Edithotel = React.lazy(() => import("./Edithotel"));
const AddDetails = React.lazy(() => import("./AddDetails"));
const Home = React.lazy(() => import("./Home"));
const Bookings = React.lazy(() => import("./Bookings"));
const EditRoomPrice = React.lazy(() => import("./EditRoomPrice"));
const Hoteldetails = React.lazy(() => import("./Hoteldetails"));

function App() {
  return (
    <div className="app">
      <Router>
        <Suspense fallback={Reloader}>
          {
            <Switch>
              <Route path="/edithotel">
                <Edithotel />
              </Route>

              <Route path="/bookings">
                <Header /> <Bookings />
              </Route>

              <Route path="/addDetails">
                <Header />
                <AddDetails />
              </Route>

              <Route path="/hotel_details">
                <Header />
                <Hoteldetails />
              </Route>

              <Route path="/" exact>
                <Header />
                <Home />
              </Route>
            </Switch>
          }
        </Suspense>
        <Route path="/adminregister">
          <AdminRegister />
        </Route>
      </Router>
    </div>
  );
}

export default App;
