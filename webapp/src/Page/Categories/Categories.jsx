import React, { useEffect, useState } from 'react'
import main from '../../Style/main.module.css';
import config from '../../Config/config.json';

function Categories() {

  const [image, setImages] = useState([]);
  const [color, setColor] = useState([]);
  const [selected, setSelected] = useState({ img: 1, color: 1 });
  const [data, setData] = useState({});
  const [name, setName] = useState('');

  useEffect(() => {
    let objs = [
      { id: 1, name: "coffee", image: config.img_path + "coffee.svg" },
      { id: 2, name: "car", image: config.img_path + "car.svg" },
      { id: 3, name: "shopping", image: config.img_path + "shopping.svg" },
      { id: 4, name: "home", image: config.img_path + "home.svg" },
      { id: 5, name: "work", image: config.img_path + "work.svg" },
      { id: 6, name: "movie", image: config.img_path + "movie.svg" },
      { id: 7, name: "rupee", image: config.img_path + "indian.svg" },
    ];
    let colors = [
      { id: 1, color: 'rgb(54, 122, 255)', back: 'rgba(54, 122, 255, 0.125)' },
      { id: 2, color: 'rgb(79, 70, 229)', back: 'rgba(79, 70, 229, 0.125);' },
      { id: 3, color: 'rgb(34, 197, 94)', back: 'rgba(34, 197, 94, 0.125)' },
      { id: 4, color: 'rgb(245, 158, 11)', back: 'rgba(245, 158, 11, 0.125)' },
      { id: 5, color: 'rgb(239, 68, 68)', back: 'rgba(239, 68, 68, 0.125)' },
      { id: 6, color: 'rgb(139, 92, 246)', back: 'rgba(139, 92, 246, 0.125)' },
      { id: 7, color: 'rgb(236, 72, 153)', back: 'rgba(236, 72, 153, 0.125)' },
      { id: 8, color: 'rgb(6, 182, 212)', back: 'rgba(6, 182, 212, 0.125)' },
    ]
    setImages(objs);
    setColor(colors);
  }, []);
  const selectedData = (id) => {
    let objs = { ...selected };
    objs.img = id;
    setSelected(objs);
  }

  const SelectedBackground = (id) => {
    let objs = { ...selected };
    objs.color = id;
    setSelected(objs);
  }

  const submit_data = () => {
    if(!name){
      return;
    }
    let formed_objs = {};
    let objs_img = image[selected.img-1];
    let colors = color[selected.color-1];
    formed_objs = {
      "cat_name" : name,
      "image": config.img_path + objs_img.name + "_white.svg",
      "color": colors.color,
      "backgground" : colors.back
    }
    console.log(formed_objs);
  }

  const onChanges = (e) => {
    let name = e.target.value;
    setName(name);
  }

  return (
    <div className='conatiner-fluid w-100' style={{ background: '#fafafa' }}>
      <label>Categories</label>
      <br />
      <label>Organize your expenses and income by category</label>
      <div className='row mt-2'>
        <div className='col-xl-6 col-lg-6 col-md-12 col-sm-12 mt-3'>
          <div className={main.box}>
            <label className={main.box_lablel}>Create New Category</label>
            <div className={main.box_div}>
              <label className={main.box_subHead}>Category Name</label>
              <input type='text' className={main.box_inputs} placeholder='e.g., Groceries, Entertainment' value={name} onChange={onChanges} />
            </div>
            <div className='col-12 mt-3'>
              <label className={main.box_subHead}>Select Icons</label>
              <div className='row'>
                {
                  image.map((val) => {
                    return (
                      <div className='col-3' key={val.id}>
                        <div className={val.id === selected.img ? main.box_divs_selected : main.box_divs} onClick={() => selectedData(val.id)}>
                          <img alt='x-ico' src={selected.img === val.id ? config.img_path + val.name + "_selected.svg" : val.image} />
                        </div>
                      </div>
                    )
                  })
                }
              </div>
            </div>
            <div className='col-12'>
              <label className={main.box_subHead}>Select Colors</label>
              <div className='row'>
                {
                  color.map((val) => {
                    return (
                      <div key={val.id} className='col-2 mt-2' >
                        <div
                          onClick={() => SelectedBackground(val.id)}
                          style={{
                            padding: "2px",
                            borderRadius: "12px",
                            backgroundColor: selected.color === val.id ? 'rgba(54, 122, 255, 0.125)' : 'transparent',
                            border: selected.color === val.id ? "2px solid rgb(54, 122, 255)" : "2px solid transparent",
                            display: "inline-block",
                            cursor: "pointer"
                          }}
                        >
                          <div
                            style={{
                              background: val.color,
                              width: "40px",
                              height: "40px",
                              borderRadius: "10px"
                            }}
                          />
                        </div>
                      </div>
                    )
                  })
                }
              </div>
            </div>
            <div className={main.add} onClick={submit_data}>
              Add Categories
            </div>
          </div>
        </div>
        <div className='col-xl-6 col-lg-6 col-md-12 col-sm-12 mt-3'>

        </div>
      </div>
    </div>
  )
}

export default Categories