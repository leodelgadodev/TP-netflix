import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.get['Content-Type'] = 'application/json';

const token = window.sessionStorage.accessToken;

export const MediaService = {
    
    getAllContents: () => {
        return axios.get(`/content`, {headers: {Authentication: token}})
    },

    getAllBanners: () =>  {
        return axios.get("/banners", {headers: {Authentication: token}})
    },

    getContent: (id) => {
        return axios.get(`/content/${id}`, {headers: {Authentication: token}})
    },
    
    search: (queryParam) =>  {
        return axios.get(`/search?text=${queryParam}`, {headers: {Authentication: token}})
    },






}