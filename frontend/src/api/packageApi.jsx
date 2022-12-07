import {backendApi} from "./backendApi";

const packageClient = backendApi('/packages')

export const packageApi = {
    getAll() {
        console.log('Fetching packages')
        return packageClient.get('')
    },

    getById(id) {
        console.log('Get package', id)
        return packageClient.get(`/${id}`)
    },

    create(aPackage) {
        console.log('Create package', aPackage)
        return packageClient.post('', aPackage)
    },

    update(id, aPackage) {
        console.log('Update package', id, aPackage)
        return packageClient.put(`/${id}`, aPackage);
    },

    delete(id) {
        console.log('Delete package', id)
        return packageClient.delete(`/${id}`)
    }
}