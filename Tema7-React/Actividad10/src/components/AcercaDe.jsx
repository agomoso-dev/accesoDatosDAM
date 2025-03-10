import { Typography, Box } from '@mui/material';

const AcercaDe = () => {
    return (
        <Box sx={{ mt: 4, maxWidth: 600, mx: 'auto' }}>
            <Typography variant="h3" gutterBottom>
                Sobre Mi
            </Typography>
            <Typography variant="body1" paragraph>
                Esta es la Actividad 10 de React, desarrollada por Antonio Jesús Gómez Osorio. 
                En esta aplicación se implementa el uso de React Router para la navegación 
                y Context API para la gestión del estado global del formulario.
            </Typography>
        </Box>
    );
};

export default AcercaDe;