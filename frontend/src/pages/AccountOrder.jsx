import React from "react";
import { useState, useEffect } from "react";

function AccountOrder(){
    const [oders, setOrders] = useState([]);
    const token = localStorage.getItem('token');

    useEffect(() => {
        fetch("http://localhost:8080/api/account/orders",{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
				// console.log(data)
                setOrders(data)
            })
            .catch((error) => console.error(error));
    }, []);

    return(
        <section className="user-dashboard page-wrapper">
		<div className="container">
			<div className="row">
				<div className="col-md-12">
					<ul className="list-inline dashboard-menu text-center">
						<li><a className="active" href="/account/order">Đơn đặt hàng</a></li>
                        <li><a href="/account">Chi Tiết hồ sơ</a></li>
					</ul>

					<div className="dashboard-wrapper user-dashboard">
						<div className="table-responsive">
							<table className="table">
								<thead>
									<tr>
										<th>ID Đơn Hàng</th>
										<th>Ngày</th>
										<th>Tổng Giá</th>
										<th>Trạng Thái</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
                                    {oders.map((e)=>(
                                        <tr key={e.id}>
                                            <td><a>{e.id}</a></td>
                                            <td>{e.date}</td>
                                            <td>
                                                {e.total}VND
                                            </td>
                                            <td><span >{e.status}</span></td>
                                            <td><a 
                                                    className="btn btn-default" href={"/account/order/"+e.id}>Xem</a></td>
                                            
                                        </tr>
                                    ))}

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
    )
}

export default AccountOrder;