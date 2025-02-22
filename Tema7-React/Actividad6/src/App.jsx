import { useState } from 'react'
import { LoadingButton } from '@mui/lab'
import { Container, Typography, Box, TextField, Grid, Card, CardMedia, CardContent } from '@mui/material'
import './App.css'

function App() {
    // API URL constant
    const API_DOG = "https://dog.ceo/api/breed/";

    // Estados
    const [breed, setBreed] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState({ error: false, message: "" });
    const [images, setImages] = useState([]);

    // Función onSubmit
    const onSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError({ error: false, message: "" });
        
        try {
            if (!breed.trim()) throw { message: "El campo raza es obligatorio" };
            const url = `${API_DOG}${breed.toLowerCase()}/images/random/6`;
            console.log(url);
            const response = await fetch(url);
            const data = await response.json();
            
            if (data.status === "error") throw { message: "Raza no encontrada" };
            
            setImages(data.message);
        } catch (ex) {
            console.log(ex);
            setError({ error: true, message: ex.message });
            setImages([]);
        } finally {
            setLoading(false);
        }
    };

    return (
        <>
            <Container maxWidth="lg" sx={{ mt: 2, mb: 4 }}>
                <Typography 
                    variant="h3" 
                    component="h1" 
                    align="center" 
                    gutterBottom
                    sx={{ color: 'primary.main', mb: 4 }}
                >
                    Buscador de Perros
                </Typography>
                <Box 
                    sx={{ 
                        display: "grid", 
                        gap: 2,
                        maxWidth: 400,
                        mx: 'auto',
                        mb: 4
                    }} 
                    component="form" 
                    autoComplete="off" 
                    onSubmit={onSubmit}
                >
                    <TextField 
                        id="breed"
                        label="Raza de perro"
                        variant="outlined"
                        required
                        fullWidth
                        size="small"
                        value={breed}
                        onChange={(e) => setBreed(e.target.value)}
                        helperText="Ejemplo: husky, labrador, poodle"
                        error={error.error}
                    />
                    <LoadingButton 
                        type="submit"
                        variant="contained"
                        loading={loading}
                        loadingIndicator="Buscando..."
                        fullWidth
                    >
                        Buscar
                    </LoadingButton>
                </Box>

                {error.error && (
                    <Typography 
                        color="error" 
                        align="center" 
                        sx={{ mb: 4 }}
                    >
                        {error.message}
                    </Typography>
                )}

                <Grid container spacing={3}>
                    {images.map((imageUrl, index) => (
                        <Grid item xs={12} sm={6} md={4} key={index}>
                            <Card 
                                sx={{ 
                                    height: '100%',
                                    display: 'flex',
                                    flexDirection: 'column',
                                    transition: 'transform 0.2s',
                                    '&:hover': {
                                        transform: 'scale(1.02)'
                                    }
                                }}
                            >
                                <CardMedia
                                    component="img"
                                    image={imageUrl}
                                    alt={`${breed} ${index + 1}`}
                                    sx={{
                                        height: 250,
                                        objectFit: 'cover'
                                    }}
                                />
                                <CardContent>
                                    <Typography 
                                        gutterBottom 
                                        variant="h6" 
                                        component="h2"
                                        sx={{ textTransform: 'capitalize' }}
                                    >
                                        {breed}
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </Container>
        </>
    )
}

export default App