import { useState, useEffect } from "react";
import HostRepository from "../api/hostRepository";

export function useHosts() {
    const [items, setItems]     = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        setLoading(true);
        const { data } = await HostRepository.getAll();
        setItems(data);
        setLoading(false);
    };

    useEffect(() => { fetchAll(); }, []);

    const create = async d => {
        await HostRepository.create(d);
        await fetchAll();
    };
    const update = async (id, d) => {
        await HostRepository.update(id, d);
        await fetchAll();
    };
    const remove = async id => {
        await HostRepository.remove(id);
        await fetchAll();
    };

    return { items, loading, create, update, remove };
}
