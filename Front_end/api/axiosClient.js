import axios from 'https://cdn.jsdelivr.net/npm/axios@1.6.7/+esm';

const axiosClient = axios.create({
    baseURL: "https://backend-production-42b5.up.railway.app",
    headers: {
        "Content-Type": "application/json"
    }
});

axiosClient.interceptors.response.use(
    (response) => {
        return response.data;
    },
    (error) => {
        console.error("Lỗi API:", error);
        return Promise.reject(error);
    }
);

export default axiosClient;