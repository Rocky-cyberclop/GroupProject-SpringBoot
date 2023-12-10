//Pages
import MainLayout from '../layouts/MainLayout';
import Main from '../pages/Main';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Forget from '../pages/Forget';
import Cart from '../pages/Cart';
import Checkout from '../pages/Checkout';
import ProductDetail from '../pages/ProductDetail';
import Account from '../pages/Account';
import AccountUpdate from '../pages/AccountUpdate';
import AccountOrder from '../pages/AccountOrder';
import AccountOrderDetail from '../pages/AccountOrderDetail';

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
    { path: '/account', component: Account, layout: null},
    { path: '/account/order', component: AccountOrder, layout: null},
    { path: '/account/order/:id', component: AccountOrderDetail, layout: null},
    { path: '/account/update', component: AccountUpdate, layout: null}
]

// const CartRoutes = [
//     { path: '/main/cart', component: Cart},
// ];

// const CheckoutRoutes = [
//     { path: '/main/checkout', component: Checkout},
// ];

export { AuthRoutes, MainRoutes, PrivateRoutes };
