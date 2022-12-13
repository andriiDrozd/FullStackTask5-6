package task1;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Reading XML File and collecting information to map
 */
public class StaxParser {

    private String type = null;

    public Map<String, Double> parse(String fileName) throws FileNotFoundException, XMLStreamException {
        Map<String, Double> map=new HashMap<>();
        XMLStreamReader xmlReader = XMLInputFactory.newInstance()
                .createXMLStreamReader(fileName, new FileInputStream(fileName));

        while (xmlReader.hasNext()) {
            xmlReader.next();

            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals("type")) {
                    xmlReader.next();
                    type = xmlReader.getText();
                } else if (xmlReader.getLocalName().equals("fine_amount")) {
                    xmlReader.next();
                    double value = Double.parseDouble(xmlReader.getText());

                    if (map.containsKey(type)) {
                        double val = map.get(type);
                        val = val + value;
                        map.put(type, val);
                    } else {
                        map.put(type, value);
                    }
                }
            }
        }
        xmlReader.close();

        return map;
    }
}

