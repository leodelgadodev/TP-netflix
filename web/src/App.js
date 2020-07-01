import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

import RegisterPage from './components/page-components/RegisterPage';
import LoginPage from './components/page-components/LoginPage';
import SearchPage from './components/page-components/SearchPage';
import HomePage from './components/page-components/HomePage'
import Page from './components/page-components/Page';
import VideoPage from './components/page-components/VideoPage';
import MediaPage from './components/page-components/MediaPage';


function App() {

  return(
    <BrowserRouter>
      <Switch>
        <Route exact path="/register" render={() => <RegisterPage/>}/>
        <Route exact path="/login" render={() => <LoginPage/>}/>
        <Route exact path="/search" render={() => <Page component={SearchPage}/>}/>
        <Route exact path="/media/:id" render={() => <Page component={MediaPage}/>} />
        <Route exact path="/" render={() => <Page component={HomePage}/>}/>
        <Route path="*" render={() => <h1>404 NOT FOUND</h1>}/>
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
