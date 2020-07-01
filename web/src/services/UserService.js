import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';

const token = window.sessionStorage.accessToken;


export const UserService = {

    getUser: () => {
        return axios.get("/user", {headers: {Authentication: token}});
    },
    
    addFavorite: (contentId) =>  {
        return axios.post(`/user/fav`,{id: contentId}, {headers: {Authentication: token}})
    },

    addLastSeen: (contentId) => {
        return axios.post(`/user/lastSeen`, {id: contentId}, {headers: {Authentication: token}})
    }

}