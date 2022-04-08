
import React from "react";
import RoomsList from "./RoomsList";
import Loading from "./Loading";
import { useState, useEffect } from "react";
import { getAllRooms } from "../controllers/rooms";
import Title from "../pages/Title";

function RoomsContainer({ ...props }) {
    const [rooms, setRooms] = useState(null);
  
    useEffect(() => {
      getAllRooms()
        .then((res) => setRooms(res.data.data))
        .catch((err) => console.log(err));
    }, []);
  
    
    if (!rooms) {
      return <Loading />;
    }
  
    console.log("rooms: ", rooms);
    return (
      <>
      
      <Title title="Rooms"></Title>
        <RoomsList rooms={rooms} />
      </>
    );
  }
  export default RoomsContainer;