package commands.commands_slash.analytics;

import cz.krystofcejchan.commands.commands_slash.analytics.EditorXML;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

class DefaultXMLTest extends EditorXML {

    /**
     * @throws IOException                  no such xml file located in the path
     * @throws SAXException                 Encapsulate a general SAX error or warning.
     *                                      This class can contain basic error or warning information from either the XML parser or the application: a parser writer or application writer can subclass it to provide additional functionality. SAX handlers may throw this exception or any exception subclassed from it.
     *                                      If the application needs to pass through other types of exceptions, it must wrap those exceptions in a SAXException or an exception derived from a SAXException.
     *                                      If the parser or application needs to include information about a specific location in an XML document, it should use the SAXParseException subclass.
     * @throws ParserConfigurationException xml was not parsed successfully
     */
    public DefaultXMLTest() throws IOException, SAXException, ParserConfigurationException {
    }

    @Test
    public void begin() {
        super.begin();
        try {
            Assertions.assertEquals(Objects.requireNonNull(readTXT()).toLowerCase(),
                    Objects.requireNonNull(readXML()).toLowerCase());
        } catch (TransformerConfigurationException transformerConfigurationException) {
            transformerConfigurationException.printStackTrace();
        }
    }

    private @org.jetbrains.annotations.Nullable String readXML() throws TransformerConfigurationException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }


    private @Nullable
    String readTXT() {
        try {
            FileReader fr = new FileReader(
                    "src/main/java/commands/commands_slash/analytics/spareXML");
            StringBuilder txt = new StringBuilder();
            int i;
            while ((i = fr.read()) != -1)
                txt.append((char) i);
            return txt.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}