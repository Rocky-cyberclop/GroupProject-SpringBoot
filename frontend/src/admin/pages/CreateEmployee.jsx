import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const CreateEmployee = () => {
    const [employee, setEmployee] = useState({});
    const [roles, setRoles] = useState([]);
	const navigate = useNavigate();
	let token = localStorage.getItem('token')

    useEffect(() => {
        if (document.getElementById('admin-script-chart')) {
            const adminChart = document.getElementById('admin-script-chart');
            document.body.removeChild(adminChart);
        }
		
        fetch("http://localhost:8080/api/admin/management/employeeAndRoles/98047",{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setRoles(data.roles)
            })
            .catch((error) => console.error(error));
        
    }, []);

    const employeeSubmit = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/admin/management/employee', {
				method: 'POST',
				headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + token
				// Add any other headers if needed
				},
				body: JSON.stringify(employee),
			});
			if(response.ok){
				navigate('/admin/management/employees');
			}
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }

	const handleInputChange = (e) => {
		const { name, value } = e.target;
		setEmployee({ ...employee, [name]: value });
	  };

    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="page-header">
						<h3 className="page-title">Thêm nhân viên</h3>
					</div>
					<div className="row">
						<div className="col-md-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<form className="forms-sample" onSubmit={employeeSubmit}>
										<div className="form-group">
											<label>Họ và tên</label>
											<input type="text" className="form-control" value={employee.name} name='name'
												placeholder="Username" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Chức vụ</label>
											<select className="form-control btn btn-outline-primary dropdown-toggle" value={employee.role} 
											name='role' onChange={handleInputChange}>
                                                {roles.map((e)=>(
                                                    <option className="dropdown-item" key={e.id} value={e.id}>{e.name}</option>
                                                ))}
											</select>
										</div>
										<div className="form-group">
											<label>Địa chỉ</label>
											<input type="text" className="form-control" value={employee.address} name='address'
												placeholder="Email" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Email</label>
											<input type="text" className="form-control" value={employee.email} name='email'
												placeholder="Password" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Năm sinh</label>
											<input type="date" className="form-control" value={employee.dob} name='dob'
												laceholder="Password" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Giới tính</label>
											<select className="form-control btn btn-outline-primary dropdown-toggle" name='gender' 
											value={employee.gender} onChange={handleInputChange}>
												<option className="dropdown-item" key="1" value="true">Nam</option>
												<option className="dropdown-item" key="0" value="false">Nữ</option>
											</select>
										</div>
										<div className="form-group">
											<label>Điện thoại</label>
											<input type="text" className="form-control" value={employee.phone} name='phone'
												placeholder="Username" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Ngày vào làm</label>
											<input type="date" className="form-control" value={employee.starting_date} name='starting_date'
												placeholder="Username" onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Ngày thôi việc</label>
											<input type="date" className="form-control" value={employee.resigning_date} name='resigning_date'
												placeholder="Ngày thôi việc" onChange={handleInputChange}/>
										</div>

										<button type="submit" className="btn btn-gradient-primary me-2">Hoàn tất chỉnh
											sửa</button>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
    );
};

export default CreateEmployee;