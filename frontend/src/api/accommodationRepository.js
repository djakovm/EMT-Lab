import axiosInstance from "../axois/axois.js";

const AccommodationRepository = {
    getAll: () => axiosInstance.get("/accommodations"),
    getById: (id) => axiosInstance.get(`/accommodations/${id}`),
    create: (data) => axiosInstance.post("/accommodations", data),
    update: (id, data) => axiosInstance.put(`/accommodations/${id}`, data),
    remove: (id) => axiosInstance.delete(`/accommodations/${id}`)
};

export default AccommodationRepository;
