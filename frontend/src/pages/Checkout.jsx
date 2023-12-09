import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'
import axios from 'axios'; 

function Checkout() {
   const [customer, setCustomer] = useState([]);
   const [cartList, setCartList] = useState([]);
   const [total, setTotal] = useState([]);
   const navigate = useNavigate();

   const totalAmount = total.totalPrice;

   const formattedTotal = formatCurrency(totalAmount);

   function formatCurrency(amount) {
      return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
   }

   useEffect(() => {
      getCustomer();
      getCart();
      getTotal();
      // getThanhToan();
   }, []);

   const getCustomer = async () => {
      try {
         const response = await axios.get("http://localhost:8080/api/cart/customers");
         setCustomer(response.data);
      } catch (error) {
         console.log(error);
      }
    }

   const getCart = async () => {
      try {
          const response = await axios.get("http://localhost:8080/api/cart/items");
          setCartList(response.data);
      } catch (error) {
          console.log(error);
      }
   }

   const getTotal = async () => {
      try {
          const response = await axios.get("http://localhost:8080/api/cart/totalPrice");
          setTotal(response.data);
      } catch (error) {
          console.log(error);
      }
   }

   const handleCheckout = async () => {
      try {
          const response = await axios.get("http://localhost:8080/api/cart/checkout");
          navigate('/main/cart');
      } catch (error) {
          console.log(error);
      }
   }

   let handleDelete = async (product_id) => {
      try {
        const confirmDelete = window.confirm("Bạn có muón xóa sản phẩm này: " + product_id + " ?");
        if (confirmDelete) {
          await axios.delete(`http://localhost:8080/api/cart/items/${product_id}`);
          getCart();
        }
      } catch (error) {
        console.log(error);
      }
   }  

    return (
      <section className="main-main">
         <section class="page-header">
            <div class="row">
               <div class="col-md-12">
                  <div class="content">
                     <h1 class="page-name">Thanh toán</h1>
                     <ol class="breadcrumb">
                        <li><a href="index.html">Trang chủ</a></li>
                        <li class="active">thanh toán</li>
                     </ol>
                  </div>
               </div>
		      </div>
         </section>
         <div class="page-wrapper" >
            <div class="checkout shopping">
               <div class="container">
                  <div class="row">
                     <div class="col-md-8">
                        <div class="block billing-details">
                           <h4 class="widget-title">CHI TIẾT THANH TOÁN</h4>
                           <form class="checkout-form">
                              <div class="form-group">
                                 <label for="full_name">Họ và tên</label>
                                 <input type="text" class="form-control" value={customer.name}/>
                              </div>
                              <div class="form-group">
                                 <label for="user_address">Địa chỉ</label>
                                 <input type="text" class="form-control" value={customer.address}/>
                              </div>
                              <div class="form-group">
                                 <label for="user_address">Số điện thoại</label>
                                 <input type="text" class="form-control" value={customer.phone}/>
                              </div>
                           </form>
                        </div>
                        <a type="submit" onClick={handleCheckout} className=' btn btn-main mt-20 '>THANH TOÁN</a>
                     </div>
                     <div class="col-md-4">
                        <div class="product-checkout-details">
                           <div class="block">
                              <h4 class="widget-title">TÓM TẮT THEO THỨ TỰ</h4>
                              <div class="media product-card">
                                 {cartList.map((cart) => {
                                    return (
                                       <tr>
                                       <a class="pull-left" href="product-single.html">
                                             </a>
                                             <div class="media-body">
                                                <h4 class="media-heading"><a href="product-single.html">{cart.name}</a></h4>
                                                <p class="price">{cart.quantity} x {cart.price} VND </p>
                                                <a>
                                                   <span onClick={() => handleDelete(cart.product_id)} type="submit" class="remove">Xóa</span>
                                                </a>
                                             </div>
                                       </tr>
                                    )
                                 })}
                              </div>
                              <div class="discount-code">
                                 <p>Có giảm giá? <a data-toggle="modal" data-target="#coupon-modal" href="#!">nhập vào đây</a></p>
                              </div>
                              <ul class="summary-prices">
                                 <li>
                                    <span>Tổng hàng hóa:</span>
                                    <span class="price">{formattedTotal}</span>
                                 </li>
                                 <li>
                                    <span>Phí vận chuyển:</span>
                                    <span>Miễn phí</span>
                                 </li>
                              </ul>
                              <div class="summary-total">
                                 <span>Tổng cộng:</span>
                                 <span>{formattedTotal}</span>
                              </div>
                              <div class="verified-icon">
                                 <img src="/assets/images/shop/verified.png"/>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
    );
}

export default Checkout;

