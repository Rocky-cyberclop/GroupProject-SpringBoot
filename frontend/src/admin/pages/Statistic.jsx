import { useEffect, createElement } from "react";
import React from 'react';

const Statistic = () => {

    useEffect(() => {
		
		const adminChart = document.createElement('script');
		adminChart.id = 'admin-script-chart';
		adminChart.src = '/admin_resources/js/admin-chart.js';
		adminChart.async = true;
		document.body.appendChild(adminChart);
        return ()=>document.body.removeChild(adminChart);
        
    }, []);
    
    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="page-header">
						<h3 className="page-title"> Thống kê toàn doanh thu </h3>

					</div>
					<form action="#" className="forms-sample mb-3">

						<div className="form-group">
							<label>Ngày bắt đầu</label>
							<input type="date" className="start-date form-control" name="start"/>
						</div>

						<div className="form-group">
							<label>Ngày kết thúc</label>
							<input type="date" className="end-date form-control" name="end"/>
						</div>


						<button type="submit" className="btn btn-gradient-primary me-2 btn-statistic">Thống kê</button>

					</form>
					<div className="row">
						<div className="col-lg-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<h4 className="card-title">Toàn doanh thu</h4>
									<canvas id="areaChart" style={{height: '250px'}}></canvas>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
    );
};

export default Statistic;