import React,{useState} from 'react'
import main from '../Style/main.module.css'
import Loading from '../Component/Loading/Loading';
import Login from '../Component/Login/Login';

function LoginPage() {
    const [data, setData] = useState({

    });
    const [msg, errMsg] = useState({
        "email": "",
        "pass": ""
    });
    const [loading, setloading] = useState(false);

    const onchange = (e) => {
        console.log(e.target.name, e.target.value)
        setData({ ...data, [e.target.name]: e.target.value });
        errMsg({ ...msg, [e.target.name]: "" });
    }

    const validate = () => {
        let err = {};
        let check = false;
        if (!data.email) {
            err.email = "Email can't be empty !";
            check = true;
        }
        if (!data.pass || data.pass.length == 0) {
            err.pass = "Password can't be empty !";
            check = true;
        }
        errMsg(err);
        console.log(data);
        return check;
    }

    const onSubmit = () => {
        let bool = validate();
        if (!bool) {
            setloading(true);
        }
    }
    return (
        <div className={main.main}>
            <Login values={data} onChange={(e) => onchange(e)} onSubmit={onSubmit} msg={msg} />
            {
                loading && <Loading />
            }
        </div>
    )
}

export default LoginPage