package core.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class XsdReader {
    public static void readDataFromXsd(File file) {
        try {
            // parse the document
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList list = doc.getElementsByTagName("xs:element");

            //loop to print data
            for(int i = 0 ; i < list.getLength(); i++)
            {
                Element first = (Element)list.item(i);
                if(first.hasAttributes())
                {
                    String nm = first.getAttribute("name");
                    System.out.println(nm);
                    String nm1 = first.getAttribute("type");
                    System.out.println(nm1);
                }
            }
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException ed)
        {
            ed.printStackTrace();
        }
    }
}
