import React from 'react';
import { Typography, Box } from '@mui/material';

const HomePage = () => {
    return (
        <Box textAlign="center" mt={4}>
            <Typography variant="h4" gutterBottom>
                Welcome to the Accommodation Management System
            </Typography>
            <Typography variant="body1">
                Use the navigation above to manage Accommodations, Hosts, and Countries.
            </Typography>
        </Box>
    );
};

export default HomePage;
