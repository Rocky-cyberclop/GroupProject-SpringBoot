import React from "react";
import { useState, useEffect } from "react";

function Account(){

	const [user, setUser] = useState({
		name:'',
		email:'',
		phone:'',
		address:'',
		dob:'',
		gender:true
	})
	const token = localStorage.getItem('token');

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

    return (
        <section className="user-dashboard page-wrapper">
		<div className="container">
			<div className="row">
				<div className="col-md-12">
					<ul className="list-inline dashboard-menu text-center">
						<li><a href="/account/order">Đơn đặt hàng</a></li>
						<li><a className="active" href="/account">Chi Tiết hồ sơ</a></li>
					</ul>
					<div className="dashboard-wrapper dashboard-user-profile">
            <div className="media">
              <div className="pull-left text-center" href="#!">
                <img className="media-object user-img" src="/assets/images/channels4_profile.jpg" alt="Hình ảnh"/>
              </div>
              <div className="media-body">
                <ul className="user-profile-list">
                  <li>
                    <span><b>Họ và tên:</b></span>
                    <p style={{display: "inline-block"}} >{user.name}</p>
                  </li>
                  <li>
                    <span><b>Email:</b></span>
                    <p style={{display: "inline-block"}} >{user.email}</p>
                  </li>
                  <li>
                    <span><b>Số điện thoại:</b></span>
                    <p style={{display: "inline-block"}} >{user.phone}</p>
                  </li>

                  <li>
                    <span><b>Địa chỉ:</b></span>
                    <p style={{display: "inline-block"}} >{user.address}</p>
                  </li>
                  <li>
                    <span><b>Ngày sinh:</b></span>
                    <p style={{display: "inline-block"}} >{user.dob}</p>
                  </li>

                  <li>
                    <span><b>Giới tính:</b></span>
                    {user.gender&&<p style={{display: "inline-block"}}>Nam</p>}
                    {!user.gender&&<p style={{display: "inline-block"}}>Nữ</p>}
                  </li>
                  <br/>
                  <li>
                    <a href="/account/update"> <i className="fa-solid fa-user-pen" style={{color: "#005eff"}}></i>
                      <p style={{display: "inline-block"}} className="text-muted">Chỉnh sửa thông tin</p>
                    </a>
                 
                  </li>

                </ul>
              </div>
            </div>
          </div>
				</div>
			</div>
		</div>
	</section>
    )
}

export default Account;