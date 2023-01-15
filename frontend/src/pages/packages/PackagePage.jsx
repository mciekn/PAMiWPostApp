import React, {useEffect, useState} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import {packageApi} from "../../api/packageApi";
import {useKeycloak} from "@react-keycloak/web";

export const PackagePage = () => {
    const {keycloak} = useKeycloak();
    const [packages, setPackages] = useState([]);

    useEffect(() => {
        packageApi.getAll(keycloak.token)
            .then((res) => {
                setPackages(res.data);
            })
    }, []);

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