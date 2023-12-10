import React from "react";
import { useEffect, useState } from "react";

const Main = () => {
  const [categories, setCategories] = useState([]);
  const [products, setProducts] = useState([]);
  useEffect(() => {
    const script = document.createElement("script");
    script.id = "main-script";
    script.async = true;
    script.type = "module";
    script.src = "/assets/js/main.js";
    document.body.appendChild(script);

    fetch("http://localhost:8080/api/main")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        // setCategories(data.categories.body);
        // setProducts(data.products.body);
        const { products, categories } = data;
        setCategories(categories.body || []);
        setProducts(products.body || []);
      })
      .catch((error) => console.error(error));
  }, []);

  return (
    <section className="main-main">
      <div className="hero-slider">
        <div
          className="slider-item th-fullpage hero-area"
          style={{ backgroundImage: "url(/assets/images/slider/slider-1.jpg)" }}
        >
          <div className="container">
            <div className="row">
              <div className="col-lg-8 text-center">
                <p
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".1"
                >
                  SẢN PHẨM
                </p>
                <h1
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".5"
                >
                  Thời trang giới trẻ <br /> tự tin, năng động.
                </h1>
                <a
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".8"
                  className="btn"
                  href="/main/cart"
                >
                  Mua ngay
                </a>
              </div>
            </div>
          </div>
        </div>
        {/* <div
          className="slider-item th-fullpage hero-area"
          style={{ backgroundImage: "url(assets/images/slider/slider-3.jpg)" }}
        >
          <div className="container">
            <div className="row">
              <div className="col-lg-8 text-left">
                <p
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".1"
                >
                  SẢN PHẨM
                </p>
                <h1
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".5"
                >
                  Thời trang giới trẻ <br /> tự tin, năng động.
                </h1>
                <a
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".8"
                  className="btn"
                  href="/main/cart"
                >
                  Mua ngay
                </a>
              </div>
            </div>
          </div>
        </div>
        <div
          className="slider-item th-fullpage hero-area"
          style={{ backgroundImage: "url(assets/images/slider/slider-2.jpg)" }}
        >
          <div className="container">
            <div className="row">
              <div className="col-lg-8 text-right">
                <p
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".1"
                >
                  SẢN PHẨM
                </p>
                <h1
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".5"
                >
                  Thời trang giới trẻ <br /> tự tin, năng động.
                </h1>
                <a
                  data-duration-in=".3"
                  data-animation-in="fadeInUp"
                  data-delay-in=".8"
                  className="btn"
                  href="/main/cart"
                >
                  Mua ngay
                </a>
              </div>
            </div>
          </div>
        </div> */}
      </div>
      <section className="product-category section">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <div className="title text-center">
                <h2>Phân Loại Sản Phẩm</h2>
              </div>
            </div>
          </div>
          <div className="row">
            {categories &&
              categories.map((category) => (
                <div key={category.id} className="col-md-4">
                  <div className="category-box col">
                    <a href="#!">
                      <img
                        src="/assets/images/shop/category/category-2.jpg"
                        alt=""
                      />
                      <div className="content">
                        <h3>{category.name}</h3>
                        <p></p>
                      </div>
                    </a>
                  </div>
                </div>
              ))}

            {/* {categories && categories.length > 0 ? (
    categories.map((category) => (
        <div key={category.id} className="col-md-4">
            <div className="category-box col">
                <a href="#!">
                    <img src="/assets/images/shop/category/category-2.jpg" alt="" />
                    <div className="content">
                        <h3>{category.name}</h3>
                        <p></p>
                    </div>
                </a>
            </div>
        </div>
    ))
) : (
    <p>No categories available</p>
)} */}
          </div>
        </div>
      </section>
      <section className="products section bg-gray">
        <div className="container">
          <div className="row">
            <div className="title text-center">
              <h2>Sản phẩm nổi bật</h2>
            </div>
          </div>
          <div className="row">
            {products.map((product) => (
              <div key={product.id} className="col-md-4">
                <div className="product-item">
                  <div className="product-thumb">
                    <img
                      className="img-responsive"
                      src={product.image}
                      alt="product-img"
                    />
                    {/* <div className="preview-meta">
                      <ul>
                        <li>
                          <span
                            data-toggle="modal"
                            data-target="#product-modal"
                          >
                            <i className="tf-ion-ios-search-strong"></i>
                          </span>
                        </li>
                        <li>
                          <a href="#!">
                            <i className="tf-ion-android-cart"></i>
                          </a>
                        </li>
                      </ul>
                    </div> */}
                  </div>
                  <div className="product-content">
                    <h4>
                      <a href={`/productdetail/${product.id}`}>
                        {product.name}
                      </a>
                    </h4>
                    <p className="price">{`${new Intl.NumberFormat(
                      "en-US"
                    ).format(product.price)} VNĐ`}</p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
      <section className="call-to-action bg-gray section">
        <div className="container">
          <div className="row">
            <div className="col-md-12 text-center">
              <div className="title">
                <h2>ĐĂNG KÝ NGAY</h2>
                <p>Đăng ký để nhận những thông tin mới nhất về sản phẩm.</p>
              </div>
              <div className="col-lg-6 col-md-offset-3">
                <div className="input-group subscription-form">
                  <a className="input-group-btn" href="/register">
                    <button className="btn btn-main" type="button">
                      Đăng Ký!
                    </button>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </section>
  );
};

export default Main;
