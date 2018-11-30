package xml;

import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StoreSQLTest {
    private String pathSource = "src/main/resources/source.xml";
    private String pathDest = "src/main/resources/dest.xml";
    private String pathScheme = "src/main/resources/scheme.xsl";
    private final int entries = 1_000_000;

    @Test
    public void test() throws TransformerException {
        try (StoreSQL sql = new StoreSQL(new Config())) {
            ArrayList<Entry> temp = new ArrayList<>(entries);
            for (int i = 0; i < entries; i++) {
                temp.add(new Entry(i + 1));
            }
            Entry[] expected = new Entry[this.entries];
            expected = temp.toArray(expected);
            sql.generate(this.entries);
            List<Entry> listEntry = sql.getListEntry();
            assertThat(listEntry.toArray(), is(expected));
            File source = new File(pathSource);
            StoreXML storeXML = new StoreXML(source);
            storeXML.save(listEntry);
            ConvertXSQT xsqt = new ConvertXSQT();
            xsqt.convert(source, new File(pathDest), new File(pathScheme));
            List<Entry> list = new ParserXML().parse(pathDest);
            int sum = 0;
            for (Entry i : list) {
                sum = i.getValue() + i.getValue();
            }
            assertThat(sum, is(2_000_000));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}