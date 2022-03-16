import { useEffect, useState } from "react";
import "../App.css";
import { useFormik } from "formik";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function Login() {
  const formik = useFormik({
    initialValues: {
      username: "",
    },
    onSubmit: (values) => {
      console.log(values);
    },
  });

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-3"></div>
        <div className="col-md-6 mt-5 loginborder shadow-lg">
          <form onSubmit={formik.handleSubmit}>
            <h3>Log in</h3>

            <Form.Floating className="mb-3">
              <Form.Control
                id="floatingInputCustom"
                type="username"
                name="username"
                value={formik.values.email}
                placeholder="username"
                onChange={formik.handleChange}
              />
              <label htmlFor="floatingInputCus">Username</label>
            </Form.Floating>

            <div className="d-grid mt-2 gap-2 ">
              <Button variant="primary" type="submit" size="md">
                Login
              </Button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
