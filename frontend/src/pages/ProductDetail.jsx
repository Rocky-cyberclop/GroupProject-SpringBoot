import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ProductDetail = () => {
	const { id } = useParams();
	const [product, setProduct] = useState({});

	const [size, setSize] = useState(0);
	const [quantity, setQuantity] = useState(0);
	const navigate = useNavigate();
	let token = localStorage.getItem('token')


	useEffect(() => {
		if (document.getElementById('admin-script-chart')) {
			const adminChart = document.getElementById('admin-script-chart');
			document.body.removeChild(adminChart);
		}
		const adminChart = document.createElement('script');
		fetch(`http://localhost:8080/api/admin/management/product/${id}`,{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
			.then((response) => response.json())
			.then((data) => {
				setProduct(data);
				adminChart.id = 'admin-script-product-detail';
				adminChart.src = '/admin_resources/js/admin-product-detail.js';
				adminChart.async = true;
				document.body.appendChild(adminChart);
			})
			.catch((error) => console.error(error));
		return () => document.body.removeChild(adminChart)

	}, [id]);



	const handleInputChange = (e) => {
		const { name, value } = e.target;

		if (name === 'size') {
			setSize(value)
		}
		else {
			setQuantity(value);
		}
	};

	const detailSubmit = async (e) => {
		e.preventDefault();
		try {
			// Set a default size if size is falsy (not selected)
			const selectedSize = size || (product?.detailDtos?.[0]?.size?.id || 0);

			const response = await fetch('http://localhost:8080/api/main/productdetail', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					'Authorization': 'Bearer ' + token
					// Add any other headers if needed
				},
				body: JSON.stringify({ id: id, size: selectedSize, quantity: quantity }),
			});

			if (response.ok) {
				navigate('/productdetail/' + id);
			}
		} catch (error) {
			console.error('An error occurred during form submission:', error);
		}
	};

	return (
		<section className="single-product">
			<div className="container">
				<div className="row">
					<div className="col-md-6">
						<ol className="breadcrumb">
							<li><a href="/">Home</a></li>
							<li className="active">Single Product</li>
						</ol>
					</div>
					
				</div>
				<div className="row mt-20">
					<div className="col-md-5">
						<div className="single-product-slider">
							<div id='carousel-custom' className='carousel slide' data-ride='carousel'>
								<div className='carousel-outer'>
									<div className='carousel-inner '>
										<div className='item active'>
											<img src={product.image} />
										</div>										
									</div>
								</div>
								<ol className='carousel-indicators mCustomScrollbar meartlab'>
									{product.images && product.images.map((image, index) => (
										<li key={index} data-target='#carousel-custom' data-slide-to={index} className={index === 0 ? 'active' : ''}>
											<img src={image} alt={`Product ${index + 1}`} />
										</li>
									))}
								</ol>
							</div>
						</div>
					</div>
					<div className="col-md-7">
						<div className="single-product-details">
							<h2>{product.name}</h2>
							<p className="product-price">{product.price} VND</p>
							<p className="product-description mt-20" >
							Loại sản phẩm: {product.category && product.category.name}
							</p>
							<form className="forms-sample" onSubmit={detailSubmit}>
								<div className="form-group">
									<div className="product-size">
										<span>Size:</span>
										<select className="form-control" value={product.size} name='size' onChange={handleInputChange}>
											{product?.detailDtos?.map(e => (
												<option key={e.size.id} value={e.size.id}>{e.size.name}</option>
											))}
										</select>
									</div>
								</div>
								<div className="form-group">
									<label>Nhập số lượng</label>
									<input type="number" className="form-control" placeholder="Số lượng" name="quantity" value={product.quantity} onChange={handleInputChange} />
								</div>
								<button type="submit" className="btn btn-gradient-primary me-2">Thêm vào giỏ hàng</button>
							</form>
						</div>
					</div>
				</div>
				<div className="row">
					<div className="col-xs-12">
						<div className="tabCommon mt-20">
							<ul className="nav nav-tabs">
								<li className="active"><a data-toggle="tab" href="#details" aria-expanded="true">Details</a></li>
							</ul>
							<div className="tab-content patternbg">
								<div id="details" className="tab-pane fade active in">
									<h4>Product Description</h4>
									<p>{product.description}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

	);
};

export default ProductDetail;