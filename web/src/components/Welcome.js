import React, { Component } from "react"
import FacebookLogin from 'react-facebook-login';
import { GoogleLogin } from 'react-google-login';
import config from '../config.json';
import Vendians from '../css/images/Vendians.png';
import "../css/style.css"
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


class Welcome extends Component {


    constructor() {
        super();
        this.state = { isAuthenticated: false, user: null, token: '', name: ''};
    }


    componentDidUpdate(){

        localStorage.setItem("token", this.state.token );
        localStorage.setItem("name", this.state.name )
        this.props.history.push('');
    }

    logout = () => {
        this.setState({isAuthenticated: false, token: '', user: null})
    };



    googleResponse = (response) => {
        let id_token = response.Zi['id_token'];
        const tokenBlob = new Blob([JSON.stringify(id_token)], {type : 'application/json'});
        const options = {
            method: 'POST',
            body: tokenBlob,
            mode: 'cors',
            cache: 'default',
            headers: {
                Accept: 'application/json'
            }
        };
        
    
        fetch('http://localhost:8080/api/login', options).then(r => {

            r.json().then(user => {
                console.log(user)
                if (user.isAuthorized === "true") {
                    this.setState({isAuthenticated: true, user, token: user.jwtToken, name: user.name})        
                    console.log(this.state);
                }else {
                    console.log("Please use venturedive email address to login");
                    toast("Please use venturedive email address to login",{ autoClose:false, className: 'black-background'});
                }
            });
            
        //     // r.json().then(user => {
        //     //     if (token) {
        //     //         this.setState({isAuthenticated: true, user, token})
        //     //         console.log(this.state);
        //     //     }
        //     // });
        })
    };


    onFailure = (error) => {
      alert("Couldn't authenticate.");
    }

   
    render() {
        let content = !!this.state.isAuthenticated ?
            (
                <div>
                    <p>Authenticated</p>
                    <div>
                        {this.state.user.email}
                    </div>
                    <div>
                        <button onClick={this.logout} className="button">
                            Log out
                        </button>
                    </div>
                </div>
            ) :
            (
                <div className="googleButton">
                    <GoogleLogin 
                        clientId={config.GOOGLE_CLIENT_ID}
                        buttonText="Login with Google"
                        onSuccess={this.googleResponse}
                        onFailure={this.onFailure}
                    />
                </div>
            );

        return (
            <div className="Welcome">
                <img src={Vendians} className="vendImage" />
                <ToastContainer />
                {content}
            </div>
        );
    }
}


export default Welcome