package org.example.wiki;

public class CollaborationService {
    OperationRepository opRepository;
    DocumentRepository docRepository;
    CRDTEngine engine;
    void applyOperation(CRDTOperation op){

        opRepository.save(op);

        CRDTDocument doc = docRepository.get(op.pageId);

        engine.apply(doc,op);

        docRepository.save(doc);

        broadcast(op);


    }

    private void broadcast(CRDTOperation op) {
        //broadcase through websocket
    }

    String getContent(String pageId){
        CRDTDocument crdtDocument = docRepository.get(pageId);
        return engine.materialize(crdtDocument);
    }
}
