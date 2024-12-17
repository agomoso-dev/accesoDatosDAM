package ejercicio;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XML {

    public static void main(String[] args) {
        try {
            // Cargar el documento XML
            File inputFile = new File("books.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // 1. Mostrar títulos de los libros
            System.out.println("Títulos de los libros:");
            NodeList titleList = doc.getElementsByTagName("title");
            for (int i = 0; i < titleList.getLength(); i++) {
                System.out.println("- " + titleList.item(i).getTextContent());
            }

            // 2. Traducir etiquetas y crear nuevo documento
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();

            Document newDoc = impl.createDocument(null, "catalogo", null);
            newDoc.setXmlVersion("1.0");
            newDoc.setXmlStandalone(true);

            Element root = newDoc.getDocumentElement();

            NodeList bookList = doc.getElementsByTagName("book");
            for (int i = 0; i < bookList.getLength(); i++) {
                Element oldBook = (Element) bookList.item(i);
                Element newBook = newDoc.createElement("libro");

                translateElement(oldBook, newBook, newDoc, "title", "titulo");
                translateElement(oldBook, newBook, newDoc, "genre", "genero");
                translateElement(oldBook, newBook, newDoc, "price", "precio");
                translateElement(oldBook, newBook, newDoc, "publish_date", "fecha_de_publicacion");
                translateElement(oldBook, newBook, newDoc, "description", "descripcion");

                root.appendChild(newBook);
            }

            // Guardar el nuevo documento
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("libros.xml"));
            transformer.transform(source, result);

            System.out.println("\nArchivo 'libros.xml' creado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void translateElement(Element oldElement, Element newElement, Document newDoc, String oldTag, String newTag) {
        NodeList nodeList = oldElement.getElementsByTagName(oldTag);
        if (nodeList.getLength() > 0) {
            Element newChild = newDoc.createElement(newTag);
            newChild.setTextContent(nodeList.item(0).getTextContent());
            newElement.appendChild(newChild);
        }
    }
}