import 'bootstrap/dist/css/bootstrap.min.css';
import $ from 'jquery';
import Popper from 'popper.js';
import 'bootstrap/dist/js/bootstrap.bundle.min';
import React from 'react';
import Header from './Header';
import FoodItem from './FoodItem';
import Order from './Order'
import { getDailyMenu } from './util/APIUtils';
import MenuAppBar from './Navigation';
import ImgMediaCard from './ImgMediaCard';
import Grid from '@material-ui/core/Grid';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import SideBar from './Sidebar';


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

        

        getDailyMenu()
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

    deductFromOrder = key => {
        const order= {...this.state.order};
        order[key] = order[key] - 1;

        if (order[key] <= 0){
            delete order[key];
        }
        this.setState({order});
    }


    clearOrder = () => {

        this.setState({order: {}})
    }



    render(){

        console.log(this.state.foodItems);

        return (
            <div>
                <MenuAppBar/>
                {/* <SideBar/> */}
                <Grid container spacing={32}  direction="row" justify="space-evenly"
                     alignItems="stretch">  
                    <Grid item xs={3}>
                        <Header tagline="Order Lunch"/>
                        {Object.keys(this.state.foodItems).map (key => 
                        <FoodItem 
                            key={key} 
                            index = {key}
                            details={this.state.foodItems[key]} 
                            addToOrder={this.addToOrder} 
                            deductFromOrder = {this.deductFromOrder}
                            itemId={this.state.foodItems[key]['itemId']}
                        />  
                        )}
                    </Grid>
                    <Grid item xs={4}>
                        <Order items={this.state.foodItems} order={this.state.order} token={this.state.token} clearOrder = {this.clearOrder}/>  
                    </Grid>
                </Grid>
                <footer className="page-footer font-small">
                    <div className="footer-copyright text-center py-3">© 2018 Copyright:
                        <a href="https://mdbootstrap.com/bootstrap-tutorial/"> VentureDive</a>
                    </div>
                </footer>
            </div>
        );
    }

}

export default App;
