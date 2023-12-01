import { useState, useEffect } from "react";
import React from 'react';
import Pagination from "../components/pagination/Pagination";
import { Link } from "react-router-dom";

const Receipts = () => {

    const management = 'order';
    const [orders, setOrders] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        if (!document.getElementById('admin-style-employee')) {
            const style1 = document.createElement('link');
            style1.id = 'admin-style-employee'
            style1.rel = 'stylesheet'
            style1.href = '/admin_resources/css/management-employee.css';
            document.head.appendChild(style1);
        }
        fetch("http://localhost:8080/api/admin/receipts")
            .then((response) => response.json())
            .then((data) => {
                setOrders(data.orderDtos)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
                // console.log(currentPage)
                // console.log(totalPages)
            })
            .catch((error) => console.error(error));
    }, []);

    function onPageChange(page){
        if(page>0&&page<=totalPages){
            fetch("http://localhost:8080/api/admin/receipts/"+page)
            .then((response) => response.json())
            .then((data) => {
                setOrders(data.orderDtos)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
            })
            .catch((error) => console.error(error));
        }
    }
    
    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="content-wrapper">
						<div className="page-header">
							<h3 className="page-title">Danh sách đơn hàng</h3>
						</div>
						<div className="col-lg-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<table className="table table-hover">
										<thead>
											<tr>
												<th>Mã đơn</th>
												<th>Ngày đặt</th>
												<th>Mã khách hàng</th>
												<th>Tổng tiền</th>
												<th>Nhân viên</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
                                            {orders.map((e)=>(
                                                <tr key={e.id}>
                                                    <td>{e.id}</td>
                                                    <td>{e.date}</td>
                                                    <td>{e.customer_id}</td>
                                                    <td>{e.total}</td>
                                                    <td>{e.employee_id}</td>
                                                    <td>
                                                        <Link to={"/admin/receipt/take/"+e.id}><button
                                                                className="btn btn-outline-secondary btn-rounded btn-icon"
                                                                href=""><i
                                                                    className="mdi mdi-information text-primary"></i></button></Link>
                                                    </td>
                                                </tr>
                                            ))}
										</tbody>
									</table>
									<Pagination currentPage={currentPage} totalPage={totalPages} onPageChange={onPageChange}/>
									

								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
    );
};

export default Receipts;