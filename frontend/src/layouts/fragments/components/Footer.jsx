import React, { useEffect } from "react";

const Footer = () => {
  //   useEffect(() => {
  //     if(document.getElementById('main-script')){
  //       const script1 = document.createElement('script');
  //       script1.src = '/assets/plugins/jquery/dist/jquery.min.js';
  //       document.head.appendChild(script1);

  //       const script2 = document.createElement('script');
  //       script2.src = '/assets/plugins/bootstrap/js/bootstrap.min.js';
  //       document.head.appendChild(script2);

  //       const script3 = document.createElement('script');
  //       script3.src = '/assets/plugins/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js';
  //       document.head.appendChild(script3);

  //       const script4 = document.createElement('script');
  //       script4.src = '/assets/plugins/instafeed/instafeed.min.js';
  //       document.head.appendChild(script4);

  //       const script5 = document.createElement('script');
  //       script5.src = '/assets/plugins/ekko-lightbox/dist/ekko-lightbox.min.js';
  //       document.head.appendChild(script5);

  //       const script6 = document.createElement('script');
  //       script6.src = '/assets/plugins/syo-timer/build/jquery.syotimer.min.js';
  //       document.head.appendChild(script6);

  //       const script7 = document.createElement('script');
  //       script7.src = '/assets/plugins/slick/slick.min.js';
  //       document.head.appendChild(script7);

  //       const script8 = document.createElement('script');
  //       script8.src = '/assets/plugins/slick/slick-animation.min.js';
  //       document.head.appendChild(script8);

  //       const script9 = document.createElement('script');
  //       script9.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyCC72vZw-6tGqFyRhhg5CkF2fqfILn2Tsw";
  //       document.head.appendChild(script9);

  //     const script10 = document.createElement('script');
  //     script10.id = 'main-script';
  //     script10.async = true;
  //     script10.type = 'module';
  //     script10.src = '/assets/js/main.js';
  //     document.body.appendChild(script10);
  //     }

  // }, [])
  return (
    <footer className="footer section text-center">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <ul className="social-media">
              <li>
                <a href="https://www.facebook.com/themefisher">
                  <i className="tf-ion-social-facebook"></i>
                </a>
              </li>

              <li>
                <a href="https://www.twitter.com/themefisher">
                  <i className="tf-ion-social-twitter"></i>
                </a>
              </li>
              <li>
                <a href="https://www.pinterest.com/themefisher/">
                  <i className="tf-ion-social-pinterest"></i>
                </a>
              </li>
            </ul>
            <ul className="footer-menu text-uppercase">
              <li>
                <a href="/">CONTACT</a>
              </li>
              <li>
                <a href="/">SHOP</a>
              </li>
              <li>
                <a href="/">Pricing</a>
              </li>
              <li>
                <a href="/">PRIVACY POLICY</a>
              </li>
            </ul>
            <p className="copyright-text">
              Copyright &copy;2023, Designed &amp; Developed by{" "}
              <a href="#">TEAM TEEN BOUTIQUE</a>
            </p>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
