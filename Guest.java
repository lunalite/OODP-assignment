package cz2002_assignment;


public class Guest {

    private String name;
    private String gender;
    private String identity;
    private String address;
    private String nationality;
    private int contact;
    private String creditCardDet;

    
    Guest(String n, String g, String i, String a, String N, int c, String ccd) {
        name = n;
        gender = g;
        identity = i;
        address = a;
        nationality = N;
        contact = c;
        creditCardDet = ccd;
    }
    
    /**
     *
     * @param roomNo
     */
    public void roomService(int roomNo) { //Useless
        
    }

    public void roomLog() {
        // Stores the data of room
    }

    public void reservationLog() {
        
    }
    
    public String getName() {return this.name;}
    public String getGender() {return this.gender;}
    public String getAddress() {return this.address;}
    public String getIdentity() {return this.identity;}
    public String getNationality() {return this.nationality;}
    public int getContact() {return this.contact;}
    public String getCreditCardDet() {return this.creditCardDet;}
    
    public void setName(String n){name = n;}
    public void setGender(String g){gender = g;}
    public void setAddress(String a){address = a;}
    public void setIdentity(String i){identity = i;}
    public void setNationality(String N){nationality = N;}
    public void setContact(int c){contact = c;}
    public void setCreditCardDet(String ccd) {creditCardDet = ccd;}
}
