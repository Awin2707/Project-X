import { Outlet } from 'react-router-dom';
import main from '../../../Style/home.module.css';

function MainContent() {
    return(
        <div className={main.left}>
            <Outlet/>
        </div>
    )
}

export default MainContent;