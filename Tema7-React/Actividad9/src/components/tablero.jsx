import { Grid, Button, Box } from '@mui/material';
import Casilla from './casilla';
import useGameStore from '../store/gameStore';

const Tablero = () => {
    const { casillas, turnoX, setCasilla, reiniciarJuego, calcularGanador } = useGameStore();
    const ganador = calcularGanador(casillas);

    return (
        <Box sx={{ 
            bgcolor: '#f5f0f5',
            p: 2,
            borderRadius: 2,
            boxShadow: 1,
            border: '3px solid #d4c4d4',
            width: '400px',
            minHeight: '450px',
            display: 'flex',
            flexDirection: 'column',
            gap: 2
        }}>
            <Grid container spacing={1} sx={{ mb: 1 }}>
                <Grid item xs={4}>
                    <Box sx={{ 
                        bgcolor: '#fff3e0',
                        px: 1,
                        py: 0.5,
                        borderRadius: 1,
                        border: '1px solid #ffe0b2',
                        textAlign: 'center'
                    }}>
                        NEXT: PLAYER {turnoX ? '2' : '1'}
                    </Box>
                </Grid>
                
                <Grid item xs={4}>
                    <Box sx={{ 
                        bgcolor: '#fff3e0',
                        px: 1,
                        py: 0.5,
                        borderRadius: 1,
                        border: '1px solid #ffe0b2',
                        textAlign: 'center',
                        visibility: ganador ? 'visible' : 'hidden'
                    }}>
                        PLAYER {ganador === 'X' ? '2' : '1'} WINS
                    </Box>
                </Grid>
                
                <Grid item xs={4}>
                    <Button 
                        variant="contained"
                        onClick={reiniciarJuego}
                        fullWidth
                        sx={{
                            bgcolor: '#4caf50',
                            '&:hover': {
                                bgcolor: '#45a049'
                            }
                        }}
                    >
                        NEW GAME
                    </Button>
                </Grid>
            </Grid>

            <Grid 
                container 
                spacing={1}
                sx={{ 
                    width: '100%',
                    maxWidth: '350px',
                    mx: 'auto',
                    aspectRatio: '1',
                    mb: 1
                }}
            >
                {casillas.map((valor, index) => (
                    <Grid item xs={4} key={index}>
                        <Casilla 
                            valor={valor}
                            onClick={() => setCasilla(index)}
                        />
                    </Grid>
                ))}
            </Grid>
        </Box>
    );
};

export default Tablero;