import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const MediaService = {

    getUser: () => {
        axios.get("/user")
    },
    
    addFavorite: (contentId) =>  {
        return axios.post(`/user/fav/${contentId}`)
    },

    addLastSeen: (contentId) => {
        return axios.post(`/user/lastSeen`, {contentId})
    },
    

}