import axios from "axios";
import * as URL from "./RestApiUrls";

export const getToken = (username) => {
  const body = { username };
  return axios.post(URL.TOKEN, body);
};

export const setAuthorizationHeader = (token) => {
  const authorizationHeaderValue = `Bearer ${token}`;
  axios.defaults.headers["Authorization"] = authorizationHeaderValue;
  console.log(token);
};

export const getAllFiles = () => {
  return axios.get(URL.GETALLFILES);
};
