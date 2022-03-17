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

export const uploadFile = (file) => {
  const formData = new FormData();
  formData.append("file", file);
  return axios
    .post(URL.UPLOADFILE, formData, {
      headers: {
        "content-type": "multipart/form-data",
      },
    })
    .then((data) => console.log(data));
};
