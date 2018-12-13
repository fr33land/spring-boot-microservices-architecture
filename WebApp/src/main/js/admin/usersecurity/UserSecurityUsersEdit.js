import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Button, Form, FormGroup, Label, Input, FormText, Col } from 'reactstrap';
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

export default class UserSecurityUsersEdit extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            user: {},
            startDate: new Date(), 
            error: null,
            isLoaded: false,
        };
        
        this.handleChange = this.handleChange.bind(this);
    }
    
    componentDidMount() {
        fetch(`/ui/admin/users/find/${this.props.match.params.id}`)
            .then(response => response.json())
            .then(
                (result) => {
                this.setState({
                    isLoaded: true,
                    user: result
                });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
    }
  
    handleChange(date) {
        this.setState({ startDate: date });
    }
    
    render() {
        return (
            <Form className="editForm">
                <FormGroup>
                    <Label for="userId">Id</Label>
                    <Input type="text" name="userId" id="userId" disabled size="200" />
                </FormGroup>
                <FormGroup>
                    <Label for="userName">Username</Label>
                    <Input type="text" name="userName" id="userName" />
                </FormGroup>
                <FormGroup>
                    <Label for="firstName">First name</Label>
                    <Input type="text" name="firstName" id="firstName"/>
                </FormGroup>
                <FormGroup>
                    <Label for="lastName">Last name</Label>
                    <Input type="text" name="lastName" id="lastName"/>
                </FormGroup>
                <FormGroup>
                    <Label for="birthday">Birthday</Label>
                    <DatePicker className="form-control edit-from-datepicker" id="birthday" name="birthday" dateFormat="yyyy-MM-dd" selected={this.state.startDate} onChange={this.handleChange}/>
                </FormGroup>
                <FormGroup>
                    <Label for="nationality">Nationality</Label>
                    <Input type="text" name="nationality" id="nationality"/>
                </FormGroup>
                <FormGroup>
                    <Label for="city">City</Label>
                    <Input type="text" name="city" id="city"/>
                </FormGroup>
                <FormGroup>
                    <Label for="address">Address</Label>
                    <Input type="text" name="address" id="address"/>
                </FormGroup>
                <FormGroup>
                    <Label for="phone">Phone</Label>
                    <Input type="text" name="phone" id="phone"/>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Email</Label>
                    <Input type="email" name="email" id="email"/>
                </FormGroup>
                <Button>Submit</Button>{' '}<Button>Cancel</Button>
            </Form>
        );
  }
}