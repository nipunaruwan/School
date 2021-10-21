package view.tm;

public class LibraryTm {
    private String iDBOOk;
    private String nAME;
    private String aUTOR;
    private String sTATUS;
    private String rEGDATE;
    private int bookTypeID;

    public LibraryTm(String iDBOOk, String nAME, String aUTOR, String sTATUS, String rEGDATE, int bookTypeID) {
        this.iDBOOk = iDBOOk;
        this.nAME = nAME;
        this.aUTOR = aUTOR;
        this.sTATUS = sTATUS;
        this.rEGDATE = rEGDATE;
        this.bookTypeID = bookTypeID;
    }

    public LibraryTm() {
    }

    public String getiDBOOk() {
        return iDBOOk;
    }

    public void setiDBOOk(String iDBOOk) {
        this.iDBOOk = iDBOOk;
    }

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public String getaUTOR() {
        return aUTOR;
    }

    public void setaUTOR(String aUTOR) {
        this.aUTOR = aUTOR;
    }

    public String getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getrEGDATE() {
        return rEGDATE;
    }

    public void setrEGDATE(String rEGDATE) {
        this.rEGDATE = rEGDATE;
    }

    public int getBookTypeID() {
        return bookTypeID;
    }

    public void setBookTypeID(int bookTypeID) {
        this.bookTypeID = bookTypeID;
    }
}