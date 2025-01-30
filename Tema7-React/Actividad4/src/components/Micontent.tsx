// miContent.tsx
import { Container, Grid } from "@mui/material";
import React from "react";
import MiCard from "./miCard";
// Importar las imágenes
import comidaImg from "../assets/img/comida.jpg";
import pavoRealImg from "../assets/img/PavoReal.jpg";
import pokemonImg from "../assets/img/Pokemon.jpg";

function MiContent() {
  const cards = [
    {
      imagePath: comidaImg,
      title: "Comida Deliciosa",
      description: "Una exquisita selección de platos para deleitar tu paladar."
    },
    {
      imagePath: pavoRealImg,
      title: "Pavo Real",
      description: "Majestuosa ave conocida por su colorido plumaje."
    },
    {
      imagePath: pokemonImg,
      title: "Pokémon",
      description: "Criaturas fantásticas del mundo Pokémon."
    }
  ];

  return (
    <Container sx={{ marginTop: 4 }}>
      <Grid container spacing={10}>
        {cards.map((card, index) => (
          <Grid item xs={12} md={6} lg={4} key={index}>
            <MiCard {...card} />
          </Grid>
        ))}
      </Grid>
    </Container>
  );
}

export default MiContent;