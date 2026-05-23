import React, { useEffect, useState } from 'react';
import main from '../../Style/main.module.css';
import config from '../../Config/config.json';
import { BarChart, Cell, Legend, Pie, Tooltip, ResponsiveContainer, PieChart, CartesianGrid, XAxis, YAxis, Bar } from 'recharts';
import { API_CALL } from '../../ApiCall/apicall';
import { useDispatch, useSelector } from 'react-redux';
import Loading from '../LoadingScreen/Loading'
import { listitems } from '../../Reducers/CategoriesReducer';

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

  const dispatch = useDispatch();
  const [balance, setBalance] = useState({
    balance: 0,
    income: 0,
    expense: 0,
    savings: 0
  });
  const [piedata, setPie] = useState([]);
  const [monthchart, setchart] = useState([]);
  const [load, setLoad] = useState(true);
  const [trans, setTrans] = useState([]);
  const select = useSelector((state) => state.categories);

  useEffect(() => {
    const fetchCall = async () => {
      const apis = await API_CALL('', 'GET', config.private_url + 'listItems');
      if (apis.response.status === 200) {
        dispatch(listitems(apis.result.msg));
        setLoad(false);
      }
    }
    fetchCall();
  }, [])
  useEffect(() => {
    listbalance();
    setPieChartValue();
    listmothly();
    if(select.listitems && select.listitems.length >= 5){
      let objs = select.listitems.slice(0,5);
      setTrans(objs);
    } else if(select.listitems && select.listitems){
      setTrans(select.listitems);
    }
  }, [select.listitems])

  const listbalance = () => {
    if (select.listitems && select.listitems.length != 0) {
      let objs = {
        income: 0,
        expense: 0,
        balance: 0
      };
      select.listitems.map((val) => {
        if (val.income) {
          objs.income += Number(val.amount);
        } else if (val.income === false) {
          objs.expense += Number(val.amount);
        }
      });
      objs.balance = objs.income - objs.expense;
      setBalance(objs);
    }
  }

  const listmothly = () => {
    // { month: "Jan", income: 4000, expense: 2400 },
    let objs = [];
    if (select.listitems && select.listitems.length != 0) {
      select.listitems.forEach((val, id) => {
        let today = new Date(val.localDateTime);
        let monthName = today.toLocaleString("default", { month: "long" });
        let checkisExists = objs.find((vals) => vals.month == monthName);
        if (checkisExists && val.income) {
          checkisExists.income += Number(val.amount);
        } else if (checkisExists && !val.income) {
          checkisExists.expense += Number(val.amount);
        }
        else {
          objs.push({
            "month": monthName,
            "income": val.income ? Number(val.amount) : 0,
            "expense": !val.income ? Number(val.amount) : 0
          })
        }
      })
      console.log("newobjs", objs);
      setchart(objs);
    }
  }

  const setPieChartValue = () => {
    if (select.listitems && select.listitems.length != 0) {
      let objs = [];
      select.listitems.forEach((val) => {
        if (val.income) {
          let isExists = objs.find((vals) => vals.name === val.categories.c_name);
          if (isExists) {
            isExists.value += Number(val.amount);
          } else {
            objs.push({
              id: val.id,
              name: val.categories.c_name,
              value: Number(val.amount),
              color: val.categories.color
            })
          }
        }
      })
      console.log(objs, "myobjs");
      setPie(objs);
    }
  }

  return (
    <div className="container-fluid">
      {load && <Loading />}
      <div className="row g-3">
        <div className="col-12 col-sm-6 col-lg-3">
          <div className={main.navbox}>
            <div className='w-75 h-100 d-flex align-items-start justify-content-evenly flex-column px-3'>
              <label>Total Balance</label>
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ {balance.balance}</label>
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
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ {balance.income}</label>
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
              <label style={{ fontSize: "19px", fontWeight: "bolder" }}>₹ {balance.expense}</label>
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
                data={piedata}
                dataKey="value"
                nameKey="name"
                cx="50%"
                cy="50%"
                outerRadius={100}
                label
              >
                {piedata.map((val, index) => (
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
          <ResponsiveContainer width={"100%"} height={300} style={{ marginTop: "10px" }}>
            <BarChart data={monthchart}>
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
        <div className='col-12 col-md-12 col-sm-12 col-lg-12 col-xl-12'>
          {console.log(trans)}
          {
            trans.length != 0 && (
            trans.map((val) => {
              return (
                <div className='col-12 d-flex align-items-center justify-content-between p-2 mt-2'
                  style={{
                    minHeight: '65px',
                    background: 'rgb(163 159 159 / 13%)',
                    borderRadius: '10px'
                  }}>
                  <div className='d-flex align-items-center'>
                    <div
                      className={main.box_divs_circle}
                      style={{ background: val.income ? '#ECFDF5' : '#FEF2F2', height: '65px', width: '65px' }}
                    >
                      <img
                        alt='x-ico'
                        src={val.income ? config.img_path + 'trend_green.svg' : config.img_path + 'down_red.svg'}
                      />
                    </div>

                    <div className='px-3 d-flex flex-column'>
                      <label className={main.label_heading}>
                        {val.categories.c_name}
                      </label>

                      <small>
                        {val.notes}
                      </small>
                    </div>
                  </div>
                  <div>
                      <label
                        style={{
                          fontWeight: '600',
                          fontSize: '18px'
                        }}
                      >
                        ₹ {val.amount}
                      </label>
                    </div>
                </div>
              )
            }))
          }
        </div>
      </div>
    </div>
  );
}

export default Dashboard;