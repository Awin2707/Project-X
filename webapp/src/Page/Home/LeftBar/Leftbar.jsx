import React, { useEffect, useState } from 'react'
import main from '../../../Style/main.module.css';
import config from '../../../Config/config.json';
import {Link, NavLink, useNavigate} from 'react-router-dom';
import {useDispatch} from 'react-redux';
import { setPage } from '../../../Reducers/UserReducers';

function Leftbar() {
    const [count, setCount] = useState(1);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    useEffect(() => {
        navigate("/home");
    },[]);

    const increaseCount = (count, name) => {
        setCount(count);
        dispatch(setPage(name));
    }

    const logout = () => {
        localStorage.clear();
        sessionStorage.clear();
        navigate("/");
    }
    return (
        <div className={main.main_left}>
            <div className={main.top}>
                <img src={config.img_path + "savingcost.png"} alt='x-ico' className={main.logo} />
                <div className='d-flex align-items-center justify-content-evenly flex-column w-50'>
                    <label className={main.head}>Saving cost </label>
                </div>
            </div>
            <div className={main.middle}>
                <NavLink to={""} className={count === 1 ? main.selected : main.not_Selected} onClick={() => increaseCount(1, "Dashboard")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 1 ? config.img_path + "house.svg" : config.img_path + "house_nc.svg" } />
                    </div>
                    <div className={count === 1 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Dashboard</label>
                    </div>
                </NavLink>
                <NavLink to={"catgeories"} className={count === 2 ? main.selected : main.not_Selected} onClick={() => increaseCount(2, "Categories")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 2 ? config.img_path + "categories.svg" : config.img_path + "categories_nc.svg" } />
                    </div>
                    <div className={count === 2 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Categories</label>
                    </div>
                </NavLink>
                <NavLink to={"incomes"} className={count === 3 ? main.selected : main.not_Selected} onClick={() => increaseCount(3, "Income")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 3 ? config.img_path + "profit.svg" : config.img_path + "profit_nc.svg" } />
                    </div>
                    <div className={count === 3 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Incomes</label>
                    </div>
                </NavLink>
                <NavLink to={"expenses"} className={count === 4 ? main.selected : main.not_Selected} onClick={() => increaseCount(4, "Expenses")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 4 ? config.img_path + "loss.svg" : config.img_path + "loss_nc.svg" } />
                    </div>
                    <div className={count === 4 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Expenses</label>
                    </div>
                </NavLink>
                <NavLink to={"filters"} className={count === 5 ? main.selected : main.not_Selected} onClick={() => increaseCount(5, "Filters")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 5 ? config.img_path + "filter.svg" : config.img_path + "filter_nc.svg" } />
                    </div>
                    <div className={count === 5 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Filter</label>
                    </div>
                </NavLink>
            </div>
            <div className={main.bottom}>
                <NavLink to={""} className={count === 6 ? main.selected : main.not_Selected} onClick={() => increaseCount(6, "Settings")}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 6 ? config.img_path + "settings.svg" : config.img_path + "settings_nc.svg" } />
                    </div>
                    <div className={count === 6 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Settings</label>
                    </div>
                </NavLink>
                <NavLink className={count === 7 ? main.selected : main.not_Selected} onClick={() => increaseCount(7)}>
                    <div className={main.icons}>
                        <img alt='x-ico' src={count === 7 ? config.img_path + "logout.svg" : config.img_path + "logout_nc.svg" } />
                    </div>
                    <div className={count === 7 ? main.deatils_nc : main.deatils}>
                        <label className={main.labels}>Log out</label>
                    </div>
                </NavLink>
            </div>
        </div>
    )
}

export default Leftbar