import React, {useEffect, useState} from 'react';
import {Button, ButtonGroup, Container, Form, FormGroup, Input, Label, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import {packageApi} from "../../api/packageApi";
import {useKeycloak} from "@react-keycloak/web";

export const LockerPage = () => {
    const [packages, setPackages] = useState([]);
    const [locker, setLocker] = useState("");
    const {keycloak} = useKeycloak();

    useEffect(() => {
        packageApi.getAll(keycloak.token)
            .then((res) => {
                setPackages(res.data);
            })
    }, []);

    const handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        console.log(name);
        console.log(value);

        setLocker(value);
        console.log(locker);

    }

    function onClear(){
        packageApi.getAll(keycloak.token)
            .then((res) => {
                setPackages(res.data);
            })
    }

    function filter(){
        console.log("setting package from lockers: "+locker)
        setPackages(packages.filter((aPackage) => ((aPackage.sender_locker_id === locker && aPackage.state == "0") ||
            (aPackage.receiver_locker_id === locker && aPackage.state == "2"))));
    }

    const remove = (id) => {
        packageApi.delete(id, keycloak.token)
            .then(() => {
                setPackages((packages) => packages.filter((aPackage) => aPackage.id !== id));
            });
    }

    const packageList = packages.map(aPackage => {
        return <tr key={aPackage.id}>
            <td style={{whiteSpace: 'nowrap'}}>{aPackage.id}</td>
            <td style={{whiteSpace: 'nowrap'}}>{aPackage.name}</td>
            <td align="center">
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/packages/" + aPackage.id}>
                        Edit
                    </Button>
                    <Button size="sm" color="danger" onClick={() => remove(aPackage.id)}>
                        Delete
                    </Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <Container fluid>
                <h3></h3>
                <h3>Locker name</h3>
                <Form>
                    <FormGroup>
                        <div className="input-group mb-3">
                            <div className="input-group-prepend">
                                <button className="btn btn-primary" type="button" onClick={filter}>Filter</button>
                                <button className="btn btn-secondary" type="button" onClick={onClear}>Reset</button>
                            </div>
                            <input type="text" id="lockerName" name="lockerName" className="form-control" value={locker || ''} onChange={handleChange} autoComplete="lockerName"
                                   aria-describedby="basic-addon1" placeholder="" aria-label=""
                            />
                        </div>

                    </FormGroup>
                </Form>
                <h3>Packages</h3>
                <Table striped bordered hover size="sm">
                    <thead>
                    <tr>
                        <th width="80px">Id</th>
                        <th>Name</th>
                        <th width="120px">
                            <div align="center">Action</div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {packageList}
                    </tbody>
                </Table>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/packages/new">
                        Add
                    </Button>
                </div>
            </Container>
        </div>
    );
}