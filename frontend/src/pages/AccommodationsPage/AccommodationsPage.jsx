// frontend/src/pages/AccommodationsPage.jsx
import React, { useState } from 'react';
import {
    Typography, Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper,
    Dialog, DialogTitle, DialogContent, DialogActions, TextField, Select, MenuItem
} from '@mui/material';
import { useAccommodations } from '../../hooks/useAccommodations';

const AccommodationsPage = () => {
    // Use custom hook to get data and CRUD functions
    const { accommodations, createAccommodation, updateAccommodation, deleteAccommodation } = useAccommodations();

    // State for dialog visibility and form data
    const [isDialogOpen, setDialogOpen] = useState(false);
    const [editingItem, setEditingItem] = useState(null); // if not null, we're editing this item

    // Form field states (for simplicity, using separate state per field)
    const [name, setName] = useState('');
    const [category, setCategory] = useState('');
    const [hostId, setHostId] = useState('');        // assuming we'll use an ID for host (requires host list)
    const [numRooms, setNumRooms] = useState('');
    const [isAvailable, setIsAvailable] = useState(false);

    // Open dialog for adding a new accommodation
    const handleOpenAdd = () => {
        setEditingItem(null);
        setName('');
        setCategory('');
        setHostId('');
        setNumRooms('');
        setIsAvailable(false);
        setDialogOpen(true);
    };

    // Open dialog for editing an existing accommodation
    const handleOpenEdit = (accommodation) => {
        setEditingItem(accommodation);
        setName(accommodation.name || '');
        setCategory(accommodation.category || '');
        // If accommodation.host is an object, assume it has an id or use its name
        setHostId(accommodation.host?.id || '');
        setNumRooms(accommodation.numRooms || '');
        setIsAvailable(accommodation.isAvailable || false);
        setDialogOpen(true);
    };

    // Close dialog without saving
    const handleCloseDialog = () => {
        setDialogOpen(false);
    };

    // Submit the form (add or edit)
    const handleSubmit = () => {
        const formData = {
            name,
            category,
            hostId,       // using hostId (might need to map to host object in real use)
            numRooms: Number(numRooms),
            isAvailable
        };
        if (editingItem) {
            // Update existing accommodation
            updateAccommodation(editingItem.id, formData);
        } else {
            // Create new accommodation
            createAccommodation(formData);
        }
        // After submitting, close the dialog
        setDialogOpen(false);
    };

    // Handle delete action (with confirmation)
    const handleDelete = (id) => {
        if (window.confirm('Are you sure you want to delete this accommodation?')) {
            deleteAccommodation(id);
        }
    };

    return (
        <div>
            {/* Page Title and Add button */}
            <Typography variant="h5" gutterBottom>
                Accommodations
            </Typography>
            <Button variant="contained" color="primary" onClick={handleOpenAdd} sx={{ mb: 2 }}>
                Add New Accommodation
            </Button>

            {/* Accommodations Table */}
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Category</TableCell>
                            <TableCell>Host</TableCell>
                            <TableCell>Rooms</TableCell>
                            <TableCell>Available</TableCell>
                            <TableCell align="center">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {accommodations && accommodations.map(acc => (
                            <TableRow key={acc.id}>
                                <TableCell>{acc.name}</TableCell>
                                <TableCell>{acc.category}</TableCell>
                                <TableCell>{acc.host ? acc.host.name : ''}</TableCell>
                                <TableCell>{acc.numRooms}</TableCell>
                                <TableCell>{acc.isAvailable ? 'Yes' : 'No'}</TableCell>
                                <TableCell align="center">
                                    <Button size="small" onClick={() => handleOpenEdit(acc)}>
                                        Edit
                                    </Button>
                                    <Button size="small" color="error" onClick={() => handleDelete(acc.id)}>
                                        Delete
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                        {accommodations && accommodations.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={6} align="center">No accommodations found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

            {/* Dialog for Add/Edit Accommodation */}
            <Dialog open={isDialogOpen} onClose={handleCloseDialog} fullWidth maxWidth="sm">
                <DialogTitle>{editingItem ? 'Edit Accommodation' : 'Add New Accommodation'}</DialogTitle>
                <DialogContent dividers>
                    <TextField
                        label="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Category"
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    {/* Example: Host selection (assuming we have a list of hosts from elsewhere) */}
                    {/* For simplicity, using a text field. In a real app, you might use a Select dropdown with host options. */}
                    <TextField
                        label="Host ID"
                        value={hostId}
                        onChange={(e) => setHostId(e.target.value)}
                        fullWidth
                        margin="normal"
                    />
                    <TextField
                        label="Number of Rooms"
                        value={numRooms}
                        onChange={(e) => setNumRooms(e.target.value)}
                        type="number"
                        fullWidth
                        margin="normal"
                    />
                    {/* For availability, we can use a simple dropdown or checkbox */}
                    <Select
                        label="Available"
                        value={isAvailable ? 'yes' : 'no'}
                        onChange={(e) => setIsAvailable(e.target.value === 'yes')}
                        fullWidth
                        margin="normal"
                    >
                        <MenuItem value="yes">Yes</MenuItem>
                        <MenuItem value="no">No</MenuItem>
                    </Select>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button onClick={handleSubmit} variant="contained">
                        {editingItem ? 'Save Changes' : 'Add Accommodation'}
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default AccommodationsPage;
