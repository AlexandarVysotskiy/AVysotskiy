package xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.File;
import java.util.List;

/**
 * Класс конвертирует все данные из в БД в XML файл.
 */
public class StoreXML {
    private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);

    /**
     * Файт куда сохраняются все данные из БД.
     */
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Метод конвертирует все данные из в БД в XML файл.
     */
    public void save(List<Entry> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(list), target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}