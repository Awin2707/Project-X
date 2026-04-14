import React from 'react'
import hom from '../Style/home.module.css';
import Navbar from '../Component/Home/NavBar/Navbar';
import MainContent from '../Component/Home/content/MainContext';
function HomePage() {
  return (
    <div className={hom.main}>
      <Navbar/>
      <MainContent />
    </div>
  )
}

export default HomePage
