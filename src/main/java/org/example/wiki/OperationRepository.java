package org.example.wiki;

import java.util.List;

public interface OperationRepository {
    void save(CRDTOperation op);
    List<CRDTOperation> getByPageId(String pageId);
}
