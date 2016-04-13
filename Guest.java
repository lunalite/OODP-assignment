package cz2002_assignment;

import java.util.Iterator;
import java.util.List;


public class Guest {

    /**
     * The guest's name
     */
    private String name;
    
    /**
     * The guest's gender
     */
    private String gender;
    
    /**
     * The guest's identity
     */
    private String identity;
    
    /**
     * The guest's address
     */
    private String address;
    
    /**
     * The guest's nationality
     */
    private String nationality;
    
    /**
     * The guest's contact number
     */
    private int contact;
    
    /**
     * The guest's credit card details
     */
    private String creditCardDet;
    
    /**
     * The reservation made by the guest
     */
    private Reservation reservation;
    
    /**
     * The room number that the guest is staying in
     */
    private String roomNo;
    
    /**
     * The room that the guest is staying in
     */
    private Room room;
    
    /**
     * Creates a new guest
     * 
     * @param name The guest's name
     * @param gender The guest's gender
     * @param identity The guest's identity
     * @param address The guest's address
     * @param nationality The guest's nationality
     * @param contact The guest's contact
     * @param creditCardDetails The guest's credit card details
     * @param roomNo The room number the guest is staying in
     */
    Guest(String name, String gender, String identity, String address, String nationality, int contact, String creditCardDetails, String roomNo) {
        this.name = name;
        this.gender = gender;
        this.identity = identity;
        this.address = address;
        this.nationality = nationality;
        this.contact = contact;
        this.creditCardDet = creditCardDetails;
        this.roomNo = roomNo;
    }
    
    
    /**
     * Update and assign room via guest's room number if it exists
     */
    public void updateGuestRoom() {
        if (roomNo.isEmpty())
            room = null;
        else {
            room = RoomMgr.getRoom(roomNo);
        }
    }
    
    /**
     * Get guest's name
     * 
     * @return name
     */
    public String getName() {return this.name;}
    
    /**
     * Get guest's gender
     * 
     * @return gender
     */
    public String getGender() {return this.gender;}
    
    /**
     * Get guest's address
     * 
     * @return address
     */
    public String getAddress() {return this.address;}
    
    /**
     * Get guest's identity
     * 
     * @return identity
     */
    public String getIdentity() {return this.identity;}
    
    /**
     * Get guest's nationality
     * 
     * @return nationality
     */
    public String getNationality() {return this.nationality;}
    
    /**
     * Get guest's contact number
     * 
     * @return contact
     */
    public int getContact() {return this.contact;}
    
    /**
     * Get guest's credit card details
     * 
     * @return creditCardDetails
     */
    public String getCreditCardDet() {return this.creditCardDet;}
    
    /**
     * Get guest's reservation
     * 
     * @return reservation
     */
    public Reservation getReservation() {return this.reservation;}
    
    /**
     * Get the room the guest is staying in
     * 
     * @return Room
     */
    public Room getRoom() {
        if (room != null)
            return RoomMgr.getRoom(roomNo);
        else
            return null;
    }
    
    /**
     * Set the guest's name
     * 
     * @param name The name of the guest
     */
    public void setName(String name){this.name = name;}
    
    /**
     * Set the guest's gender
     * 
     * @param gender The gender of the guest
     */
    public void setGender(String gender){this.gender = gender;}
    
    /**
     * Set the guest's address
     * 
     * @param address The address of the guest 
     */
    public void setAddress(String address){this.address = address;}
    
    /**
     * Set the guest's identity
     * 
     * @param identity The identity of the guest
     */
    public void setIdentity(String identity){this.identity = identity;}
    
    /**
     *  Set the guest's nationality
     * 
     * @param nationality The nationality of the guest
     */
    public void setNationality(String nationality){this.nationality = nationality;}
    
    /**
     * Set the guest's contact number
     * 
     * @param contact The contact number of the guest
     */
    public void setContact(int contact){this.contact = contact;}
    
    /**
     * Set the guest's credit card details
     * 
     * @param creditCardDetails The credit card details of the guest
     */
    public void setCreditCardDet(String creditCardDetails) {this.creditCardDet = creditCardDetails;}
    
    /**
     * Set the guest's reservation
     * 
     * @param reservation The reservation of the guest
     */
    public void setReservation(Reservation reservation) {this.reservation = reservation;}
    
    /**
     * Set the room that the guest is staying in
     * 
     * @param room The assigned room of the guest
     */
    public void setRoom(Room room) {this.room = room;}
}
