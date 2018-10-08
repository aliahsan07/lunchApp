import React from 'react';
import Header from './Header';
import FoodItem from './FoodItem';
import Order from './Order'

class App extends React.Component{

    state={
        order: {},
        foodItems: {},
        isAuthenticated: false, 
        user: null, 
        token: null
    }

    componentDidMount(){

        const localStorageRef = localStorage.getItem('token');

        //this.checkPermissions(this.props.user);
        this.checkPermissions(localStorageRef)
        

        fetch('http://localhost:8080/api/customers/dailymenu')
        .then(response => response.json())
        .then(data => this.setState({foodItems : data['items']}));

    
    }

    checkPermissions = (token) => {
        // if ((!user || !user.access_token) && this.props.history.location.pathname !== '/login') {
        //   this.props.history.push('/login');
        // }

        if (!token){
            this.props.history.push('login');
        }
        
        this.setState({token: token});
      }


    addToOrder = key => {

        const order = {...this.state.order};
        order[key] = order[key] + 1 || 1;
        this.setState({order});
    }



    render(){
        return (
            <div className="Lunchbox-main">
                <div className="menu">
                 <Header tagline="Lets order Lunch"/>
                 <ul className="items">
                    {Object.keys(this.state.foodItems).map (key => 
                    <FoodItem 
                        key={key} 
                        index = {key}
                        details={this.state.foodItems[key]} 
                        addToOrder={this.addToOrder} 
                        itemId={this.state.foodItems[key]['itemId']}
                    />  
                    )}
                 </ul>
                </div>
                <Order items={this.state.foodItems} order={this.state.order} token={this.state.token}/>
            </div>
        );
    }

}

export default App;
