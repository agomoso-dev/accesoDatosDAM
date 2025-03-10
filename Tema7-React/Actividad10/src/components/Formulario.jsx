import { useContext } from 'react';
import { FormularioContext } from '../context/FormularioContext';
import { Box, TextField, Button, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const Formulario = () => {
    const { setDatosFormulario, setCiudad } = useContext(FormularioContext);
    const navegar = useNavigate();

    const manejarEnvio = (evento) => {
        evento.preventDefault();
        const datos = new FormData(evento.currentTarget);
        const valoresFormulario = {
            nombre: datos.get('nombre'),
            apellidos: datos.get('apellidos'),
            correo: datos.get('correo'),
            ciudad: datos.get('ciudad')
        };
        setDatosFormulario(valoresFormulario);
        setCiudad(datos.get('ciudad'));
        navegar('/resumen');
    };

    return (
        <Box component="form" onSubmit={manejarEnvio} sx={{ mt: 4, maxWidth: 400, mx: 'auto' }}>
            <Typography variant="h4" gutterBottom>
                Registro de Usuario
            </Typography>
            <TextField
                fullWidth
                margin="normal"
                name="nombre"
                label="Nombre"
                required
            />
            <TextField
                fullWidth
                margin="normal"
                name="apellidos"
                label="Apellidos"
                required
            />
            <TextField
                fullWidth
                margin="normal"
                name="correo"
                label="Correo Electrónico"
                type="email"
                required
            />
            <TextField
                fullWidth
                margin="normal"
                name="contraseña"
                label="Contraseña"
                type="password"
                required
            />
            <TextField
                fullWidth
                margin="normal"
                name="ciudad"
                label="Ciudad"
            />
            <Button
                type="submit"
                variant="contained"
                fullWidth
                sx={{ mt: 3 }}
            >
                Registrar
            </Button>
        </Box>
    );
};

export default Formulario;