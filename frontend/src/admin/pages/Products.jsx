import { useState, useEffect } from "react";
import React from 'react';
import Pagination from "../components/pagination/Pagination";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const Products = () => {

    const management = 'prodcuct';
    const [products, setProducts] = useState([]);
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
        fetch("http://localhost:8080/api/admin/management/products",{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setProducts(data.productDtos)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
                // console.log(currentPage)
                // console.log(totalPages)
            })
            .catch((error) => console.error(error));
    }, []);

    function onPageChange(page){
        if(page>0&&page<=totalPages){
            fetch("http://localhost:8080/api/admin/management/products/"+page,{
                headers:{
                    'Authorization': 'Bearer ' + token
                }
            })
            .then((response) => response.json())
            .then((data) => {
                setProducts(data.productDtos)
                setCurrentPage(data.currentPage)
                setTotalPages(data.totalPage)
                // console.log(currentPage)
                // console.log(totalPages)
            })
            .catch((error) => console.error(error));
        }
    }

    const deleteProduct = async (e)=>{
        // console.log(e.target.getAttribute('data'))
        try{
			const response = await fetch('http://localhost:8080/api/admin/management/product', {
				method: 'DELETE',
				headers: {
				'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
				// Add any other headers if needed
				},
				body: JSON.stringify(e.target.getAttribute('data')),
			});
            console.log(response.ok)
			if(response.ok){
				navigate('/admin');
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
                        <h3 className="page-title">Quản lý sản phẩm</h3>
                    </div>
                    <Link to="/admin/management/product/create"
                        className="btn btn-gradient-primary me-2 mb-5">Thêm Sản Phẩm</Link>
                    <Link to="/admin/management/category/create"
                        className="btn btn-gradient-primary me-2 mb-5">Thêm Loại</Link>
                    <Link to="/admin/management/size/create"
                        className="btn btn-gradient-primary me-2 mb-5">Thêm Size</Link>
                    <div className="row">
                        <div className="col-lg-12 grid-margin stretch-card">
                            <div className="card">
                                <div className="card-body">
                                    <table className="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên</th>
                                                <th>Giá bán</th>
                                                <th>Loại</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {products.map((e) =>
                                            (<tr key={e.id}>

                                                <td>{e.id}</td>
                                                <td>{e.name}</td>
                                                <td>{e.price}</td>
                                                <td>{e.category.name}</td>
                                                <td>
                                                    <Link
                                                        to={"/admin/management/product/detail/" + e.id}><button
                                                            className="btn btn-outline-secondary btn-rounded btn-icon"
                                                            href=""><i
                                                                className="mdi mdi-information text-primary"></i></button></Link>
                                                    <a ><button onClick={deleteProduct} data={e.id}
                                                            className="btn btn-outline-secondary btn-rounded btn-icon"><i
                                                                className="mdi mdi-delete text-danger" data={e.id}></i></button></a>
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

export default Products;