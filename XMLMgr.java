/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 

public class XMLMgr {
    
    /*
    ** 
    */
    //PLEASE EDIT THIS FILE FOR IT TO WORK!!
    private static final String directory = "D:\\Documents\\NetBeansProjects"
            + "\\CZ2002_Assignment\\src\\cz2002_assignment\\XML\\";
    /*
    **
    */
    private static final String guestXMLFilePath = directory + "guestList.xml";
    private static final String itemXMLFilePath = directory + "itemMenu.xml";
    private static File guestFile = new File(guestXMLFilePath);
    private static File itemFile = new File(itemXMLFilePath);
    
    private static Document doc;
    private static List<Guest> guestList = new ArrayList();
    private static int guestXMLAttrStart;
    private static List<Item> itemMenu = new ArrayList();
    private static int itemXMLAttrStart;

    
    public void fromXML(){
        try {	
            System.out.println("XML files are being uploaded...");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            // importing of guestList data from XML file
            doc = dBuilder.parse(guestFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Guest");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    guestList.add(new Guest(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("gender").item(0).getTextContent(),
                            eElement.getElementsByTagName("identity").item(0).getTextContent(),
                            eElement.getElementsByTagName("address").item(0).getTextContent(),
                            eElement.getElementsByTagName("nationality").item(0).getTextContent(),
                            Integer.parseInt(eElement.getElementsByTagName("contact").item(0).getTextContent()),
                            eElement.getElementsByTagName("creditCardDet").item(0).getTextContent()));
                    guestXMLAttrStart = Integer.parseInt(eElement.getAttribute("id"));
                }
            }
            System.out.println("guest XML files are uploaded.");
            
            // importing of itemMenu list
             doc = dBuilder.parse(itemFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("item");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    itemMenu.add(new Item(eElement.getElementsByTagName("name").item(0).getTextContent(),
                            eElement.getElementsByTagName("description").item(0).getTextContent(),
                            Double.parseDouble(eElement.getElementsByTagName("price").item(0).getTextContent())));
                    itemXMLAttrStart = Integer.parseInt(eElement.getAttribute("id"));
                }
            }
            System.out.println("itemMenu XML files are uploaded.");
            System.out.println("");
        }   
        catch (Exception e) {e.printStackTrace();}
        
        
    }
    /*
    public void toGuestXML(){
        try {
                            // Initialise the document builder
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = dbFactory.newDocumentBuilder();
                            doc = builder.parse(guestFile);
                            // end initialisation of document builder
                            
                            Node root = doc.getDocumentElement();
                            
                            Element guest = doc.createElement("Guest");
                            Element name = doc.createElement("name");
                            Element gen = doc.createElement("gender");
                            Element add = doc.createElement("identity");
                            Element id = doc.createElement("address");
                            Element nat = doc.createElement("nationality");
                            Element contact = doc.createElement("contact");
                            Element ccd = doc.createElement("creditCardDet");
                            
                            name.appendChild(doc.createTextNode(name2B));
                            gen.appendChild(doc.createTextNode(gender2B));
                            add.appendChild(doc.createTextNode(address2B));
                            id.appendChild(doc.createTextNode(identity2B));
                            nat.appendChild(doc.createTextNode(nat2B));
                            contact.appendChild(doc.createTextNode(contact2));
                            ccd.appendChild(doc.createTextNode(ccd2B));
                            
                            root.appendChild(guest);
                            guest.appendChild(name);
                            guest.appendChild(gen);
                            guest.appendChild(add);
                            guest.appendChild(id);
                            guest.appendChild(nat);
                            guest.appendChild(contact);
                            guest.appendChild(ccd);
                            
                            // Start output file through transformer to guestFile
                            TransformerFactory tFactory = TransformerFactory.newInstance();
                            Transformer transformer = tFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(guestFile);
                            transformer.transform(source, result); 
                            System.out.println("Done");

                        }
                        catch (Exception e) {System.out.println(e.getMessage());}

                        //End of output file
    }*/
    
    public List<Guest> getGuestList(){return guestList;}
    public List<Item> getItemMenu(){return itemMenu;}
}
