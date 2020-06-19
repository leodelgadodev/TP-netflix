import React from 'react';
import {Link} from 'react-router-dom' 


class Register extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            email: "",
            name: "",
            password: "",
            imageLink: "",
            creditCard: "",
        }
    }

    register = () => {
        
    }

    updatePassword = (event) => {
        this.setState({password: event.target.value})
    }
    updateName = (event) => {
        this.setState({name: event.target.value})
    }
    updateImageLink = (event) => {
        this.setState({imageLink: event.target.value})
    }
    updateEmail = (event) => {
        this.setState({email: event.target.value})
    }
    updateCC = (event) => {
        this.setState({creditCard: event.target.value})
    }
    render(){
        return(
            <div className="container" id="register-container">
                <div className= "register-form">
                    <form>
                        <div className="form-group"> 
                            <input type="email" className="form-control" value={this.state.email} onChange= {this.updateEmail} placeholder="Email..."/>
                        </div>
                        <div className="form-group"> 
                            <input type="text" className="form-control" value={this.state.name} onChange= {this.updateName} placeholder="Name..."/>
                        </div>
                        <div className="form-group"> 
                            <input type="password" className="form-control" value={this.state.password} onChange= {this.updatePassword} placeholder="Password..."/>
                        </div>
                        <div className="form-group"> 
                            <input type="url" className="form-control" value={this.state.imageLink} onChange= {this.updateImageLink} placeholder="Image Link..."/>
                        </div>
                        <div className="form-group"> 
                            <input type="text" className="form-control" value={this.state.creditCard} onChange= {this.updateCC} placeholder="Credit Card..."/>
                        </div>
                        <button type="submit" className="btn btn-primary" id="register" onClick={this.register}>Register</button>
                    </form>
                </div>
                <div className="btn-register" id="close-register">
                    <u> <Link to="/"> back </Link> </u>
                </div>
            </div>
        );
    }
}

export default Register;