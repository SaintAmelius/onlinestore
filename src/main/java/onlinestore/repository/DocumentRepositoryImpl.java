package onlinestore.repository;

import onlinestore.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DocumentRepositoryImpl implements DocumentRepository {
//    Map<User, Map<Long, Document>> mapOfDocuments = new HashMap<>();
    Map<Long, Document> mapOfDocuments = new HashMap<>();
//    Scanner scanner = new Scanner(System.in);
    Long idGenerator = 0L;

    public Long generateID() {
        return idGenerator++;
    }

//    public void addDocuments(Document document) {
//        Long docID = generateID();
//        document.setDocID(docID);
//        if (mapOfDocuments.get(user) != null) {
//            mapOfDocuments.get(user).put(docID, document);
//        } else {
//            HashMap<Long, Document> newMap = new HashMap<>();
//            newMap.put(docID, document);
//            mapOfDocuments.put(user, newMap);
//        }
//    }

//    public List<Document> findAll(User user) {
//        return new ArrayList<>(mapOfDocuments.get(user).values());
//    }

//    public Document findByID(User user, Long id) {
//        return mapOfDocuments.get(user).get(id);
//    }

    @Override
    public List<Document> findAllByUserID(Long userID) {
        return mapOfDocuments.values()
                .stream()
                .filter(document -> document.getOwner().equals(userID))
                .collect(Collectors.toList());
    }

    @Override
    public Document findByID(Long id) {
        return mapOfDocuments.get(id);
    }

    @Override
    public void addDocument(Document document) {
        Long docID = generateID();
        document.setDocID(docID);
        mapOfDocuments.put(docID, document);
    }
}
