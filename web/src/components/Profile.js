import React from 'react';

class Profile extends React.Component {


    state = {
        name: '',
        url: '',
        email: '',
        id: 3
    }


    componentDidMount(){

        fetch('http://localhost:8080/api/customers/profile/' + this.state.id)
        .then(response => response.json())
        .then(data => this.setState({name:data['name'], url:data['imageUrl'], email: data['userName']}))


    }

    render(){
        return (
            <div>
                <div>
                    <h1>{this.state.name}</h1>
                    <h2>{this.state.email}</h2>
                    <img src={this.state.url} alt={this.state.name} />
                </div>
            </div>
        )
    }
}


export default Profile;