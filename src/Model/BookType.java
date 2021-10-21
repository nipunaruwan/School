package Model;

public class BookType {
    private int bookTypeID;
    private String typeName;
    private String status;

    public BookType(int bookTypeID, String typeName, String status) {
        this.bookTypeID = bookTypeID;
        this.typeName = typeName;
        this.status = status;
    }

    public BookType() {
    }

    public int getBookTypeID() {
        return bookTypeID;
    }

    public void setBookTypeID(int bookTypeID) {
        this.bookTypeID = bookTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
