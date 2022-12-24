package com.met622.lucene;

import com.met622.constant.Constant;
import com.met622.util.ParserUtil;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Paths;

public class LuceneWriter {
    /**
     * generates indexwriter
     * @return
     * @throws IOException
     */
    private IndexWriter createWriter() throws IOException
    {
        FSDirectory dir = FSDirectory.open(Paths.get(Constant.INDEX_DIR).toFile());
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40,new StandardAnalyzer(Version.LUCENE_40));
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    /**
     * point of execution
     * @param totalDocs
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void execute(int totalDocs) throws IOException, ParserConfigurationException, SAXException {
        IndexWriter writer = createWriter();
        writer.deleteAll();
        new ParserUtil().xmlIndexWriter(writer, totalDocs);
        writer.commit();
        writer.close();
    }
}
