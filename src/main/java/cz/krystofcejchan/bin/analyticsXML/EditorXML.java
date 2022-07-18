package cz.krystofcejchan.bin.analyticsXML;

import cz.krystofcejchan.enums_annotations_exceptions.exceptions.InvalidXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.CheckForNull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class EditorXML extends DefaultXML {
    protected final Document document;

    /**
     * @throws IOException                  no such xml file located in the path
     * @throws SAXException                 Encapsulate a general SAX error or warning.
     *                                      This class can contain basic error or warning information from either the XML parser or the application: a parser writer or application writer can subclass it to provide additional functionality. SAX handlers may throw this exception or any exception subclassed from it.
     *                                      If the application needs to pass through other types of exceptions, it must wrap those exceptions in a SAXException or an exception derived from a SAXException.
     *                                      If the parser or application needs to include information about a specific location in an XML document, it should use the SAXParseException subclass.
     * @throws ParserConfigurationException xml was not parsed successfully
     */
    public EditorXML() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File XMLFILE = new File("src/main/java/commands/commands_slash/analytics/analyticsforslashcommands.xml");
        document = db.parse(XMLFILE);
    }

    public void AddElement(@CheckForNull String parentElement, String attribute) {
        NodeList parentList = document.getElementsByTagName("commands");
        Node parent = parentList.item(0);

        Element child = document.createElement("command");
        child.setAttribute("name", attribute);
        child.appendChild(document.createTextNode("0"));
        parent.appendChild(child);
    }

    @SuppressWarnings("unused")
    public void AddFirstElement() {
        super.begin();
    }

    /**
     * adds necessary elements if not present
     */
    public void setUp() throws InvalidXML {

        document.getDocumentElement().normalize();
        System.out.println("Root element: " + document.getDocumentElement().getNodeName());
        if (!isElementPresentInXML("commands"))
            throw new InvalidXML();

        NodeList nodeList = document.getElementsByTagName("commands");

        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
//foreach command add element
            }
        }
    }

    public boolean isElementPresentInXML(String elementName) {
        return document.getElementsByTagName(elementName).getLength() > 0;
    }


}


