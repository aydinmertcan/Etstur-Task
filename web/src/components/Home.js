import { useState, useEffect } from "react";
import Accordion from "react-bootstrap/Accordion";
import Button from "react-bootstrap/Button";
import { getAllFiles, uploadFile } from "../api/ApiCalls";
import Form from "react-bootstrap/Form";

function Home() {
  const [files, setFiles] = useState([]);
  const [file, setFile] = useState("");
  const loadFiles = async () => {
    await getAllFiles().then((res) => setFiles(res.data));
  };
  const onChangeFile = (event) => {
    if (event.target.files.length < 1) {
      return;
    }
    const file = event.target.files[0];
    setFile(file);
  };

  useEffect(() => {
    loadFiles();
  }, []);

  return (
    <>
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
                <Button onClick={(e) => uploadFile(file)} variant="secondary">
                  Yükle
                </Button>
              </Form.Group>
            </form>
          </Accordion.Body>
        </Accordion.Item>
        <Accordion.Item eventKey="1">
          <Accordion.Header>
            {" "}
            <b>Dosyaları Görüntüle</b>{" "}
          </Accordion.Header>
          <Accordion.Body>
            {files.map((file, index) => {
              const { name, path, size, type } = file;
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
                              <Button variant="secondary">Update</Button>{" "}
                              <Button variant="success">Success</Button>{" "}
                              <Button variant="danger">Delete</Button>
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
