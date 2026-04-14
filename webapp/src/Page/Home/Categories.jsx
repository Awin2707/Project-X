import { useEffect, useState } from 'react';
import home from '../../Style/home.module.css';
const Categories = () => {

    const[categories, setCategories] = useState([]);

    useEffect(() => {
        setCategories([]);
    })

    return(
        <div className="col-12 col-md-12 col-sm-12" style={{height: "95%", marginTop: "10px"}}>
            <div className='w-100 d-flex align-items-start justify-content-between'>
                <label className={home.newheading}>All Categories</label>
                <button className={home.greenbtn}>+ Add Categories</button>
            </div>
            <div className='d-flex align-items-center justify-content-center'>
                {
                    categories.length == 0 && (
                        <div className={home.background}> 
                            <label className={`${home.newheading} mt-3`}>Categoriex Sources</label>
                            <label className='mt-3'>No Categories add yet. Add Some to get started !</label>
                        </div>
                    )
                }
            </div>
            <div className={home.modal}>
                <div className={home.newModal}>
                    <div className={home.spacebetween}>
                        <label className={home.normalfont}>Add Categoris</label>
                        <label className={home.close}>X</label>
                    </div>
                    <div className={`${home.spacebetween} mt-3`}>
                        <img alt='x-ico' src='' width={"200px"} height={"200px"} />
                    </div>
                </div>
            </div>
        </div>
    )
}
export default Categories;