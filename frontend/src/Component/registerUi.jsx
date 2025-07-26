import reg from "../Css/register.module.css";
function RegisterUi(){
    return(
        <div className={reg.main}>
            <h2>Welcome New User</h2>
            <div className={reg.form}>
                <div className={reg.email_div}>
                    <img src="/gmail.png" alt="gmail icon" className={reg.icon}/>
                    <input type="email" placeholder="Enter the Email id " className={reg.email}/>
                </div>
                <div className={reg.name_div}>
                    <img src="/user.png" alt="gmail icon" className={reg.icon}/>
                    <input type="text" placeholder="Enter your user name " className={reg.name}/>
                </div>
                <div className={reg.pass_div}>
                    <img src="/password.png" alt="gmail icon" className={reg.icon}/>
                    <input type="password" placeholder="Create your password " className={reg.pass}/>
                </div>
                <label className={reg.err}></label>
                <div className={reg.btn}>
                    <input type="button" value={"Create Account"} className={reg.butn} />
                </div>
                <div className={reg.det}>
                    <label>I have a account already ? </label>
                    <a href="/login" className={reg.log}>Login Now !</a>
                </div>
            </div>
        </div>
    )
}
export default RegisterUi;