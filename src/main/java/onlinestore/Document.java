package onlinestore;

public class Document {

    private Long ownerID;
    private String docName;
    private String docPath;
    private Long docID;

    public Document(Long ownerID, String docName, String docPath) {
        this.ownerID = ownerID;
        this.docName = docName;
        this.docPath = docPath;
    }

    public Long getDocID() {
        return docID;
    }

    public Long getOwner() {
        return ownerID;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public void setDocID(Long docID) {
        this.docID = docID;
    }
}
