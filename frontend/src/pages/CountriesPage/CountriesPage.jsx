// frontend/src/pages/CountriesPage.jsx
import React, { useState } from 'react';
import {
    Typography, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper,
    Dialog, DialogTitle, DialogContent, DialogActions, TextField
} from '@mui/material';
import { useCountries } from '../../hooks/useCountries';

const CountriesPage = () => {
    const { items: countries, create, update, remove } = useCountries();

    const [isDialogOpen, setDialogOpen] = useState(false);
    const [editingCountry, setEditingCountry] = useState(null);
    // Form fields
    const [name, setName] = useState('');
    const [continent, setContinent] = useState('');

    const handleOpenAdd = () => {
        setEditingCountry(null);
        setName('');
        setContinent('');
        setDialogOpen(true);
    };

    const handleOpenEdit = (country) => {
        setEditingCountry(country);
        setName(country.name || '');
        setContinent(country.continent || '');
        setDialogOpen(true);
    };

    const handleCloseDialog = () => {
        setDialogOpen(false);
    };

    const handleSubmit = () => {
        const formData = { name, continent };
        if (editingCountry) {
            update(editingCountry.id, formData);
        } else {
            create(formData);
        }
        setDialogOpen(false);
    };

    const handleDelete = (id) => {
        if (window.confirm('Delete this country?')) {
            remove(id);
        }
    };

    return (
        <div>
            <Typography variant="h5" gutterBottom>Countries</Typography>
            <Button variant="contained" onClick={handleOpenAdd} sx={{ mb: 2 }}>
                Add New Country
            </Button>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Continent</TableCell>
                            <TableCell align="center">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {countries && countries.map(country => (
                            <TableRow key={country.id}>
                                <TableCell>{country.name}</TableCell>
                                <TableCell>{country.continent}</TableCell>
                                <TableCell align="center">
                                    <Button size="small" onClick={() => handleOpenEdit(country)}>Edit</Button>
                                    <Button size="small" color="error" onClick={() => handleDelete(country.id)}>Delete</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                        {countries && countries.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={3} align="center">No countries found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog open={isDialogOpen} onClose={handleCloseDialog} fullWidth maxWidth="sm">
                <DialogTitle>{editingCountry ? 'Edit Country' : 'Add New Country'}</DialogTitle>
                <DialogContent dividers>
                    <TextField
                        label="Country Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Continent"
                        value={continent}
                        onChange={(e) => setContinent(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button onClick={handleSubmit} variant="contained">
                        {editingCountry ? 'Save Changes' : 'Add Country'}
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default CountriesPage;
