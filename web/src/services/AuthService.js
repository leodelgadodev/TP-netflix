import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const AuthService = {

    register: (name, email, password, image, creditCard) =>  {
        return axios.post("/register", {name, email, password, image, creditCard}, {})
    },
    
    login: (email, password) => {
        return axios.post("/login", {email, password})
    },

    token: () => {
        return window.sessionStorage.accessToken;
    },

    authenticate: (responseToken) => {
        window.sessionStorage.accessToken = responseToken;
    },

    logout: () => {
        window.sessionStorage.accessToken = "";
    }
}