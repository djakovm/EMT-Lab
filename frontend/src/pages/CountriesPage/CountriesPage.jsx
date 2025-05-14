import React from 'react';
import {
    Typography, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper
} from '@mui/material';
import { useCountries } from '../../hooks/useCountries';

const CountriesPage = () => {
    const { items: countries } = useCountries();

    return (
        <div>
            <Typography variant="h5" gutterBottom>Countries</Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Continent</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {countries && countries.map(country => (
                            <TableRow key={country.id}>
                                <TableCell>{country.name}</TableCell>
                                <TableCell>{country.continent}</TableCell>
                            </TableRow>
                        ))}
                        {countries && countries.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={2} align="center">No countries found.</TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
};

export default CountriesPage;
