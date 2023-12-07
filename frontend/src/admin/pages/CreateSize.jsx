import { useState, useEffect } from "react";
import React from 'react';
import Pagination from "../components/pagination/Pagination";
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const CreateSize = () => {
    const [size, setSize] = useState({});
    const navigate = useNavigate();

    const handleInputChange = (e) => {
		const { name, value } = e.target;
		setSize({ ...size, [name]: value });
	  };

    const sizeSubmit = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/admin/management/size', {
				method: 'POST',
				headers: {
				'Content-Type': 'application/json',
				// Add any other headers if needed
				},
				body: size.name,
			});
			if(response.ok){
				navigate('/admin/management/products');
			}
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }
    
    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="page-header">
						<h3 className="page-title"></h3>
					</div>
					<div className="row">
						<div className="col-md-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<form className="forms-sample" onSubmit={sizeSubmit}>
										<div className="form-group">
											<label>Tên size</label>
											<input type="text" className="form-control" name="name" value={size.name} 
                                            onChange={handleInputChange}
												placeholder="Name"/>
										</div>
										<button type="submit" className="btn btn-gradient-primary me-2">Thêm size</button>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
    );
};

export default CreateSize;