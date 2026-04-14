import {createSlice} from '@reduxjs/toolkit'

const initialState ={
    token : "",
    users:null,
    isLogout: true,
    isAuth: false
}

const userReducers = createSlice({
    name: "user",
    initialState,
    reducers:{
        setToken: (state, action) => {state.token = action.payload; console.log(action.payload, "mypayload")},
        setUsers: (state, action) => {state.users = action.payload} ,
        setLogout: (state, action) => {state.isLogout = action.payload},
        setAuth: (state, action) => {state.isAuth = action.payload}
    }
})

export const {setToken, setUsers, setLogout, setAuth} = userReducers.actions;
export default userReducers.reducer;