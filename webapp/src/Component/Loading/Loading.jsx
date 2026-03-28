import React from 'react'
import main from '../../Style/main.module.css';
function Loading() {
  return (
    <div className={main.divs}>
        <div className={main.subdivsa}>
            <div className={main.circle}>
              <div className={main.subcircle}></div>
            </div>
            <div className={main.label}>Please wait . . .</div>
        </div>
    </div>
  )
}

export default Loading