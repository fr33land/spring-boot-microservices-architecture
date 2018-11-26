import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Link, Redirect } from 'react-router-dom'
import { TabContent, TabPane, Nav, NavItem, NavLink, Row, Col } from 'reactstrap';
import classnames from 'classnames';

class UserSecurity extends React.Component {
    constructor(props) {
        super(props);
            this.toggle = this.toggle.bind(this);
            this.state = { activeTab: 'users' };
    }

    toggle(tab) {
        if (this.state.activeTab !== tab) {
            this.setState({activeTab: tab});            
            if (tab === 'users' && !$.fn.dataTable.isDataTable('#users_table')) {
                this.showUsersTable();
            } else if (tab === 'roles' && !$.fn.dataTable.isDataTable('#roles_table')) {
                this.showRolesTable();
            } else if (tab === 'permissions' && !$.fn.dataTable.isDataTable('#permissions_table')) {

            }
        }
    }
    
    componentDidMount() {
        this.showUsersTable();
    }
    
    showUsersTable(){
        var _ = this;
        
        $('#users_table').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
                url: "/ui/admin/users/find",
                contentType: "application/json",
                type: "POST",
                data: function (d) {
                    return JSON.stringify(d);
                }
            },
            initComplete: function () {
                $( document ).on("click", "#users_table > tbody > tr[role='row']", function(){
                    _.showEditForm("/user/edit", $(this).children('td:first-child').text());
                });
            },
            columns: [
                {data: "userId"},
                {data: "firstName"},
                {data: "lastName"},
                {data: "birthday",
                    render: function (d) {
                        return moment(d).format("YYYY-MM-DD");
                    }
                },
                {data: "city"},
                {data: "address"},
                {data: "phone"}
            ]
        });
        
        
    }
    
    showRolesTable(){
        var _ = this;
        
        $('#roles_table').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
                url: "/ui/admin/roles/find",
                contentType: "application/json",
                type: "POST",
                data: function (d) {
                    return JSON.stringify(d);
                }
            },
            initComplete: function () {
                $( document ).on("click", "#roles_table > tbody > tr[role='row']", function(){
                    _.showEditForm("/role/edit", $(this).children('td:first-child').text());
                });
            },            
            columns: [
                {data: "id"},
                {data: "name"},
                {data: "description"}
            ]
        });
    }

    showPermissionsTable() {
        var _ = this;    
    
        $('#permissions_table').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
                url: "/ui/admin/permissions/find",
                contentType: "application/json",
                type: "POST",
                data: function (d) {
                    return JSON.stringify(d);
                },
                error: function (xhr, textStatus, errorThrown) {
                    alert(textStatus);
                }
            },
            initComplete: function () {
                $( document ).on("click", "#permissions_table > tbody > tr[role='row']", function(){
                    _.showEditForm("/permission/edit", $(this).children('td:first-child').text());
                });
            },
            columns: [
                {data: "userId"},
                {data: "firstName"},
                {data: "lastName"},
                {data: "birthday",
                    render: function (d) {
                        return moment(d).format("YYYY-MM-DD");
                    }
                },
                {data: "city"},
                {data: "address"},
                {data: "phone"}
            ]
        });
    }
    
    showEditForm(url, id){
        this.props.history.replace("/ui/admin/users" + url + "/" + id);
    }

    render() {
        return (
            <div>
                <Nav tabs>
                    <NavItem>
                        <NavLink className={classnames({ active: this.state.activeTab === 'users' })} onClick={() => { this.toggle('users'); }}>
                            Users
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink className={classnames({ active: this.state.activeTab === 'roles' })} onClick={() => { this.toggle('roles'); }}>
                            Roles
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink className={classnames({ active: this.state.activeTab === 'permissions' })} onClick={() => { this.toggle('permissions'); }}>
                            Permissions
                        </NavLink>
                    </NavItem>
                </Nav>
                <TabContent activeTab={this.state.activeTab}>
                    <TabPane tabId="users">
                        <Row>
                            <Col sm="12">
                            <br/>
                            <table id="users_table" className="table table-hover" style={{width:'100%'}}>
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>First name</th>
                                        <th>Last name</th>
                                        <th>Birthday</th>
                                        <th>City</th>
                                        <th>Address</th>
                                        <th>Phone</th>
                                    </tr>
                                </thead>
                            </table>
                            </Col>
                        </Row>
                    </TabPane>
                    <TabPane tabId="roles">
                        <Row>
                            <Col sm="12">
                                <br/>
                                <table id="roles_table" className="table table-hover" style={{width:'100%'}}>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Role name</th>
                                            <th>Description</th>
                                        </tr>
                                    </thead>
                                </table>
                            </Col>
                        </Row>  
                    </TabPane>
                    <TabPane tabId="permissions">
                        <Row>
                            <Col sm="12">
                                <br/>
                                <table id="permissions_table" className="table table-hover" style={{width:'100%'}}>
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Permission name</th>
                                            <th>Description</th>
                                        </tr>
                                    </thead>
                                </table>
                            </Col>
                        </Row>
                    </TabPane>
                </TabContent>
            </div>
        );
    }
}

export default UserSecurity