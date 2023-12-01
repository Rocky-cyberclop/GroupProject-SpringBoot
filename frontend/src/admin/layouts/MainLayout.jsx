import React from 'react';
import Header from './fragments/Header';
import Sidebar from './fragments/Sidebar';

function MainLayout({ children }) {
  return (
    <div>
      <div className="container-scroller">
        <Header />
        <div className="container-fluid page-body-wrapper">
          <Sidebar />

          {children}

        </div>
      </div>
    </div>
  );
}

export default MainLayout;
