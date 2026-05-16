import {configureStore} from '@reduxjs/toolkit';
import UserReducers from '../Reducers/UserReducers';
import CategoriesReducers from '../Reducers/CategoriesReducer';
const store = configureStore({
    reducer: {
        "user": UserReducers,
        "categories": CategoriesReducers
    }
});

export default store;