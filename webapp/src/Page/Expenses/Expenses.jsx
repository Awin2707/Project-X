import React, { use, useEffect, useState } from 'react'
import { Modal, Button } from "react-bootstrap";
import main from '../../Style/main.module.css';
import { useDispatch, useSelector } from 'react-redux';
import { API_CALL } from '../../ApiCall/apicall';
import { expenses, setIncome } from '../../Reducers/CategoriesReducer';
import config from '../../Config/config.json';

function Expenses() {
  const [show, setShow] = useState(false);
  const [data, setData] = useState({});
  const [shows, setShows] = useState(false);
  const [details, setDetails] = useState({});

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const showsClose = () => setShows(false);
  const select = useSelector((state) => state.categories);
  const sel = useSelector((state) => state.user);
  const dispatch = useDispatch();
  const [month, setMonth] = useState({});

  useEffect(() => {
    // let obj = [
    //   {
    //     "id": 152,
    //     "email": "naveenvishnu1007@gmail.com",
    //     "income": true,
    //     "amount": "20000",
    //     "categories": {
    //       "c_name": "bike",
    //       "img": "hello",
    //       "background": "rgba(0,0,0,0.125)"
    //     },
    //     "localDateTime": "2026-05-03",
    //     "notes": ""
    //   },
    //   {
    //     "id": 202,
    //     "email": "naveenvishnu1007@gmail.com",
    //     "income": true,
    //     "amount": "20000",
    //     "categories": {
    //       "c_name": "bike",
    //       "img": "hello",
    //       "background": "rgba(0,0,0,0.125)"
    //     },
    //     "localDateTime": "2026-05-03",
    //     "notes": ""
    //   },
    //   {
    //     "id": 203,
    //     "email": "naveenvishnu1007@gmail.com",
    //     "income": true,
    //     "amount": "20000",
    //     "categories": {
    //       "c_name": "bike123",
    //       "img": "hello",
    //       "background": "rgba(0,0,0,0.125)"
    //     },
    //     "localDateTime": "2026-05-03",
    //     "notes": ""
    //   },
    //   {
    //     "id": 304,
    //     "email": "naveenvishnu1007@gmail.com",
    //     "income": true,
    //     "amount": "25000",
    //     "categories": {
    //       "c_name": "user cars",
    //       "img": "https://raw.githubusercontent.com/Awin2707/Project-X/refs/heads/icons/shopping_white.svg",
    //       "background": "rgba(245, 158, 11, 0.125)"
    //     },
    //     "localDateTime": "2026-05-18",
    //     "notes": "279"
    //   },
    //   {
    //     "id": 352,
    //     "email": "naveenvishnu1007@gmail.com",
    //     "income": true,
    //     "amount": "46151",
    //     "categories": {
    //       "c_name": "user cars",
    //       "img": "https://raw.githubusercontent.com/Awin2707/Project-X/refs/heads/icons/shopping_white.svg",
    //       "background": "rgba(245, 158, 11, 0.125)"
    //     },
    //     "localDateTime": "2026-05-27",
    //     "notes": "this is the end !"
    //   }
    // ]
    // dispatch(setIncome(obj));
    const apicall = async () => {
      let api = await API_CALL('', 'GET', config.private_url + 'listExpenses');
      if(api.response.status === 200){
        dispatch(expenses(api.result.msg));
      }
    }
    apicall();
  }, []);

  useEffect(() => {
    let objs = {
      today: 0,
      weekly: 0,
      monthly: 0,
      yearly: 0
    };
    let todaydate = new Date();
    console.log(select.income, "sel");
    if (select.expenses.length != 0) {
      select.expenses.map((val) => {
        let income = new Date(val.localDateTime);
        let diff = (todaydate - income) / (1000 * 60 * 60 * 24);
        if (income.toDateString() >= todaydate.toDateString()) {
          objs.today += Number(val.amount);
        } else if (diff <= 7) {
          objs.weekly += Number(val.amount);
        } else if (income.getMonth() === todaydate.getMonth() && income.getFullYear() === todaydate.getFullYear()) {
          objs. monthly += Number(val.amount);
        } else if (income.getFullYear() === todaydate.getFullYear()) {
          objs.yearly += Number(val.amount);
        }
      });
    }
    setMonth(objs);
  },[select.expenses])

  const DropItems = async (id, email) => {
    let objs = {
      id,
      email
    }
    const apires = await API_CALL(objs, 'DELETE', config.private_url + 'deleteExpenses');
    if(apires.response.status === 200){
      dispatch(expenses(apires.result.msg));
      showsClose();
    }
  }

  const userIncome = (e) => {
    const { name, value } = e.target;
    let obj = {
      ...data,
      [name]: value
    }
    setData(obj);
  }

  const onUserDetails = (objs) => {
    setShows(true);
    console.log(objs);
    setDetails(objs);
  }

  const SubmitData = async () => {
    console.log(data);
    data.email = sel.user.email
    const apires = await API_CALL(data, 'POST', config.private_url + 'addExpenses');
    if (apires.response.status === 200) {
      dispatch(expenses(apires.result.msg));
    }
    handleClose();
  }
  return (
    <div className='container-fluid'>
      <div className='d-flex w-100 align-items-center justify-content-between'>
        <label>Add Expenses</label>
        <Button style={{color: '#ef4444', background: '#FEF2F2', fontSize: '16px', border: '1px solid #ef4444', borderRadius: '7px'}} onClick={handleShow}>
          Add Expenses
        </Button>
      </div>
      <div className='col-12 mt-4'>
        <div className="row g-3">
          <div className="col-6 col-sm-6 col-lg-3">
            <div className={main.navbox}>
              <div className='w-75 h-100 d-flex align-items-center justify-content-evenly flex-column px-3'>
                <label style={{ color: '#6B7280', fontSize: '16px' }}>Today Expenses</label>
                <label style={{ fontSize: "19px", color: '#22C55E' }}>₹ {month.today}</label>
              </div>
            </div>
          </div>

          <div className="col-6 col-sm-6 col-lg-3">
            <div className={main.navbox}>
              <div className='w-75 h-100 d-flex align-items-center justify-content-evenly flex-column px-3'>
                <label style={{ color: '#6B7280', fontSize: '16px' }}>Weekly Expenses</label>
                <label style={{ fontSize: "19px", color: '#22C55E' }}>₹ {month.weekly}</label>
              </div>
            </div>
          </div>

          <div className="col-6 col-sm-6 col-lg-3">
            <div className={main.navbox}>
              <div className='w-75 h-100 d-flex align-items-center justify-content-evenly flex-column px-3'>
                <label style={{ color: '#6B7280', fontSize: '16px' }}>Monthly Expenses</label>
                <label style={{ fontSize: "19px", color: '#22C55E' }}>₹ {month.monthly}</label>
              </div>
            </div>
          </div>

          <div className="col-6 col-sm-6 col-lg-3">
            <div className={main.navbox}>
              <div className='w-75 h-100 d-flex align-items-center justify-content-evenly flex-column px-3'>
                <label style={{ color: '#6B7280', fontSize: '16px' }}>Yearly Expenses</label>
                <label style={{ fontSize: "19px", color: '#22C55E' }}>₹ {month.yearly}</label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className='col-12 mt-3'>
        <div className='row g-2'>
          {
            select.expenses.length !== 0 ? (
              select.expenses && select.expenses.map((val) => {
                return (
                  <div
                    key={val.id}
                    className='col-12 d-flex align-items-center justify-content-between p-2'
                    style={{
                      minHeight: '65px',
                      background: val.categories.background,
                      borderRadius: '10px'
                    }}
                    onClick={() => onUserDetails(val)}
                  >
                    <div className='d-flex align-items-center'>
                      <div
                        className={main.box_divs_circle}
                        style={{ background: val.categories.color, height: '65px', width: '65px' }}
                      >
                        <img
                          alt='x-ico'
                          src={val.categories.img}
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
              })
            ) : (
              <div className='text-center mt-3'>
                No Income Added
              </div>
            )
          }
        </div>
      </div>
      <Modal show={shows} onHide={showsClose} centered>
        <Modal.Header closeButton>
          <Modal.Title>
            Transaction Details
          </Modal.Title>
        </Modal.Header>

        <Modal.Body>

          <div className='mb-3'>
            <label className='fw-bold'>
              Amount
            </label>

            <div>
              ₹ {details.amount}
            </div>
          </div>

          <div className='mb-3'>
            <label className='fw-bold'>
              Notes
            </label>

            <div>
              {details.notes}
            </div>
          </div>

          <div className='mb-3'>
            <label className='fw-bold'>
              Date
            </label>

            <div>
              {details.localDateTime}
            </div>
          </div>

          <div className='mb-3'>
            <label className='fw-bold'>
              Category
            </label>

            <div>
              {details.categories?.c_name}
            </div>
          </div>

          <button className='btn btn-danger w-100' onClick={() => DropItems(details.id, details.email)}>
            Drop
          </button>

        </Modal.Body>
      </Modal>
      <Modal show={show} onHide={handleClose}>
        <Modal.Body>
          <div className={main.income}>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Amount</label>
              <input type='number' className={main.box_inputs} placeholder='0.0' name='amount' value={data.income} onChange={userIncome} />
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Source</label>
              <select className={main.box_inputs} name='categories' value={data.select} onChange={userIncome}>
                {
                  select.categories.length != 0 ? (
                    select.categories.map((val) => {
                      return (
                        <option key={val.id} value={val.c_name}>{val.c_name}</option>
                      )
                    })
                  ) : <option selected>No item Selected</option>
                }
              </select>
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Date</label>
              <input type='date' className={main.box_inputs} name='date' value={data.date} onChange={userIncome} />
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Notes (Optional)</label>
              <textarea className={main.box_inputs} cols={22} placeholder='Add any additional details...' value={data.notes} name='notes' onChange={userIncome}>
              </textarea>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={SubmitData}>
            Add Income
          </Button>
        </Modal.Footer>
      </Modal>

    </div>
  )
}

export default Expenses