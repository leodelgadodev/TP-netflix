import axios from 'axios';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://localhost:7000';

const token = window.sessionStorage.accessToken;

export const MediaService = {
    
    getAllContents: () => {
        return axios.get(`/content`, {headers: {Authentication: token}})
    },

    getAllBanners: () =>  {
        return axios.get(`/banners`, {headers: {Authentication: token}})
    },

    getContent: (contentId) => {
        return axios.get(`/content/${contentId}`, {headers: {Authentication: token}})
    },

    verifyPoster: (url) => {
        return fetch(url);
    },
    
    search: (queryParam) =>  {
        return axios.get(`/search?text=${queryParam}`, {headers: {Authentication: token}})
    },

}
