import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function AccountUpdate(){
    const [user, setUser] = useState({
		name:'',
		phone:'',
		address:'',
		dob:'',
		gender:true
	})
    const navigate = useNavigate();

    const token = localStorage.getItem("token")

    useEffect(() => {
        fetch("http://localhost:8080/api/account",{
            headers:{
                'Authorization': 'Bearer ' + token
            }
        })
            .then((response) => response.json())
            .then((data) => {
				console.log(data)
                setUser(data)
            })
            .catch((error) => console.error(error));
    }, []);

    const handleInputChange = (e) => {
		const { name, value } = e.target;
		setUser({ ...user, [name]: value });
	};
    
    const userSubmit = async (e)=>{
        e.preventDefault();
        try{
			const response = await fetch('http://localhost:8080/api/account', {
				method: 'PUT',
				headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + token
				// Add any other headers if needed
				},
				body: JSON.stringify(user),
			});
			if(response.ok){
				navigate('/account');
			}
		}
		catch (error) {
			console.error('An error occurred during form submission:', error);
		}
    }

    return(
        <section className="signin-page account">
        <div className="container">
        <div className="row">
            <div className="col-md-6 col-md-offset-3">
            <div className="block text-center">

                <h2 className="text-center">Cập nhật tài khoản</h2>

                <form className="text-left clearfix" onSubmit={userSubmit}>
                <div className="form-group">
                    <input type="text" name="name" value={user.name} className="form-control" 
                    onChange={handleInputChange}
                    placeholder="Họ và tên"
                    required/>
                </div>
                <div className="form-group">
                    <input type="text" name="phone" value={user.phone} className="form-control"
                    placeholder="Số điện thoại" onChange={handleInputChange} required/>
                </div>
                <div className="form-group">
                    <input type="date" name="dob" value={user.dob} className="form-control" 
                    onChange={handleInputChange}
                    placeholder="Nhập ngày tháng"
                    required/>

                </div>
                <div className="form-group">
                    <input type="text" name="address" value={user.address} className="form-control" 
                    onChange={handleInputChange}
                    placeholder="Địa chỉ"
                    required/>
                </div>
                <div className="form-group">
                    
                    <select className="form-control btn btn-outline-primary dropdown-toggle" name='gender' 
											value={user.gender} onChange={handleInputChange}>
												<option className="dropdown-item" key="1" value="true">Nam</option>
												<option className="dropdown-item" key="0" value="false">Nữ</option>
											</select>
                
                    
                </div>
                <div className="form-group">
                    <div style={{display: "flex", justifyContent: "space-between"}}>
                    <div className="text-center">
                        <button type="submit" className="btn btn-primary text-center">Lưu</button>
                        </div>
                        <div className="text-center">
                        <a href="/account"
                            className="btn btn-warning text-center">Hủy</a>
                        </div>
                    </div>
                    
                </div>
                </form>

            </div>
            </div>
        </div>
        </div>
        
  </section>
    )
}
export default AccountUpdate;