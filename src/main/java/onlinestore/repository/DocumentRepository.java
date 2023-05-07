package onlinestore.repository;

import onlinestore.Document;

import java.util.List;

public interface DocumentRepository {
    List<Document> findAllByUserID(Long userID);

    Document findByID(Long id);

    void addDocument(Document document);
}
