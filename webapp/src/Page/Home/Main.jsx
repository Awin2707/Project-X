import React, { useState } from 'react'
import main from '../../Style/main.module.css';
import Leftbar from './LeftBar/Leftbar';
import TopBar from './LeftBar/TopBar';
import { Outlet } from 'react-router-dom';

function Main() {
  return (
    <div className={main.main}>
        <div className={`${main.left} col-4 col-sm-2 col-md-4 col-lg-4 col-xl-3`}>
          <Leftbar/>
        </div>
        <div className={`${main.right} col-8 col-sm-10 col-md-8 col-lg-8 col-xl-9`}>
          <TopBar />
          <div className={main.top_botoom}>
            <Outlet />
          </div>
        </div>
    </div>
  )
}
  
export default Main