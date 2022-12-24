package com.met622.lucene;

import com.met622.constant.Constant;
import com.met622.model.Pair;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Lucene Index Reader handler class
 */
public class LuceneReader {
    /**
     * generates a IndexSearcher object pointing to the constant dir
     * @return
     * @throws IOException
     */
    private IndexSearcher createSearcher() throws IOException
    {
        Directory dir = FSDirectory.open(Paths.get(Constant.INDEX_DIR).toFile());
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }

    /**
     * Search docs
     * @param id
     * @param searcher
     * @return
     * @throws Exception
     */
    private TopDocs searchByTitle(String id, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser(Version.LUCENE_40,"title", new StandardAnalyzer(Version.LUCENE_40));
        Query idQuery = qp.parse(id.toString());
        TopDocs hits = searcher.search(idQuery, 10);
        return hits;
    }

    /**
     * point of execution
     * @param wordList
     * @return
     * @throws Exception
     */
    public Pair<Integer, Double> execute(List<String> wordList) throws Exception {
        int totalDocumentsFound = 0;
        long start = System.currentTimeMillis();
        IndexSearcher searcher = createSearcher();
        for(String temp : wordList){
            TopDocs foundDocs = searchByTitle(temp, searcher);
            System.out.println("Total Results for keyword "+temp+":: " + foundDocs.totalHits);
            totalDocumentsFound += foundDocs.totalHits;
        }
        double timeTaken = (System.currentTimeMillis() - start)/1000f;

        return new Pair<Integer, Double>(totalDocumentsFound, timeTaken);
    }
}
