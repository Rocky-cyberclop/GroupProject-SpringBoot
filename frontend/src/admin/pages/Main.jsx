import React from 'react';
import { useEffect } from 'react';

const Main = () => {
	useEffect(() => {
		const script6 = document.createElement('script');
		script6.id = 'admin-script';
		script6.async = true;
		script6.src = '/admin_resources/js/dashboard.js';
		document.body.appendChild(script6);

		return ()=>{document.body.removeChild(script6)}
		// }
	}, [])
    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="page-header">
						<h3 className="page-title">
							<span className="page-title-icon bg-gradient-primary text-white me-2">
								<i className="mdi mdi-home"></i>
							</span> Trang chủ
						</h3>
						<nav aria-label="breadcrumb">
							<ul className="breadcrumb">
								<li className="breadcrumb-item active" aria-current="page">
									<span></span>Tổng thể <i
										className="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
								</li>
							</ul>
						</nav>
					</div>
					<div className="row">
						<div className="col-md-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<div className="clearfix">
										<h4 className="card-title float-left">Thống kê</h4>
										<div id="visit-sale-chart-legend"
											className="rounded-legend legend-horizontal legend-top-right float-right"></div>
									</div>
									<canvas id="visit-sale-chart" className="mt-4"></canvas>
								</div>
							</div>
						</div>
						
					</div>
					<div className="row">
						<div className="col-12 grid-margin">
							<div className="card">
								<div className="card-body">
									<h4 className="card-title">Quản lý</h4>
									<div className="table-responsive">
										<table className="table">
											<thead>
												<tr>
													<th> Assignee </th>
													<th> Subject </th>
													<th> Status </th>
													<th> Last Update </th>
													<th> Tracking ID </th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<img src="/admin_resources/images/faces/face1.jpg" className="me-2"
															alt="image"/> David Grey
													</td>
													<td> Fund is not recieved </td>
													<td>
														<label className="badge badge-gradient-success">DONE</label>
													</td>
													<td> Dec 5, 2017 </td>
													<td> WD-12345 </td>
												</tr>
												<tr>
													<td>
														<img src="/admin_resources/images/faces/face2.jpg" className="me-2"
															alt="image"/> Stella Johnson
													</td>
													<td> High loading time </td>
													<td>
														<label className="badge badge-gradient-warning">PROGRESS</label>
													</td>
													<td> Dec 12, 2017 </td>
													<td> WD-12346 </td>
												</tr>
												<tr>
													<td>
														<img src="/admin_resources/images/faces/face3.jpg" className="me-2"
															alt="image"/> Marina Michel
													</td>
													<td> Website down for one week </td>
													<td>
														<label className="badge badge-gradient-info">ON HOLD</label>
													</td>
													<td> Dec 16, 2017 </td>
													<td> WD-12347 </td>
												</tr>
												<tr>
													<td>
														<img src="/admin_resources/images/faces/face4.jpg" className="me-2"
															alt="image"/> John Doe
													</td>
													<td> Loosing control on server </td>
													<td>
														<label className="badge badge-gradient-danger">REJECTED</label>
													</td>
													<td> Dec 3, 2017 </td>
													<td> WD-12348 </td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div className="row">
						<div className="col-md-7 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<h4 className="card-title">Duyệt đơn</h4>
									<div className="table-responsive">
										<table className="table">
											<thead>
												<tr>
													<th> # </th>
													<th> Name </th>
													<th> Due Date </th>
													<th> Progress </th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td> 1 </td>
													<td> Herman Beck </td>
													<td> May 15, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-success"
																role="progressbar" style={{width: "25%"}} aria-valuenow="25"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td> 2 </td>
													<td> Messsy Adam </td>
													<td> Jul 01, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-danger"
																role="progressbar" style={{width: "75%"}} aria-valuenow="75"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td> 3 </td>
													<td> John Richards </td>
													<td> Apr 12, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-warning"
																role="progressbar" style={{width: "90%"}} aria-valuenow="90"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td> 4 </td>
													<td> Peter Meggik </td>
													<td> May 15, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-primary"
																role="progressbar" style={{width: "50%"}} aria-valuenow="50"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td> 5 </td>
													<td> Edward </td>
													<td> May 03, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-danger"
																role="progressbar" style={{width: "35%"}} aria-valuenow="35"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
												<tr>
													<td> 5 </td>
													<td> Ronald </td>
													<td> Jun 05, 2015 </td>
													<td>
														<div className="progress">
															<div className="progress-bar bg-gradient-info"
																role="progressbar" style={{width: "65%"}} aria-valuenow="65"
																aria-valuemin="0" aria-valuemax="100"></div>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						
					</div>
				</div>

		
			</div>
    );
};

export default Main;