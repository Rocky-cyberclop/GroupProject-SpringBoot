import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { publicRoutes, privateRoutes } from './admin/routes';
import MainLayoutAdmin from './admin/layouts/MainLayoutAdmin';
import { AuthRoutes, MainRoutes, CartRoutes, CheckoutRoutes } from './routes';
import AuthenticationLayout from './layouts/AuthenticationLayout';
import MainLayout from './layouts/MainLayout';


function App() {
  
  return (
    <Router>
      <div className='App'>
        <Routes>
          {publicRoutes.map((route, index)=>{
            const Page = route.component;
            let Layout = MainLayoutAdmin;
            if(route.layout!==null){
              Layout = route.layout;
            }
            return (
              <Route 
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page/>
                  </Layout>
                }>

              </Route>
            )
          })}

          {AuthRoutes.map((route, index)=>{
            const Page = route.component;
            return (
              <Route 
                key={index}
                path={route.path}
                element={
                  <AuthenticationLayout>
                    <Page/>
                  </AuthenticationLayout>
                }>

              </Route>
            )
          })}

          {MainRoutes.map((route, index)=>{
            const Page = route.component;
            let Layout = MainLayout;            
            return (
              <Route 
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page/>
                  </Layout>
                }>

              </Route>
            )
          })}

          {CartRoutes.map((route, index)=>{
            const Page = route.component;
            let Layout = MainLayout;          
            return (
              <Route 
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page/>
                  </Layout>
                }>

              </Route>
            )
          })}

          {CheckoutRoutes.map((route, index)=>{
            const Page = route.component;
            let Layout = MainLayout;          
            return (
              <Route 
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page/>
                  </Layout>
                }>

              </Route>
            )
          })}
        </Routes>
      </div>
    </Router>
  );
}

export default App;