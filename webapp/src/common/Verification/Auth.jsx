import React from 'react'
import {useSelector} from 'react-redux';
import { Navigate, Outlet } from 'react-router-dom';

function Auth() {

    const isAuth = useSelector((state) => state.users.isAuth);
    const isLogout = useSelector((state) => state.users.isLogout);
    console.log(!isAuth, isLogout);
    return !isAuth && isLogout ? <Outlet/> : <Navigate to={"/"} />
}

export default Auth
