import axios from "axios";
import * as Const from "../constants";

const semiEndpoint = Const.semiEndpoint;
// Stub for making the REST call
export function confirmBooking(dataJson, token) {
  axios.defaults.headers.common["authorization"] = token;
  // return axios.post(semiEndpoint + "/checkout", dataJson);
  return axios.post(semiEndpoint + "/checkout", dataJson);
}
