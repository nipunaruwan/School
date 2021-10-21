package Model;

public class Library {
    private String IDBOOk;
    private String NAME;
    private String AUTOR;
    private String STATUS;
    private String REGDATE;
    private int BookTypeID;




    public Library(String IDBOOk, String NAME, String AUTOR, String STATUS, String REGDATE, int bookTypeID) {
        this.setIDBOOk(IDBOOk);
        this.setNAME(NAME);
        this.setAUTOR(AUTOR);
        this.setSTATUS(STATUS);
        this.setREGDATE(REGDATE);
        setBookTypeID(bookTypeID);

    }

    public String getIDBOOk() {
        return IDBOOk;
    }

    public void setIDBOOk(String IDBOOk) {
        this.IDBOOk = IDBOOk;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getAUTOR() {
        return AUTOR;
    }

    public void setAUTOR(String AUTOR) {
        this.AUTOR = AUTOR;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getREGDATE() {
        return REGDATE;
    }

    public void setREGDATE(String REGDATE) {
        this.REGDATE = REGDATE;
    }

    public int getBookTypeID() {
        return BookTypeID;
    }

    public void setBookTypeID(int bookTypeID) {
        BookTypeID = bookTypeID;
    }



    @Override
    public String toString() {
        return "LibraryTm{" +
                "IDBOOk='" + IDBOOk + '\'' +
                ", NAME='" + NAME + '\'' +
                ", AUTOR='" + AUTOR + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", REGDATE='" + REGDATE + '\'' +
                ", BookTypeID='" + BookTypeID + '\'' +

                '}';
    }

}