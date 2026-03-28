import { useState } from 'react'
import './App.css'
import RegisterPage from '../src/Page/RegisterPage';
import LoginPage from '../src/Page/LoginPage';
import VerificationPage from '../src/Page/VerificationPage';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import Auth from './common/Verification/Auth'
import HomePage from './Page/HomePage'

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
              <Route path='/home' element={<HomePage/>} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
