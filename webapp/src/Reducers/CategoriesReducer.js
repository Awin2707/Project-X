import {createSlice} from '@reduxjs/toolkit'

const initialState= {
    categories: [],
    addCategories: [],
    income: [],
    listitems: [],
    expenses: []
}

const CategoriesReducers = createSlice({
    name: 'cat',
    initialState,
    reducers: {
        addCategories : (state, action) => {state.addCategories = action.payload},
        setCategories : (state, action) => {state.categories = action.payload},
        setIncome: (state, action) => {state.income = action.payload},
        listitems: (state, action) => {state.listitems = action.payload},
        expenses: (state, action) => {state.expenses = action.payload}
    }
})
export const {addCategories, setCategories, setIncome, listitems, expenses} = CategoriesReducers.actions;
export default CategoriesReducers.reducer;