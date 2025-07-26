import ind from "../Css/index.module.css";

function IndexUi(){
    return(
        <div className={ind.main}>
            <p>Welcome to Your Chats App Please Select the given Options Below</p>
            <div className={ind.det}>
                <a href="/login" className={ind.log}>Login</a>
                <label>Or</label>
                <a href="/register" className={ind.reg}>Register</a>
            </div>
        </div>
    )
}
export default IndexUi;