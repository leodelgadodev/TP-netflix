import React from 'react';
import './App.css';
import RegisterPageComponent from './components/page-components/RegisterPageComponent';
import LoginPageComponent from './components/page-components/LoginPageComponent';
import { BrowserRouter, Switch, Route } from 'react-router-dom';


function App(){
  return(
    <BrowserRouter>
      <Switch>
        <Route path="/register" component={RegisterPageComponent}/>
        <Route path= "/" component={LoginPageComponent}/>
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
