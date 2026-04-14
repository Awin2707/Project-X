import { PieChart } from "react-minimal-pie-chart";
import hom from '../../Style/home.module.css';
import { useEffect, useState } from "react";
import TranscationHistory from "../../Component/Home/DashBoard/TranscationHistory";
import ExpenseHistory from "../../Component/Home/Expense/Expense";
import IncomeHistory from "../../Component/Home/Income/IncomeHistory";
const Dashboard = () => {
    const [piechart, piechartData] = useState([
        { title: "income", value: 1, color: "#22C55E" },
        { title: "expense", value: 1, color: "#EF4444" }
    ]);
    const [trans, setTransction] = useState([]);

    useEffect(() => {
        // setTimeout(() => {
        //     piechartData([
        //     {"title": "income", value: 200, color: "#22C55E"},
        //     {"title": "expense", value: 100, color: '#EF4444'}
        // ])
        // },300)
        if(trans.length > 5){
            let data = trans.splice(0, 5);
            setTransction(data);
        }
    },[])
    return(
       <div className={hom.lefts}>
            <label className={hom.heading}>Dash Board</label>
            <div className={hom.leftContent}>
                <div className={hom.stack}>
                    <div className={hom.substack}>
                        <TranscationHistory data = {trans}/>
                    </div>
                    <div className={hom.substack}>
                        <div className={`${hom.spacebetween}`}>
                            <label className="ms-2">Pie chart</label>
                        </div>
                        <div style={{position: 'relative'}} className={"mt-5"}>
                            <PieChart data={piechart} animate animationDuration={1200} lineWidth={20} />
                            <label style={{position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)'}}>Total Income: 0</label>
                        </div>
                    </div>
                </div>
                <div className={hom.stack}>
                    <div className={hom.substack}>
                        <ExpenseHistory data={trans}/>
                    </div>
                    <div className={hom.substack}>
                        <IncomeHistory data={trans}/>
                    </div>
                </div>
            </div>
       </div>
    )
}
export default Dashboard;