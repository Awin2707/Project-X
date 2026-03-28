import React, { useEffect, useState } from 'react'
import main from '../Style/main.module.css'
import Register from '../Component/Register/Register'
import Loading from '../Component/Loading/Loading';
// import { decode, encode } from '../Common/Base64';
// import { apiCall } from '../Common/ApiCall';
function RegisterPage() {

    const [data, setData] = useState({
        "name":"",
        "email":"",
        "pass":""
    });
    const [msg, errMsg] = useState({});
    const [warn, setWaring] = useState(false);
    const [loading, setloading] = useState(false);

    const onchange = (e) => {
        console.log(e.target.name, e.target.value)
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
        console.log(data);
        return check;
    }

    const onSubmit = async () => {
        let bool = validate();
        
    }

    return (
        <div className={main.main}>
            {console.log(data)  }
            <Register values={data} onChange={(e) => onchange(e)} onSubmit={onSubmit} msg={msg} warn={warn} />
            {
                loading && <Loading />
            }
        </div>
    )
}

export default RegisterPage