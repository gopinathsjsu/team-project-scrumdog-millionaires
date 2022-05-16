import React, { useState, useEffect } from "react";
import axios from "axios";
import "./adddetails.css";
import * as config from "./config";
import ProgressBar from "react-bootstrap/ProgressBar";
import {
  ToastsContainer,
  ToastsStore,
  ToastsContainerPosition,
} from "react-toasts";
function AddDetails() {
  var [price, setPrice] = useState("");
  var [minGuests, setMinGuests] = useState("");
  var [roomType, setRoomType] = useState("");
  var [roomFacility, setRoomFacility] = useState("");
  var [roomImage, setRoomImage] = useState("");
  var [facility, setFacility] = useState("");
  var [facility_details, setFacilityDetails] = useState([]);
  var endpoint = config.endpoint;
  var fetchRoomfacilities = endpoint + "/bookings";
  var [uploadPercentage, setPercent] = useState(0);

  async function addnewroom() {
    var request = await axios.post(endpoint + "/rooms", {
      name: facility,
      room_type: roomType,
      basePrice: price,
      min_guests: minGuests,
    });
    ToastsStore.success(`Room Added Successfully ${request.data}!`);
  }

  var uploadFile = ({ target: { files } }) => {
    let data = new FormData();
    data.append("profile", files[0]);

    const options = {
      onUploadProgress: (progressEvent) => {
        console.log(progressEvent);
        const { loaded, total } = progressEvent;
        let percent = Math.floor((loaded * 100) / total);
        console.log(percent);
        if (percent < 100) {
          setPercent(percent);
        }
      },
    };

    axios.post("http://localhost:8000/upload", data, options).then((res) => {
      console.log(res.data.profile_url);
      setRoomImage(res.data.profile_url);
      setPercent(100);
      setTimeout(() => {
        setPercent(0);
      }, 2000);
      ToastsStore.success(`Image Upload Successful`);
    });
  };

  useEffect(() => {
    async function fetchFacility() {
      var request = await axios.get(fetchRoomfacilities);
      setFacilityDetails(request.data);
    }
    fetchFacility();
  }, [fetchRoomfacilities]);

  return (
    <div className="container">
      <ToastsContainer
        store={ToastsStore}
        position={ToastsContainerPosition.TOP_CENTER}
      />
      <h4>Add New Room </h4>
      <br></br>
      <div className="add" style={{ marginTop: "15px", width: "56%" }}>
        <label
          className="label"
          style={{ paddingLeft: "0px", fontWeight: "bold" }}
        >
          Name:
        </label>
        <input
          type="text"
          className="p_input"
          value={facility}
          onChange={(e) => {
            setFacility(e.target.value);
          }}
        />

        <label
          className="label"
          style={{ paddingLeft: "0px", fontWeight: "bold" }}
        >
          Cost:
        </label>
        <input
          type="text"
          className="p_input"
          value={price}
          onChange={(e) => {
            setPrice(e.target.value);
          }}
        />

        <label
          className="label"
          style={{ paddingLeft: "0px", fontWeight: "bold" }}
        >
          Minimum Guests:
        </label>
        <input
          type="text"
          className="p_input"
          value={minGuests}
          onChange={(e) => {
            setMinGuests(e.target.value);
          }}
        />

        {/* <button
          style={{
            backgroundColor: "#392F5A",
            color: "white",
            padding: "3px 15px",
            marginLeft: "10px",
          }}
          onClick={addfacility}
        >
          Add
        </button> */}
      </div>

      <br />
      <div className="container-fluid add">
        <label style={{ fontWeight: "bold", marginLeft: "-230px" }}>
          Room Type:
        </label>
        <br></br>
        <select
          onChange={(e) => setRoomType(parseInt(e.target.value))}
          style={{ marginLeft: "-230px" }}
        >
          <option value={1}>A/C</option>
          <option value={2}>Non A/C</option>
        </select>
        <br /> <br />
        {/* <label style={{ fontWeight: "bold", marginLeft: "-230px" }}>
          Room Facilities:
        </label>
        <br></br>
        <select
          onChange={(e) => {
            setRoomFacility(parseInt(e.target.value));
          }}
          style={{ marginLeft: "-230px" }}
        >
          {facility_details.map((item) => (
            <option value={item.facility_id}>{item.facility}</option>
          ))}
        </select>
        <br></br> */}
        <button
          onClick={addnewroom}
          style={{
            background: "#392F5A",
            color: "white",
            padding: "5px 15px",
          }}
        >
          Add Room
        </button>
      </div>
    </div>
  );
}

export default AddDetails;
