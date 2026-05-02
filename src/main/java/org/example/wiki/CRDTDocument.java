package org.example.wiki;

import java.util.List;

public class CRDTDocument {
    String pageId;
    List<CRDTOperation> operations;
    String cachedContent;
}
