import axiosInstance from "../axois/axois.js";

const AccommodationRepository = {
    getAll: () => axiosInstance.get("/accommodations"),
    getById: (id) => axiosInstance.get(`/accommodations/${id}`),
    create: (data) => axiosInstance.post("/accommodations", data),
    update: (id, data) => axiosInstance.put(`/accommodations/${id}`, data),
    remove: (id) => axiosInstance.delete(`/accommodations/${id}`),
    rent: (id, guestId, from, to) =>
        axiosInstance.post(`/accommodations/${id}/rent`, null, {
            params: { guestId, from, to }
        })

};

export default AccommodationRepository;
