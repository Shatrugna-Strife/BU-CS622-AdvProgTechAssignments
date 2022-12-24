package com.met622.Entity;

import com.met622.util.ParserUtil;
import com.mongodb.*;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static com.met622.singleton.Singleton.parserUtil;

/**
 * MongoEntity class, it prepares the database and populates the table with the dblp data.
 */
public class MongoEntity {
    MongoClient mongoClient = null;
    DB database = null;
    DBCollection dblp = null;

    public MongoEntity(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("bu");
        dblp = database.getCollection("DBLP");
        dblp.drop();
        dblp = database.getCollection("DBLP");
        dblp.createIndex(new BasicDBObject("title", "text"), "indexTitle");
    }

    public void insertData() throws SQLException, ParserConfigurationException, IOException, SAXException {
        parserUtil.mongoInsert(dblp);
    }

    /**
     * does simple text search
     * @param text
     * @return
     */
    public int textSearch(String text){
        DBObject search = new BasicDBObject(
                "$text", new BasicDBObject("$search", "\""+text+"\"")
        );
        return dblp.find(search).count();
    }

    /**
     * does simple text from the one time to another
     * @param text
     * @param from
     * @param to
     * @return
     */
    public int rangeTextSearch(String text, String from, String to){
        DBObject search = new BasicDBObject(
                "$text", new BasicDBObject("$search", "\""+text+"\"")
        ).append("mdate",new BasicDBObject("$gte", Date.from(LocalDate.parse(from).atStartOfDay().toInstant(ZoneOffset.UTC)))
        .append("$lte", Date.from(LocalDate.parse(to).atStartOfDay().toInstant(ZoneOffset.UTC))));
        return dblp.find(search).count();
    }

//    BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get()
}
