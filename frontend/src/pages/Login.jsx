import React, { useState } from 'react';

const Login = () => {
  const [error, setError] = useState(false);
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const handleInputChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      }).then(response=>response.json()).then(data=>{
        console.log(data.jwt)
      });
  
      if (response.ok) {
        // Successful login, you can redirect or perform other actions here
        console.log('Login successful');
      } else {
        // Unsuccessful login
        setError(true);
        console.error('Login failed');
      }
    } catch (error) {
      setError(true);
      console.error('Error:', error);
    }
  };
  
    return (
      <section className="signin-page account">
        <div className="container">
        <div className="row">
          <div className="col-md-6 col-md-offset-3">
            <div className="block text-center">
              <a className="logo" href="/">
                <img src="/assets/images/logo.png" alt="Logo" />
              </a>
              <h2 className="text-center">Chào Mừng Bạn </h2>
              <form className="text-left clearfix" onSubmit={handleSubmit}>
                <div className="form-group">
                  <input type="text" className="form-control" placeholder="Email" name="email" value={formData.email} onChange={handleInputChange}/>
                </div>
                <div className="form-group">
                  <input type="password" className="form-control" placeholder="Mật Khẩu" name="password" value={formData.password} onChange={handleInputChange} />
                </div>
                <div className="text-center">
                  <button type="submit" className="btn btn-main text-center">
                    Đăng Nhập
                  </button>
                </div>
                <span className={error ? 'text-danger' : 'hidden'}>
                Bạn đã nhập sai email hoặc mật khẩu
              </span>
              </form>
              <p className="mt-20">
                Lần đầu vào trang ?<a href="/register"> Tạo Tài Khoản Mới</a>
              </p>
              <p>
                <a href="/forget"> Quên Mật Khẩu?</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    );
};

export default Login;