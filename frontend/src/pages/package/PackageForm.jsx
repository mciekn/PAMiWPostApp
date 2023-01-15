import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {packageApi} from "../../api/packageApi";
import {useKeycloak} from "@react-keycloak/web";

export const PackageForm = () => {
    const {keycloak} = useKeycloak();
    const navigate = useNavigate();
    const {packageId} = useParams();
    const [aPackage, setPackage] = useState({
        name: '',
        sender_locker_id: '',
        receiver_locker_id: '',
        receiver_id: '',
        state: ''
    });

    useEffect(() => {
        if (packageId !== 'new') {
            packageApi.getById(packageId, keycloak.token)
                .then((res) => {
                    setPackage(res.data);
                });
        }
        keycloak.updateToken()
    }, [packageId]);

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        console.log(name);
        console.log(value);

        setPackage({...aPackage, [name]: value});
        console.log(aPackage);
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (aPackage.id) {
            await packageApi.update(aPackage.id, aPackage, keycloak.token)
        } else {
            await packageApi.create(aPackage, keycloak.token)
        }
        navigate('/packages')
    }

    const title = <h2>{aPackage.id ? 'Edit package' : 'Add Package'}</h2>;

    return (
        <Container>
            {title}
            <Form onSubmit={handleSubmit}>
                <FormGroup>
                    <Label for="name">Name</Label>
                    <Input
                        readOnly={aPackage.state !== "" ? true : false }
                        id="name"
                        name="name"
                        type="text"
                        value={aPackage.name || ''}
                        onChange={handleChange}
                        autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Label for="sender_locker_id">Source Locker</Label>
                    <Input id="sender_locker_id"
                           readOnly={aPackage.state !== "" ? true : false }
                           name="sender_locker_id"
                           type="text"
                           value={aPackage.sender_locker_id || ''}
                           onChange={handleChange}
                           autoComplete="sender_locker_id"/>
                </FormGroup>
                <FormGroup>
                    <Label for="receiver_locker_id">Destination Locker</Label>
                    <Input id="receiver_locker_id"
                           readOnly={aPackage.state !== "" ? true : false }
                           name="receiver_locker_id"
                           type="text"
                           value={aPackage.receiver_locker_id || ''}
                           onChange={handleChange}
                           autoComplete="receiver_locker_id"/>
                </FormGroup>
                <FormGroup>
                    <Label for="receiver_id">Receiver name</Label>
                    <Input id="receiver_id"
                           readOnly={aPackage.state !== "" ? true : false }
                           name="receiver_id"
                           type="text"
                           value={aPackage.receiver_id || ''}
                           onChange={handleChange}
                           autoComplete="receiver_id"/>
                </FormGroup>
                <FormGroup>
                    <div className="btn-group btn-group-toggle" onChange={handleChange} >
                        <label className={aPackage.state === "0" ? "btn btn-success" : "btn btn-secondary" } >
                            <input disabled={aPackage.state >= "1" ? true : false} type="radio" value="0" name="state" /> POSTED
                        </label>
                        <label className={aPackage.state === "1" ? "btn btn-success" : "btn btn-secondary" } style={{display: aPackage.state >= "0" ? 'inline' : 'none' }}>
                            <input disabled={aPackage.state >= "2" ? true : false}  type="radio" value="1" name="state" /> TRANSFERRING
                        </label>
                        <label className={aPackage.state === "2" ? "btn btn-success" : "btn btn-secondary" } style={{display: aPackage.state >= "1" ? 'inline' : 'none' }}>
                            <input disabled={aPackage.state >= "3" ? true : false}  type="radio" value="2" name="state" /> DELIVERED
                        </label>
                        <label className={aPackage.state === "3" ? "btn btn-success" : "btn btn-secondary" } style={{display: aPackage.state >= "2" ? 'inline' : 'none' }}>
                            <input type="radio" value="3" name="state"  /> RECEIVED
                        </label>
                    </div>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/packages">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
    )
}