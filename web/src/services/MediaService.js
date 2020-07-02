import axios from 'axios';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.baseURL = 'http://localhost:7000';


export const MediaService = {
    
    getAllContents: () => {
        const token = window.sessionStorage.accessToken
        return axios.get(`/content`, {headers: {Authentication: token}})
    },

    getAllBanners: () =>  {
        const token = window.sessionStorage.accessToken
        return axios.get(`/banners`, {headers: {Authentication: token}})
    },

    getContent: (contentId) => {
        const token = window.sessionStorage.accessToken
        return axios.get(`/content/${contentId}`, {headers: {Authentication: token}})
    },

    verifyPoster: (url) => {
        return fetch(url);
    },
    
    search: (queryParam) =>  {
        const token = window.sessionStorage.accessToken
        return axios.get(`/search?text=${queryParam}`, {headers: {Authentication: token}})
    },

}
