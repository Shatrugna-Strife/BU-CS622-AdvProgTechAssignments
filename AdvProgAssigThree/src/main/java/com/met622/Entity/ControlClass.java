package com.met622.Entity;

import com.met622.constant.Constant;
import com.met622.lucene.LuceneReader;
import com.met622.lucene.LuceneWriter;
import com.met622.model.Pair;
import com.met622.util.ParserUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.met622.singleton.Singleton.*;

/**
 * Here where the entire program logic starts.
 */
public class ControlClass {
    public Pair<List<Integer>, Pair<List<Double>, List<Double>>> execute(String word){
        new File(Constant.INDEX_DIR).mkdirs();
        List<Double> timeTakenIndexMethod = new ArrayList<>();
        List<Double> timeTakenBruteForce = new ArrayList<>();
        List<Integer> numberOfArticles = new ArrayList<>();

        System.out.println("\n\nKeyword: " + word);
        for(int i = 10;i<=100;i+=10) {
            try {
                numberOfArticles.add(i);
                luceneWriter.execute(i);
                Pair<Integer, Double> temp = luceneReader.execute(new ArrayList<String>() {{
                    add(word);
                }});
                System.out.println("Lucene read, total docs found " + temp.getFirst());
                timeTakenIndexMethod.add(temp.getSecond());
                temp = parserUtil.bruteForceScan(i, new ArrayList<String>(){{add(word);}});
                System.out.println("Brute Force search, total docs found " + temp.getFirst());
                timeTakenBruteForce.add(temp.getSecond());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        for(int i = 1000;i<=10000;i+=1000) {
            try {
                numberOfArticles.add(i);
                luceneWriter.execute(i);
                Pair<Integer, Double> temp = luceneReader.execute(new ArrayList<String>() {{
                    add(word);
                }});
                System.out.println("Lucene read, total docs found " + temp.getFirst());
                timeTakenIndexMethod.add(temp.getSecond());
                temp = parserUtil.bruteForceScan(i, new ArrayList<String>(){{add(word);}});
                System.out.println("Brute Force search, total docs found " + temp.getFirst());
                timeTakenBruteForce.add(temp.getSecond());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }


//        System.out.println(timeTakenIndexMethod);
//        System.out.println(timeTakenBruteForce);
        return new Pair<>(numberOfArticles, new Pair<>(timeTakenIndexMethod, timeTakenBruteForce));
    }
}
