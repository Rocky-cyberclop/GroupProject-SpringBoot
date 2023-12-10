import { useState, useEffect } from "react";
import React from "react";
import Pagination from "../layouts/fragments/components/Pagination";
import { useParams } from "react-router-dom";

const ProductCate = () => {
  const { id } = useParams();
  const [products, setProducts] = useState([]);
  const [category, setCategory] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  let token = localStorage.getItem("token");

  useEffect(() => {
    fetch(`http://localhost:8080/api/main/product/category/${id}`, {
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setProducts(data.productsDto.productDtos);
        setCurrentPage(data.productsDto.currentPage);
        setTotalPages(data.productsDto.totalPage);
        setCategory(data.categoryName);
        console.log(category)
      })
      .catch((error) => console.error(error));
  }, [id]);

  function onPageChange(page) {
    if (page > 0 && page <= totalPages) {
      fetch(`http://localhost:8080/api/main/product/category/${id}/` + page, {
        headers: {
          Authorization: "Bearer " + token,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          setProducts(data.productsDto.productDtos);
          setCurrentPage(data.productsDto.currentPage);
          setTotalPages(data.productsDto.totalPage);
          setCategory(data.category);
        })
        .catch((error) => console.error(error));
    }
  }

  return (
    <section className="main-main">
      <section className="products section">
        <div className="title text-center">
          <h2>{category}</h2>
        </div>
        <div className="container">
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
          <Pagination
            currentPage={currentPage}
            totalPage={totalPages}
            onPageChange={onPageChange}
          />
        </div>
      </section>
    </section>
  );
};

export default ProductCate;
