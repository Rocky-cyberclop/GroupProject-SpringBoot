import React from 'react';
import Header from './fragments/components/Header';
import Footer from './fragments/components/Footer';

function MainLayout({ children }) {
    return (
      <div>
        <Header />
        <div>
        {children}
        </div>
        <Footer/>
      </div>
    );
  }
  
  export default MainLayout;
  