import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import Register from './Pages/register';
import Login from './Pages/login';

const router = createBrowserRouter([
  {
    path : "/",
    element: <App/>
  },{
    path : "/register",
    element: <Register/>
  },{
    path : "/verify",
    element: <App/>
  },{
    path : "/login",
    element: <Login/>
  },{
    path : "/home",
    element: <App/>
  }
])
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
