import React, { useState, useEffect } from "react";
import { AiFillPhone, AiFillCreditCard } from "react-icons/ai";
import { BsFillPersonFill } from "react-icons/bs";
import { MdEmail, MdEdit } from "react-icons/md";
import { FaHotel } from "react-icons/fa";
import { MdLocationOn } from "react-icons/md";
import {} from "react-icons/fa";
import axios from "axios";
import { useHistory } from "react-router-dom";
import * as config from "./config";

function Hoteldetails() {
  var [types, setTypes] = useState([]);
  var [deleteid, setDeleteID] = useState("");
  var endpoint = config.endpoint;
  var fetchRoomfacilities = endpoint + "/facilities";
  // var fetchRoomfacilities = "http://localhost:3000/facilities";
  //var fetchRoomfacilities = "http://18.212.225.65:8000/facilities";
  var [facilities, setFacilities] = useState([]);
  //var fetchUrl = "http://18.212.225.65:8000/hotel/details";
  // var fetchUrl = "http://localhost:3000/hotel/details";
  var fetchUrl = endpoint + "/hotel/details";

  var [hotel_details, setHotel] = useState([]);
  var fetchRoomType = endpoint + "/room_types";
  // var fetchRoomType = "http://localhost:3000/room_types";

  var history = useHistory();

  // async function deletefacility(e) {
  //   var id_ = e.target.id;
  //   console.log(id_);
  //   const res = await axios.delete(
  //     `http://localhost:3000/facility/delete/${id_}`,
  //     { data: { id: 5 } }
  //   );
  //   console.log(res.data);
  // }

  useEffect(() => {
    async function fetchData() {
      var request = await axios.get(fetchUrl);
      var request = [];
      setHotel(request.data);
    }

    async function fetchType() {
      var request = await axios.get(fetchRoomType);
      var request = [];
      console.log(request.data);
      setTypes(request.data);
    }

    async function fetchFacility() {
      var request = await axios.get(fetchRoomfacilities);
      var request = [];
      console.log(request.data);
      // let payload = [
      //   {
      //     id: 32596,
      //     name: "Queen Suite",
      //     location: "San Jose",
      //     description: "Spacious room",
      //     base_price: 100,
      //     room_type: "suites",
      //     min_guests: 2,
      //     guest_fee: 0,
      //     week_end_surge: 125,
      //     festival_surge: 125,
      //     created_at: "2022-05-15T05:15:32.000Z",
      //     modified_at: null,
      //   },
      //   {
      //     id: 32596,
      //     name: "Queen Suite",
      //     location: "San Jose",
      //     description: "Spacious room",
      //     base_price: 100,
      //     room_type: "suites",
      //     min_guests: 2,
      //     guest_fee: 0,
      //     week_end_surge: 125,
      //     festival_surge: 125,
      //     created_at: "2022-05-15T05:15:32.000Z",
      //     modified_at: null,
      //   },
      //   {
      //     id: 32596,
      //     name: "Queen Suite",
      //     location: "San Jose",
      //     description: "Spacious room",
      //     base_price: 100,
      //     room_type: "suites",
      //     min_guests: 2,
      //     guest_fee: 0,
      //     week_end_surge: 125,
      //     festival_surge: 125,
      //     created_at: "2022-05-15T05:15:32.000Z",
      //     modified_at: null,
      //   },
      // ];
      // setFacilities(payload);
      // console.log(payload);
      // console.log("&^&^&^&^&^&^");
      // // console.log(facilities);
      setFacilities(request.data);
    }

    fetchData();
    fetchFacility();
    fetchType();
  }, [fetchUrl, fetchRoomType]);

  console.log("&^&^&^&^&^&^");
  console.log(facilities);

  var renderFacilityHeader = () => {
    let header = ["Facility Type", "Facility Cost(Surge, Weekend bumps)"];

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

  var renderTypesHeader = () => {
    let header = ["Room Type", "Room Cost"];

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

  return (
    <div>
      <div className="container">
        {/* <h4>Hotel Details</h4>
        <br></br>
        {hotel_details?.map((item) => (
          <div className="table">
            <div className="table-row">
              <div className="table-data">
                <FaHotel /> Hotel Name
              </div>
              <div className="table-data">{item?.hotel_name}</div>
            </div>
            <div className="table-row">
              <div className="table-data">
                <AiFillPhone /> Number
              </div>
              <div className="table-data">{item.hotel_phone}</div>
            </div>
            <div className="table-row">
              <div className="table-data">
                <MdEmail /> EmailID
              </div>
              <div className="table-data">{item.hotel_email}</div>
            </div>
            <div className="table-row">
              <div className="table-data">
                <MdLocationOn /> Address
              </div>
              <div className="table-data">{item.hotel_addr}</div>
            </div>
          </div> 
        ) )}
        <button
          className="button"
          style={{
            backgroundColor: "#6DECAF",
            border: "none",
            padding: "10px 20px",
          }}
          onClick={() => {
            history.push("/edithotel");
          }}
        >
          <MdEdit /> Edit
        </button>
        */}
        <br />
        <br />
        <h4>Room Facilities </h4>
        <br />
        <div className="table">
          <div className="table-header">{renderFacilityHeader()}</div>
          {facilities?.map((item) => (
            <div className="table-row">
              <div className="table-data">{item.name}</div>
              <div className="table-data">
                {item.base_price} +<small> ({item.festival_surge},</small>{" "}
                <small> {item.week_end_surge}) </small>
              </div>
            </div>
          ))}
        </div>
        <br />
        <h4>Room Types </h4>
        <br></br>
        <div className="table">
          <div className="table-header">{renderTypesHeader()}</div>
          {types?.map((item) => (
            <div className="table-row">
              <div className="table-data"> {item.type_name}</div>
              <div className="table-data">{item.cost}</div>
            </div>
          ))}
        </div>
        {/* <button className="button" style={{"backgroundColor":"#6DECAF","border":"none","padding":"10px 20px"}}> <MdEdit /> Edit Room Cost</button>
           
               */}
      </div>
    </div>
  );
}

export default Hoteldetails;
