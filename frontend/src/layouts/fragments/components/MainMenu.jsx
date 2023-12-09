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
                                    <li><a href="shop.html">Shop</a></li>
                                    <li><a href="/main/checkout">Checkout</a></li>
                                    <li><a href="/main/cart">Cart</a></li>
                                    <li><a href="pricing.html">Pricing</a></li>
                                    <li><a href="confirmation.html">Confirmation</a></li>
                                </ul>
                                </div>
                                <div className="col-lg-6 col-md-6 mb-sm-3">
                                <ul>
                                    <li className="dropdown-header">Layout</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="product-single.html">Product Details</a></li>
                                    <li><a href="shop-sidebar.html">Shop With Sidebar</a></li>
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
                                <div className="col-sm-3 col-xs-12">
                                <ul>
                                    <li className="dropdown-header">Introduction</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="contact.html">Contact Us</a></li>
                                    <li><a href="about.html">About Us</a></li>
                                    <li><a href="404.html">404 Page</a></li>
                                    <li><a href="coming-soon.html">Coming Soon</a></li>
                                    <li><a href="faq.html">FAQ</a></li>
                                </ul>
                                </div>
                                <div className="col-sm-3 col-xs-12">
                                <ul>
                                    <li className="dropdown-header">Dashboard</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="/account">User Interface</a></li>
                                    <li><a href="/account/order">Orders</a></li>
                                    <li><a href="/account/profileDetails">Profile Details</a></li>
                                </ul>
                                </div>

                                <div className="col-sm-3 col-xs-12">
                                <ul>
                                    <li className="dropdown-header">Utility</li>
                                    <li role="separator" className="divider"></li>
                                    <li><a href="login.html">Login Page</a></li>
                                    <li><a href="signin.html">Signin Page</a></li>
                                    <li><a href="forget-password.html">Forget Password</a></li>
                                </ul>
                                </div>

                                <div className="col-sm-3 col-xs-12">
                                    <a href="shop.html">
                                        <img className="img-responsive" src="/assets/images/shop/header-img.jpg" alt="menu image" />
                                    </a>
                                </div>
                            </div>
                            </div>
                        </li>
                        <li className="dropdown dropdown-slide">
                            <a href="#!" className="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                            data-delay="350" role="button" aria-haspopup="true" aria-expanded="false">
                            Blog <span className="tf-ion-ios-arrow-down"></span>
                            </a>
                            <ul className="dropdown-menu">
                            <li><a href="blog-left-sidebar.html">Blog Left Sidebar</a></li>
                            <li><a href="blog-right-sidebar.html">Blog Right Sidebar</a></li>
                            <li><a href="blog-full-width.html">Blog Full Width</a></li>
                            <li><a href="blog-grid.html">Blog 2 Columns</a></li>
                            <li><a href="blog-single.html">Blog Single</a></li>
                            </ul>
                        </li>
                        <li className="dropdown dropdown-slide">
                            <a href="#!" className="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                            data-delay="350" role="button" aria-haspopup="true" aria-expanded="false">
                            Elements <span className="tf-ion-ios-arrow-down"></span>
                            </a>
                            <ul className="dropdown-menu">
                            <li><a href="typography.html">Typography</a></li>
                            <li><a href="buttons.html">Buttons</a></li>
                            <li><a href="alerts.html">Alerts</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </section>
    </div>
  
);

export default MainMenu;