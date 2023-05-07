package onlinestore;

public class Letter {
    enum LetterType {
        SEND,
        RECEIVED
    }

    private String sender;
    private String receiver;
    private String subject;
    private LetterType letterType;
    private String letterBody;

    public Letter(String sender, String receiver, String subject, LetterType letterType, String letterBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.letterType = letterType;
        this.letterBody = letterBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLetterBody() {
        return letterBody;
    }

    public void setLetterBody(String letterBody) {
        this.letterBody = letterBody;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public LetterType getLetterType() {
        return letterType;
    }

    public void setLetterType(LetterType letterType) {
        this.letterType = letterType;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", subject='" + subject + '\'' +
                ", letterType=" + letterType +
                ", letterBody='" + letterBody + '\'' +
                '}';
    }
}
