import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.get['Content-Type'] = 'application/json';

export const MediaService = {

    getAllContents: () => {
        return axios.get(`/content`)
    },

    getAllBanners: () =>  {
        return axios.get("/banners")
    },

    getContent: (id) => {
        return axios.get(`/content/${id}`)
    },
    
    search: (queryParam) =>  {
        return axios.get(`/search?text=${queryParam}`)
    },






}