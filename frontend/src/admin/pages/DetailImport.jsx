import { useState, useEffect } from "react";
import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const DetailImport = () => {
	const { id, size } = useParams();
    const [quantity, setQuantity] = useState(0);
    const navigate = useNavigate();
	let token = localStorage.getItem('token')

    const handleInputChange = (e) => {
		const { name, value } = e.target;
		setQuantity(value);
	  };

    const detailImportSubmit = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/admin/management/product/detail', {
				method: 'PUT',
				headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + token
				// Add any other headers if needed
				},
				body: JSON.stringify({id: id, size: size, quantity: quantity}),
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
									<form className="forms-sample" onSubmit={detailImportSubmit}>
										<div className="form-group">
											<label>Số lượng nhập kho</label>
											<input type="number" min={0} className="form-control" name="quantity" value={quantity} 
                                            onChange={handleInputChange} placeholder="Số lượng"/>
										</div>
										<button type="submit" className="btn btn-gradient-primary me-2">Nhập kho</button>

									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
    );
};

export default DetailImport;