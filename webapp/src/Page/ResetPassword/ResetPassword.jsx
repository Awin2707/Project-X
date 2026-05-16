import React, { useEffect, useRef, useState } from 'react'
import acc from '../../Style/acc.module.css';
import config from '../../Config/config.json';
import UserField from '../../InputField/UserField';
import { useParams, useSearchParams } from 'react-router-dom';
import { generateFingerPrintID } from '../../common/Common';
import { API_CALL } from '../../ApiCall/apicall';

function ResetPassword() {
    const[input, setInputs] = useState([]);
    const [errors, setError] = useState({});
    const[data, setData] = useState({});
    const refs = useRef([]);
    const {redirectParam} = useParams();
    const [token, setToken] = useState('');
    useEffect(() => {
        let objs = [
            { type: "password", name: "user_email", placeholder: "Enter your new Password", pass: true, img: config.img_path + "password.svg", err: "", label: "Password" },
            { type: "password", name: "user_pass", placeholder: "Confirm the new Password", pass: true, img: config.img_path + "password.svg", err: "", label: "Confirm Password" }
        ];
        setInputs(objs);
        let value = redirectParam;
        setToken(value);
        console.log(value);
    },[]);

    const chnageInput = (e) => {
        const { value, name } = e.target;
        let objs = { ...data, [name]: value };
        setData(objs);
    }

    const keyPress = (e, id) => {
        if (e.key === "Enter") {
            refs.current[id + 1]?.focus();
        }
    }

    const checkValidation = () => {
        const err = {};
        let bool = true;
        if(!data.user_email){
            err.msg = "* Password can't be empty !";
            bool = false;
        }else if(data.user_email.length < 7){
            err.msg = "* New Password length should be more than 7 chars";
            bool = false;
        }

        if(!data.user_pass){
            err.msg = "* Password can't be empty !";
            bool = false;
        }else if(data.user_pass != data.user_email){
            err.msg = "* password not match !";
            bool = false;
        }
        setError(err);
        return bool;
    }

    const submitData = async () => {
        let booleanValue = checkValidation();
        if(booleanValue){
            let objs = {
                "token": token,
                "password": data.user_pass,
                "setFingerPrint": generateFingerPrintID()
            }
            const apiCall = await API_CALL(objs, 'POST', config.public_url + 'reset-password');
            if(apiCall.response.status === 200){
                console.log("success !");
            }else{
                console.log("failed !");
            }
        }
    }
    return (
        <div className={acc.top}>
            <div className={acc.box}>
                <label className={acc.heading}>Reset Password</label>
                <label className={acc.subHeading}>Choose a strong password to secure your account.</label>
                {
                    input.map((val, id) => {
                        return (
                            <UserField key={id} type={val.type} name={val.name} onchnage={chnageInput} ind={id} onkey={(e) => keyPress(e, id)} ref={(el) => { refs.current[id] = el }} pass={val.pass} img={val.img} placeholder={val.placeholder} label={val.label} err={errors[val.name]} value={data[val.name]} />
                        )
                    })
                }
                <button className={acc.btns} onClick={submitData}>Reset Password</button>
            </div> 
        </div>
    )
}

export default ResetPassword