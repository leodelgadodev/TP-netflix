import React from 'react';
import './App.css';
import Register from './Register';
import Login from './Login';
import { BrowserRouter, Switch, Route } from 'react-router-dom';


function App(){
  return(
    <BrowserRouter>
      <Switch>
        <Route path="/register" component={Register}/>
        <Route path= "/" component={Login}/>
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
