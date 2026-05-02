import React, { useState } from 'react'
import { Modal, Button } from "react-bootstrap";
import main from '../../Style/main.module.css';

function Income() {
  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  return (
    <div className='container-fluid'>
      <div className='d-flex w-100 align-items-center justify-content-between'>
        <label>Add Your Income</label>
        <Button variant="primary" onClick={handleShow}>
          Launch demo modal
        </Button>
      </div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Body>
          <div className={main.income}>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Amount</label>
              <input type='number' className={main.box_inputs} placeholder='0.0' />
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Source</label>
              <select className={main.box_inputs}>
                <option selected>Select 1</option>
                <option >Select 3</option>
                <option >Select 2</option>
                <option >Select 4</option>
              </select>
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Date</label>
              <input type='date' className={main.box_inputs} placeholder='e.g., Groceries, Entertainment'  />
            </div>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Notes (Optional)</label>
              <textarea className={main.box_inputs} cols={22} placeholder='Add any additional details...'>

              </textarea>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleClose}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

    </div>
  )
}

export default Income