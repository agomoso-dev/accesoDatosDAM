import { createContext, useState } from 'react';

export const FormularioContext = createContext();

export const FormularioProvider = ({ children }) => {
    const [datosFormulario, setDatosFormulario] = useState(null);
    const [ciudad, setCiudad] = useState('');

    return (
        <FormularioContext.Provider value={{ 
            datosFormulario, 
            setDatosFormulario,
            ciudad,
            setCiudad 
        }}>
            {children}
        </FormularioContext.Provider>
    );
};