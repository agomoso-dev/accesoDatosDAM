package tarea3Xpath;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * This class demonstrates the use of XPath to query an XML document about ships.
 * It performs various queries on the XML data and prints the results.
 */
public class Tarea3 {

    /**
     * The main method that executes all XPath queries.
     *
     * @param args Command line arguments (not used)
     * @throws Exception If there's an error parsing the XML or executing XPath queries
     */
    public static void main(String[] args) throws Exception {
        // Create a new DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // Enable namespace awareness

        // Create a DocumentBuilder
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file
        Document doc = builder.parse("barcos.xml");

        // Create XPath
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();

        // Execute queries
        getBarcosPost2000(doc, xpath);
        getBarcoMasLargo(doc, xpath);
        getCantidadCruceros(doc, xpath);
        getBarcosConstruidosPorSTX(doc, xpath);
        getBarcosTonelajeSuperior200000(doc, xpath);
    }

    /**
     * Retrieves and prints the names of ships built after the year 2000.
     *
     * @param doc The XML document
     * @param xpath The XPath object
     * @throws Exception If there's an error executing the XPath query
     */
    private static void getBarcosPost2000(Document doc, XPath xpath) throws Exception {
        System.out.println("1) Barcos construidos después del año 2000:");
        XPathExpression expr = xpath.compile("//barco[año>2000]/nombre/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
        System.out.println();
    }

    /**
     * Retrieves and prints the name of the longest ship.
     *
     * @param doc The XML document
     * @param xpath The XPath object
     * @throws Exception If there's an error executing the XPath query
     */
    private static void getBarcoMasLargo(Document doc, XPath xpath) throws Exception {
        System.out.println("2) Barco más largo:");
        XPathExpression expr = xpath.compile("//barco[eslora = //barco/eslora[not(. < //barco/eslora)]]/nombre/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
        System.out.println();
    }

    /**
     * Counts and prints the number of ships of type "Crucero" (Cruise).
     *
     * @param doc The XML document
     * @param xpath The XPath object
     * @throws Exception If there's an error executing the XPath query
     */
    private static void getCantidadCruceros(Document doc, XPath xpath) throws Exception {
        System.out.println("3) Cantidad de barcos tipo Crucero:");
        XPathExpression expr = xpath.compile("count(//barco[tipo='Crucero'])");
        Object result = expr.evaluate(doc, XPathConstants.NUMBER);
        Double cruceros = (Double) result;
        System.out.println(cruceros.intValue());
        System.out.println();
    }

    /**
     * Retrieves and prints the names of ships built by 'STX Europe'.
     *
     * @param doc The XML document
     * @param xpath The XPath object
     * @throws Exception If there's an error executing the XPath query
     */
    private static void getBarcosConstruidosPorSTX(Document doc, XPath xpath) throws Exception {
        System.out.println("4) Barcos construidos por 'STX Europe':");
        XPathExpression expr = xpath.compile("//barco[constructor='STX Europe']/nombre/text()");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println(nodes.item(i).getNodeValue());
        }
        System.out.println();
    }

    /**
     * Retrieves and prints the name and type of ships with a tonnage greater than 200,000.
     *
     * @param doc The XML document
     * @param xpath The XPath object
     * @throws Exception If there's an error executing the XPath query
     */
    private static void getBarcosTonelajeSuperior200000(Document doc, XPath xpath) throws Exception {
        System.out.println("5) Barcos con tonelaje superior a 200,000:");
        XPathExpression expr = xpath.compile("//barco[tonelaje>200000]");
        Object result = expr.evaluate(doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            System.out.println("Nombre: " + nodes.item(i).getChildNodes().item(1).getTextContent());
            System.out.println("Tipo: " + nodes.item(i).getChildNodes().item(7).getTextContent());
        }
        System.out.println();
    }
}