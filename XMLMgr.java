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
    /**
     * The directory where the XML files are stored
     */
    private static final String directory = "D:\\Documents\\NetBeansProjects"
            + "\\CZ2002_Assignment\\src\\cz2002_assignment\\XML\\";
    /*
    **
    */
    
    /**
     * The path where the XML file of guests list is stored
     */
    private static final String guestXMLFilePath = directory + "guestList.xml";
    
    /**
     * The path where the XML file of items menu is stored
     */
    private static final String itemXMLFilePath = directory + "itemMenu.xml";
    
    /**
     * The path where the XML file of rooms list is stored
     */
    private static final String roomXMLFilePath = directory + "roomList.xml";
    
    /**
     * The path where the XML file of room calendars is stored
     */
    private static final String roomCalXMLFilePath = directory + "roomCalendar.xml";
    
    /**
     * Data of guests list file
     */
    private static File guestFile = new File(guestXMLFilePath);
    
    /**
     * Data of item menu file
     */
    private static File itemFile = new File(itemXMLFilePath);
    
    /**
     * Data of rooms list file
     */
    private static File roomFile = new File(roomXMLFilePath);
    
    /**
     * Data of room calendars file
     */
    private static File roomCalFile = new File(roomCalXMLFilePath);
    
    /**
     * XML parser
     */
    private static Document doc;
    
    /**
     * List of guests
     */
    private static List<Guest> guestList = new ArrayList();
    
    /**
     * List of item provided in room service
     */
    private static List<Item> itemMenu = new ArrayList();
    
    /**
     * Array of room objects
     */
    private static Room[] roomData = new Room[RoomMgr.totalRooms];
    
    /**
     * 2D array of rooms calendar
     */
    private static RoomCalendar[][] statusCalendar = new RoomCalendar[RoomMgr.totalRooms][30];
    
    /**
     * List of payments
     */
    private static Payment[] paymentList = new Payment[RoomMgr.totalRooms];

    /**
     * Load data from the XML files
     */
    public void fromXML(){
        try {	
            System.out.println("XML files are being uploaded...");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            dbFactory.setIgnoringElementContentWhitespace(true);

            // importing of itemMenu list
            doc = dBuilder.parse(itemFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");
            
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
            
            int counter = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                        roomData[counter] = new Room(eElement.getAttribute("num"),
                        Boolean.parseBoolean(eElement.getElementsByTagName("wifienabled").item(0).getTextContent()),
                        eElement.getElementsByTagName("faceview").item(0).getTextContent(),
                        Boolean.parseBoolean(eElement.getElementsByTagName("smoking").item(0).getTextContent()),
                        RoomType.valueOf(eElement.getElementsByTagName("roomtype").item(0).getTextContent()));
                    counter ++;
                }
            }
            System.out.println("roomData XML files are uploaded.");
            
            // importing of guestList data from XML file
            doc = dBuilder.parse(guestFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("guest");
            
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
                            eElement.getElementsByTagName("creditCardDet").item(0).getTextContent(),
                            eElement.getElementsByTagName("room").item(0).getTextContent()));
                }
            }
            GuestMgr.setGuestList(guestList);
            System.out.println("guest XML files are uploaded.");
            
            // importing of roomCalendar data from XML file
            doc = dBuilder.parse(roomCalFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("room");
            
            int counterOut = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    NodeList childNodeList = nNode.getChildNodes();
                    counter = 0;
                    for (int j = 0; j < childNodeList.getLength(); j ++) {
                        Node childNode = childNodeList.item(j);
                        if (childNode.getNodeType() == Node.ELEMENT_NODE){
                            Element eElement = (Element) childNode; 
                            String currentGuest = "";
                            String guestName = "";
                            
                            if (eElement.getElementsByTagName("guestname").item(0).getTextContent() != null)
                                guestName = eElement.getElementsByTagName("guestname").item(0).getTextContent();
                            else
                                guestName = "";

                            // Obtain name of current guest
                            if (counter == CZ2002_Assignment.currentDay-1) {
                                currentGuest = eElement.getElementsByTagName("guestname").item(0).getTextContent();
                            }
                            
                            statusCalendar[counterOut][counter] = new RoomCalendar(
                                RoomStatus.valueOf(eElement.getElementsByTagName("status").item(0).getTextContent().toUpperCase()),
                                Double.parseDouble(eElement.getElementsByTagName("rate").item(0).getTextContent()),
                                guestName);
                            
                            // Add into payment
                            // Only when the  payments are <= currentDay do the payments exist.
                            if (j <= CZ2002_Assignment.currentDay) {
                                // Check that current guest living in room matches.
                                if (guestName.equals(currentGuest) && !currentGuest.equals("")){
                                    paymentList[counterOut] = new Payment();
                                    paymentList[counterOut].addRoomServiceBill(Double.parseDouble(
                                            eElement.getElementsByTagName("roomservicebill").item(0).getTextContent()));
                                }
                            }
                            counter ++;
                        }
                    }
                    counterOut++;
                }
            }
            System.out.println("roomCalendar XML files are uploaded.");
            System.out.println("payment XML files are uploaded.");
            
            System.out.println("");
        }   
        catch (Exception e) {e.printStackTrace();}
        
        
    }
    
    /**
     * Save data to XML files
     * 
     * @param paymentArr The payment array to be stored
     */
    public void toXML(Payment[] paymentArr){
        try {
            System.out.println("Initialising storing of data to XML files...");
            guestList = new ArrayList(GuestMgr.getGuestList());
            
            Iterator<Guest> guestListItr = guestList.iterator();
            Iterator<Item> itemMenuItr = MenuMgr.getItemMenuList().iterator();
            
            // Initialise DocumentBuilder and Transformer
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            dbFactory.setIgnoringElementContentWhitespace(true);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            
            // Create document for guestList.XML
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
                Element rom = doc.createElement("room");
            
                Guest g = guestListItr.next();
                
                name.appendChild(doc.createTextNode(g.getName()));
                gen.appendChild(doc.createTextNode(g.getGender()));
                add.appendChild(doc.createTextNode (g.getAddress()));
                id.appendChild(doc.createTextNode(g.getIdentity()));
                nat.appendChild(doc.createTextNode(g.getNationality()));
                contact.appendChild(doc.createTextNode(String.valueOf(g.getContact())));
                ccd.appendChild(doc.createTextNode(g.getCreditCardDet()));
                if (g.getRoom() == null)
                    rom.appendChild(doc.createTextNode(""));
                else
                    rom.appendChild(doc.createTextNode(g.getRoom().getRoomNo()));
                
                rootElement.appendChild(guest);
                guest.appendChild(name);
                guest.appendChild(gen);
                guest.appendChild(add);
                guest.appendChild(id);
                guest.appendChild(nat);
                guest.appendChild(contact);
                guest.appendChild(ccd);
                guest.appendChild(rom);
                
                counter ++;
            }

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(guestFile);
            transformer.transform(source, result); 
            System.out.println("guest XML files are stored.");
            
            // Create document for itemMenu.XML
            doc = builder.newDocument();
            rootElement = doc.createElement("Root");
            doc.appendChild(rootElement);
            
            counter = 0;
            
            while (itemMenuItr.hasNext()) {
                Attr itemAttr = doc.createAttribute("id");
                itemAttr.setValue(String.valueOf(counter));
                Element item = doc.createElement("item");
                item.setAttributeNode(itemAttr);
                Element name = doc.createElement("name");
                Element des = doc.createElement("description");
                Element price = doc.createElement("price");
            
                Item i = itemMenuItr.next();
                
                name.appendChild(doc.createTextNode(i.getName()));
                des.appendChild(doc.createTextNode(i.getDescription()));
                price.appendChild(doc.createTextNode (String.valueOf(i.getPrice())));
                
                rootElement.appendChild(item);
                item.appendChild(name);
                item.appendChild(des);
                item.appendChild(price);
                
                counter ++;
            }

            source = new DOMSource(doc);
            result = new StreamResult(itemFile);
            transformer.transform(source, result); 
            System.out.println("itemMenu XML files are stored.");

            
            // Create document for roomCalendar.XML
            doc = builder.newDocument();
            rootElement = doc.createElement("Root");
            doc.appendChild(rootElement);
            
            Attr monthAttr = doc.createAttribute("month");
            monthAttr.setValue("4");
            Element month = doc.createElement("month");
            month.setAttributeNode(monthAttr);
            rootElement.appendChild(month);
                
            for (int i = 0; i < RoomMgr.totalRooms; i ++) {
                Room r = RoomMgr.getRoom(RoomMgr.roomIntToStr(i));
                
                Attr roomAttr = doc.createAttribute("num");
                roomAttr.setValue(RoomMgr.roomIntToStr(i).replace("-", ""));
                Element room = doc.createElement("room");
                room.setAttributeNode(roomAttr);
                month.appendChild(room);
                
                for (int j = 0; j < 30; j ++) {
                    Attr dayAttr = doc.createAttribute("date");
                    dayAttr.setValue(String.valueOf(j+1));
                    Element day = doc.createElement("day");
                    day.setAttributeNode(dayAttr);
                    
                    Element stat = doc.createElement("status");
                    Element rat = doc.createElement("rate");
                    Element rsb = doc.createElement("roomservicebill");
                    Element gn = doc.createElement("guestname");
                    
                    if (r.getRoomStatus(j+1) != null)
                        stat.appendChild(doc.createTextNode(r.getRoomStatus(j+1).toString()));
                    else
                        stat.appendChild(doc.createTextNode(RoomStatus.VACANT.toString()));
                    
                    if (r.getRoomStatus(j+1) != null)
                        rat.appendChild(doc.createTextNode(String.valueOf(r.getStatusCalendar(j+1).getRate())));
                    else if (j == 22 || j == 23 || j == 29)
                        rat.appendChild(doc.createTextNode("1.5"));
                    else
                        rat.appendChild(doc.createTextNode("1.0"));
                    
                    if (j == CZ2002_Assignment.currentDay - 1)
                        rsb.appendChild(doc.createTextNode(String.valueOf(paymentArr[i].getRoomServiceBill())));
                    else
                        rsb.appendChild(doc.createTextNode(""));
                    
                    if (r.getStatusCalendar(j+1).getGuest() != null)
                        gn.appendChild(doc.createTextNode(r.getStatusCalendar(j+1).getGuest().getName()));
                    else
                        gn.appendChild(doc.createTextNode(""));
                        
                    room.appendChild(day);
                    day.appendChild(stat);
                    day.appendChild(rat);
                    day.appendChild(rsb);
                    day.appendChild(gn);
                    
                }
            }
            
            source = new DOMSource(doc);
            result = new StreamResult(roomCalFile);
            transformer.transform(source, result); 
            System.out.println("roomCalendar XML files are stored.");
         
        }
        catch (NullPointerException e) {System.out.println("null pointer exception error: " + e.getMessage());}
        catch (Exception e) {System.out.println(e.getMessage());}

        
        
        //End of output file
    }
    
    /**
     * Get list of guests
     * 
     * @return guestList
     */
    public List<Guest> getGuestList(){return guestList;}
    
    /**
     * Get list of items offered in room service
     * 
     * @return itemMenu
     */
    public List<Item> getItemMenu(){return itemMenu;}
    
    /**
     * Get the array with all the rooms details
     * 
     * @return roomData
     */
    public Room[] getRoomData(){return roomData;}   
    
    /**
     * Get the 2D array of status calendar which contains the status of all rooms on all days in the calendar
     *
     * @return statusCalendar
     */
    public RoomCalendar[][] getRoomCalData(){return statusCalendar;}
    
    /**
     * Get list of all payments
     * 
     * @return paymentList
     */
    public Payment[] getPaymentList(){return paymentList;}
}
