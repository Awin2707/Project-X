import {createSlice} from '@reduxjs/toolkit'

const initialState= {
    user: {},
    isAut: false,
    jwtToken: null,
    token: null,
    page: null,
}

const UserReducers = createSlice({
    name: "users",
    initialState,
    reducers: {
        setUsers: (state, action) => {state.user = action.payload;},
        setJwtToken: (state, action) => {state.jwtToken = action.payload},
        setToken: (state, action) => {state.token = action.payload},
        setPage: (state, action) => {state.page = action.payload}
    }
})
export const {setUsers, setJwtToken, setToken, setPage} = UserReducers.actions;
export default UserReducers.reducer;