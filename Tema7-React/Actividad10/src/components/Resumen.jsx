import { useContext } from 'react';
import { FormularioContext } from '../context/FormularioContext';
import { Box, Typography, Paper, Divider } from '@mui/material';
import { useQuery } from '@tanstack/react-query';
import { getWeatherData } from '../services/weatherService';

const Resumen = () => {
    const { datosFormulario } = useContext(FormularioContext);

    const { data: weather } = useQuery({
        queryKey: ['weather', datosFormulario?.ciudad],
        queryFn: () => getWeatherData(datosFormulario?.ciudad),
        enabled: Boolean(datosFormulario?.ciudad),
        retry: false
    });

    if (!datosFormulario) {
        return (
            <Typography variant="h6" sx={{ mt: 4, textAlign: 'center' }}>
                No hay datos de registro disponibles
            </Typography>
        );
    }

    return (
        <Box sx={{ mt: 4, maxWidth: 600, mx: 'auto' }}>
            <Paper elevation={3} sx={{ p: 3 }}>
                <Typography variant="h4" gutterBottom>
                    Resumen de Registro
                </Typography>
                <Typography variant="body1" sx={{ mb: 1 }}>
                    Nombre: {datosFormulario.nombre}
                </Typography>
                <Typography variant="body1" sx={{ mb: 1 }}>
                    Apellidos: {datosFormulario.apellidos}
                </Typography>
                <Typography variant="body1" sx={{ mb: 1 }}>
                    Email: {datosFormulario.correo}
                </Typography>
                <Typography variant="body1" sx={{ mb: 2 }}>
                    Ciudad: {datosFormulario.ciudad}
                </Typography>

                {weather && (
                    <>
                        <Divider sx={{ my: 2 }} />
                        <Typography variant="h5" gutterBottom>
                            El tiempo en tu ciudad
                        </Typography>
                        <Box sx={{ textAlign: 'center', mt: 2 }}>
                            <Box 
                                component="img" 
                                src={weather.icon}
                                alt={weather.conditionText}
                                sx={{ width: 64, height: 64 }}
                            />
                            <Typography variant="h4">
                                {weather.temp}ºC
                            </Typography>
                            <Typography variant="body1">
                                {weather.conditionText}
                            </Typography>
                        </Box>
                    </>
                )}
            </Paper>
        </Box>
    );
};

export default Resumen;