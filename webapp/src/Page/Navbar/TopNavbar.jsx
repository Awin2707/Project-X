import React from 'react'
import acc from '../../Style/acc.module.css';
import config from '../../Config/config.json';
function TopNavbar() {
  return (
    <div className={acc.main_nav}>
        <img alt='x-ico' src={config.img_path + "savingcost.png"} className={acc.imgs}/>
        <label className={acc.heading}>Saving Cost Management</label>
    </div>
  )
}

export default TopNavbar