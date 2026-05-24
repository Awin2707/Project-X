import React from 'react'
import main from '../../Style/main.module.css';
import acc from '../../Style/acc.module.css';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { setLogout, setPage } from '../../Reducers/UserReducers';

function Logout() {
    const navi = useNavigate();
    const path = useSelector((state) => state.user.page);
    const dispatch = useDispatch();


    const notLogout = () => {
        console.log(path);
        dispatch(setLogout(false));
        dispatch(setPage("Dashboard"));
        navi(`/home`);
    }
    const yesLogout = () => {
        navi("/");
    }
  return (
    <div className={main.loading}>
        <div className={acc.box}>
            <label className={acc.heading}>Log out</label>
            <label className={acc.subHeading}>are you sure you need to logout ?</label>
            <div className="d-flex w-100 p-2 gap-2">
                <button className="btn btn-primary w-100" onClick={yesLogout}>Yes</button>
                <button className="btn btn-secondary w-100" onClick={notLogout}>No</button>
            </div>
        </div>
    </div>
  )
}

export default Logout;