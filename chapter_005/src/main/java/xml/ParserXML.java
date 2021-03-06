package xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс  парсит файл из xml.
 */
public class ParserXML extends DefaultHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ParserXML.class);
    private static final String TAG_ENTRY = "entry";
    private List<Entry> list = new LinkedList<>();

    public List<Entry> parse(String path) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            Handler handler = new Handler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(path);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        } catch (SAXException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    private class Handler extends DefaultHandler {

        @Override
        public void startElement(String url, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(TAG_ENTRY)) {
                list.add(new Entry(
                        Integer.parseInt(attributes.getValue("href"))
                ));
            }
        }
    }
}