import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import NotFound from './NotFound';
import App from './App';
import Welcome from './Welcome';
import Navigation from './Navigation';

const Router = () => (

    <BrowserRouter>
        <Switch>
            <Route exact path="/" component={App}/>
            <Route exact path="/login" component={Welcome}/>
            {/* <Route exact path="/profile" component={}/> */}
            <Route component={NotFound}/>
        </Switch>
    
    </BrowserRouter>
);

export default Router;