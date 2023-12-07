import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();
    useEffect(() => {
        if(!document.getElementById('admin-style1')){
            const style1 = document.createElement('link');
            style1.id = 'admin-style1'
            style1.rel = 'stylesheet'
            style1.href = '/admin_resources/vendors/mdi/css/materialdesignicons.min.css';
            document.head.appendChild(style1);
            const style2 = document.createElement('link');
            style2.rel = 'stylesheet'
            style2.href = '/admin_resources/vendors/css/vendor.bundle.base.css';
            document.head.appendChild(style2);
            const style3 = document.createElement('link');
            style3.rel = 'stylesheet'
            style3.href = '/admin_resources/css/style.css';
            document.head.appendChild(style3);
            const style4 = document.createElement('link');
            style4.rel = 'shortcut icon'
            style4.href = '/admin_resources/images/favicon.ico';
            document.head.appendChild(style4);
        }
    })
    const handleLogout = function(){
        localStorage.removeItem("token")
        
        navigate('/admin');
    }
    return (
        <nav className="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div className="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
                <a className="navbar-brand brand-logo" href="index.html"><img src="/admin_resources/images/logo.svg"
                    alt="logo" /></a>
                <a className="navbar-brand brand-logo-mini" href="index.html"><img src="/admin_resources/images/logo-mini.svg"
                    alt="logo" /></a>
            </div>
            <div className="navbar-menu-wrapper d-flex align-items-stretch">
                <button className="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                    <span className="mdi mdi-menu"></span>
                </button>
                {/* <div className="search-field d-none d-md-block">
                    <form className="d-flex align-items-center h-100" action="#">
                        <div className="input-group">
                            <div className="input-group-prepend bg-transparent">
                                <i className="input-group-text border-0 mdi mdi-magnify"></i>
                            </div>
                            <input type="text" className="form-control bg-transparent border-0" placeholder="Search projects"/>
                        </div>
                    </form>
                </div> */}
                <ul className="navbar-nav navbar-nav-right">
                    {/* <li className="nav-item dropdown">
                        <a className="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#"
                            data-bs-toggle="dropdown">
                            <i className="mdi mdi-bell-outline"></i>
                            <span className="count-symbol bg-danger"></span>
                        </a>
                        <div className="dropdown-menu dropdown-menu-right navbar-dropdown preview-list"
                            aria-labelledby="notificationDropdown">
                            <h6 className="p-3 mb-0">Notifications</h6>
                            <div className="dropdown-divider"></div>
                            <a className="dropdown-item preview-item">
                                <div className="preview-thumbnail">
                                    <div className="preview-icon bg-success">
                                        <i className="mdi mdi-calendar"></i>
                                    </div>
                                </div>
                                <div
                                    className="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 className="preview-subject font-weight-normal mb-1">Event today</h6>
                                    <p className="text-gray ellipsis mb-0"> Just a reminder that you have an event today </p>
                                </div>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a className="dropdown-item preview-item">
                                <div className="preview-thumbnail">
                                    <div className="preview-icon bg-warning">
                                        <i className="mdi mdi-settings"></i>
                                    </div>
                                </div>
                                <div
                                    className="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 className="preview-subject font-weight-normal mb-1">Settings</h6>
                                    <p className="text-gray ellipsis mb-0"> Update dashboard </p>
                                </div>
                            </a>
                            <div className="dropdown-divider"></div>
                            <a className="dropdown-item preview-item">
                                <div className="preview-thumbnail">
                                    <div className="preview-icon bg-info">
                                        <i className="mdi mdi-link-variant"></i>
                                    </div>
                                </div>
                                <div
                                    className="preview-item-content d-flex align-items-start flex-column justify-content-center">
                                    <h6 className="preview-subject font-weight-normal mb-1">Launch Admin</h6>
                                    <p className="text-gray ellipsis mb-0"> New admin wow! </p>
                                </div>
                            </a>
                            <div className="dropdown-divider"></div>
                            <h6 className="p-3 mb-0 text-center">See all notifications</h6>
                        </div>
                    </li> */}
                    <li className="nav-item nav-logout d-none d-lg-block">
                        <a className="nav-link" href="#" onClick={handleLogout}>
                            <i className="mdi mdi-power"></i>
                        </a>
                    </li>
                    <li className="nav-item nav-settings d-none d-lg-block">
                        <a className="nav-link" href="#">
                            <i className="mdi mdi-format-line-spacing"></i>
                        </a>
                    </li>
                </ul>
                <button className="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                    data-toggle="offcanvas">
                    <span className="mdi mdi-menu"></span>
                </button>
            </div>
        </nav>
    );
};

export default Header;