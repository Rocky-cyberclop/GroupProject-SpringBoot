import React from 'react';
import Header from './fragments/Header';

function ProductDetailLayout({ children }) {
  return (
    <div>
      <Header />
      <div>
      {children}
      </div>
    </div>
  );
}

export default ProductDetailLayout;
