import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:7000';
axios.defaults.headers.post['Content-Type'] = 'application/json';

function AuthService() {
    
    const register = (name, email, password, image, creditCard) =>  {
        axios.post("/register", {name, email, password, image, creditCard}, {})
    }
    
    const login = (email, password) => {
        axios.post("/login", {email, password})
    }
}


export default AuthService;