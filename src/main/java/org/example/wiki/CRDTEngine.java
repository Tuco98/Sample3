package org.example.wiki;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CRDTEngine {
    void apply(CRDTDocument crdtDocument, CRDTOperation operation){
        if (operation.operationType.equals(OperationType.INSERT)){
            crdtDocument.operations.add(operation);
        }else {
            crdtDocument.operations.removeIf(o->o.positionId.equals(operation.positionId));
        }
    }

    String materialize(CRDTDocument doc){
        List<CRDTOperation> ops = new ArrayList<>(doc.operations);
        ops.sort(Comparator.comparing(a -> a.positionId));

        StringBuilder sb = new StringBuilder();
        for(CRDTOperation op: ops){
            if(op.operationType.equals(OperationType.INSERT)){
                sb.append(op.value);
            }
        }
        return sb.toString();

    }
}
