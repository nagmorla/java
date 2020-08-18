/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsalg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nmorla
 * @since Aug 18, 2020
 */
public class BalancedWeightTree {

    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
//        linkedList.;
        
        List<Integer> parents = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        parents.add(-1);
        parents.add(0);
        parents.add(0);
        parents.add(1);
        parents.add(1);
        parents.add(2);

        sizes.add(1);
        sizes.add(2);
        sizes.add(2);
        sizes.add(1);
        sizes.add(1);
        sizes.add(1);
        int n = sizes.size();
        BalWeightTree tree = new BalWeightTree();
        for (int i = 0; i < n; i++) {
            tree.addElement(parents.get(i), i, sizes.get(i));
        }
        System.out.println("" + tree.getTotalWeight(2));
        System.out.println("" + tree.getTotalWeight(0));
        System.out.println("" + tree.getTotalWeight(3));
        System.out.println("" + tree.getTotalWeight(5));
    }

    static class BalWeightTree {

        private Node root;

        public void addElement(int parentNode, int label, int size) {
            Node ele = new Node(label, size);
            if (root == null || parentNode == -1) {
                System.out.println("Root is null - Creating it now. " + root + ",,," + parentNode);
                root = ele;
            } else {
                Node parent = findWithLabel(root, parentNode);
                ele.parent = parent;
                parent.childs.add(ele);
                Node tParent = parent;
                int childSize = size;
                while (tParent != null) {
                    System.out.println("Accumulating "+childSize+" for "+tParent);
                    tParent.totalSize = tParent.totalSize+childSize;
//                    childSize = tParent.totalSize;
                    tParent = tParent.parent;
                }
                System.out.println(ele + " is added to  <--> " + parent + "  <--> root : " + root);
            }
        }

        public int getTotalWeight(int label) {
            Node desired = findWithLabel(root, label);
            if (desired != null) {
                return desired.totalSize;
            } else {
                System.out.println("Couldn't find a node with label-" + label);
                return 0;
            }
        }

        private Node findWithLabel(Node t, int label) {
            if (t.label == label) {
                return t;
            } else {
                List<Node> childs = t.childs;
                for (Node child : childs) {
                    Node desired = findWithLabel(child, label);
                    if (desired != null) {
                        return desired;
                    }
                }
                return null;
            }
        }

        private class Node {

            int label;
            int size;
            int totalSize;
            Node parent;
            List<Node> childs = new ArrayList<>();

            public Node(int label, int size) {
                this.label = label;
                this.size = size;
                this.totalSize = size;
            }

            @Override
            public String toString() {
                return "{Label:" + label + ", Size:" + size + ", Total:" + totalSize + "}";
            }

        }
    }
}
