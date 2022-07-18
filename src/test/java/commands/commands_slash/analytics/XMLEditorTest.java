package commands.commands_slash.analytics;

import cz.krystofcejchan.bin.analyticsXML.EditorXML;
import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class XMLEditorTest {

    @org.junit.jupiter.api.Test
    void assertElementIsPresentInXML() throws IOException, ParserConfigurationException, SAXException {
        Assertions.assertTrue(new EditorXML().isElementPresentInXML("commands"));
      //  Assertions.assertTrue(new EditorXML().isElementPresentInXML("cmd1"));
    }
}