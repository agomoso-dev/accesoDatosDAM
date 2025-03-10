import { AppBar, Toolbar, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const BarraNavegacion = () => {
    return (
        <AppBar position="static" sx={{ bgcolor: '#FFD700' }}>
            <Toolbar>
                <Button 
                    color="inherit" 
                    component={Link} 
                    to="/home"
                    sx={{ 
                        color: 'black',
                        '&:hover': {
                            bgcolor: '#FFE44D'
                        }
                    }}
                >
                    Inicio
                </Button>
                <Button 
                    color="inherit" 
                    component={Link} 
                    to="/formulario"
                    sx={{ 
                        color: 'black',
                        '&:hover': {
                            bgcolor: '#FFE44D'
                        }
                    }}
                >
                    Registro
                </Button>
                <Button 
                    color="inherit" 
                    component={Link} 
                    to="/resumen"
                    sx={{ 
                        color: 'black',
                        '&:hover': {
                            bgcolor: '#FFE44D'
                        }
                    }}
                >
                    Resumen
                </Button>
                <Button 
                    color="inherit" 
                    component={Link} 
                    to="/tiempo"
                    sx={{ 
                        color: 'black',
                        '&:hover': {
                            bgcolor: '#FFE44D'
                        }
                    }}
                >
                    El Tiempo
                </Button>
                <Button 
                    color="inherit" 
                    component={Link} 
                    to="/about"
                    sx={{ 
                        color: 'black',
                        '&:hover': {
                            bgcolor: '#FFE44D'
                        }
                    }}
                >
                    Acerca De
                </Button>
            </Toolbar>
        </AppBar>
    );
};

export default BarraNavegacion;