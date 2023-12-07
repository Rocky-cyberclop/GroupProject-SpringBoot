import React from 'react';
import Header from './fragments/Header';

function AuthenticationLayout({ children }) {
  return (
    <div>
      <Header />
      <div>
      {children}
      </div>
    </div>
  );
}

export default AuthenticationLayout;
