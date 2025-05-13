import axiosInstance from "../axois/axois.js";

const CountryRepository = {
    getAll: () => axiosInstance.get("/countries"),
    getById: (id) => axiosInstance.get(`/countries/${id}`),
    create: (data) => axiosInstance.post("/countries", data),
    update: (id, data) => axiosInstance.put(`/countries/${id}`, data),
    remove: (id) => axiosInstance.delete(`/countries/${id}`)
};

export default CountryRepository;
