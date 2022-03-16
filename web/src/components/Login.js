import { useState } from "react";
import "../App.css";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useNavigate } from "react-router-dom";
import { getToken, setAuthorizationHeader } from "../api/ApiCalls";
import Alert from "react-bootstrap/Alert";
function Login() {
  let navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [error, setError] = useState();
  const [isLogin, setIsLogin] = useState(false);

  const submission = async (e) => {
    e.preventDefault();
    try {
      const response = await getToken(username);
      response.data && setAuthorizationHeader(response.data);
      navigate("/home");
    } catch (error) {
      console.log(error.response);
      setError(error.response.data);
    }
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-3"></div>
        <div className="col-md-6 mt-5 loginborder shadow-lg">
          <h3>Log in</h3>
          <form onSubmit={(e) => submission(e)}>
            <Form.Floating className="mb-3">
              <Form.Control
                id="floatingInputCustom"
                type="username"
                name="username"
                placeholder="username"
                onChange={(e) => setUsername(e.target.value)}
              />
              <label htmlFor="floatingInputCus">Username</label>
            </Form.Floating>

            <div className="d-grid mt-2 gap-2 ">
              <Button variant="primary" type="submit" size="md">
                Login
              </Button>
            </div>
            {error && (
              <Alert
                variant="danger"
                onClose={() => setError(undefined)}
                dismissible
                className="mt-3"
              >
                <Alert.Heading>Bir hatayla karşılaştık</Alert.Heading>
                <p>
                  {error.validationErrors
                    ? error.validationErrors.username
                    : error.message}
                </p>
              </Alert>
            )}
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
