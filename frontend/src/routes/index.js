//Pages
import MainLayout from '../layouts/MainLayout';
import Main from '../pages/Main';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Forget from '../pages/Forget';
import Cart from '../pages/Cart';
import Checkout from '../pages/Checkout';
import ProductDetail from '../pages/ProductDetail';

//Routes
const AuthRoutes = [
    { path: '/login', component: Login},
    { path: '/register', component: Register},
    { path: '/forget', component: Forget},    
];

const MainRoutes = [
    { path: '/', component: Main},
    // { path: '/productdetail/:id', component: ProductDetail, layout: null},
];

const PrivateRoutes = [
    { path: '/main/cart', component: Cart},
    { path: '/main/checkout', component: Checkout},
    { path: '/productdetail/:id', component: ProductDetail, layout: null},
]

// const CartRoutes = [
//     { path: '/main/cart', component: Cart},
// ];

// const CheckoutRoutes = [
//     { path: '/main/checkout', component: Checkout},
// ];

export { AuthRoutes, MainRoutes, PrivateRoutes };
