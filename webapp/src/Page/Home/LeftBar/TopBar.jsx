import React from 'react'
import main from '../../../Style/main.module.css';
import config from '../../../Config/config.json';

function TopBar() {
  return (
    <div className={main.TopNavBar}>
        <label className={main.topHeading}>Dashboard</label>
        <div className={main.left_bottom_bar}>
            <img alt='x-ico' src={config.img_path + "notification.svg"} className={main.roundedicons}/>
            <div className={main.line}></div>
            <img alt='x-ico' src={config.img_path + "user.svg"} className={main.roundedicons}/>
        </div>
    </div>
  )
}

export default TopBar