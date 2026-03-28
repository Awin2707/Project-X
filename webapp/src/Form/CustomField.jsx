import React, { lazy, useEffect, useState } from 'react'
import design from '../Style/main.module.css';
function CustomField({ value, name, onchange, placeholder, type, errMsg, pass, img}) {

    const[passShow, setPass] = useState();

    useEffect(() => {
        console.log(value);
    },[value])

    return (
        <div className={design.custom_div}>
            <div className={design.input} style={{border: errMsg ? "1px solid rgb(236, 87, 87)" : "1px solid #5a5a5a"}}>
                <img alt='x-ioc' src={img} className={design.icons}/>
                <input type={passShow ? "text" : type} placeholder={placeholder} name={name} onChange={(e) => onchange(e)} style={{width: pass ? "80%" : "100%"}} className={design.inps} autoComplete='off'/>
                { pass &&
                    (<label className={design.label} onClick={() => setPass(!passShow)}>{passShow ? "Hide": "Show"}</label>)
                }
            </div>
            {errMsg && <label className={design.err}>{errMsg}</label>}
        </div>
    )
}

export default CustomField