package cz.krystofcejchan.commands.commands_slash.analytics;

import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DefaultXML {

    private StreamResult streamResultOut;
    private TransformerHandler tHandler;

    public void begin() {
        try {
            BufferedReader bufferedReaderIn = new BufferedReader(
                    new FileReader("src/main/java/commands/commands_slash/analytics/spareXML"));
            streamResultOut = new StreamResult(
                    new File("src/main/java/commands/commands_slash/analytics/analyticsforslashcommands.xml"));
            openXml();
            String str;
              /*  while ((str = bufferedReaderIn.readLine()) != null) {
                    process(str);
                }*/
            bufferedReaderIn.close();
            closeXml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openXml() throws SAXException, TransformerConfigurationException {

        SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        tHandler = tf.newTransformerHandler();

        Transformer serializer = tHandler.getTransformer();
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");

        tHandler.setResult(streamResultOut);
        tHandler.startDocument();
        tHandler.startElement(null, null, "commands", null);
    }


    public void closeXml() throws SAXException {
        tHandler.endElement(null, null, "commands");
        tHandler.endDocument();
    }
}

