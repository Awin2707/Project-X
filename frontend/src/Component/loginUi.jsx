import reg from "../Css/login.module.css";
function LoginUi(){
    return(
        <div className={reg.main}>
            <h2>Welcome Back User !</h2>
            <div className={reg.form}>
                <div className={reg.email_div}>
                    <img src="/gmail.png" alt="gmail icon" className={reg.icon}/>
                    <input type="email" placeholder="Enter your Email id " className={reg.email}/>
                </div>
                <div className={reg.pass_div}>
                    <img src="/password.png" alt="gmail icon" className={reg.icon}/>
                    <input type="password" placeholder="Enter your password " className={reg.pass}/>
                </div>
                <label className={reg.err}></label>
                <div className={reg.btn}>
                    <input type="button" value={"Login"} className={reg.butn} />
                </div>
                <div className={reg.det}>
                    <label>I don't have a account already ? </label>
                    <a href="/register" className={reg.reg}>Register</a>
                </div>
            </div>
        </div>
    )
}
export default LoginUi;