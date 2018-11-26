import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, BrowserRouter, Switch } from 'react-router-dom'
import UserSecurity from './UserSecurity'
import UserSecurityUsersEdit from './UserSecurityUsersEdit'
import UserSecurityRolesEdit from './UserSecurityRolesEdit'
import UserSecurityPermissionsEdit from './UserSecurityPermissionsEdit'

const UserSecurityRouter = (
    <BrowserRouter>
        <Switch>
            <Route exact path="/ui/admin/users" component={UserSecurity} />
            <Route path="/ui/admin/users/user/edit/:id" component={UserSecurityUsersEdit} />      
            <Route path="/ui/admin/users/role/edit/:id" component={UserSecurityRolesEdit} />     
            <Route path="/ui/admin/users/permission/edit/:id" component={UserSecurityPermissionsEdit} />     
        </Switch>
    </BrowserRouter>
)

ReactDOM.render(UserSecurityRouter, document.getElementById('users-content'));