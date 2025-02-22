import { useState } from 'react'
import { LoadingButton } from '@mui/lab'
import { Container, Typography, Box, TextField } from '@mui/material'
import './App.css'

function App() {
    // API URL constant
    const API_WEATHER = `https://api.weatherapi.com/v1/current.json?key=${import.meta.env.VITE_API_KEY}&q=`;

    // Estados (already implemented correctly)
    const [city, setCity] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState({ error: false, message: "" });
    const [weather, setWeather] = useState({
        city: "",
        country: "",
        temp: "",
        condition: "",
        icon: "",
        conditionText: "",
    });

    // Updated onSubmit function
    const onSubmit = async (e) => {
        e.preventDefault();
        console.log("submit " + city);
        setLoading(true);
        setError({ error: false, message: "" });
        
        try {
            if (!city.trim()) throw { message: "El campo ciudad es obligatorio" };
            console.log(API_WEATHER + city);
            const response = await fetch(API_WEATHER + city);
            const data = await response.json();
            console.log(data);
            
            if (data.error) throw { message: data.error.message };
            
            setWeather({
                city: data.location.name,
                country: data.location.country,
                temp: data.current.temp_c,
                condition: data.current.condition.code,
                icon: data.current.condition.icon,
                conditionText: data.current.condition.text,
            });
        } catch (ex) {
            console.log(ex);
            setError({ error: true, message: ex.message });
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
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
                    />
                    <LoadingButton 
                        type="submit"
                        variant="contained"
                        loading={loading}
                        loadingIndicator="Cargando..."
                    >
                        Buscar
                    </LoadingButton>
                </Box>

                {weather.city && (
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
        </>
    )
}

export default App