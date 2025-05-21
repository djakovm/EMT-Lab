import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    MenuItem,
    Paper,
    Select,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField,
    Typography
} from '@mui/material';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import {useAccommodations} from '../../hooks/useAccommodations';

const AccommodationsPage = () => {
    const {items: accommodations, create, update, remove, rent} = useAccommodations();

    const [isDialogOpen, setDialogOpen] = useState(false);
    const [editingItem, setEditingItem] = useState(null);

    const [name, setName] = useState('');
    const [category, setCategory] = useState('');
    const [hostId, setHostId] = useState('');
    const [numRooms, setNumRooms] = useState('');
    const [isAvailable, setIsAvailable] = useState(false);

    const [categoryFilter, setCategoryFilter] = useState('ALL');

    const handleOpenAdd = () => {
        setEditingItem(null);
        setName('');
        setCategory('');
        setHostId('');
        setNumRooms('');
        setIsAvailable(false);
        setDialogOpen(true);
    };

    const handleOpenEdit = (accommodation) => {
        setEditingItem(accommodation);
        setName(accommodation.name || '');
        setCategory(accommodation.category || '');
        setHostId(accommodation.hostId || '');
        setNumRooms(accommodation.numRooms || '');
        setIsAvailable(accommodation.isAvailable || false);
        setDialogOpen(true);
    };

    const handleCloseDialog = () => {
        setDialogOpen(false);
    };

    const handleSubmit = () => {
        const formData = {
            name,
            category,
            hostId,
            numRooms: Number(numRooms),
            isAvailable
        };

        if (editingItem) {
            update(editingItem.id, formData);
        } else {
            create(formData);
        }

        setDialogOpen(false);
    };

    const handleDelete = (id) => {
        if (window.confirm('Are you sure you want to delete this accommodation?')) {
            remove(id);
        }
    };

    const handleRent = (id) => {
        const guestId = prompt("Enter guest ID:");
        const from = prompt("Enter start date (yyyy-mm-dd):");
        const to = prompt("Enter end date (yyyy-mm-dd):");

        if (guestId && from && to) {
            rent(id, guestId, from, to);
        }
    };

    const filteredAccommodations = categoryFilter === 'ALL'
        ? accommodations
        : accommodations.filter(acc => acc.category === categoryFilter);

    return (
        <div>
            <Typography variant="h5" gutterBottom color='#0009'>
                Accommodations
            </Typography>
            <Button variant="contained" color="primary" onClick={handleOpenAdd} sx={{mb: 2}}>
                Add New Accommodation
            </Button>

            <ToggleButtonGroup
                color="primary"
                value={categoryFilter}
                exclusive
                onChange={(e, newCategory) => {
                    if (newCategory !== null) setCategoryFilter(newCategory);
                }}
                sx={{mb: 2}}
            >
                <ToggleButton value="ALL">All</ToggleButton>
                <ToggleButton value="ROOM">Room</ToggleButton>
                <ToggleButton value="HOUSE">House</ToggleButton>
                <ToggleButton value="FLAT">Flat</ToggleButton>
                <ToggleButton value="APARTMENT">Apartment</ToggleButton>
                <ToggleButton value="HOTEL">Hotel</ToggleButton>
                <ToggleButton value="MOTEL">Motel</ToggleButton>
            </ToggleButtonGroup>

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
                        {filteredAccommodations.map(acc => (
                            <TableRow key={acc.id}>
                                <TableCell>{acc.name}</TableCell>
                                <TableCell>{acc.category}</TableCell>
                                <TableCell>{acc.host?.name || ''}</TableCell>
                                <TableCell>{acc.numRooms}</TableCell>
                                <TableCell>{acc.isAvailable ? 'Yes' : 'No'}</TableCell>
                                <TableCell align="center">
                                    <Button size="small" onClick={() => handleOpenEdit(acc)}>
                                        Edit
                                    </Button>
                                    <Button size="small" color="error" onClick={() => handleDelete(acc.id)}>
                                        Delete
                                    </Button>
                                    <Button size="small" color="secondary" onClick={() => handleRent(acc.id)}>
                                        Rent
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                        {filteredAccommodations.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={6} align="center">No accommodations found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>

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
                    <Select
                        value={category}
                        onChange={(e) => setCategory(e.target.value)}
                        fullWidth
                        displayEmpty
                        margin="normal"
                    >
                        <MenuItem value="" disabled>Select category</MenuItem>
                        <MenuItem value="ROOM">Room</MenuItem>
                        <MenuItem value="HOUSE">House</MenuItem>
                        <MenuItem value="FLAT">Flat</MenuItem>
                        <MenuItem value="APARTMENT">Apartment</MenuItem>
                        <MenuItem value="HOTEL">Hotel</MenuItem>
                        <MenuItem value="MOTEL">Motel</MenuItem>
                    </Select>
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
                    <Select
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
