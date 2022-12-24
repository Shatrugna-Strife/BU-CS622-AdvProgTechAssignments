package com.met622.util;

import com.met622.Main;
import com.met622.constant.Constant;
import com.met622.model.Pair;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * An util class to have generalised methods
 */
public class ParserUtil{
    private org.w3c.dom.Document doc;
    public ParserUtil() throws IOException, SAXException, ParserConfigurationException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(Constant.XML_FILE_NAME);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(is);
        doc.getDocumentElement().normalize();
    }

    /**
     * Method to parse the xml and index the data by lucene index writer
     * @param writer
     * @param totalDocs
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void xmlIndexWriter(IndexWriter writer, int totalDocs) throws ParserConfigurationException, IOException, SAXException {
        NodeList flowList = doc.getDocumentElement().getChildNodes();
        int track = 0;
        for (int i = 0; i < flowList.getLength() && track < totalDocs; i++) {
            Node parent = flowList.item(i);
            if(parent.getNodeName().equals("article")) {
                track++;
                Document document = new Document();
                NodeList childList = flowList.item(i).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if(childNode.getNodeType() == Node.ELEMENT_NODE){
                        document.add(new TextField(childNode.getNodeName(), childNode.getTextContent() , Field.Store.YES));
                    }
                }
                writer.addDocument(document);
            }
        }
    }

    /**
     * Brute Force scan of the xml data
     * @param totalDocs
     * @param worldListPara
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Pair<Integer, Double> bruteForceScan(int totalDocs, List<String> worldListPara) throws ParserConfigurationException, IOException, SAXException {

        List<String> wordList = divideSubstringList(worldListPara);

        long start = System.currentTimeMillis();

//        InputStream is = Main.class.getClassLoader().getResourceAsStream(Constant.XML_FILE_NAME);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        org.w3c.dom.Document doc = builder.parse(is);
//        doc.getDocumentElement().normalize();

        int totalDocumentFound = 0;

        NodeList flowList = doc.getDocumentElement().getChildNodes();
        int track = 0;
        for (int i = 0; i < flowList.getLength() && track<totalDocs; i++) {
            Node parent = flowList.item(i);
            if(parent.getNodeName().equals("article")) {
                track++;
//                Document document = new Document();

                NodeList childList = flowList.item(i).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if(childNode.getNodeType() == Node.ELEMENT_NODE){
                        if(childNode.getNodeName().equals("title")){
                            String temp = childNode.getTextContent().toLowerCase(Locale.ROOT);
                            for(String tmp : wordList){
                                if(temp.contains(tmp)){
                                    totalDocumentFound++;
                                }
                            }
                        }
//                        document.add(new TextField(childNode.getNodeName(), childNode.getTextContent() , Field.Store.YES));
                    }
                }

            }
        }
        double timeTaken = (System.currentTimeMillis() - start)/1000f;
        return new Pair<Integer, Double>(totalDocumentFound, timeTaken);
    }

    /**
     * A simple method to break the strings within the list to smaller strings.
      * @param wordList
     * @return
     */
    private List<String> divideSubstringList(List<String> wordList){
        List<String> result = new ArrayList<>();
        for(String tmp:wordList){
            String[] temp = tmp.split(" ");
            for(String i : temp){
                String tmp1 = i.toLowerCase(Locale.ROOT);
                if(!tmp1.equals("")) {
                    result.add(tmp1);
                }
            }
        }
        return result;
    }
}
