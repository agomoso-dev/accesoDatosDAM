import { useQuery } from '@tanstack/react-query'
import { LoadingButton } from '@mui/lab'
import { Container, Typography, Box, TextField } from '@mui/material'
import { useState } from 'react'
import { getWeatherData } from './services/servicio'

function App() {
    const [city, setCity] = useState("");

    const { data: weather, isLoading, error, refetch } = useQuery({
        queryKey: ['weather', city],
        queryFn: () => getWeatherData(city),
        enabled: Boolean(city.trim()),
        retry: false
    });

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
                Clima en tu ciudad
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
}

export default App