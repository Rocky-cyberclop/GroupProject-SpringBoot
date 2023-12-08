import React, { useEffect } from 'react';
import TopHeader from './TopHeader';
import MainMenu from './MainMenu';
const Header = () => {
    useEffect(() => {
        if(!document.getElementById('main-style')){
            const style1 = document.createElement('link');
            style1.id = 'main-style'
            style1.rel = 'stylesheet'
            style1.href = '/assets/images/favicon.png';
            document.head.appendChild(style1);
            const style2 = document.createElement('link');
            style2.rel = 'stylesheet'
            style2.href = '/assets/plugins/themefisher-font/style.css';
            document.head.appendChild(style2);
            const style3 = document.createElement('link');
            style3.rel = 'stylesheet'
            style3.href = '/assets/plugins/bootstrap/css/bootstrap.min.css';
            document.head.appendChild(style3);
            const style4 = document.createElement('link');
            style4.rel = 'stylesheet'
            style4.href = '/assets/plugins/animate/animate.css';
            document.head.appendChild(style4);
            const style5 = document.createElement('link');
            style5.rel = 'stylesheet'
            style5.href = '/assets/plugins/slick/slick.css';
            document.head.appendChild(style5);
            const style6 = document.createElement('link');
            style6.rel = 'stylesheet'
            style6.href = '/assets/plugins/slick/slick-theme.css';
            document.head.appendChild(style6);
            const style7 = document.createElement('link');
            style7.rel = 'stylesheet'
            style7.href = '/assets/css/style.css';
            document.head.appendChild(style7);
        }
    })
    return (
        <div>
            <TopHeader/>
            <MainMenu/>
        </div>
    );
};

export default Header;

