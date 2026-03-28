import { configureStore } from '@reduxjs/toolkit';
import userReducer from '../Redcuers/UserReducers';
const store = configureStore({
    reducer: {   // ✅ correct key
        users: userReducer
    }
});

export default store;