//Pages
import MainLayout from '../layouts/MainLayout';
import Main from '../pages/Main';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Forget from '../pages/Forget';

//Routes
const AuthRoutes = [
    { path: '/login', component: Login},
    { path: '/register', component: Register},
    { path: '/forget', component: Forget}
];

const MainRoutes = [
    { path: '/', component: Main},
];

export { AuthRoutes, MainRoutes };