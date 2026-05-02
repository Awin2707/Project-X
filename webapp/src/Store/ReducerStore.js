import {configureStore} from '@reduxjs/toolkit';
import UserReducers from '../Reducers/UserReducers';
const store = configureStore({
    reducer: {
        "user": UserReducers
    }
});

export default store;