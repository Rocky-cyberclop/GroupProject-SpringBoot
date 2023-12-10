import React from "react";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";

function AccountOrderDetail(){
    const {id} = useParams();
    const [orderDetails, setOrderDetails] = useState([]);
    const token = localStorage.getItem('token');

    useEffect(() => {
        fetch("http://localhost:8080/api/account/order/"+id,{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setOrderDetails(data)
            })
            .catch((error) => console.error(error));
    }, []);

    return(
        <>
            <section className="page-header">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <div className="content">
                                <h1 className="page-name">Chi tiết đơn hàng {id}
                                </h1>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="user-dashboard page-wrapper">
                <div className="container-fluid">

                    <table className="table table-hover table-bordered">
                        <thead>
                            <tr>
                                <th>Tên hàng hóa</th>
                                <th>Số lượng</th>
                                <th style={{width: "150px"}}>Giá tiền</th>
                                <th>Kích cỡ</th>
                            </tr>
                        </thead>
                        <tbody>
                            {orderDetails.map((e)=>(
                                <tr key={e.name}>
                                    <td >{e.name}</td>
                                    <td >{e.quantity}</td>
                                    <td > {e.price}VND
                                    </td>
                                    <td >{e.size}</td>
                                </tr>
                            ))}
                            
                        </tbody>
                    </table> <br/> 
                    {/* <h4 className="text-primary" th:text="@{'Tổng tiền: '+ ${#numbers.formatDecimal(sum, 0, 'COMMA', 0, 'POINT')+' VNĐ'}}"></h4> */}
                </div>
                
            </section>
        </>
    )
}

export default AccountOrderDetail;