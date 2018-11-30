package xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Класс конвертирует XML файл в XSTL файл.
 */

public class ConvertXSQT {

    /**
     * Метод конвертирует XML файл в XSTL файл.
     * @param source исходный файл формата XML.
     * @param dest результата работы метода файл XSTL.
     * @param scheme XSTL схема, по которой происходит приеобразование.
     * @throws TransformerException
     */
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
    }
}