import React from 'react';

const MainMenu = () => (
    <div>
  <section className="menu">
        <nav className="navbar navigation">
            <div className="container">
                <div className="navbar-header">
                    <h2 className="menu-title">Main Menu</h2>
                    <button type="button" className="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                    <span className="sr-only">Toggle navigation</span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                    <span className="icon-bar"></span>
                    </button>
                </div>
                <div id="navbar" className="navbar-collapse collapse text-center">
                    <ul className="nav navbar-nav">
                        <li className="dropdown ">
                            <a href="/">Home</a>
                        </li>
                        <li className="dropdown dropdown-slide">
                            <a href="#!" className="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                            data-delay="350" role="button" aria-haspopup="true" aria-expanded="false">
                            Shop <span className="tf-ion-ios-arrow-down"></span>
                            </a>
                            <div className="dropdown-menu">
                            <div className="row">
                                <div className="col-lg-6 col-md-6 mb-sm-3">
                                <ul>
                                    <li className="dropdown-header">Pages</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="/">Shop</a></li>                                    
                                    <li><a href="/main/cart">Cart</a></li>
                                    <li><a href="/main/checkout">Checkout</a></li>
                                </ul>
                                </div>
                            </div>
                            </div>
                        </li>
                        <li className="dropdown full-width dropdown-slide">
                            <a href="#!" className="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                            data-delay="350" role="button" aria-haspopup="true" aria-expanded="false">
                            Pages <span className="tf-ion-ios-arrow-down"></span>
                            </a>
                            <div className="dropdown-menu">
                            <div className="row">                                
                                <div className="col-sm-6 col-xs-12">
                                <ul>
                                    <li className="dropdown-header">Dashboard</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="/account">User Interface</a></li>
                                    <li><a href="/account/order">Orders</a></li>
                                    <li><a href="/account/profileDetails">Profile Details</a></li>
                                </ul>
                                </div>

                                <div className="col-sm-6 col-xs-12">
                                <ul>
                                    <li className="dropdown-header">Utility</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="/login">Login Page</a></li>
                                    <li><a href="/register">Signin Page</a></li>
                                    <li><a href="/forget">Forget Password</a></li>
                                </ul>
                                </div>                                
                            </div>
                            </div>
                        </li>
                        
                    </ul>
                </div>
            </div>
        </nav>
    </section>
    </div>
  
);

export default MainMenu;