import home from '../../../Style/home.module.css';
const ExpenseHistory = ({data}) => {
    
    return(
        <div className={home.smallMain}>
            {console.log(data.length == 0, data == null, data == undefined)}
            <div className={home.spacebetween}>
                <label className='ms-2'>Expense History</label>
                <button className={`me-2 ${home.more}`}>More</button>
            </div>
            <div className='m-2 '>
            {
                data.length != 0 && data != null && data != undefined && (
                    data.map((val, ind) => {
                        return(
                            <div>
                                hello + {val} , {ind}
                            </div>
                        )
                    })
                )
            }
            </div>
            {
                (data.length == 0 || data == null || data == undefined) && (
                    <div className={home.newlable}>
                        <label>No Expense History !</label>
                    </div>
                )
            }
        </div>
    )
}
export default ExpenseHistory;