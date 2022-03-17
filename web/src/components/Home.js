import { useState, useEffect } from "react";
import Accordion from "react-bootstrap/Accordion";
import Button from "react-bootstrap/Button";
import {
  deleteFile,
  getAllFiles,
  uploadFile,
  updateFile,
} from "../api/ApiCalls";
import Form from "react-bootstrap/Form";

function Home() {
  const [files, setFiles] = useState([]);
  const [file, setFile] = useState("");
  const [error, setError] = useState("");
  let response;

  const loadFiles = async () => {
    await getAllFiles().then((res) => setFiles(res.data));
  };

  useEffect(() => {
    loadFiles();
  }, [response]);
  const onChangeFile = (event) => {
    if (event.target.files.length < 1) {
      return;
    }
    const file = event.target.files[0];
    setFile(file);
  };

  const uploadBtnClick = async (e) => {
    try {
      response = await uploadFile(file);
      console.log(response);
    } catch (error) {
      console.log(error.response);
    }
  };

  const deleteBtnClick = async (e) => {
    const { name } = e.target;
    response = name;
    await deleteFile(name);
  };

  const updateBtnClick = async (e) => {
    const { name } = e.target;
    try {
      response = await updateFile(name, file);
      console.log(response);
    } catch (error) {
      console.log(error.response);
    }
  };

  return (
    <>
      <div className="float-end mb-3 mt-2">
        <a href="https://etstur-task.herokuapp.com/">Heroku App</a>
        <a
          className="ms-2 me-2"
          href="https://etstur-task.herokuapp.com/swagger-ui/index.html"
        >
          Heroku Swagger App
        </a>
        <a
          className="me-2"
          href="/"
          onClick={(e) => localStorage.removeItem("access_token")}
        >
          Çıkış yap
        </a>
      </div>
      <Accordion defaultActiveKey="0">
        <Accordion.Item eventKey="0">
          <Accordion.Header>
            {" "}
            <b>Dosya Ekle</b>{" "}
          </Accordion.Header>
          <Accordion.Body>
            <form>
              <Form.Group controlId="formFile" className="mb-3">
                <Form.Label>
                  Lütfen eklemek istediğiniz dosyayı seçin
                </Form.Label>
                <Form.Control type="file" onChange={onChangeFile} />
                <Button onClick={uploadBtnClick} variant="secondary">
                  Yükle
                </Button>
              </Form.Group>
            </form>
          </Accordion.Body>
        </Accordion.Item>
        <Accordion.Item eventKey="1" onClick={() => loadFiles()}>
          <Accordion.Header>
            {" "}
            <b>Dosyaları Görüntüle</b>{" "}
          </Accordion.Header>
          <Accordion.Body>
            {files.map((file, index) => {
              const { id, name, path, size, type } = file;
              return (
                <>
                  <div className="container">
                    <div className="row">
                      <div className="col-md-3"></div>
                      <div className="col-md-6">
                        <Accordion defaultActiveKey="0">
                          <Accordion.Item eventKey="1">
                            <Accordion.Header>
                              <p>
                                {" "}
                                {index + 1}) {name}
                              </p>
                            </Accordion.Header>
                            <Accordion.Body>
                              <h4>Özellikler</h4>
                              <p>
                                <b>Path:</b> {path}
                              </p>
                              <p>
                                <b>Boyut:</b> {size}
                              </p>
                              <p>
                                <b>Uzantı:</b> {type}
                              </p>
                              <Button
                                onClick={deleteBtnClick}
                                name={id}
                                variant="danger"
                              >
                                Sil
                              </Button>{" "}
                              <br /> <br />
                              <Form.Control
                                type="file"
                                onChange={onChangeFile}
                              />{" "}
                              <Button
                                name={id}
                                onClick={updateBtnClick}
                                variant="success"
                              >
                                Güncelle
                              </Button>
                            </Accordion.Body>
                          </Accordion.Item>
                        </Accordion>
                      </div>
                    </div>
                  </div>
                </>
              );
            })}
          </Accordion.Body>
        </Accordion.Item>
      </Accordion>
    </>
  );
}

export default Home;
