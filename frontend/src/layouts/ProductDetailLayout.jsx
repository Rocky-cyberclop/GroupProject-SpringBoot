import React from 'react';
import Header from './fragments/components/Header';
import Footer from './fragments/components/Footer';

function ProductDetailLayout({ children }) {
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

export default ProductDetailLayout;


