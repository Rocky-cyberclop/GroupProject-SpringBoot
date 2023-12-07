import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ProductEdit = () => {
    const { id } = useParams();
    const [product, setProduct] = useState({});
	const [file, setFile] = useState(null);
	const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        if (document.getElementById('admin-script-chart')) {
            const adminChart = document.getElementById('admin-script-chart');
            document.body.removeChild(adminChart);
        }
		const adminChart = document.createElement('script');
        fetch(`http://localhost:8080/api/admin/management/product/edit/${id}`)
		.then((response) => response.json())
		.then((data) => {
			setProduct(data.productDto);
			setCategories(data.categoryDtos);
			adminChart.id = 'admin-script-product-detail';
			adminChart.src = '/admin_resources/js/admin-product-detail.js';
			adminChart.async = true;
			document.body.appendChild(adminChart);
          })
          .catch((error) => console.error(error));
		return ()=>document.body.removeChild(adminChart)
    }, [id]);
        
    const productSubmit = async (e)=>{
        e.preventDefault();
		try{
			const response = await fetch('http://localhost:8080/api/admin/management/product', {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
					// Add any other headers if needed
					},
				body: JSON.stringify(product),
			});
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
		const formData = new FormData();
		if(file!==null){
			formData.append("product", id);
			formData.append("file", file);
			try{
				const response = await fetch('http://localhost:8080/api/admin/management/product/update/image', {
					method: 'POST',
					body: formData,
				});
				
			}
			catch (error) {
				console.error('An error occurred during form submission:', error);
			}
		}
		navigate('/admin/management/product/detail/'+id);
    }
    
    const handleInputChange = (e) => {
		const { name, value } = e.target;
		if (name === 'category') {
			for(let i=0; i<categories.length; i++){
				console.log(value, categories[i].id, value==categories[i].id)
				if(value==categories[i].id){
					setProduct({ ...product, category: {id: value, name: categories[i].name} });
					break;
				}
			}
		} 
		else {
			setProduct({ ...product, [name]: value });
		}
	  };

	const handleFileChange = (e) => {
		const selectedFile =  e.target.files[0];
		setFile(selectedFile);
	  };

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
									<form className="forms-sample" onSubmit={productSubmit}>
										<div className="form-group">
											<label>Tên sản phẩm</label>
											<textarea className="form-control"name='name' value={product.name} onChange={handleInputChange}></textarea>
										</div>
										<div className="form-group">
											<label>Loại sản phẩm</label>
											<select className="form-control btn btn-outline-primary dropdown-toggle"
												name='category'
												value={product.category && product.category.id} onChange={handleInputChange}>
													{categories.map((e)=>(
														<option className="dropdown-item" key={e.id} 
														value={e.id}>{e.name}</option>
													))}
											</select>
										</div>
										<div className="form-group">
											<label>Giá bán</label>
											<input type="number" min="0" className="form-control" 
												value={product.price} 
												name='price'
												placeholder="Email"
												onChange={handleInputChange}/>
										</div>
										<div className="form-group">
											<label>Mô tả</label>
											<textarea className="form-control" value={product.description} 
												name='description'
												onChange={handleInputChange}
												placeholder="Description" id="description"></textarea>
										</div>
										<div className="form-group">
											<img className="form-control admin-product-img-first" src={product.image}
												width="200" />
										</div>
										<div className="form-group">
												<label>Đổi hình đại diện</label>
											<input className="form-control admin-product-img-first" 
												type="file" 
												name="avatar"
												onChange={handleFileChange}
											/>
										</div>


										<button type="submit" className="btn btn-gradient-primary me-2">Hoàn tất chỉnh sửa</button>

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

export default ProductEdit;