import { Paper } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import CircleIcon from '@mui/icons-material/Circle'; 

const Casilla = ({ valor, onClick }) => {
    return (
        <Paper 
            onClick={onClick}
            sx={{
                width: '100%',
                height: 100,
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                cursor: 'pointer',
                bgcolor: '#e8f5e9',
                border: '2px solid #2e7d32',
                '&:hover': {
                    bgcolor: '#c8e6c9'
                }
            }}
        >
            {valor === 'X' && (
                <CloseIcon 
                    sx={{ 
                        fontSize: 60,
                        color: '#d32f2f',
                        position: 'absolute',
                        strokeWidth: 1,    
                        stroke: '#d32f2f'   
                    }} 
                />
            )}
            {valor === 'O' && (
                <CircleIcon    
                    sx={{ 
                        fontSize: 50,
                        color: '#1976d2',
                        position: 'absolute'
                    }} 
                />
            )}
        </Paper>
    );
};

export default Casilla;