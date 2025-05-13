// src/App.jsx
import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Layout from './layout/Layout.jsx';
import HomePage from './pages/HomePage/HomePage.jsx';
import AccommodationsPage from './pages/AccommodationsPage/AccommodationsPage.jsx';
import HostsPage from './pages/HostsPage/HostsPage.jsx';
import CountriesPage from './pages/CountriesPage/CountriesPage.jsx';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout />}>
                    <Route index element={<HomePage />} />
                    <Route path="accommodations" element={<AccommodationsPage />} />
                    <Route path="hosts" element={<HostsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
