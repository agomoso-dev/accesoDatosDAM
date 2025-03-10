import { useState, useContext, useEffect } from 'react';
import { FormularioContext } from '../context/FormularioContext';
import { LoadingButton } from '@mui/lab';
import { Container, Typography, Box, TextField } from '@mui/material';
import { useQuery } from '@tanstack/react-query';
import { getWeatherData } from '../services/weatherService';

const Tiempo = () => {
    const { ciudad } = useContext(FormularioContext);
    const [city, setCity] = useState(ciudad || "");

    const { data: weather, isLoading, error, refetch } = useQuery({
        queryKey: ['weather', city],
        queryFn: () => getWeatherData(city),
        enabled: Boolean(city.trim()),
        retry: false
    });

    useEffect(() => {
        if (ciudad) {
            setCity(ciudad);
            refetch();
        }
    }, [ciudad]);

    const onSubmit = (e) => {
        e.preventDefault();
        if (!city.trim()) return;
        refetch();
    };

    return (
        <Container maxWidth="xs" sx={{ mt: 2 }}>
            <Typography 
                variant="h3" 
                component="h1" 
                align="center" 
                gutterBottom
            >
                El Tiempo
            </Typography>
            <Box 
                sx={{ display: "grid", gap: 2 }} 
                component="form" 
                autoComplete="off" 
                onSubmit={onSubmit}
            >
            <TextField 
                id="city"
                label="Ciudad"
                variant="outlined"
                required
                fullWidth
                size="small"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                error={Boolean(error)}
                helperText={error?.message}
            />
                <LoadingButton
                    type="submit"
                    variant="contained"
                    loading={isLoading}
                    loadingIndicator="Buscando..."
                >
                    Buscar
                </LoadingButton>
            </Box>

            {weather && (
                <Box sx={{mt: 2, display: "grid", gap: 2, textAlign: "center"}}>
                    <Typography variant='h4' component='h2'>
                        {weather.city}, {weather.country}
                    </Typography>
                    <Box 
                        component='img' 
                        alt={weather.conditionText} 
                        src={weather.icon} 
                        sx={{ margin: "0 auto"}}
                    />
                    <Typography variant='h2' component='h2'>
                        {weather.temp}ºC
                    </Typography>
                    <Typography variant='h5' component='h5'>
                        {weather.conditionText}
                    </Typography>
                </Box>
            )}
        </Container>
    );
};

export default Tiempo;