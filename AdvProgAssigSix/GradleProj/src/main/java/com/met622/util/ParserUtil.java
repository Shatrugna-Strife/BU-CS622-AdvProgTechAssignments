package com.met622.util;

import com.met622.Main;
import com.met622.constant.Constant;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

/**
 * An util class to have generalised methods
 */
public class ParserUtil{
    private org.w3c.dom.Document doc;
    private Map<String,String> sqlMap = new HashMap<String,String>(){{put("author",null);put("title",null);put("pages", null);put("mdate", null);put("volume", null);put("journal", null);put("ee", null);put("url", null);}};
    public ParserUtil() throws IOException, SAXException, ParserConfigurationException {
        InputStream is = Main.class.getClassLoader().getResourceAsStream(Constant.XML_FILE_NAME);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(is);
        doc.getDocumentElement().normalize();
    }

    /**
     * insertion of dblp data in mysql
     * @param connect
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws SQLException
     */
    public void sqlInsert(Connection connect) throws ParserConfigurationException, IOException, SAXException, SQLException {
        String col = "";
        for(String tmp:sqlMap.keySet()){
            col+=tmp+",";
        }
        col = col.substring(0, col.length() - 1);

        long start = System.currentTimeMillis();

        int totalDocumentFound = 0;
        Statement statement = connect.createStatement();
        int track = 0;

        NodeList flowList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < flowList.getLength(); i++) {
            Node parent = flowList.item(i);
            if(parent.getNodeName().equals("article")) {
                track++;

                NodeList childList = flowList.item(i).getChildNodes();
                NamedNodeMap attr = flowList.item(i).getAttributes();
                Node date = attr.getNamedItem("mdate");
                if(date!=null){
                    sqlMap.put("mdate",date.getNodeValue());
                }
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if(childNode.getNodeType() == Node.ELEMENT_NODE){
                        if(sqlMap.containsKey(childNode.getNodeName())){
                            sqlMap.put(childNode.getNodeName(), childNode.getTextContent().toLowerCase(Locale.ROOT));
                        }

                    }
                }

                String val = "";
                for(String tmp:sqlMap.keySet()){
                    if(sqlMap.get(tmp)!=null)
                        val+="'"+sqlMap.get(tmp).replaceAll("\'","")+"',";
                }
                val = val.substring(0,val.length()-1);
                try{
                    statement.addBatch("insert into bu.DBLP ("+col+") values("+val+");");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(track==100){
                statement.executeBatch();
                statement = connect.createStatement();
                track = 0;
            }
        }
        statement.executeBatch();
    }

    /**
     * insertion of dblp data in MongoDB
     * @param dblp
     */
    public void mongoInsert(DBCollection dblp) {
        String col = "";
        for(String tmp:sqlMap.keySet()){
            col+=tmp+",";
        }
        col = col.substring(0, col.length() - 1);

        long start = System.currentTimeMillis();

        int track = 0;

        NodeList flowList = doc.getDocumentElement().getChildNodes();
        for (int i = 0; i < flowList.getLength(); i++) {
            Node parent = flowList.item(i);
            if(parent.getNodeName().equals("article")) {
                DBObject data = new BasicDBObject();
                track++;

                NodeList childList = flowList.item(i).getChildNodes();
                NamedNodeMap attr = flowList.item(i).getAttributes();
                Node date = attr.getNamedItem("mdate");

                if(date!=null){
                    sqlMap.put("mdate",date.getNodeValue());
                    data.put("mdate", Date.from(LocalDate.parse(date.getNodeValue()).atStartOfDay().toInstant(ZoneOffset.UTC)));
                }
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if(childNode.getNodeType() == Node.ELEMENT_NODE){
                        if(sqlMap.containsKey(childNode.getNodeName())){
                            sqlMap.put(childNode.getNodeName(), childNode.getTextContent().toLowerCase(Locale.ROOT));
                            data.put(childNode.getNodeName(), childNode.getTextContent().toLowerCase(Locale.ROOT));
                        }

                    }
                }

                try{
                    dblp.insert(data);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
