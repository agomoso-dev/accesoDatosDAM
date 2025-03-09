import { create } from 'zustand'

const useGameStore = create((set, get) => ({
    casillas: Array(9).fill(null),
    turnoX: false,
    
    setCasilla: (index) => {
        const { casillas, turnoX, calcularGanador } = get()
        if (calcularGanador(casillas) || casillas[index]) return
        
        const nuevasCasillas = [...casillas]
        nuevasCasillas[index] = turnoX ? 'X' : 'O'
        
        set({
            casillas: nuevasCasillas,
            turnoX: !turnoX
        })
    },
    
    reiniciarJuego: () => set({
        casillas: Array(9).fill(null),
        turnoX: false
    }),
    
    calcularGanador: (casillas) => {
        const lineas = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8],
            [0, 3, 6], [1, 4, 7], [2, 5, 8],
            [0, 4, 8], [2, 4, 6]
        ]
        
        for (const [a, b, c] of lineas) {
            if (casillas[a] && casillas[a] === casillas[b] && casillas[a] === casillas[c]) {
                return casillas[a]
            }
        }
        return null
    }
}))

export default useGameStore