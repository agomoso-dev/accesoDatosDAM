import { Container } from '@mui/material';
import Tablero from './components/tablero';
import './App.css';

function App() {
    return (
        <Container 
            sx={{
                minHeight: '100vh',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                bgcolor: '#f0f2f5'
            }}
        >
            <Tablero />
        </Container>
    );
}

export default App;