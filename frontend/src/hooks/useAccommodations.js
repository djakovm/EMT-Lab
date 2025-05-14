import { useState, useEffect } from "react";
import AccommodationRepository from "../api/accommodationRepository";

export function useAccommodations() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        try {
            setLoading(true);
            const { data } = await AccommodationRepository.getAll();
            console.log(data)
            setItems(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error("Failed to fetch accommodations:", error);
            setItems([]);
        } finally {
            setLoading(false);
        }
    };

    const create = async (d) => {
        try {
            await AccommodationRepository.create(d);
            await fetchAll();
        } catch (error) {
            console.error("Failed to create accommodation:", error);
        }
    };

    const update = async (id, d) => {
        try {
            await AccommodationRepository.update(id, d);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to update accommodation ${id}:`, error);
        }
    };

    const remove = async (id) => {
        try {
            await AccommodationRepository.remove(id);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to delete accommodation ${id}:`, error);
        }
    };

    const rent = async (id, guestId, from, to) => {
        try {
            await AccommodationRepository.rent(id, guestId, from, to);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to rent accommodation ${id}:`, error);
        }
    };


    useEffect(() => {
        fetchAll();
    }, []);

    return { items, loading, create, update, remove };
}
