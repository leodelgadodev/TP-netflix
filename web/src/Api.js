import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.post['Content-Type'] = 'application/json';

const reg = (name, email, password, image, creditCard) => axios.post("/register", {name, email, password, image, creditCard}, {})
const log = (email, password) => axios.post("/login", {email, password})

export default {
    reg,
    log,
};