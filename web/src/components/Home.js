import { useState, useEffect } from "react";
import Card from "react-bootstrap/Card";
import Accordion from "react-bootstrap/Accordion";
import Button from "react-bootstrap/Button";
import { getAllFiles } from "../api/ApiCalls";

function Home() {
  const [files, setFiles] = useState([]);
  const loadFiles = async () => {
    await getAllFiles().then((res) => setFiles(res.data));
  };

  useEffect(() => {
    loadFiles();
  }, []);

  return (
    <div>
      {files.map((file, index) => {
        const { name, path, size, type } = file;
        return (
          <>
            <div className="container">
              <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-6">
                  <Accordion defaultActiveKey="0">
                    <Accordion.Item eventKey="0">
                      <Accordion.Header>
                        <p>
                          {" "}
                          {index + 1}) {name}
                        </p>
                      </Accordion.Header>
                      <Accordion.Body>
                        <h4>Properties</h4>
                        <p>
                          <b>path:</b> {path}
                        </p>
                        <p>
                          <b>size:</b> {size}
                        </p>
                        <p>
                          <b>type:</b> {type}
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
    </div>
  );
}

export default Home;
