import {createSlice} from '@reduxjs/toolkit'

const initialState= {
    user: {},
    isAut: false,
}

const UserReducers = createSlice({
    name: "users",
    initialState,
    reducers: {
        setUsers: (state, action) => {state.user = action.payload;}
    }
})
export const {setUsers} = UserReducers.actions;
export default UserReducers.reducer;