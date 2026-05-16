import React, { useEffect, useRef, useState } from 'react'
import acc from '../../Style/acc.module.css';
import TopNavbar from '../Navbar/TopNavbar';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { API_CALL } from '../../ApiCall/apicall';
import config from '../../Config/config.json';
import { setJwtToken } from '../../Reducers/UserReducers';

function Verify() {

    const [otp, setOtp] = useState(new Array(6).fill(''));
    const [time, setTime] = useState(60);
    const refs = useRef([]);
    const [resend, setResend] = useState(false);
    const [error, setError] = useState(false);
    const selector = useSelector((state) => state.user);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        console.log(selector.token);
        if(selector.token == null){
            navigate('/');
        }
        const timer = setInterval(() => {
            setTime((prev) => {
                if(prev <= 1) {
                    clearInterval(timer);
                    return 0;
                };
                return prev - 1;
            } )
        },1000);
        return () => {clearInterval(timer)}
    },[]);

    const onchange = (e, ind) => {
        let value = e.target.value;
        if (!/^\d?$/.test(value)) return;

        const otps = [...otp];
        otps[ind] = value;
        setOtp(otps);
        if (value && ind < otp.length - 1) {
            refs.current[ind + 1]?.focus();
        }
    }

    const keydownPress = (e, ind) => {
        let keys = e.key;
        if (keys === "Backspace") {
            if (!otp[ind] && ind > 0) {
                refs.current[ind - 1]?.focus();
            }
        }
    }

    const submitData = async () => {
        let value = otp.join('');
        if(value.length < 6) {
            setError("invalid format otp !");
        }else{
            console.log(selector);
            let objs = {
                "Code": value,
                "email": selector.user.user_email,
                "token": selector.token,
            }
            let res = await API_CALL(objs, 'POST', config.public_url + 'verification');
            setError('');
            if(res.response.status === 200) {
                dispatch(setJwtToken(res.result.msg));
                navigate('/home');
            }else{
                setError(res.result.msg);
            }
        }
    }

    return (
        <div className={acc.main}>
            <TopNavbar />
            <div className={acc.top}>
                <div className={acc.box}>
                    <label className={acc.heading}>Verification</label>
                    <label className={acc.subHeading}>We've sent a 6-digit code to your email</label>
                    <div className='w-100 d-flex align-items-center justify-content-evenly my-2'>
                    {
                        otp.map((val, id) => {
                            return(
                                <input key={id} type='number' className={acc.numbers} onChange={e => onchange(e, id)} inputMode='numeric' maxLength={1} onKeyDown={(e) => keydownPress(e, id)} ref={(el) => refs.current[id] = el} value={otp[id]}/>
                            )
                        })
                    }
                    </div>
                    {
                        error && (
                            <label className={acc.err}>{error}</label>
                        )
                    }
                    <div className='w-100 d-flex align-items-center justify-content-between mt-4'>
                        <label className={acc.subHeading}>Didn't recived the OTP  ?</label>
                        {
                            time > 0 ? (
                                <label className={acc.subHeading}>00 : {time < 10 &&0}{time}</label>
                            ) : (
                                <label className={acc.resend} onClick={() =>setResend(true)} style={resend ? {cursor: 'not-allowed', color: '#ccc'} : {cursor: "pointer"}}>Resend</label>
                            )
                        }
                    </div>
                        <button className={acc.btns} onClick={submitData}>Verify</button>
                    <div>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default Verify