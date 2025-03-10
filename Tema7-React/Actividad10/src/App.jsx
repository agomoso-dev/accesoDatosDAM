import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { FormularioProvider } from './context/FormularioContext';
import BarraNavegacion from './components/BarraNavegacion';  
import Formulario from './components/Formulario';
import Home from './components/Home';
import AcercaDe from './components/AcercaDe';
import Resumen from './components/Resumen';
import Tiempo from './components/Tiempo';
import { Container } from '@mui/material';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient();

function App() {
    return (
        <QueryClientProvider client={queryClient}>
            <Router>
                <FormularioProvider>
                    <BarraNavegacion />
                    <Container>
                        <Routes>
                            <Route path="/home" element={<Home />} />
                            <Route path="/formulario" element={<Formulario />} />
                            <Route path="/resumen" element={<Resumen />} />
                            <Route path="/about" element={<AcercaDe />} />
                            <Route path="/tiempo" element={<Tiempo />} />
                            <Route path="/" element={<Home />} />
                        </Routes>
                    </Container>
                </FormularioProvider>
            </Router>
        </QueryClientProvider>
    );
}

export default App;