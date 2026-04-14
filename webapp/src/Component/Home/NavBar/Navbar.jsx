import React, { useState } from 'react'
import hom from '../../../Style/home.module.css';
import icon from '../../../Assets/savingcost.png';
import img from '../../../common/imageCall/img';
import { NavLink } from 'react-router-dom';
function Navbar() {
  const [count, setCount] = useState(1);
  const link = (index) => {
    setCount(index);
  }
  return (
    <div className={hom.nav}>
         <div className={hom.navtop}>
            <div className={hom.cen} >
              <img alt='x-ico' src={icon} width={"42px"} height={"42px"}/>
              <label className={hom.bold}>Saving cost Managemnet</label>
            </div>

            <div className={hom.items}>
              <NavLink className={hom.subitems} onClick={() => link(1)} to={""}>
                <img src={count == 1 ? img.dashboard_select : img.dashboard}  alt='x-ico' className={hom.icos}/>
                <label className={count == 1 ? hom.bold : hom.normal}>Dash Board</label>
              </NavLink>
              <NavLink className={hom.subitems} onClick={() => link(2)} to={"Categories"}>
                <img src={count == 2 ? img.cat_select : img.cat}  alt='x-ico'  className={hom.icos}/>
                <label className={count == 2 ? hom.bold : hom.normal}>Categories</label>
              </NavLink>
              <NavLink className={hom.subitems} onClick={() => link(3)} to={"Expenses"}>
                <img src={count == 3 ? img.expense_slect : img.expense}  alt='x-ico'  className={hom.icos}/>
                <label className={count == 3 ? hom.bold : hom.normal}>Expense</label>
              </NavLink>
              <NavLink className={hom.subitems} onClick={() => link(4)} to={"Incomes"}>
                <img src={count == 4 ? img.income_select : img.income}  alt='x-ico'  className={hom.icos}/>
                <label className={count == 4 ? hom.bold : hom.normal}>Income</label>
              </NavLink>
              <NavLink className={hom.subitems} onClick={() => link(5)} to={"Filters"}>
                <img src={count == 5 ? img.filter_select : img.filter}  alt='x-ico'  className={hom.icos}/>
                <label className={count == 5 ? hom.bold : hom.normal}>Filters</label>
              </NavLink>
              <NavLink className={hom.subitems} onClick={() => link(6)} to={"Savings"}>
                <img src={count == 6 ? img.savings_select : img.savings}  alt='x-ico'  className={hom.icos}/>
                <label className={count == 6 ? hom.bold : hom.normal}>Savings</label>
              </NavLink>
            </div>
         </div>
         <div className={hom.bottom}>
            <NavLink className={hom.subitems} to={"Savings"} onClick={() => link(0)}>
              <img src={img.settings}  alt='x-ico'  className={hom.icos}/>
              <label className={hom.bold}>Settings</label>
            </NavLink>
            <NavLink className={hom.subitems} to={"Savings"} onClick={() => link(0)}>
              <img src={img.profile}  alt='x-ico'  className={hom.icos}/>
              <label className={`${hom.bold} pe-3`}>Profile</label>
            </NavLink>
         </div>
    </div>
  )
}
 
export default Navbar
