package ru.sfedu.accounting;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class XMLReader implements XMLInterface{
    protected String path;
    protected static Logger logger = Logger.getLogger(XMLReader.class);
    public XMLReader(String path){
        this.path = path;
    }
    public ArrayList<String> getContent(String tag) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        ArrayList<String> xmlText = new ArrayList<>();
        try {
            builder = factory.newDocumentBuilder();
        } catch (Exception e) {
            logger.info(e);
            return xmlText;
        }
        try {
            Document document = builder.parse(this.getClass().getClassLoader().getResourceAsStream(path));
            NodeList nodeList = document.getElementsByTagName(tag);
            for (int i = 0; i < nodeList.getLength(); i++)
                xmlText.add(nodeList.item(i).getTextContent());
            return xmlText;
        } catch (Exception e) {
            logger.info(e);
            return xmlText;
        }
    }



    }

