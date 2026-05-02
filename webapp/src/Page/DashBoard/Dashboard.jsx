import React from 'react';
import main from '../../Style/main.module.css';
import config from '../../Config/config.json';
import { BarChart, Cell, Legend, Pie, Tooltip, ResponsiveContainer, PieChart, CartesianGrid, XAxis, YAxis, Bar } from 'recharts';

function Dashboard() {

  const pieChart = [
  { id: 0, value: 20000, name: "food", color: "#fa541c" },
  { id: 2, value: 1000, name: "travel", color: "#52c41a" },
  { id: 3, value: 50000, name: "rent", color: "#1890ff" },
  { id: 1, value: 100, name: "loan", color: "#fadb14" },
];
  const data = [
    { month: "Jan", income: 4000, expense: 2400 },
    { month: "Feb", income: 3000, expense: 1398 },
    { month: "Mar", income: 5000, expense: 2800 },
    { month: "Apr", income: 4780, expense: 3908 },
    { month: "May", income: 5890, expense: 4800 },
    { month: "Jun", income: 4390, expense: 3800 },
  ];

  return (
    <div className="container-fluid">
      <div className="row g-3">
        <div className="col-12 col-sm-6 col-lg-3">
          <div className={main.navbox}>
            <div className='w-75 h-100 d-flex align-items-start justify-content-evenly flex-column px-3'>
              <label>Total Balance</label>
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ 25,000</label>
            </div>
            <div className='w-25 h-100 d-flex align-items-center justify-content-evenly flex-column'>
              <img alt='x-ico' src={config.img_path + 'wallet_blue.svg'} className={main.wallet} />
            </div>
          </div>
        </div>

        <div className="col-12 col-sm-6 col-lg-3">
          <div className={main.navbox}>
            <div className='w-75 h-100 d-flex align-items-start justify-content-evenly flex-column px-3'>
              <label>Total Income</label>
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ 25,000</label>
            </div>
            <div className='w-25 h-100 d-flex align-items-center justify-content-evenly flex-column'>
              <img alt='x-ico' src={config.img_path + 'trend_green.svg'} className={main.green} />
            </div>
          </div>
        </div>

        <div className="col-12 col-sm-6 col-lg-3">
          <div className={main.navbox}>
            <div className='w-75 h-100 d-flex align-items-start justify-content-evenly flex-column px-3'>
              <label>Total Expenses</label>
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ 25,000</label>
            </div>
            <div className='w-25 h-100 d-flex align-items-center justify-content-evenly flex-column'>
              <img alt='x-ico' src={config.img_path + 'down_red.svg'} className={main.reds} />
            </div>
          </div>
        </div>

        <div className="col-12 col-sm-6 col-lg-3">
          <div className={main.navbox}>
            <div className='w-75 h-100 d-flex align-items-start justify-content-evenly flex-column px-3'>
              <label>Total Savings</label>
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ 25,000</label>
            </div>
            <div className='w-25 h-100 d-flex align-items-center justify-content-evenly flex-column'>
              <img alt='x-ico' src={config.img_path + 'piggy.svg'} className={main.savings} />
            </div>
          </div>
        </div>
      </div>
      <div className='row g-3 mt-3'>
        <div className='col-md-12 col-sm-12 col-lg-6 col-xl-6'>
          <label>Monthly Income</label>
          <ResponsiveContainer width="100%" height={300}>
            <PieChart>
              <Pie
                data={pieChart}
                dataKey="value"
                nameKey="name"
                cx="50%"
                cy="50%"
                outerRadius={100}
                label
              >
                {pieChart.map((val, index) => (
                  <Cell key={`cell-${index}`} fill={val.color} />
                ))}
              </Pie>
              <Tooltip />
              <Legend />
            </PieChart>
          </ResponsiveContainer>
        </div>
        <div className='col-md-12 col-sm-12 col-lg-6 col-xl-6'>
          <label>Monthly Income and expenses </label>
          <ResponsiveContainer width={"100%"} height={300} style={{marginTop: "10px"}}>
            <BarChart data={data}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey={"month"} />
              <YAxis />
              <Tooltip />
              <Legend />
              <Bar dataKey="income" fill="#4CAF50" name="Income" />
              <Bar dataKey="expense" fill="#F44336" name="Expense" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
      <div className='row g-3 mt-3'>
        <div className='col-8 col-md-12 col-sm-12 col-lg-4 col-xl-4'>

        </div>
        <div className='col-4 col-md-12 col-sm-12 col-lg-4 col-xl-4'>

        </div>
      </div>
    </div>
  );
}

export default Dashboard;