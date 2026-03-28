import React from 'react'
import CustomField from '../../Form/CustomField'
import email from '../../Assets/email.svg'
import main from '../../Style/main.module.css';
import pass from '../../Assets/password.svg'
import logo from '../../Assets/savingcost.png'
function Login({ onChange, values, onSubmit, msg }) {
    return (
        <div className={main.register}>
            <div className={main.mindivs}>
                <img alt='x-ico' src={logo} className={main.logo} />
                <label className={main.subHead}>Save Cost Management</label>
            </div>
            <div className={main.minsiv1}>
                <label className={main.head}>Welcome Back</label>
                <label className={main.smallhead}>Login your account. Start saving smarter.</label>
            </div>
            <form>
                <CustomField name={"email"} placeholder={"Enter the first Name"} onchange={(e) => onChange(e)} value={values.email} type={"email"} errMsg={msg.email} img={email} />
                <CustomField name={"pass"} placeholder={"Enter the first Name"} onchange={(e) => onChange(e)} value={values.pass} type={"password"} errMsg={msg.pass} img={pass} pass={true} />
                <div className={main.divs4}>
                    <label className={main.log}>Forget Password ?</label>
                </div>
                <input type='button' value={"Login"} onClick={onSubmit} className={main.btns} />
                <div className={main.divs3}>
                    <label className={main.labs}>I don't have account already ? </label>
                    <label className={main.log}> Register Now</label>
                </div>
            </form>
        </div>
    )
}

export default Login