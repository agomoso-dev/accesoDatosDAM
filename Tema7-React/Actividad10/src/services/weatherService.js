export const getWeatherData = async (city) => {
    if (!city) return null;
    
    try {
        const API_WEATHER = `https://api.weatherapi.com/v1/current.json?key=${import.meta.env.VITE_API_KEY}&q=${city}&aqi=no`;
        
        const response = await fetch(API_WEATHER);
        
        if (!response.ok) {
            throw new Error('Error en la petición');
        }

        const data = await response.json();

        return {
            city: data.location.name,
            country: data.location.country,
            temp: data.current.temp_c,
            condition: data.current.condition.code,
            icon: data.current.condition.icon,
            conditionText: data.current.condition.text,
        };
    } catch (error) {
        console.error('Error:', error);
        throw new Error('No se encontró la ciudad');
    }
};