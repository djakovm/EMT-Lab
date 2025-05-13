import React from 'react';
import { Outlet } from 'react-router-dom';
import { Container, AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';  // for linking with react-router

const Header = () => {
    return (
        <AppBar position="static" color="primary">
            <Toolbar>
                {/* App name/title on the left */}
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Accommodation Management
                </Typography>
                {/* Navigation links */}
                <Button color="inherit" component={RouterLink} to="/">
                    Home
                </Button>
                <Button color="inherit" component={RouterLink} to="/accommodations">
                    Accommodations
                </Button>
                <Button color="inherit" component={RouterLink} to="/hosts">
                    Hosts
                </Button>
                <Button color="inherit" component={RouterLink} to="/countries">
                    Countries
                </Button>
            </Toolbar>
        </AppBar>
    );
};

const Footer = () => {
    return (
        <Box component="footer" sx={{ py: 2, textAlign: 'center', mt: 'auto', backgroundColor: '#f5f5f5' }}>
            <Typography variant="body2" color="textSecondary">
                © 2025 Accommodation Management App
            </Typography>
        </Box>
    );
};

const Layout = () => {
    return (
        <Box display="flex" flexDirection="column" minHeight="100vh">
            {/* Header on top */}
            <Header />
            {/* Main content: we wrap it in a Container and render the current page */}
            <Container maxWidth="lg" sx={{ my: 4 }}>
                <Outlet /> {/* This will render the component for the current route */}
            </Container>
            {/* Footer at bottom */}
            <Footer />
        </Box>
    );
};

export default Layout;