import React, { useState } from "react";

const Forget = () => {
    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [formData, setFormData] = useState({
        email: '',
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = formData.email;
        console.log(data);
        if (data != "") {
            try {
                const response = await fetch('http://localhost:8080/api/forget', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData),
                });

                if (response.ok) {
                    setSuccessMessage('Một Mail Chứa Mật Khẩu Mới Của Bạn Đã Được Gửi');
                    setErrorMessage('');
                    console.log('Send email succesful')
                } else {
                    setSuccessMessage('');
                    setErrorMessage('Email Không Tồn Tại Với Tài Khoản Nào');
                    console.log('Email is incorrect')
                }
            } catch (error) {
                console.error('Error sending email:', error);
                setSuccessMessage('');
                setErrorMessage('Không thể gửi email. Vui lòng thử lại');
            }
        }
    };

    return (
        <section className="forget-password-page account">
        <div className="container">
            <div className="row">
            <div className="col-md-6 col-md-offset-3">
                <div className="block text-center">
                <a className="logo" href="/">
                    <img src="/assets/images/logo.png" alt="Logo" />
                </a>
                <h2 className="text-center">Chào Mừng Trở Lại</h2>
                <form className="text-left clearfix" onSubmit={handleSubmit}>
                    <p>
                    Hãy nhập vào địa chỉ email của tài khoản bạn. Một mã số xác thực sẽ được gửi đến bạn. Một khi bạn nhận được mã số xác thực, bạn sẽ có thể tạo một mật khẩu mới cho tài khoản của bạn.
                    </p>
                    <div className="form-group">
                    <input type="email" className="form-control" name="email" placeholder="Email Của Tài Khoản" value={formData.email} onChange={handleChange}/>
                    </div>
                    <div className="text-center">
                    <button type="submit" className="btn btn-main text-center">Đặt Lại Mật Khẩu</button>
                    </div>
                    {successMessage && (
                        <div className="alert alert-success" role="alert">
                            <p>{successMessage}</p>
                        </div>
                    )}
                    {errorMessage && (
                        <div className="alert alert-danger" role="alert">
                            <p>{errorMessage}</p>
                        </div>
                    )}
                </form>
                <p className="mt-20"><a href="/login">Quay Trở Về Đăng Nhập</a></p>
                </div>
            </div>
            </div>
        </div>
        </section>
    );
};

export default Forget;