package onlinestore.repository;

import onlinestore.Letter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MailRepository {
    HashMap<String, MailBox> mailBoxesByUserEmail = new HashMap<>();

    public List<Letter> getSentLetters(String userEmail) {
        if (mailBoxesByUserEmail.get(userEmail) != null) {
            return mailBoxesByUserEmail.get(userEmail).sent;
        } else {
            return List.of();
        }

    }

    public List<Letter> getReceivedLetters(String userEmail) {
        if (mailBoxesByUserEmail.get(userEmail) != null) {
            return mailBoxesByUserEmail.get(userEmail).received;
        } else {
            return List.of();
        }

    }

    public void sendLetter(Letter letter) {
        MailBox senderMailBox = mailBoxesByUserEmail.get(letter.getSender());
        MailBox receiverMailBox = mailBoxesByUserEmail.get(letter.getReceiver());
        if (senderMailBox == null) {
            senderMailBox = new MailBox();
            mailBoxesByUserEmail.put(letter.getSender(), senderMailBox);
        }
        if (receiverMailBox == null) {
            receiverMailBox = new MailBox();
            mailBoxesByUserEmail.put(letter.getReceiver(), receiverMailBox);
        }
        senderMailBox.sent.add(letter);
        receiverMailBox.received.add(letter);
    }

    static class MailBox {
        List<Letter> sent = new ArrayList<>();
        List<Letter> received = new ArrayList<>();
    }
}

