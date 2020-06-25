import React from 'react';
import {Link} from 'react-router-dom' 

class Login extends React.Component{
    constructor(props){
        super(props);
        this.props = props;
        this.state = {
            password: "",
            user: "",
        }
    }

    login = () => {
    }
    
    updatePassword = (event) => {
        this.setState({password: event.target.value});
    }
    updateUser = (event) => {
        this.setState({user: event.target.value})
    }

    render(){
        return (
            <div className="container" id="login-container">
                <img className="unqflix-logo" src= "https://pbs.twimg.com/profile_images/2241566105/Logo_Q_TWITER_400x400.jpg" alt= "Unflix logo" id="login-logo"></img>
                <div className="login-form">
                    <form>
                        <div className="form-group">
                            <label>User</label>
                            <input type="text" className="form-control" value={this.state.user} onChange= {this.updateUser}/>
                        </div>
                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" value={this.state.password} onChange={this.updatePassword}/>
                        </div>
                        <button type="submit" className="btn btn-primary" id="login" onClick={this.login}>Login</button>
                    </form>
                </div>
                <div className="btn-register" id="open-register">
                    <u><Link to="/register">Register </Link></u>
                </div>
            </div>
        );
    }
}

export default Login;