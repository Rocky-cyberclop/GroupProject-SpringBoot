import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  useEffect(() => {
    // const script1 = document.createElement('script');
    // script1.src = '/admin/vendors/js/vendor.bundle.base.js';
    // document.head.appendChild(script1);
    // const script2 = document.createElement('script');
    // script2.src = '/admin/vendors/chart.js/Chart.min.js';
    // document.head.appendChild(script2);
    // const script3 = document.createElement('script');
    // script3.type = 'text/javascript';
    // script3.src = '/admin/js/jquery.cookie.js';
    // document.head.appendChild(script3);
    // const script4 = document.createElement('script');
    // script4.src = '/admin/js/off-canvas.js';
    // document.head.appendChild(script4);
    // const script5 = document.createElement('script');
    // script5.src = '/admin/js/hoverable-collapse.js';
    // document.head.appendChild(script5);
    // if(document.getElementById('admin-script')){
    //   const script6 = document.getElementById('admin-script');
    //   document.body.removeChild(script6);
    // }
    if(!document.getElementById('admin-script')){
      const script6 = document.createElement('script');
      script6.id = 'admin-script';
      script6.async = true;
      script6.src = '/admin_resources/js/admin.js';
      document.body.appendChild(script6);
    }
    // const script7 = document.createElement('script');
    // script7.src = '/admin/js/todolist.js';
    // document.head.appendChild(script7);
    
}, [])
  return (
    <nav className="sidebar sidebar-offcanvas" id="sidebar">
      <ul className="nav">
        <li className="nav-item nav-profile">
          <a href="#" className="nav-link">
            <div className="nav-profile-image">
              <img src="/admin_resources/images/faces/avatar.png" alt="profile"/>
                <span className="login-status online"></span>
                
            </div>
            <div className="nav-profile-text d-flex flex-column">
              <span className="font-weight-bold mb-2">Tên</span>
              <span className="text-secondary text-small" >Vai trò</span>
            </div>
            <i className="mdi mdi-bookmark-check text-success nav-profile-badge"></i>
          </a>
        </li>
        <li className="nav-item">
          <Link className='nav-link' to='/admin'>
            <span className="menu-title">Trang chủ</span>
            <i className="mdi mdi-home menu-icon"></i>
            </Link>
        </li>
        <li className="nav-item">
          <a className="nav-link" data-bs-toggle="collapse" href="#ui-basic" aria-expanded="false"
            aria-controls="ui-basic">
            <span className="menu-title">Quản lý</span>
            <i className="menu-arrow"></i>
            <i className="mdi mdi-contacts menu-icon"></i>
          </a>
          <div className="collapse" id="ui-basic">
            <ul className="nav flex-column sub-menu">
              <li className="nav-item"> <Link className='nav-link' to='/admin/management/employees'>Nhân viên</Link>
              </li>
              <li className="nav-item"> <Link className="nav-link" to="/admin/management/products">Sản phẩm</Link>
              </li>
            </ul>
          </div>
        </li>
        <li className="nav-item">
          <Link className="nav-link" to="/admin/receipts">
            <span className="menu-title">Duyệt đơn</span>
            <i className="mdi mdi-receipt menu-icon"></i>
          </Link>
        </li>
        <li className="nav-item">
          <a className="nav-link" data-bs-toggle="collapse" href="#statistic-basic" aria-expanded="false"
            aria-controls="ui-basic">
            <span className="menu-title">Thống kê</span>
            <i className="menu-arrow"></i>
            <i className="mdi mdi-chart-bar menu-icon"></i>
          </a>
          <div className="collapse" id="statistic-basic">
            <ul className="nav flex-column sub-menu">
              <li className="nav-item"> <Link className="nav-link" to="/admin/statistic/all">Tất cả sản phẩm</Link>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </nav>
  );
};

export default Sidebar;