import React from 'react';
import {Route, Routes} from 'react-router-dom';
import {NavBar} from "./components/NavBar";
import {Home} from "./pages/home/Home";
import {PackagePage} from "./pages/packages/PackagePage";
import {PackageForm} from "./pages/package/PackageForm";

export default function App(){
    return (
        <>
            <NavBar/>
            <Routes>
                <Route index element={<Home/>}/>
                <Route path='/packages' element={<PackagePage/>}/>
                <Route path='/packages/:packageId' element={<PackageForm/>}/>
            </Routes>
        </>
    )
}