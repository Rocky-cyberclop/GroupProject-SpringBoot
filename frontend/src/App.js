import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { publicRoutes as publicAdminRoutes, privateRoutes as privateAdminRoutes } from './admin/routes';
import MainLayoutAdmin from './admin/layouts/MainLayoutAdmin';
import PrivateRouter from './admin/routes/PrivateRouter';

function App() {
  
  return (
    <Router>
      <div className='App'>
        <Routes>
          {publicAdminRoutes.map((route, index)=>{
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
          {privateAdminRoutes.map((route, index) => {
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
                      <PrivateRouter>
                          <Layout>
                              <Page />
                          </Layout>
                      </PrivateRouter>
                  }
              />
            );            
            })}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
