import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {NavBar} from "./components/NavBar";
import {Home} from "./pages/home/Home";
import {PackagePage} from "./pages/packages/PackagePage";
import {PackageForm} from "./pages/package/PackageForm";
import {LockerPage} from "./pages/lockers/LockerPage";
import {ProtectedRoute} from "./components/ProtectedRoute";

export default function App(){
    return (
        <>
            <NavBar/>
            <Routes>
                <Route index element={<Home/>}/>
                <Route element={<ProtectedRoute/>}>
                    <Route path='/packages' element={<PackagePage/>}/>
                    <Route path='/lockers' element={<LockerPage/>}/>
                    <Route path='/packages/:packageId' element={<PackageForm/>}/>
                </Route>
            </Routes>
        </>
    )
}