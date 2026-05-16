import React from 'react'
import main from '../../../Style/main.module.css';
import config from '../../../Config/config.json';
import { useSelector } from 'react-redux';

function TopBar() {
  const sel = useSelector((state) => state.user);
  return (
    <div className={main.TopNavBar}>
        <label className={main.topHeading}>{sel.page}</label>
        <div className={main.left_bottom_bar}>
            <img alt='x-ico' src={config.img_path + "notification.svg"} className={main.roundedicons}/>
            <div className={main.line}></div>
            <img alt='x-ico' src={config.img_path + "user.svg"} className={main.roundedicons}/>
        </div>
    </div>
  )
}

export default TopBar