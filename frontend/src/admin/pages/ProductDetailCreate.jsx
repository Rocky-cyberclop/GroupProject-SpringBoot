import { useState, useEffect } from "react";
import React from 'react';
import Pagination from "../components/pagination/Pagination";
import { Link } from "react-router-dom";
import { useNavigate, useParams } from 'react-router-dom';

const ProductDetailCreate = () => {
    const { id } = useParams();
    const [sizes, setSizes] = useState([]);
    const [size, setSize] = useState(1);
    const [quantity, setQuantity] = useState(0);
    const navigate = useNavigate();
	let token = localStorage.getItem('token')

    useEffect(() => {
		
        fetch("http://localhost:8080/api/admin/management/sizes",{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
                setSizes(data)
            })
            .catch((error) => console.error(error));
        
    }, []);

    const handleInputChange = (e) => {
		const { name, value } = e.target;
		if (name === 'size') {
            setSize(value)
		} 
		else {
			setQuantity(value);
		}
	  };

    const detailSubmit = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/admin/management/product/detail', {
				method: 'POST',
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
						<h3 className="page-title">Thêm size</h3>
					</div>
					<div className="row">
						<div className="col-md-12 grid-margin stretch-card">

							<div className="card">
								<div className="card-body">
									<form className="forms-sample" onSubmit={detailSubmit}>
										<div className="form-group">
											<label>Size</label>
											<select className="form-control btn btn-outline-primary dropdown-toggle"
												name="size" value={size} onChange={handleInputChange}>
                                                    {sizes.map((e)=>(
                                                        <option className="dropdown-item" key={e.id} value={e.id}>{e.name}</option>
                                                    ))}
											</select>
										</div>
										<div className="form-group">
											<label>Số lượng</label>
											<input type="number" min="0" className="form-control" 
                                            name="quantity" onChange={handleInputChange}
                                            value={quantity}/>
										</div>
										<button type="submit" className="btn btn-gradient-primary me-2">Thêm</button>

									</form>
								</div>
								<div className="card-body">

								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
    );
};

export default ProductDetailCreate;