import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {packageApi} from "../../api/packageApi";

export const PackageForm = () => {
    const navigate = useNavigate();
    const {packageId} = useParams();
    const [aPackage, setPackage] = useState({
        name: ''
    });

    useEffect(() => {
        if (packageId !== 'new') {
            packageApi.getById(packageId)
                .then((res) => {
                    setPackage(res.data);
                });
        }
    }, [packageId]);

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        setPackage({...aPackage, [name]: value});
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (aPackage.id) {
            await packageApi.update(aPackage.id, aPackage)
        } else {
            await packageApi.create(aPackage)
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
                        id="name"
                        name="name"
                        type="text"
                        value={aPackage.name || ''}
                        onChange={handleChange}
                        autoComplete="name"/>
                </FormGroup>
                <FormGroup>
                    <Button color="primary" type="submit">Save</Button>{' '}
                    <Button color="secondary" tag={Link} to="/packages">Cancel</Button>
                </FormGroup>
            </Form>
        </Container>
    )
}