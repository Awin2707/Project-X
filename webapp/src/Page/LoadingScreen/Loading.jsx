import React from 'react'
import second from '../../Style/main.module.css';
function Loading() {
  return (
    <div className={second.loading}>
        <div className={second.load}>
        </div>
        <label className={second.loadtext}>Please wait . . .</label>
    </div>
  )
}

export default Loading