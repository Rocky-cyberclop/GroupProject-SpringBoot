import { useState, useEffect } from "react";
import React from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [data, setData] = useState({id:'', pass:''});
	const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
		const { name, value } = e.target;
		setData({ ...data, [name]: value });
	  };

    const login = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/admin/auth', {
				method: 'POST',
				headers: {
				'Content-Type': 'application/json',
				// Add any other headers if needed
				},
				body: JSON.stringify(data)
			}).then(response=>response.json()).then(data=>{
				console.log(data.jwt)
				if(data.jwt!=="Fail"){
					localStorage.setItem("token", data.jwt)
					navigate('/admin');
				}
				else{
					setError("Bạn đã nhập sai mã nhân viên hoặc mật khẩu")
				}
			});
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }
    
    return (
        <div className="container-scroller">
		<div className="container-fluid page-body-wrapper full-page-wrapper">
			<div className="content-wrapper d-flex align-items-center auth">
				<div className="row flex-grow">
					<div className="col-lg-4 mx-auto">
						<div className="auth-form-light text-left p-5">
							<div className="brand-logo">
								<img src="/admin_resources/images/logo.svg"/>
							</div>
							<h4>Sign in to continue.</h4>
							<form className="pt-3" onSubmit={login}>
								<div className="form-group">
									<input type="text" className="form-control form-control-lg" id="exampleInputEmail1"
										placeholder="Username" name="id" value={data.id} onChange={handleInputChange}/>
								</div>
								<div className="form-group">
									<input type="password" className="form-control form-control-lg"
										id="exampleInputPassword1" placeholder="Password" name="pass" value={data.pass} onChange={handleInputChange}/>
								</div>
								<div className="mt-3 mb-3">
									<button
										className="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">SIGN
										IN</button>
								</div>
								<span className="text-danger">{error}</span>
							</form>
						</div>
					</div>
				</div>
			</div>
		
		</div>
		
	</div>
    );
};

export default Login;