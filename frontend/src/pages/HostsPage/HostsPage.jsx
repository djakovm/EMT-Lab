import React, { useState } from 'react';
import {
    Typography, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper,
    Dialog, DialogTitle, DialogContent, DialogActions, TextField, Select, MenuItem
} from '@mui/material';
import { useHosts } from '../../hooks/useHosts';
import { useCountries } from '../../hooks/useCountries';

const HostsPage = () => {
    const { items: hosts, create, update, remove } = useHosts();
    const { items: countries } = useCountries();

    const [isDialogOpen, setDialogOpen] = useState(false);
    const [editingHost, setEditingHost] = useState(null);

    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [countryId, setCountryId] = useState('');

    const handleOpenAdd = () => {
        setEditingHost(null);
        setName('');
        setSurname('');
        setCountryId('');
        setDialogOpen(true);
    };

    const handleOpenEdit = (host) => {
        setEditingHost(host);
        setName(host.name || '');
        setSurname(host.surname || '');
        setCountryId(host.country ? host.country.id : '');
        setDialogOpen(true);
    };

    const handleCloseDialog = () => {
        setDialogOpen(false);
    };

    const handleSubmit = () => {
        const formData = { name, surname, countryId };
        if (editingHost) {
            update(editingHost.id, formData);
        } else {
            create(formData);
        }
        setDialogOpen(false);
    };

    const handleDelete = (id) => {
        if (window.confirm('Delete this host?')) {
            remove(id);
        }
    };

    return (
        <div>
            <Typography variant="h5" gutterBottom>Hosts</Typography>
            <Button variant="contained" onClick={handleOpenAdd} sx={{ mb: 2 }}>
                Add New Host
            </Button>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Surname</TableCell>
                            <TableCell>Country</TableCell>
                            <TableCell align="center">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {hosts && hosts.map(host => (
                            <TableRow key={host.id}>
                                <TableCell>{host.name}</TableCell>
                                <TableCell>{host.surname}</TableCell>
                                <TableCell>{host.country ? host.country.name : ''}</TableCell>
                                <TableCell align="center">
                                    <Button size="small" onClick={() => handleOpenEdit(host)}>Edit</Button>
                                    <Button size="small" color="error" onClick={() => handleDelete(host.id)}>Delete</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                        {hosts && hosts.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={4} align="center">No hosts found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog open={isDialogOpen} onClose={handleCloseDialog} fullWidth maxWidth="sm">
                <DialogTitle>{editingHost ? 'Edit Host' : 'Add New Host'}</DialogTitle>
                <DialogContent dividers>
                    <TextField
                        label="First Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Last Name"
                        value={surname}
                        onChange={(e) => setSurname(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <Select
                        label="Country"
                        value={countryId}
                        onChange={(e) => setCountryId(e.target.value)}
                        fullWidth
                        margin="normal"
                        displayEmpty
                    >
                        <MenuItem value="" disabled>Select Country</MenuItem>
                        {countries && countries.map(country => (
                            <MenuItem key={country.id} value={country.id}>
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button onClick={handleSubmit} variant="contained">
                        {editingHost ? 'Save Changes' : 'Add Host'}
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default HostsPage;
