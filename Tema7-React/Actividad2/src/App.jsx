import { useState } from 'react'
import Button from '@mui/material/Button';
import Slider from '@mui/material/Slider';
import ButtonGroup from '@mui/material/ButtonGroup';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import TextField from '@mui/material/TextField';
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';
import './App.css'
import MiComponente from "./components/MiComponente";

function App() {

  return (
    <>
        <div>
            <h1>Hola desde App</h1>
            <MiComponente />
        </div>
      <div>
        <Button variant="contained">Hello world!</Button>
      </div>
      <div>
      <Slider defaultValue={10} sx={{ width: 200, color: 'success.main' }} />    
      </div>
      <div><Slider
        defaultValue={30}
        sx={{
          width: 300,
          color: 'success.main',
          '& .MuiSlider-thumb': {
            borderRadius: '1px',
          },
      }}
      />
      </div>
      <div>
      <ButtonGroup variant="contained" aria-label="Basic button group">
        <Button>Opcion 1</Button>
        <Button>Opcion 2</Button>
        <Button>Opcion 4</Button>
        <Button>Opcion 5</Button>
      </ButtonGroup>
      </div>
      <div>
      <FormGroup>
        <FormControlLabel control={<Checkbox defaultChecked color="secondary" />} label="Platanos" />
        <FormControlLabel required control={<Checkbox />} label="Macarrones" />
        <FormControlLabel control={<Checkbox color="success" />} label="Arroz" />
        <FormControlLabel control={<Checkbox />} label="Mayonesa" />
        <FormControlLabel disabled control={<Checkbox />} label="Tomate" />
      </FormGroup>
      </div>
      <div>
      <TextField 
        id="outlined-basic" 
        label="Nombre" 
        variant="outlined" />
      <TextField 
        id="outlined-basic" 
        label="Apellido" 
        variant="outlined" />
      <TextField
          id="outlined-password-input"
          label="Contraseña"
          type="password"
          autoComplete="current-password"
        />
      </div>
      <Typography component="legend">Estrellitas</Typography>
      <Rating
        name="simple-uncontrolled"
        onChange={(event, newValue) => {
          console.log(newValue);
        }}
        defaultValue={1}
      />
    </>
  )
}

export default App
