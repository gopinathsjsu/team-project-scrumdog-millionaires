import React, { useState, useEffect } from "react";
import * as config from "./config";
import "./Profile.css";
import "./Bookings.css";
import { AiFillPhone, AiFillCreditCard } from "react-icons/ai";
import { BsFillPersonFill } from "react-icons/bs";
import { MdEmail, MdEdit } from "react-icons/md";
import { FaHotel } from "react-icons/fa";
import axios from "axios";

import { fetch_bookings } from "./controllers/bookings";

import { useStateValue } from "./StateProvider";
import { Link, useHistory } from "react-router-dom";

function Bookings() {
  var endpoint = config.endpoint;
  var fetchbookings = endpoint + "/bookings/admin";
  var [booking_details, setBookings] = useState([]);

  useEffect(() => {
    async function bookings() {
      var request = await axios.get(fetchbookings);
      const { status, data } = await fetch_bookings();
      console.info("Bookings.js::useEffect::Bookings = ", data);
      if (status === 200) {
        // setBookings(request.data);
        setBookings(data.data);
      }
    }
    bookings();
  }, []);

  var formatDate = (date) => {
    var d = new Date(date);
    return d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
  };

  var history = useHistory();

  var renderTableHeader = () => {
    let header = [
      "Room ID",
      "Booking Date",
      "Start Date",
      "End Date",
      "Amount",
    ];

    return header.map((key_) => {
      return (
        <div className="header__item">
          <a id="losses" className="filter__link filter__link--number" href="#">
            {key_}
          </a>
        </div>
      );
    });
  };

  var formatDate = (date) => {
    var d = new Date(date);
    return d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
  };

  var renderTable = () => {
    return booking_details.map((item) => {
      var { room_id, booking_date, start_date, end_date, amount } = item;
      return (
        <div className="table-row">
          <div className="table-data">{room_id}</div>
          <div className="table-data">{formatDate(booking_date)}</div>
          <div className="table-data">{formatDate(start_date)}</div>
          <div className="table-data">{formatDate(end_date)}</div>
          <div className="table-data">{amount}</div>
        </div>
      );
    });
  };

  return (
    <div className="container">
      <br />
      <h4 style={{ textAlign: "left", color: "" }}>
        <FaHotel /> Customer Reservation Details
      </h4>
      <br />
      <div className="table">
        <div className="table-header">{renderTableHeader()}</div>
        <div className="table-content">{renderTable()}</div>
      </div>
    </div>
  );
}

export default Bookings;
