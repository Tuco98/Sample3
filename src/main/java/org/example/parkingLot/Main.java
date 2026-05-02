package org.example.parkingLot;

import java.util.*;

class Node {
    String label;
    List<Node> children;
    Node parent;
    int ancestorLocked, descendantLocked, userID;
    boolean isLocked;

    Node(String name, Node parentNode) {
        label = name;
        parent = parentNode;
        ancestorLocked = descendantLocked = userID = 0;
        isLocked = false;
        children = new ArrayList<>();
    }

    void addChildren(List<String> childLabels) {
        for (String childLabel : childLabels) {
            children.add(new Node(childLabel, this));
        }
    }
}

class LockingTree {
    private Node root;
    private Map<String, Node> labelToNode = new HashMap<>();
    private List<String> outputLog = new ArrayList<>();

    LockingTree(Node treeRoot) {
        root = treeRoot;
    }

    Node getRoot() {
        return root;
    }

    void fillLabelToNode(Node currentNode) {
        if (currentNode == null) return;

        labelToNode.put(currentNode.label, currentNode);
        for (Node child : currentNode.children)
            fillLabelToNode(child);
    }

    void updateDescendant(Node currentNode, int value) {
        for (Node child : currentNode.children) {
            child.ancestorLocked += value;
            updateDescendant(child, value);
        }
    }

    boolean checkDescendantsLocked(Node currentNode, int id,
                                   List<Node> lockedNodes) {

        if (currentNode.isLocked) {
            if (currentNode.userID != id)
                return false;
            lockedNodes.add(currentNode);
        }

        if (currentNode.descendantLocked == 0)
            return true;

        for (Node child : currentNode.children) {
            if (!checkDescendantsLocked(child, id, lockedNodes))
                return false;
        }

        return true;
    }

    boolean lockNode(String label, int id) {
        Node targetNode = labelToNode.get(label);

        if (targetNode.isLocked)
            return false;

        if (targetNode.ancestorLocked != 0 || targetNode.descendantLocked != 0)
            return false;

        Node currentNode = targetNode.parent;

        while (currentNode != null) {
            currentNode.descendantLocked++;
            currentNode = currentNode.parent;
        }

        updateDescendant(targetNode, 1);
        targetNode.isLocked = true;
        targetNode.userID = id;

        return true;
    }

    boolean unlockNode(String label, int id) {
        Node targetNode = labelToNode.get(label);

        if (!targetNode.isLocked)
            return false;

        if (targetNode.userID != id)
            return false;

        Node currentNode = targetNode.parent;

        while (currentNode != null) {
            currentNode.descendantLocked--;
            currentNode = currentNode.parent;
        }

        updateDescendant(targetNode, -1);
        targetNode.isLocked = false;

        return true;
    }

    boolean upgradeNode(String label, int id) {
        Node targetNode = labelToNode.get(label);

        if (targetNode.isLocked)
            return false;

        if (targetNode.ancestorLocked != 0 || targetNode.descendantLocked == 0)
            return false;

        List<Node> lockedDescendants = new ArrayList<>();

        if (!checkDescendantsLocked(targetNode, id, lockedDescendants))
            return false;

        for (Node lockedNode : lockedDescendants) {
            unlockNode(lockedNode.label, id);
        }

        return lockNode(label, id);
    }

    void processQueries(List<int[]> queries, List<String> nodeNames) {
        for (int i = 0; i < queries.size(); i++) {
            int opcode = queries.get(i)[0];
            String label = nodeNames.get(i);
            int userId = queries.get(i)[1];

            boolean result = false;

            switch (opcode) {
                case 1:
                    result = lockNode(label, userId);
                    break;
                case 2:
                    result = unlockNode(label, userId);
                    break;
                case 3:
                    result = upgradeNode(label, userId);
                    break;
            }

            outputLog.add(result ? "true" : "false");
        }
    }

    void printOutputLog() {
        for (String result : outputLog)
            System.out.println(result);
    }
}

public class Main {

    static Node buildTree(Node root, int numChildren, List<String> nodeLabels) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int startIndex = 1;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (startIndex >= nodeLabels.size())
                continue;

            List<String> childLabels = new ArrayList<>();

            for (int i = startIndex;
                 i < startIndex + numChildren && i < nodeLabels.size();
                 i++) {
                childLabels.add(nodeLabels.get(i));
            }

            currentNode.addChildren(childLabels);
            startIndex += numChildren;

            for (Node child : currentNode.children)
                queue.add(child);
        }

        return root;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int numNodes = sc.nextInt();
        int numChildren = sc.nextInt();
        int numQueries = sc.nextInt();

        List<String> nodeLabels = new ArrayList<>();

        for (int i = 0; i < numNodes; i++)
            nodeLabels.add(sc.next());

        Node rootNode = new Node(nodeLabels.get(0), null);
        rootNode = buildTree(rootNode, numChildren, nodeLabels);

        LockingTree lockingTree = new LockingTree(rootNode);
        lockingTree.fillLabelToNode(lockingTree.getRoot());

        List<int[]> queries = new ArrayList<>();
        List<String> queryLabels = new ArrayList<>();

        for (int i = 0; i < numQueries; i++) {
            int opcode = sc.nextInt();
            String label = sc.next();
            int userId = sc.nextInt();

            queries.add(new int[]{opcode, userId});
            queryLabels.add(label);
        }

        sc.close();

        lockingTree.processQueries(queries, queryLabels);
        lockingTree.printOutputLog();
    }
}

