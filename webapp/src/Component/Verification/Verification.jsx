import React, { useEffect, useRef, useState } from 'react'
import main from '../../Style/main.module.css';
import logo from '../../Assets/savingcost.png';
import { apiCall } from '../../common/ApiCall/Apicall';
import ApiJson from '../../../config.json';
import { Navigate, useNavigate } from 'react-router-dom';
import {useSelector} from 'react-redux';

function Verification() {

    const [otp, setOtp] = useState(Array(6).fill(''));
    const ref = useRef([]);
    const[count, setCount] = useState(60);
    const[resend, setResend] = useState(false);
    const[err, setErr] = useState(false);
    const navigate = useNavigate();
    const token = useSelector((state) => state.users.token);
    const userData = useSelector((state) => state.users.users);

    useEffect(() => {
        let interval = setInterval(() => {
            setCount((prev) => {
                if(prev > 0) {
                    return prev -1;
                }
                return prev;
            })
        },1000)
        return () => {clearInterval(interval)}
    },[])

    const onkeyDownPress = (e, index) => {
        if (e.key == "Backspace") {
            if (otp[index] === "" && index > 0) {
                ref.current[index - 1]?.focus();
            }
        }
    }

    const resendOtpCode = () => {
        setResend(true);
    }

    const onchangesWork = (e, index) => {
        let value = e.target.value;

        if (!/^[0-9]?$/.test(value)) return;

        const arr = [...otp];
        arr[index] = value;
        setOtp(arr);

        if (value && index < 5) {
            ref.current[index + 1]?.focus();
        }
    }

    const handleSubmit = async () => {
        let value = otp.join("");
        console.log(value);
        let data = {
            email: userData.email,
            token: token,
            code: value
        }
        await apiCall(ApiJson.apipublic_path+"verify", "POST",data)
            .then(async (res) => {
                let response = await res.json();
                if(res.ok){
                    navigate("/home");
                }else if(res.status == 400){
                    console.log(response.msg);
                }
            })
    }

    useEffect(() => {
        console.log(token);
        if(!token || token == undefined){
            navigate("/");
        }
    },[])

    return (
        <div className={main.main}>
            <div className={main.register}>
                <div className={main.mindivs}>
                    <img alt='x-ico' src={logo} className={main.logo} />
                    <label className={main.subHead}>Save Cost Management</label>
                </div>
                <div className={main.minsiv1}>
                    <label className={main.head}>Verification Page</label>
                    <label className={main.smallhead}>We have sent 6 digit OTP code to your email id.</label>
                    <div className={main.divs3}>
                    </div>
                    <div className={main.divs5}>
                        {
                            otp.map((val, index) => {
                                return (
                                    <input className={main.inputs} type='text' ref={(el) => ref.current[index] = el} onKeyDown={e => onkeyDownPress(e, index)} onChange={e => onchangesWork(e, index)} value={val} />
                                )
                            })
                        }
                    </div>
                    <div className={main.divs3}>
                        {err && <label>{err}</label>}
                    </div>
                    <div className={main.divs5}>
                        <label className={main.label}>I did not recived the OTP Code ? </label>
                        {
                            count == 0 ? <label className={main.log} onClick={resendOtpCode} style={{color: resend ? "#EBEBE4" : "#4585f4", cursor: resend ? "not-allowed" : "pointer"}}>Resend Code</label> :
                            <label className={main.log}>0: {count}</label>
                        }
                    </div>
                    <div className={main.divs3}>
                    </div>
                </div>
                <input type='button' value={"Verify"}  className={main.btns} onClick={() => handleSubmit()}/>

            </div>
        </div>
    )
}

export default Verification