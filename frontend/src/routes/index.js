//Pages
import Login from '../pages/Login';
import Register from '../pages/Register';
import Forget from '../pages/Forget';

//Routes
const AuthRoutes = [
    { path: '/login', component: Login},
    { path: '/register', component: Register},
    { path: '/forget', component: Forget}
];

export { AuthRoutes };