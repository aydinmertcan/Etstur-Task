import { useState, useEffect } from "react";
import Card from "react-bootstrap/Card";
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
            <Card style={{ width: "18rem" }}>
              <Card.Body>
                <Card.Title>Card Title</Card.Title>
                <Card.Text>
                  <Card.Img
                    variant="bottom"
                    src="https://picsum.photos/200/300?grayscale/"
                  />
                  <p>{name}</p>
                  <p>{path}</p>
                  <p>{size}</p>
                  <p>{type}</p>
                </Card.Text>
                <Button variant="primary">Go somewhere</Button>
              </Card.Body>
            </Card>
          </>
        );
      })}
    </div>
  );
}

export default Home;
