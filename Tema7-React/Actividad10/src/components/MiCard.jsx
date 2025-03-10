import { Card, CardMedia, CardContent, Typography, CardActions, Button } from "@mui/material";

function MiCard({ imagePath, title, description }) {
    return (
        <Card sx={{ maxWidth: 300, height: '100%' }}>
            <CardMedia
                component="img"
                height="400"
                image={imagePath}
                alt={title}
                sx={{ objectFit: "cover" }}
            />
            <CardContent>
                <Typography variant="h5">{title}</Typography>
                <Typography variant="body2" color="text.secondary">
                    {description}
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small">Ver más</Button>
                <Button size="small">Compartir</Button>
            </CardActions>
        </Card>
    );
}

export default MiCard;