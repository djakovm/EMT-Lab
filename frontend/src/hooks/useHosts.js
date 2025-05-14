import { useState, useEffect } from "react";
import HostRepository from "../api/hostRepository";

export function useHosts() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchAll = async () => {
        try {
            setLoading(true);
            const { data } = await HostRepository.getAll();
            setItems(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error("Failed to fetch hosts:", error);
            setItems([]);
        } finally {
            setLoading(false);
        }
    };

    const create = async (d) => {
        try {
            await HostRepository.create(d);
            await fetchAll();
        } catch (error) {
            console.error("Failed to create host:", error);
        }
    };

    const update = async (id, d) => {
        try {
            await HostRepository.update(id, d);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to update host ${id}:`, error);
        }
    };

    const remove = async (id) => {
        try {
            await HostRepository.remove(id);
            await fetchAll();
        } catch (error) {
            console.error(`Failed to delete host ${id}:`, error);
        }
    };

    useEffect(() => {
        fetchAll();
    }, []);

    return { items, loading, create, update, remove };
}
