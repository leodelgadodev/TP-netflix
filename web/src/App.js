import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

import RegisterPage from './components/page-components/RegisterPage';
import LoginPage from './components/page-components/LoginPage';
import SearchPage from './components/page-components/SearchPage';
import HomePage from './components/page-components/HomePage'
import Page from './components/page-components/Page';

function App() {

  return(
    <BrowserRouter>
      <Switch>
        <Route exact path="/register" render={() => <RegisterPage/>}/>
        <Route exact path="/login" render={() => <LoginPage/>}/>
        <Route exact path="/search" render={() => <Page component={SearchPage}/>}/>
        <Route exact path="/" render={() => <Page component={HomePage}/>}/>
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
