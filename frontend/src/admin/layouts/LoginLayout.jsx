import React from 'react';
import { useEffect } from 'react';

function LoginLayout({ children }) {
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
  return (
    children
  );
}

export default LoginLayout;
