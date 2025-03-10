import { Typography, Box, Grid } from '@mui/material';
import MiCard from './MiCard';
import comida from '../assets/img/comida.jpg';
import pajaros from '../assets/img/PavoReal.jpg';
import pokemon from '../assets/img/Pokemon.jpg';

const Home = () => {
    const cards = [
        {
            imagePath: comida,
            title: "Comida Deliciosa",
            description: "Una deliciosa selección de platos para disfrutar"
        },
        {
            imagePath: pajaros,
            title: "Pajaros especiales",
            description: "Los mejores pajaros tu día a día"
        },
        {
            imagePath: pokemon,
            title: "Pokemons",
            description: "Pokemons especiales"
        }
    ];
    return (
        <Box sx={{ mt: 4 }}>
            <Typography variant="h2" gutterBottom align="center">
                ¡¡HOLAAA!!
            </Typography>
            <Typography variant="h5" align="center" sx={{ mb: 4 }}>
                SOY ANTONIO JESÚS, ENCANTADO. ESTAS SON MIS PRIMERAS PRUEBAS CON REACT
            </Typography>
            
            <Grid container spacing={4} justifyContent="center">
                {cards.map((card, index) => (
                    <Grid item key={index} xs={12} sm={6} md={4}>
                        <MiCard
                            imagePath={card.imagePath}
                            title={card.title}
                            description={card.description}
                        />
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default Home;