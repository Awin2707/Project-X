import React, { useEffect } from 'react'
import main from '../../Style/main.module.css';
import Leftbar from './LeftBar/Leftbar';
import TopBar from './LeftBar/TopBar';
import { Outlet, useNavigate } from 'react-router-dom';
import config from '../../Config/config.json';
import { API_CALL } from '../../ApiCall/apicall';
import Logout from '../Logout/logout';
import { useSelector } from 'react-redux';
function Main() {
  const navigate = useNavigate();
  const sel = useSelector((state) => state.user);
  useEffect(() => {
  const fetchData = async () => {
    try {
      const res = await API_CALL('', 'GET', config.private_url + 'version');

      if (res.response.status != 200) {
        navigate('/');
      }
    } catch (err) {
      console.error(err);
      navigate('/');
    }
  };

  fetchData();
}, []);
  return (
    <div className={main.main}>
      {sel.logout === true && <Logout />}
        <div className={`${main.left} col-4 col-sm-2 col-md-4 col-lg-4 col-xl-3`}>
          <Leftbar/>
        </div>
        <div className={`${main.right} col-8 col-sm-10 col-md-8 col-lg-8 col-xl-9`}>
          <TopBar />
          <div className={`${main.top_botoom} mt-3`}>
            <Outlet />
          </div>
        </div>
    </div>
  )
}
  
export default Main