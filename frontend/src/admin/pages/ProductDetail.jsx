import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const ProductDetail = () => {
    const { id } = useParams();
    const [product, setProduct] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        if (document.getElementById('admin-script-chart')) {
            const adminChart = document.getElementById('admin-script-chart');
            document.body.removeChild(adminChart);
        }
		const adminChart = document.createElement('script');
        fetch(`http://localhost:8080/api/admin/management/product/${id}`)
		.then((response) => response.json())
		.then((data) => {
			setProduct(data);
			adminChart.id = 'admin-script-product-detail';
			adminChart.src = '/admin_resources/js/admin-product-detail.js';
			adminChart.async = true;
			document.body.appendChild(adminChart);
          })
          .catch((error) => console.error(error));
		return ()=>document.body.removeChild(adminChart)
    }, [id]);
        
    function productSubmit(e){
        e.preventDefault();
        navigate('/admin/management/product/edit/'+id);
    }
    
    const handleInputChange = (e) => {
		const { name, value } = e.target;
		setProduct({ ...product, [name]: value });
	  };

    return (
        <div className="main-panel main-admin">
				<div className="content-wrapper">
					<div className="page-header">
						<h3 className="page-title">Chi tiết sản phẩm</h3>
					</div>
					<div className="row">
						<div className="col-md-12 grid-margin stretch-card">

							<div className="card">
								<div className="card-body">
									<form className="forms-sample" onSubmit={productSubmit}>
										<div className="form-group">
											<label>Mã sản phẩm</label>
											<input type="text" className="form-control" name='id' value={product.id}
												placeholder="Username" disabled/>
										</div>
										<div className="form-group">
											<label>Tên sản phẩm</label>
											<textarea className="form-control" name='name' value={product.name} disabled></textarea>
										</div>
										<div className="form-group">
											<label>Loại sản phẩm</label>
											<input className="form-control" name='category' value={product.category && product.category.name} disabled />
										</div>
										<div className="form-group">
											<label>Giá bán</label>
											<input type="text" className="form-control" name='price' value={product.price}
												placeholder="Email" disabled/>
										</div>
										<div className="form-group">
											<label>Mô tả</label>
											<textarea className="form-control" 
                                                name='description' 
                                                value={product.description}
												placeholder="Description" id="description" 
                                                onChange={handleInputChange}
                                                disabled></textarea>
										</div>
										<div className="form-group">
											<img className="form-control admin-product-img-first" src={product.image}
												style= { {width: '400px'}} alt='some pic'/>
										</div>
										<button type="submit" className="btn btn-gradient-primary me-2">Chỉnh sửa</button>

									</form>
								</div>
								<div className="card-body">
									
								</div>
							</div>
						</div>
					</div>
					<div className="row">
						<div className="col-lg-12 grid-margin stretch-card">
							<div className="card">
								<div className="card-body">
									<div className="page-header">
										<h3 className="page-title">Quản lý tồn kho</h3>
									</div>
									<Link to={"/admin/management/product/detail/create/"+product.id}
										className="btn btn-gradient-primary me-2 mb-5">Thêm size bán</Link>
									<table className="table table-hover">
										<thead>
											<tr>
												<th>Loại size</th>
												<th>Số lượng tồn</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>
                                            {product.detailDtos&&product.detailDtos.map((e)=>(
                                                <tr key={e.size.id}>
                                                    <td>{e.size.name}</td>
                                                    <td>{e.quantity}</td>
                                                    <td>
                                                        <Link
                                                            to={"/admin/management/product/detail/import/"+product.id+"/"+e.size.id}><button
                                                                className="btn btn-outline-secondary" href=""><i
                                                                    className="mdi mdi-database-plus text-primary"></i>Nhập
                                                                kho</button></Link>
                                                        <Link
                                                            to={"/admin/management/product/detail/export/"+product.id+"/"+e.size.id}><button
                                                                className="btn btn-outline-secondary"><i
                                                                    className="mdi mdi-database-minus text-danger"></i>Xuất
                                                                kho</button></Link>
                                                    </td>
                                                </tr>
                                            ))}
											
										</tbody>
									</table>

								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
    );
};

export default ProductDetail;