import { useState } from 'react'
import './App.css'
import RegisterPage from '../src/Page/RegisterPage';
import LoginPage from '../src/Page/LoginPage';
import VerificationPage from '../src/Page/VerificationPage';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Auth from './common/Verification/Auth'
import HomePage from './Page/HomePage'
import Dashboard from './Page/Home/DashBoard';
import Categoris from './Page/Home/Categories';
import Expense from './Page/Home/Expense';
import Income from './Page/Home/Income';
import Filters from './Page/Home/FIlters';
import Savings from './Page/Home/Savings';

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<RegisterPage/>} />
          <Route path='/login' element={<LoginPage/>} />
          <Route path='/verification' element={<VerificationPage/>} />
          <Route element={<Auth/>}>
              <Route path='/home' element={<HomePage/>} >
                <Route path='' element={<Dashboard/>} />
                <Route path='Categories' element={<Categoris/>} />
                <Route path='Expenses' element={<Expense/>} />
                <Route path='Incomes' element={<Income/>} />
                <Route path='Filters' element={<Filters/>} />
                <Route path='Savings' element={<Savings/>} />
              </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
