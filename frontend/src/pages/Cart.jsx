import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'

function Cart() {
    const [cartList, setCartList] = useState([]);
    const [total, setTotal] = useState([]);
    let token = localStorage.getItem('token')

    const totalAmount = total.totalPrice;

    const formattedTotal = formatCurrency(totalAmount);

    function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
    }

    useEffect(() => {
        getCart();
        getTotal();
    }, []);

    const getCart = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/cart/items", {
                headers:{
                    'Authorization': 'Bearer ' + token
                }
            });
            setCartList(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    const getTotal = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/cart/totalPrice", {
                headers:{
                    'Authorization': 'Bearer ' + token
                }
            });
            setTotal(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    let handleDelete = async (product_id) => {
        try {
        const confirmDelete = window.confirm("Bạn có muón xóa sản phẩm này: " + product_id + " ?");
        if (confirmDelete) {
            await axios.delete(`http://localhost:8080/api/cart/items/${product_id}`, {
                headers:{
                    'Authorization': 'Bearer ' + token
                }
            });
            getCart();
        }
        } catch (error) {
        console.log(error);
        }
    }

    return (
        <section className="main-main">
            <section class="page-header">
                <div className="row">
                    <div className="col-md-12">
                        <div className="content">
                            <h1 className="page-name">Giỏ hàng</h1>
                            <ol className="breadcrumb">
                                <li><a href="index.html">Trang chủ</a></li>
                                <li className="active">giỏ hàng</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </section>
        
            <div className="page-wrapper">
                <div className="cart shopping">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-8 col-md-offset-2">
                                <div className="block">
                                    <div className="product-list">
                                        <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="">Tên Sản Phẩm</th>
                                                <th className="">Số lượng</th>
                                                <th className="">Giá</th>
                                                <th className="">Hành Động</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {cartList.map((cart) => {
                                                return (
                                                    <tr> {/* Sửa đổi tại đây */}
                                                        <td>{cart.name}</td> {/* Sửa đổi tại đây */}
                                                        <td>{cart.quantity}</td>
                                                        <td>{cart.price}</td> {/* Giả sử có trường giá sản phẩm */}
                                                        <th>
                                                            <button onClick={() => handleDelete(cart.product_id)} className='btn btn-danger btn-sm mr-1'>Xóa</button> {/* Sửa đổi tại đây */}
                                                        </th>
                                                    </tr>
                                                )
                                            })}
                                        </tbody>
                                        </table>
                                        <div class="row panel-body">
                                            <h5>Tổng tiền: {formattedTotal}</h5>
                                            <Link to={`/main/checkout`} className='btn btn-main pull-right'>Thanh toán</Link>
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

export default Cart;