import { useContext } from 'react';
import { FormularioContext } from '../context/FormularioContext';
import { Box, Typography, Paper } from '@mui/material';

const Resumen = () => {
    const { datosFormulario } = useContext(FormularioContext);

    if (!datosFormulario) {
        return (
            <Box sx={{ mt: 4, textAlign: 'center' }}>
                <Typography variant="h6">
                    No hay datos de registro disponibles.
                    Por favor, complete el formulario primero.
                </Typography>
            </Box>
        );
    }

    return (
        <Box sx={{ mt: 4, maxWidth: 600, mx: 'auto', p: 2 }}>
            <Paper elevation={3} sx={{ p: 3 }}>
                <Typography variant="h4" gutterBottom>
                    Resumen del Registro
                </Typography>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    Nombre: {datosFormulario.nombre}
                </Typography>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    Apellidos: {datosFormulario.apellidos}
                </Typography>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    Correo: {datosFormulario.correo}
                </Typography>
            </Paper>
        </Box>
    );
};

export default Resumen;