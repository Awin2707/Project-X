import React from 'react'
import CustomField from '../../Form/CustomField'
import user from '../../Assets/user.svg'
import email from '../../Assets/email.svg'
import main from '../../Style/main.module.css';
import pass from '../../Assets/password.svg'
import logo from '../../Assets/savingcost.png'

function Register({ onChange, values, onSubmit, msg, warn }) {
  console.log(values);
  return (
    <div className={main.register}>
      <div className={main.mindivs}>
        <img alt='x-ico' src={logo}  className={main.logo}/>
        <label className={main.subHead}>Save Cost Management</label>
      </div>
      <div className={main.minsiv1}>
        <label className={main.head}>Create Account</label>
        <label className={main.smallhead}>Create your account. Start saving smarter.</label>
      </div>
      <form>
        <CustomField name={"name"} placeholder={"Enter the first Name"} onchange={(e) => onChange(e)} value={values.name} type={"text"} errMsg={msg.name} img={user} />
        <CustomField name={"email"} placeholder={"Enter the first Name"} onchange={(e) => onChange(e)} value={values.email} type={"email"} errMsg={msg.email} img={email} />
        <CustomField name={"pass"} placeholder={"Enter the first Name"} onchange={(e) => onChange(e)} value={values.pass} type={"password"} errMsg={msg.pass} img={pass} pass={true}/>
        {
          warn && (
            <div class="password-note">
              <h4>Password should contain:</h4>
              <ul>
                <li>At least one uppercase letter</li>
                <li>At least one lowercase number</li>
                <li>At least one special character</li>
                <li>Minimum 8 characters are required !</li>
              </ul>
            </div>
          )
        }
        <div className={main.divs3}>
          <input type='checkbox' />
          <label className={main.labs}>I have agree all the <span className={main.log}>Terms</span> and <span className={main.log}> Conditions </span> </label>
        </div>
        <div className={main.divs3}>
          <input type='button' value={"Create Account"} onClick={onSubmit} className={main.btns} />
        </div>
        <div className={main.divs3}>
          <label className={main.labs}>I have account already ? </label>
          <label className={main.log}> Login Now</label>
        </div>
      </form>
    </div>
  )
}

export default Register