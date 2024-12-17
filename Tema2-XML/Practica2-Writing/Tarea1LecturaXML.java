/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea1lecturaxml;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;
public class Tarea1LecturaXML {

    private static final String INDENT_CHAR = " ";

    public static void muestraNodo(Node nodo, int level, PrintStream ps) {
        // Imprimir indentación
        ps.print(INDENT_CHAR.repeat(level * 2));

        // Imprimir información del nodo
        ps.print("Tipo: " + nodo.getNodeType());
        ps.print(", Nombre: " + nodo.getNodeName());

        if (nodo.getNodeValue() != null && !nodo.getNodeValue().trim().isEmpty()) {
            ps.print(", Valor: " + nodo.getNodeValue().trim());
        }

        ps.println();

        // Si es el nodo documento, imprimir información adicional
        if (nodo.getNodeType() == Node.DOCUMENT_NODE) {
            Document doc = (Document) nodo;
            ps.println("Codificación XML: " + doc.getXmlEncoding());
            ps.println("Versión XML: " + doc.getXmlVersion());
        }

        // Imprimir atributos si los hay
        NamedNodeMap attrs = nodo.getAttributes();
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node attr = attrs.item(i);
                ps.print(INDENT_CHAR.repeat((level + 1) * 2));
                ps.println("Atributo: " + attr.getNodeName() + " = " + attr.getNodeValue());
            }
        }

        // Procesar nodos hijos recursivamente
        NodeList hijos = nodo.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            muestraNodo(hijos.item(i), level + 1, ps);
        }
    }

    public static void main(String[] args) {
        String nomFich = args[0];

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(nomFich));
            muestraNodo(doc, 0, System.out);
        } catch (FileNotFoundException | ParserConfigurationException | SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}