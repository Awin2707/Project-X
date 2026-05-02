import React, { useState } from 'react'
import mod from '../Style/field.module.css';
import config from '../Config/config.json';

function UserField({ type, name, onchnage, ind, onkey, ref, pass, img, placeholder, label, err, value }) {

    const[show, setShow]= useState(false);
    const showandHide = () => {
        setShow(!show);
    }

    return (
        <div className={mod.main}>
            <div className={mod.subMain}>
                <div className={mod.box_width}>
                    <img alt='x-ico' src={img} className={mod.icons} />
                </div>
                <input type={pass && pass == true ? show ? "text" : "password" : type} name={name} className={mod.inputs} placeholder={placeholder} required onChange={onchnage} ref={ref} onKeyDown={(e) => onkey(e, ind)} autoComplete='off' value={value}/>
                <label className={mod.label}>{label}</label>
                {
                    pass && pass != "" && (
                        <div className={mod.pass_filed}>
                            <img alt='x-ico' src={show ? config.img_path + "hide.svg" : config.img_path + "show.svg"} className={mod.ico} onClick={showandHide}/>
                        </div>
                    )
                }
            </div>
            {
                err && err != "" && (
                    <label className={mod.err}>{err}</label>
                )
            }
        </div>
    )
}

export default UserField