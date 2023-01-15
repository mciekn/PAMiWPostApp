import {backendApi} from "./backendApi";
import {bearerAuth} from "./bearerAuth";

const packageClient = backendApi('/packages')

export const packageApi = {
    getAll(token) {
        console.log('Fetching packages')
        return packageClient.get('', {
            headers: {'Authorization': bearerAuth(token)}
        })
    },

    getById(id, token) {
        console.log('Get package', id)
        return packageClient.get(`/${id}`, {
            headers: {'Authorization': bearerAuth(token)}
        })
    },

    create(aPackage, token) {
        console.log('Create package', aPackage)
        return packageClient.post('', aPackage, {
            headers: {'Authorization': bearerAuth(token)}
        })
    },

    update(id, aPackage, token) {
        console.log('Update package', id, aPackage)
        return packageClient.put(`/${id}`, aPackage, {
            headers: {'Authorization': bearerAuth(token)}
        });
    },

    delete(id, token) {
        console.log('Delete package', id)
        return packageClient.delete(`/${id}`, {
            headers: {'Authorization': bearerAuth(token)}
        })
    }
}