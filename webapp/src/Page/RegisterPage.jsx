import React, { useEffect, useState } from 'react'
import main from '../Style/main.module.css'
import Register from '../Component/Register/Register'
import Loading from '../Component/Loading/Loading';
import ApiJson from '../../config.json';
import {useNavigate} from 'react-router-dom';
import { apiCall } from '../common/ApiCall/Apicall';
import { decode } from '../common/Base64/Base64';
import {useDispatch} from 'react-redux'
import { setToken, setUsers } from '../Redcuers/UserReducers';
// import { decode, encode } from '../Common/Base64';
// import { apiCall } from '../Common/ApiCall';
function RegisterPage() {

    const [data, setData] = useState({
        "name":"",
        "email":"",
        "pass":"",
        "browser": "brave"
    });
    const navigate = useNavigate();
    const [msg, errMsg] = useState({});
    const [warn, setWaring] = useState(false);
    const [loading, setloading] = useState(false);
    const dispatch = useDispatch();

    const onchange = (e) => {
        setData({...data, [e.target.name] : e.target.value});
        errMsg({...msg, [e.target.name]: ""});
    }

    const validate = () => {
        let err = {};
        let check = false;
        if(!data.name){
            err.name = "Name can't be empty !";
            check = true;
        }
        if(!data.email){
            err.email = "Email can't be empty !";
            check = true;
        }
        if(!data.pass || data.pass.length == 0){
            err.pass = "Password can't be empty !";
            check = true;
        }else if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{8,}$/.test(data.pass)){
            setWaring(true);
            check = true;
        }else{
            setWaring(false);
        }
        errMsg(err);
        return check;
    }

    const onSubmit = async () => {
        let bool = validate();
        setloading(true);
        if(!bool){
            await apiCall(ApiJson.apipublic_path+"createAccount", "POST", data).then(async (res) => {
                const response = await res.json();
                let err = {};
                if(res.ok){
                    console.log(response.msg, response, "myresponse");
                    dispatch(setToken(response.msg));
                    dispatch(setUsers(data));
                    setloading(false);
                    navigate("/verification")
                }else if(res.status == 400){
                    err.email = res.msg;
                    errMsg(msg);
                    setloading(false);
                }
            });
            // navigate("/verification")
        }
    }

    return (
        <div className={main.main}>
            <Register values={data} onChange={(e) => onchange(e)} onSubmit={onSubmit} msg={msg} warn={warn} />
            {
                loading && <Loading />
            }
        </div>
    )
}

export default RegisterPage