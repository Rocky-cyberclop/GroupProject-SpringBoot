import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const ProductCreate = () => {
	const [id, setId] = useState(0);
    const [product, setProduct] = useState({
		id: 1,
		name: '',
		description: '',
		price: 0,
		category: {},
		image: ''
	});
	const [file, setFile] = useState(null);
	const [files, setFiles] = useState(null);
	const [categories, setCategories] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`http://localhost:8080/api/admin/management/categories`)
		.then((response) => response.json())
		.then((data) => {
			setCategories(data);
          })
          .catch((error) => console.error(error));
		fetch(`http://localhost:8080/api/admin/management/product/id`)
		  .then((response) => response.json())
		  .then((data) => {
			  setId(data);
			})
			.catch((error) => console.error(error));
    }, []);
        
    const productSubmit = async (e)=>{
        e.preventDefault();
		try{
			const response = await fetch('http://localhost:8080/api/admin/management/product', {
				method: 'POST',
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
		if(file!==null){
			const formData = new FormData();
			formData.append("product", id);
			formData.append("file", file);
			try{
				const response = await fetch('http://localhost:8080/api/admin/management/product/create/image', {
					method: 'POST',
					body: formData,
				});
				
			}
			catch (error) {
				console.error('An error occurred during form submission:', error);
			}
		}
		if(files!==null){
			const formData = new FormData();
			formData.append("product", id);

			// Append each file to the FormData object
			for (let i = 0; i < files.length; i++) {
			formData.append('files', files[i]);
			}
			try{
				const response = await fetch('http://localhost:8080/api/admin/management/product/create/images', {
					method: 'POST',
					body: formData,
				});
				
			}
			catch (error) {
				console.error('An error occurred during form submission:', error);
			}
		}

		navigate('/admin/management/products');
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
	const handleMultiFileChange = (e) => {
		// Get the selected files from the input element
		const selectedFiles = e.target.files;
	
		// Update the state with the selected files
		setFiles(selectedFiles);
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
												style={{height: '460px'}}
												placeholder="Description" id="description"></textarea>
										</div>
										<div className="form-group">
												<label>Thêm hình đại diện</label>
											<input className="form-control admin-product-img-first" 
												type="file" 
												name="avatar"
												onChange={handleFileChange}
											/>
										</div>
										<div className="form-group">
												<label>Thêm hình phụ</label>
											<input className="form-control admin-product-img-first" 
												type="file" 
												name="avatar"
												onChange={handleMultiFileChange}
												multiple='multiple'
											/>
										</div>


										<button type="submit" className="btn btn-gradient-primary me-2">Thêm sản phẩm</button>

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

export default ProductCreate;