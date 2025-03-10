import { createContext, useState } from 'react';

export const FormularioContext = createContext();

export const FormularioProvider = ({ children }) => {
    const [datosFormulario, setDatosFormulario] = useState(null);

    return (
        <FormularioContext.Provider value={{ datosFormulario, setDatosFormulario }}>
            {children}
        </FormularioContext.Provider>
    );
};