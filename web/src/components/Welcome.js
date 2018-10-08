import React, { Component } from "react"
import FacebookLogin from 'react-facebook-login';
import { GoogleLogin } from 'react-google-login';
import config from '../config.json';


class Welcome extends Component {


    constructor() {
        super();
        this.state = { isAuthenticated: false, user: null, token: ''};
    }


    componentDidUpdate(){
        console.log("UPDATED")
        localStorage.setItem("token", this.state.token );
        this.props.history.push('');
    }

    logout = () => {
        this.setState({isAuthenticated: false, token: '', user: null})
    };


    facebookResponse = (response) => {
        const tokenBlob = new Blob([JSON.stringify({access_token: response.accessToken}, null, 2)], {type : 'application/json'});
        const options = {
            method: 'POST',
            body: tokenBlob,
            mode: 'cors',
            cache: 'default'
        };
        fetch('http://localhost:4000/api/v1/auth/facebook', options).then(r => {
            const token = r.headers.get('x-auth-token');
            r.json().then(user => {
                if (token) {
                    this.setState({isAuthenticated: true, user, token})
                }
            });
        })
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
                if (user.jwtToken) {
                    this.setState({isAuthenticated: true, user, token: user.jwtToken})
                    console.log(this.state);
                }
            });
            
            // r.json().then(user => {
            //     if (token) {
            //         this.setState({isAuthenticated: true, user, token})
            //         console.log(this.state);
            //     }
            // });
        })
    };


    onFailure = (error) => {
      alert(error);
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
                <div>
                    <FacebookLogin
                        appId={config.FACEBOOK_APP_ID}
                        autoLoad={false}
                        fields="name,email,picture"
                        callback={this.facebookResponse} 
                        />
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
                <header>Welcome to Roti Khilao! (UI to be updated later) </header>
                {content}
            </div>
        );
    }
}


export default Welcome