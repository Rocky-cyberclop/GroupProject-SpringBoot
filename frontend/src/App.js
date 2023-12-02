import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { publicRoutes, privateRoutes } from './admin/routes';
import MainLayoutAdmin from './admin/layouts/MainLayoutAdmin';

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
        </Routes>
      </div>
    </Router>
  );
}

export default App;
