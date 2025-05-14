import React from 'react';
import {
    Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper
} from '@mui/material';
import { useHosts } from '../../hooks/useHosts';

const HostsPage = () => {
    const { items: hosts } = useHosts();

    return (
        <div>
            <Typography variant="h5" gutterBottom>Hosts</Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Surname</TableCell>
                            <TableCell>Country</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {hosts && hosts.map(host => (
                            <TableRow key={host.id}>
                                <TableCell>{host.name}</TableCell>
                                <TableCell>{host.surname}</TableCell>
                                <TableCell>{host.country ? host.country.name : ''}</TableCell>
                            </TableRow>
                        ))}
                        {hosts && hosts.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={3} align="center">No hosts found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default HostsPage;
