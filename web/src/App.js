import React, { useState } from 'react';
import './App.css';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

import RegisterPage from './components/page-components/RegisterPage';
import LoginPage from './components/page-components/LoginPage';
import SearchPage from './components/page-components/SearchPage';
import HomePage from './components/page-components/HomePage'
import Page from './components/page-components/Page';

function App() {
  const [token, setToken] = useState("");

  const authenticate = (token) => {
    setToken(token);
    }

  return(
    <BrowserRouter>
      <Switch>
        {/* <Route exact path="/search" render={() => <SearchPage token={token}/>} /> */}
        <Route exact path="/" render={() => <HomePage token={token}/>} /> 
        <Route exact path="/register" render={() => <RegisterPage auth={authenticate}/>}/>
        <Route exact path="/login" render={() => <LoginPage auth={authenticate}/>}/>
        {/* <Route exact path="/search" render={() => <Page token={token} component={<SearchPage/>}/>}/>
        <Route exact path="/" render={() => <Page token={token.toString()} component={<HomePage/>}/>}/> */}
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
