import { useState, useEffect } from "react";
import AccommodationRepository from "../api/accommodationRepository";

export function useAccommodations() {
    const [items, setItems]     = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        setLoading(true);
        const { data } = await AccommodationRepository.getAll();
        setItems(data);
        setLoading(false);
    };

    useEffect(() => { fetchAll(); }, []);

    const create = async d => {
        await AccommodationRepository.create(d);
        await fetchAll();
    };
    const update = async (id, d) => {
        await AccommodationRepository.update(id, d);
        await fetchAll();
    };
    const remove = async id => {
        await AccommodationRepository.remove(id);
        await fetchAll();
    };

    return { items, loading, create, update, remove };
}
