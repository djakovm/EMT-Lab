import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    },
    auth: {
        username: 'user',
        password: 'user'
    }
});

export default axiosInstance;
