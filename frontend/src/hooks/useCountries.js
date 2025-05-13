import { useState, useEffect } from "react";
import CountryRepository from "../api/countryRepository";

export function useCountries() {
    const [items, setItems]     = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        setLoading(true);
        const { data } = await CountryRepository.getAll();
        setItems(data);
        setLoading(false);
    };

    useEffect(() => { fetchAll(); }, []);

    const create = async d => {
        await CountryRepository.create(d);
        await fetchAll();
    };
    const update = async (id, d) => {
        await CountryRepository.update(id, d);
        await fetchAll();
    };
    const remove = async id => {
        await CountryRepository.remove(id);
        await fetchAll();
    };

    return { items, loading, create, update, remove };
}
