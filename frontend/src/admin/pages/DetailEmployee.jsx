import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const DetailEmployee = () => {
    const { id } = useParams();
    const [employee, setEmployee] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        if (document.getElementById('admin-script-chart')) {
            const adminChart = document.getElementById('admin-script-chart');
            document.body.removeChild(adminChart);
        }
        fetch("http://localhost:8080/api/admin/management/employee/" + id)
            .then((response) => response.json())
            .then((data) => {
                setEmployee(data)
            })
            .catch((error) => console.error(error));
    }, []);

    function employeeSubmit(e){
        e.preventDefault();
        navigate('/admin/management/employee/edit/'+id);
    }

    return (
        <div className="main-panel main-admin">
            <div className="content-wrapper">
                <div className="page-header">
                    <h3 className="page-title">Chi tiết nhân viên</h3>
                </div>
                <div className="row">
                    <div className="col-md-12 grid-margin stretch-card">
                        <div className="card">
                            <div className="card-body">
                                <form onSubmit={employeeSubmit} className="forms-sample">
                                    <div className="form-group">
                                        <label>Họ và tên</label>
                                        <input type="text" className="form-control" value={employee.name}
                                            placeholder="Username" disabled />
                                    </div>
                                    <div className="form-group">
											<label>Chức vụ</label>
											<input type="text" className="form-control" value={employee.role}
												placeholder="Username" disabled/>
										</div>
										<div className="form-group">
											<label>Địa chỉ</label>
											<input type="text" className="form-control" value={employee.phone}
												placeholder="Email" disabled/>
										</div>
										<div className="form-group">
											<label>Email</label>
											<input type="text" className="form-control" value={employee.email}
												placeholder="Password" disabled/>
										</div>
										<div className="form-group">
											<label>Năm sinh</label>
											<input type="text" className="form-control" value={employee.dob}
												laceholder="Password" disabled/>
										</div>
										<div className="form-group">
											<label>Giới tính</label>
											<input type="text" className="form-control" value={(employee.gender&&'Nam')||(!employee.gender&&'Nữ')}
												disabled/>
										</div>
										<div className="form-group">
											<label>Điện thoại</label>
											<input type="text" className="form-control" value={employee.phone}
												placeholder="Username" disabled/>
										</div>
										<div className="form-group">
											<label>Ngày vào làm</label>
											<input type="date" className="form-control" value={employee.starting_date}
												placeholder="Username" disabled/>
										</div>
										<div className="form-group">
											<label>Ngày thôi việc</label>
											<input type="date" className="form-control" value={employee.resigning_date}
												placeholder="Ngày thôi việc" disabled/>
										</div>

                                    <button type="submit" className="btn btn-gradient-primary me-2">Chỉnh sửa</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DetailEmployee;