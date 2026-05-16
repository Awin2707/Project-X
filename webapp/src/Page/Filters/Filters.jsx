import React, { useState } from 'react'
import { Modal, Button } from "react-bootstrap";
import main from '../../Style/main.module.css';
import { useSelector } from 'react-redux';

function Filters() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const select = useSelector((state) => state.categories);

  const [data, setData] = useState({});
  const [result, setResult] = useState([]);

  const chnageInputs = (e) => {
    let { name, value } = e.target;
    let objs = {
      ...data,
      [name]: value
    }
    setData(objs);
  }

  const handleSubmit = () => {
    console.log(data, "myData");
    getDetails();
    handleClose();
  }

  const getDetails = () => {
    if (select.listitems && select.listitems.length !== 0) {

      let datas = select.listitems.filter((val) => {
        if (data.amount && Number(val.amount) !== Number(data.amount)) {
          return false;
        }
        if (data.categories) {
          let bool = data.categories === "true";

          if (val.income !== bool) {
            return false;
          }
        }

        if (data.start && data.end) {

          let startDate = new Date(data.start);
          let endDate = new Date(data.end);
          let findDate = new Date(val.localDateTime);

          if (!(findDate >= startDate && findDate <= endDate)) {
            return false;
          }
        }
        return true;
      });
      console.log(datas);
      setResult(datas);
    }
  }

  return (
    <div className='container-fluid'>
      <div className='d-flex w-100 align-items-center justify-content-end'>
        <Button style={{ color: '#367AFF', background: '#3679ff5d', fontSize: '16px', border: '1px solid #367AFF', borderRadius: '7px' }} onClick={handleShow}>
          Add Filters
        </Button>
        <Modal show={show} onHide={handleClose} centered>
          <Modal.Header closeButton>
            <Modal.Title>
              Filter your Transaction
            </Modal.Title>
          </Modal.Header>

          <Modal.Body>
            <div className='mb-3'>
              <label className='fw-bold'>
                Amount
              </label>
              <div>
                <input className={main.box_inputs} placeholder='eg: 2,000' name='amount' onChange={chnageInputs} />
              </div>
            </div>

            <div className='mb-3'>
              <label className='fw-bold'>
                Select Type
              </label>
              <div>
                <select className={main.box_inputs} name='categories' onChange={chnageInputs}>
                  <option value={''} selected>Select Transcation</option>
                  <option value={true}>Income</option>
                  <option value={false} >Expenses</option>
                </select>
              </div>
            </div>

            <div className='mb-3'>
              <label className='fw-bold'>
                Start Date
              </label>
              <div>
                <input type='date' className={main.box_inputs} name='from' onChange={chnageInputs} />
              </div>
            </div>

            <div className='mb-3'>
              <label className='fw-bold'>
                End Date
              </label>
              <div>
                <input type='date' className={main.box_inputs} name='to' onChange={chnageInputs} />
              </div>
            </div>
            <button className='btn btn-danger w-100' onClick={handleSubmit}>
              Search
            </button>
          </Modal.Body>
        </Modal>
      </div>
      <div className='col-12 mt-3'>
        <div className='row g-2'>
          {
            result.length !== 0 ? (
              result && result.map((val) => {
                return (
                  <div
                    key={val.id}
                    className='col-12 d-flex align-items-center justify-content-between p-2'
                    style={{
                      minHeight: '65px',
                      background: val.categories.background,
                      borderRadius: '10px'
                    }}>
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
                No Result Found !
              </div>
            )
          }
        </div>
      </div>
    </div>
  )
}

export default Filters