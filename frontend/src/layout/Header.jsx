import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header style={{ padding: '1rem', backgroundColor: '#1976d2', color: 'white' }}>
            <nav style={{ display: 'flex', gap: '1rem' }}>
                <Link to="/" style={{ color: 'white' }}>Home</Link>
                <Link to="/accommodations" style={{ color: 'white' }}>Accommodations</Link>
                <Link to="/hosts" style={{ color: 'white' }}>Hosts</Link>
                <Link to="/countries" style={{ color: 'white' }}>Countries</Link>
            </nav>
        </header>
    );
};

export default Header;