//Pages
import Login from '../pages/Login';
import Register from '../pages/Register';
import Forget from '../pages/Forget';
import ProductDetail from '../pages/ProductDetail';

//Routes
const AuthRoutes = [
    { path: '/login', component: Login},
    { path: '/register', component: Register},
    { path: '/forget', component: Forget},
   // { path: '/productdetail', component: ProductDetail},
    {path: '/productdetail/:id', component: ProductDetail, layout: null},
];



export { AuthRoutes };