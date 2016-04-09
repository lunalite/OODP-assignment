/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz2002_assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
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
import org.w3c.dom.Attr;

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
    private static final String roomXMLFilePath = directory + "roomList.xml";
    private static File guestFile = new File(guestXMLFilePath);
    private static File itemFile = new File(itemXMLFilePath);
    private static File roomFile = new File(roomXMLFilePath);
    
    private static Document doc;
    private static List<Guest> guestList = new ArrayList();
    private static List<Item> itemMenu = new ArrayList();
    private static Room[] roomData = new Room[RoomMgr.totalRooms];
    

    
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
                }
            }
            System.out.println("itemMenu XML files are uploaded.");
            
            //importing of roomList 
            doc = dBuilder.parse(roomFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("room");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                        roomData[temp] = new Room(eElement.getAttribute("num"),
                        Boolean.parseBoolean(eElement.getElementsByTagName("wifienabled").item(0).getTextContent()),
                        eElement.getElementsByTagName("faceview").item(0).getTextContent(),
                        Boolean.parseBoolean(eElement.getElementsByTagName("smoking").item(0).getTextContent()),
                        RoomType.valueOf(eElement.getElementsByTagName("roomtype").item(0).getTextContent()));
                }
            }
            System.out.println("roomData XML files are uploaded.");
            
            System.out.println("");
        }   
        catch (Exception e) {e.printStackTrace();}
        
        
    }
    
    public void toXML(List<Guest> gL){
        try {
            System.out.println("Initialising storing of data to XML files...");
            guestList = new ArrayList(gL);
            Iterator<Guest> guestListItr = guestList.iterator();
            
            // Initialise the document builder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            doc = builder.newDocument();
            
            Element rootElement = doc.createElement("Root");
            doc.appendChild(rootElement);
            
            int counter = 0;
            
            while (guestListItr.hasNext()) {
                Attr guestAttr = doc.createAttribute("id");
                guestAttr.setValue(String.valueOf(counter));
                Element guest = doc.createElement("guest");
                guest.setAttributeNode(guestAttr);
                Element name = doc.createElement("name");
                Element gen = doc.createElement("gender");
                Element add = doc.createElement("identity");
                Element id = doc.createElement("address");
                Element nat = doc.createElement("nationality");
                Element contact = doc.createElement("contact");
                Element ccd = doc.createElement("creditCardDet");
            
                
                Guest g = guestListItr.next();
                System.out.println(g.getName());
                
                name.appendChild(doc.createTextNode(g.getName()));
                gen.appendChild(doc.createTextNode(g.getGender()));
                add.appendChild(doc.createTextNode (g.getAddress()));
                id.appendChild(doc.createTextNode(g.getIdentity()));
                nat.appendChild(doc.createTextNode(g.getNationality()));
                contact.appendChild(doc.createTextNode(String.valueOf(g.getContact())));
                ccd.appendChild(doc.createTextNode(g.getCreditCardDet()));
                
                rootElement.appendChild(guest);
                guest.appendChild(name);
                guest.appendChild(gen);
                guest.appendChild(add);
                guest.appendChild(id);
                guest.appendChild(nat);
                guest.appendChild(contact);
                guest.appendChild(ccd);
                
                counter ++;
            }



            // Start output file through transformer to guestFile
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(guestFile);
            transformer.transform(source, result); 
            System.out.println("guest XML files are stored.");


        }
        catch (Exception e) {System.out.println(e.getMessage());}

        //End of output file
    }
    
    public List<Guest> getGuestList(){return guestList;}
    public List<Item> getItemMenu(){return itemMenu;}
    public Room[] getRoomData(){return roomData;}   
}
