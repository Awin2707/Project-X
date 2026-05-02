import React, { useEffect, useRef, useState } from 'react'
import UserField from '../../InputField/UserField';
import acc from '../../Style/acc.module.css';
import config from '../../Config/config.json';
import { NavLink } from 'react-router-dom';
import TopNavbar from '../Navbar/TopNavbar';
import { useDispatch } from 'react-redux';
import { setUsers } from '../../Reducers/UserReducers';

function Login() {
    const [inputs, setInput] = useState([]);
    const [data, setdata] = useState({
        "user_email": "",
        "user_pass": "",
        "keep_me" : false,
    });
    const [errors, setError] = useState({});
    const refs = useRef([]);
    const dispatch = useDispatch();

    useEffect(() => {
        let objs = [
            { type: "email", name: "user_email", placeholder: "Enter your Email id", pass: false, img: config.img_path + "mail.svg", err: "", label: "Email" },
            { type: "password", name: "user_pass", placeholder: "Enter your Password", pass: true, img: config.img_path + "password.svg", err: "", label: "Password" }
        ];
        setInput(objs);
    }, [errors]);

    const chnageInput = (e) => {
        const { value, name } = e.target;
        let objs = { ...data, [name]: value };
        setdata(objs);
    }

    const keyPress = (e, id) => {
        if (e.key === "Enter") {
            refs.current[id + 1]?.focus();
        }
    }
    const clicksVerify = (e) => {
        const { checked } = e.target;
        let objs = {...data};
        objs.keep_me = checked;
        setdata(objs);
    }

    const validCheck = () => {
        let bool = true;
        const err = {};
        if (!data.user_email) {
            err.user_email = "*email id can't be empty !";
            bool = false;
        } else if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(data.user_email)) {
            err.user_email = "*invalid email id !";
            bool = false;
        }
        if (!data.user_pass) {
            err.user_pass = "*passowrd can't be empty !";
            bool = false;
        } else if (data.user_pass.length < 7) {
            err.user_pass = "*password length should be more than 7 chars !";
            bool = false;
        }
        setError(err);
        return bool;
    }

    const submitData = () => {
        let bools = validCheck();
        if (bools) {
            dispatch(setUsers(data))
        }
    }

    return (
    <div className={acc.main}>
      <TopNavbar />
      <div className={acc.top}>
        <div className={acc.box}>
          <label className={acc.heading}>Sign in</label>
          <label className={acc.subHeading}>Sign in to enjoy the features of your savings.</label>
          {
            inputs.map((val, id) => {
              return(
                <UserField key={id} type={val.type} name={val.name} onchnage={chnageInput} ind={id} onkey={(e) => keyPress(e, id)} ref={(el) => {refs.current[id] = el}} pass={val.pass} img={val.img} placeholder={val.placeholder} label={val.label} err={errors[val.name]} value={data[val.name]}/>
              )
            })
          }
          <div style={{margin: "15px 0px", display: "flex", alignItems: "center", justifyContent: "space-between"}}>
            <div className={acc.left_div}>
                <input type='checkbox' style={{width: "15px", height: "15px"}} onClick={(e) => clicksVerify(e)}/>
                <label style={{marginLeft: "5px",}}>Keep me Sign in</label>
            </div>
            <div className={acc.right_div}>
                <label style={{cursor: 'pointer'}}>Forget Password ?</label>
            </div>
          </div>
          <button className={acc.btns} onClick={submitData}>Sign In</button>
          <div className={acc.centers}>
            <label className={acc.label_text}>Already have an account ?</label>
            <NavLink to={"/"}>Sign Up</NavLink>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Login