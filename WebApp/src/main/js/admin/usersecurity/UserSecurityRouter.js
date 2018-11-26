import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, BrowserRouter, Switch } from 'react-router-dom'
import UserSecurity from './UserSecurity'
import UserSecurityEdit from './UserSecurityEdit'

const UserSecurityRouter = (
    <BrowserRouter>
        <Switch>
            <Route exact path="/ui/admin/users" component={UserSecurity} />
            <Route path="/ui/admin/users/edit/:id" component={UserSecurityEdit} />      
        </Switch>
    </BrowserRouter>
)

ReactDOM.render(UserSecurityRouter, document.getElementById('users-content'));