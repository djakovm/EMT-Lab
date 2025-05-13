import axiosInstance from "../axois/axois.js";

const HostRepository = {
    getAll: () => axiosInstance.get("/hosts"),
    getById: (id) => axiosInstance.get(`/hosts/${id}`),
    create: (data) => axiosInstance.post("/hosts", data),
    update: (id, data) => axiosInstance.put(`/hosts/${id}`, data),
    remove: (id) => axiosInstance.delete(`/hosts/${id}`)
};

export default HostRepository;
