import axios from "axios";
import * as Const from "../constants";

const semiEndpoint = Const.semiEndpoint;

export function getAllRooms() {
  return axios.get(semiEndpoint + "/rooms");
}

export function getAmenities() {
  return axios.get(semiEndpoint + "/bookings/ammenities");
}
