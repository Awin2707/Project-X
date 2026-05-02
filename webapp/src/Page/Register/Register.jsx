import React, { useEffect, useRef, useState } from 'react'
import UserField from '../../InputField/UserField';
import acc from '../../Style/acc.module.css';
import config from '../../Config/config.json';
import { NavLink } from 'react-router-dom';
import TopNavbar from '../Navbar/TopNavbar';
import {useDispatch} from 'react-redux';
import { setUsers } from '../../Reducers/UserReducers';

function Register() {

  const [inputs, setInput] = useState([]);
  const [data, setdata] = useState({
    "user_name": "",
    "user_email": "",
    "user_pass": "",
    "agree": false
  });
  const [errors, setError] = useState({});
  const refs = useRef([]);
  const dispatch = useDispatch();

  useEffect(() => {
    let objs = [
      { type: "text", name: "user_name", placeholder: "Create your User Name", pass: false, img: config.img_path + "user.svg", err: "", label: "Your Name" },
      { type: "email", name: "user_email", placeholder: "Enter your Email id", pass: false, img: config.img_path + "mail.svg", err: "", label: "Email" },
      { type: "password", name: "user_pass", placeholder: "Create your Password", pass: true, img: config.img_path + "password.svg", err: "", label: "Password" }
    ];
    setInput(objs);
  },[errors]);

  const chnageInput = (e) => {
    const {value, name} = e.target;
    let objs = {...data, [name] : value};
    setdata(objs);
  }

  const keyPress = (e, id) => {
    if(e.key === "Enter"){
      refs.current[id+1]?.focus();
    }
  }

  const validCheck = () => {
    let bool = true;
    const err = {};
    if(!data.user_name){
      err.user_name = "*user name can't be empty !";
      bool = false;
    }else if(data.user_name.length < 3){
      err.user_name = "*user name length should be more than 3 chars !";
      bool = false;
    }
    if(!data.user_email){
      err.user_email = "*email id can't be empty !";
      bool = false;
    }else if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(data.user_email)){
      err.user_email = "*invalid email id !";
      bool = false;
    }
    if(!data.user_pass){
      err.user_pass = "*passowrd can't be empty !";
      bool = false;
    }else if(data.user_pass.length < 7){
      err.user_pass = "*password length should be more than 7 chars !";
      bool = false;
    }
    if(!data.agree){
      err.agree = "*please agree the terms and conditions !";
    }
    setError(err);
    return bool;
  }

  const submitData = () => {
    let bools = validCheck();
    if(bools){
      dispatch(setUsers(data))
    }
  }
  const clicksVerify = (e) => {
    const {checked} = e.target;
    let objs = {...data};
    objs.agree = checked;
    setdata(objs);
  }

  return (
    <div className={acc.main}>
      <TopNavbar />
      <div className={acc.top}>
        <div className={acc.box}>
          <label className={acc.heading}>Sign up</label>
          <label className={acc.subHeading}>Sign up to enjoy the features of your savings.</label>
          {
            inputs.map((val, id) => {
              return(
                <UserField key={id} type={val.type} name={val.name} onchnage={chnageInput} ind={id} onkey={(e) => keyPress(e, id)} ref={(el) => {refs.current[id] = el}} pass={val.pass} img={val.img} placeholder={val.placeholder} label={val.label} err={errors[val.name]} value={data[val.name]}/>
              )
            })
          }
          <div style={{margin: "15px 0px", display: "flex", alignItems: "center", justifyContent: "center"}}>
            <input type='checkbox' style={{width: "15px", height: "15px"}} onClick={(e) => clicksVerify(e)}/>
            <label style={{marginLeft: "5px", fontSize: "15px"}}>I have agree all the <span style={{color: '#367AFF', cursor: "pointer", fontSize: "15px"}}>terms </span>and <span style={{color: '#367AFF', cursor: "pointer", fontSize: "15px"}}>Condition</span></label>
          </div>
          {
            errors.agree && (
              <label className={acc.err}>{errors.agree}</label>
            )
          }
          <button className={acc.btns} onClick={submitData}>Sign up</button>
          <div className={acc.centers}>
            <label className={acc.label_text}>Already have an account ?</label>
            <NavLink to={"/signin"}>Sign In</NavLink>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Register