import logo from './logo.svg';
import './App.css';
import UserField from './InputField/UserField';
import config from './Config/config.json';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Register from './Page/Register/Register';
import Login from './Page/Login/Login';
import Verify from './Page/Verification/Verify';
import Main from './Page/Home/Main';
import DashBoard from './Page/DashBoard/Dashboard';
import Categories from './Page/Categories/Categories';
import Income from './Page/Income/Income';
import Expenses from './Page/Expenses/Expenses';
import Filters from './Page/Filters/Filters';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Register/>}/>
        <Route path='/signin' element={<Login/>}/>
        <Route path='/verify' element={<Verify/>} /> 
        <Route path='/home' element={<Main/>} >
          <Route index element={<DashBoard/>} />
          <Route path='catgeories' element={<Categories/>} />
          <Route path='incomes' element={<Income/>} />
          <Route path='expenses' element={<Expenses/>} />
          <Route path='filters' element={<Filters/>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
