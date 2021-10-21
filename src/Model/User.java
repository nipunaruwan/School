package Model;

public class User {
    private String userId;
    private String Fname;
    private String Lname;
    private String DoB;
    private String NIC;
    private String TP1;

    private String No;
    private String STREET;
    private String CITY;
    private String EMAIL;
    private String Type;
    private String GEN;
    private int STATUS;
    private String PASSWORD;

    public User(String string, String rstString, String s, String string1, String rstString1) {
    }

    public User(String userId, String fname, String lname, String doB, String NIC, String TP1,  String no, String STREET, String CITY, String EMAIL, String type, String GEN, int STATUS, String PASSWORD) {
        this.setUserId(userId);
        setFname(fname);
        setLname(lname);
        setDoB(doB);
        this.setNIC(NIC);
        this.setTP1(TP1);

        setNo(no);
        this.setSTREET(STREET);
        this.setCITY(CITY);
        this.setEMAIL(EMAIL);
        setType(type);
        this.setGEN(GEN);
        this.setSTATUS(STATUS);
        this.setPASSWORD(PASSWORD);
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getTP1() {
        return TP1;
    }

    public void setTP1(String TP1) {
        this.TP1 = TP1;
    }


    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getSTREET() {
        return STREET;
    }

    public void setSTREET(String STREET) {
        this.STREET = STREET;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getGEN() {
        return GEN;
    }

    public void setGEN(String GEN) {
        this.GEN = GEN;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}

