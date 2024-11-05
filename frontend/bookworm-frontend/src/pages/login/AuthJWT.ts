import axios from "axios";

export const fetchData = async (endpoint: string) => {
    const token = localStorage.getItem("jwtToken");

    try {
        const response = await axios.get(endpoint, {
            headers: {
                Authorization: `Bearer ${token}`,
            }
        });
        return response.data
        
    } catch (error) {
        console.log(error);
        
        
    }
}