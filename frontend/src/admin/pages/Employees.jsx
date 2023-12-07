import { useState, useEffect } from "react";
import React from 'react';
import Pagination from "../components/pagination/Pagination";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const Employees = () => {

    const management = 'employee';
    const [employees, setEmployees] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const navigate = useNavigate();

    let token = localStorage.getItem('token')
    useEffect(() => {
        if (!document.getElementById('admin-style-employee')) {
            const style1 = document.createElement('link');
            style1.id = 'admin-style-employee'
            style1.rel = 'stylesheet'
            style1.href = '/admin_resources/css/management-employee.css';
            document.head.appendChild(style1);
        }
        if (document.getElementById('admin-script-chart')) {
            const adminChart = document.getElementById('admin-script-chart');
            document.body.removeChild(adminChart);
        }
        fetch("http://localhost:8080/api/admin/management/employees", {
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setEmployees(data.employees)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
                // console.log(currentPage)
                // console.log(totalPages)
            })
            .catch((error) => console.error(error));
    }, []);

    function onPageChange(page){
        if(page>0&&page<=totalPages){
            fetch("http://localhost:8080/api/admin/management/employees/"+page, {
                headers:{
                    'Authorization': 'Bearer ' + token
                }
            })
            .then((response) => response.json())
            .then((data) => {
                setEmployees(data.employees)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
                console.log(currentPage)
                console.log(totalPages)
            })
            .catch((error) => console.error(error));
        }
    }

    const deleteEmployee = async (e)=>{
        console.log(e.target.getAttribute('data'))
        try{
            
			const response = await fetch('http://localhost:8080/api/admin/management/employee', {
				method: 'DELETE',
				headers: {
				'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
				// Add any other headers if needed
				},
				body: JSON.stringify(e.target.getAttribute('data')),
			});
			if(response.ok){
				navigate('/admin/management/employees');
			}
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }
    
    return (
        <div className="main-panel main-admin">
            <div className="content-wrapper">
                <div className="content-wrapper">
                    <div className="page-header">
                        <h3 className="page-title">Quản lý nhân viên</h3>
                    </div>
                    <Link to="/admin/management/employee/create"
                        className="btn btn-gradient-primary me-2 mb-5">Thêm nhân viên</Link>
                    <Link to="/admin/management/role/create"
                        className="btn btn-gradient-primary me-2 mb-5">Thêm vai trò</Link>
                    <div className="row">
                        <div className="col-lg-12 grid-margin stretch-card">
                            <div className="card">
                                <div className="card-body">
                                    <table className="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Chức vụ</th>
                                                <th>Tên</th>
                                                <th>Địa chỉ</th>
                                                <th>Năm sinh</th>
                                                <th>Email</th>
                                                <th>Giới tính</th>
                                                <th>Điện thoại</th>
                                                <th>Ngày vào làm</th>
                                                <th>Ngày thôi việc</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {employees.map((e) =>
                                            (<tr key={e.id}>

                                                <td>{e.id}</td>
                                                <td>{e.role.name}</td>
                                                <td>{e.name}</td>
                                                <td>{e.address}</td>
                                                <td>{e.dob}</td>
                                                <td>{e.email}</td>
                                                <td>{(e.gender&&'Nam')||(!e.gender&&'Nữ')}</td>
                                                <td>{e.phone}</td>
                                                <td>{e.starting_date}</td>
                                                <td>{e.resigning_date}</td>
                                                <td>
                                                    <Link
                                                        to={"/admin/management/employee/detail/" + e.id}><button
                                                            className="btn btn-outline-secondary btn-rounded btn-icon"
                                                            href=""><i
                                                                className="mdi mdi-information text-primary"></i></button></Link>
                                                    <a ><button onClick={deleteEmployee} data={e.id}
                                                            className="btn btn-outline-secondary btn-rounded btn-icon"><i
                                                                className="mdi mdi-delete text-danger"></i></button></a>
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
        </div>
    );
};

export default Employees;