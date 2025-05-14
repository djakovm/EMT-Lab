import React from 'react';
import { Outlet } from 'react-router-dom';
import { Container, AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const Header = () => {
    return (
        <AppBar position="static" color="primary">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Accommodation Management
                </Typography>
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
        <Box display="flex" flexDirection="column" minHeight="100vh" minWidth="100vw">
            <Header />
            <Container maxWidth={false} disableGutters sx={{ my: 4 }}>
                <Outlet />
            </Container>

            <Footer />
        </Box>
    );
};

export default Layout;