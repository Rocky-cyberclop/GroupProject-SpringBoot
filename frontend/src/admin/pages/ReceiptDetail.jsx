import { useState, useEffect } from "react";
import React from 'react';
import { useParams, useNavigate } from "react-router-dom";

const ReceiptDetail = () => {
    const {id} = useParams();
    const [orderDetails, setDetails] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (!document.getElementById('admin-style-employee')) {
            const style1 = document.createElement('link');
            style1.id = 'admin-style-employee'
            style1.rel = 'stylesheet'
            style1.href = '/admin_resources/css/management-employee.css';
            document.head.appendChild(style1);
        }
        fetch("http://localhost:8080/api/admin/receipt/"+id)
            .then((response) => response.json())
            .then((data) => {
                setDetails(data)
            })
            .catch((error) => console.error(error));
    }, []);

    const taked = async (e)=>{
        try{
			const response = await fetch('http://localhost:8080/api/admin/receipt', {
				method: 'PUT',
				headers: {
				'Content-Type': 'application/json',
				// Add any other headers if needed
				},
				body: JSON.stringify(id),
			});
			if(response.ok){
				navigate('/admin/receipts');
			}
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }
    let i=0;
    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="content-wrapper">
						<div className="page-header">
							<h3 className="page-title">Danh sách mặc hàng</h3>
						</div>
						<div className="col-lg-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<table className="table table-hover">
										<thead>
											<tr>
												<th>Mã hàng</th>
												<th>Size</th>
												<th>Số lượng</th>
												<th>Giá</th>
											</tr>
										</thead>
										<tbody>
                                            {orderDetails.map((e)=>(
                                                <tr key={i++}>
                                                    <td>{e.product}</td>
                                                    <td>{e.size}</td>
                                                    <td>{e.quantity}</td>
                                                    <td>{e.price}</td>
                                                </tr>
                                            ))}
										</tbody>
									</table>
									<button className="btn btn-gradient-primary mt-5" onClick={taked}>Nhận đơn</button>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
    );
};

export default ReceiptDetail;