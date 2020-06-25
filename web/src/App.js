import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

import RegisterPage from './components/page-components/RegisterPage';
import LoginPage from './components/page-components/LoginPage';
import SearchPage from './components/page-components/SearchPage';
import VideoPage from './components/page-components/VideoPage';

function App(){
  return(
    <BrowserRouter>
      <Switch>
        <Route path="/register" component={RegisterPage}/>
        <Route path="/login" component={LoginPage}/>
        <Route path="/search" component={SearchPage} />
        <Route path="/" component={VideoPage}/>
      </Switch>
    </BrowserRouter> 
  );
}
export default App;
