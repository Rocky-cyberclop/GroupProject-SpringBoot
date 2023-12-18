//Layouts
import MainLayoutAdmin from '../layouts/MainLayoutAdmin';
import LoginLayout from '../layouts/LoginLayout';

//Pages
import Main from '../pages/Main';
import Employees from '../pages/Employees';
import Statistic from '../pages/Statistic';
import DetailEmployee from '../pages/DetailEmployee';
import EditEmployee from '../pages/EditEmployee';
import CreateRole from '../pages/CreateRole';
import CreateEmployee from '../pages/CreateEmployee';
import Products from '../pages/Products';
import ProductDetail from '../pages/ProductDetail';
import ProductEdit from '../pages/ProductEdit';
import ProductDetailCreate from '../pages/ProductDetailCreate';
import DetailImport from '../pages/DetailImport';
import DetailExport from '../pages/DetailExport';
import CreateCaterogy from '../pages/CreateCaterogy';
import CreateSize from '../pages/CreateSize';
import ProductCreate from '../pages/ProductCreate';
import Receipts from '../pages/Receipts';
import ReceiptDetail from '../pages/ReceiptDetail';
import Login from '../pages/Login';

//Public routes
const publicRoutes = [
    { path: '/admin/login', component: Login, layout: LoginLayout}
];

const privateRoutes = [
    { path: '/admin', component: Main, layout: null},
    { path: '/admin/management/employees', component: Employees, layout: null},
    { path: '/admin/management/role/create', component: CreateRole, layout: null},
    { path: '/admin/management/employee/create', component: CreateEmployee, layout: null},
    { path: '/admin/management/employee/detail/:id', component: DetailEmployee, layout: null},
    { path: '/admin/management/employee/edit/:id', component: EditEmployee, layout: null},
    { path: '/admin/management/products', component: Products, layout: null},
    { path: '/admin/management/product/create', component: ProductCreate, layout: null},
    { path: '/admin/management/category/create', component: CreateCaterogy, layout: null},
    { path: '/admin/management/size/create', component: CreateSize, layout: null},
    { path: '/admin/management/product/detail/create/:id', component: ProductDetailCreate, layout: null},
    { path: '/admin/management/product/detail/:id', component: ProductDetail, layout: null},
    { path: '/admin/management/product/detail/import/:id/:size', component: DetailImport, layout: null},
    { path: '/admin/management/product/detail/export/:id/:size', component: DetailExport, layout: null},
    { path: '/admin/management/product/edit/:id', component: ProductEdit, layout: null},
    { path: '/admin/receipts', component: Receipts, layout: null},
    { path: '/admin/receipt/take/:id', component: ReceiptDetail, layout: null},
    { path: '/admin/statistic/all', component: Statistic, layout: null}
];

export { publicRoutes, privateRoutes };