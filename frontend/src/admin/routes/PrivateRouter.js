import { Navigate, useLocation } from 'react-router-dom';

function PrivateRouter({ children }) {
    
    const isHasToken = localStorage.getItem('token');
    
    const location = useLocation();
    // if the user is not authenticated, redirect to the login page with some state
    if (!isHasToken) {
        return (
            <Navigate
                to="/admin/login"
                state={{ from: location }} // the original location of the user
            />
        );
    }

    // if the user is authenticated, render the element prop
    return children;
}

export default PrivateRouter;