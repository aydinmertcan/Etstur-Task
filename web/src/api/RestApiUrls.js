import axios from "axios";
import * as URL from "./RestApiUrls";

export const login = (body) => {
  return axios.post(URL.MEDIA_URL, body).then((data) => console.log(data));
};
