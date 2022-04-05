import axios from "axios";
import * as Const from "../constants";

const semiEndpoint = Const.semiEndpoint;

export function registerUser(dataJson) {
  return axios.post(semiEndpoint + "/register", dataJson);
}

export function loginUser(dataJson) {
  return axios.post(semiEndpoint + "/login", dataJson);
}
