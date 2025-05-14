import { useState, useEffect } from "react";
import CountryRepository from "../api/countryRepository";

export function useCountries() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        try {
            setLoading(true);
            const { data } = await CountryRepository.getAll();
            setItems(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error("Failed to fetch countries:", error);
            setItems([]);
        } finally {
            setLoading(false);
        }
    };

    const create = async (d) => {
        try {
            await CountryRepository.create(d);
            await fetchAll();
        } catch (error) {
            console.error("Failed to create country:", error);
        }
    };

    const update = async (id, d) => {
        try {
            await CountryRepository.update(id, d);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to update country ${id}:`, error);
        }
    };

    const remove = async (id) => {
        try {
            await CountryRepository.remove(id);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to delete country ${id}:`, error);
        }
    };

    useEffect(() => {
        fetchAll();
    }, []);

    return { items, loading, create, update, remove };
}
