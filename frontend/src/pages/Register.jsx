import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [error, setError] = useState(false);
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        dob: '',
        address: '',
        phone: '',
        gender: true,
        email: '',
        password: '',
    });

    const handleChange = (e) => {
        setFormData({ 
        ...formData, 
        [e.target.name]: e.target.value 
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const newErrors = {};

        if (!formData.name) {
            newErrors.name = 'Tên không được để trống';
        }

        const currentDate = new Date();
        const Dob = new Date(formData.dob)
        if (Dob >= currentDate) {
            newErrors.dob = 'Ngày Sinh Phải Ở Quá Khứ';
        }

        if (!formData.address) {
            newErrors.address = 'Địa chỉ không được để trống';
        }

        if (!/^[0-9]{10}$/.test(formData.phone)) {
            newErrors.phone = 'Số điện thoại phải có 10 số';
        }

        if (!/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Z|a-z]{2,}$/.test(formData.email)) {
            newErrors.email = 'Email không hợp lệ';
        } else if (formData.email.length < 10 || formData.email.length > 50) {
            newErrors.email = 'Email phải từ 10 đến 50 ký tự';
        }

        if (!formData.password) {
            newErrors.password = 'Mật khẩu không được để trống';
        }

        setError(newErrors);

        if (Object.keys(newErrors).length === 0) {
            console.log('Form submitted:', formData);
            try {
                const response = await fetch('http://localhost:8080/api/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData),
                });
                if (response.ok) {
                    navigate("/login");
                    console.log('Register successful');
                } else {
                    setError(true);
                    console.error('Register failed');
                } 
            } catch (error) {
                setError(true);
                console.error('Error:', error);
            }
        }

    }

    return (
      <section className="signin-page account">
        <div className="container">
            <div className="row">
            <div className="col-md-6 col-md-offset-3">
                <div className="block text-center">
                <a className="logo" href="/">
                    <img src="/assets/images/logo.png" alt="Logo" />
                </a>
                <h2 className="text-center">Tạo Tài Khoản Mới</h2>
                <form className="text-left clearfix" onSubmit={handleSubmit}>
                    <div className="form-group">
                    <input type="text" className="form-control" name="name" placeholder="Tên" value={formData.name} onChange={handleChange}/>
                    {error.name && <div className="text-danger">{error.name}</div>}
                    </div>
                    <div className="form-group">
                    <input type="date" className="form-control" name="dob" placeholder="Ngày Sinh" value={formData.dob} onChange={handleChange}/>
                    {error.dob && <div className="text-danger">{error.dob}</div>}
                    </div>
                    <div className="form-group">
                    <input type="text" className="form-control" name="address" placeholder="Địa Chỉ" value={formData.address} onChange={handleChange}/>
                    {error.address && <div className="text-danger">{error.address}</div>}
                    </div>
                    <div className="form-group">
                    <input type="text" className="form-control" name="phone" placeholder="Số Điện Thoại" value={formData.phone} onChange={handleChange}/>
                    {error.phone && <div className="text-danger">{error.phone}</div>}
                    </div>
                    <div className="form-group">
                    <label>Giới tính</label>
                    <select className="form-control btn btn-outline-primary dropdown-toggle" name="gender" value={formData.gender} onChange={handleChange}>
                        <option value="true">Nam</option>
                        <option value="false">Nữ</option>
                    </select>
                    </div>
                    <div className="form-group">
                    <input type="email" className="form-control" name="email" placeholder="Email" value={formData.email} onChange={handleChange}/>
                    {error.email && <div className="text-danger">{error.email}</div>}
                    </div>
                    <div className="form-group">
                    <input type="password" className="form-control" name="password" placeholder="Mật Khẩu" value={formData.password} onChange={handleChange}/>
                    </div>
                    {error.password && <div className="text-danger">{error.password}</div>}
                    <div className="text-center">
                    <button type="submit" className="btn btn-main text-center">Đăng Ký</button>
                    </div>
                </form>
                <p className="mt-20">Đã có tài khoản ?<a href="/login"> Đăng Nhập </a></p>
                <p><a href="/forget"> Quên Mật Khẩu?</a></p>
                </div>
            </div>
            </div>
        </div>
      </section>
    );
};

export default Register;