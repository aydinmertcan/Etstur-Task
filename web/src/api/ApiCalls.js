import axios from "axios";
import * as URL from "./RestApiUrls";

export const getToken = (username) => {
  const body = { username };
  return axios.post(URL.TOKEN, body);
};

export const setAuthorizationHeader = (token) => {
  const authorizationHeaderValue = `Bearer ${token}`;
  axios.defaults.headers["Authorization"] = authorizationHeaderValue;
  localStorage.setItem("access_token", token);
};

export const getAllFiles = () => {
  setAuthorizationHeader(localStorage.getItem("access_token"));
  return axios.get(URL.GETALLFILES);
};

export const uploadFile = (file) => {
  const formData = new FormData();
  formData.append("file", file);
  return axios.post(URL.UPLOADFILE, formData, {
    headers: {
      "content-type": "multipart/form-data",
    },
  });
};

export const updateFile = (id, file) => {
  const formData = new FormData();
  formData.append("file", file);
  return axios.put(URL.UPDATEFILE + id, formData, {
    headers: {
      "content-type": "multipart/form-data",
    },
  });
};

export const deleteFile = (id) => {
  return axios.delete(URL.DELETEFILE + id);
};
