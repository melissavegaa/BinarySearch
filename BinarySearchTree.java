package com.company;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BinaryTree {
    Node root;

    static class Node {
        int value;
        Node right, left;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }

        public int getValue() {
            return value;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int getSize() {
        return getSizeRecursive(root);
    }

    private int getSizeRecursive(Node current) {
        return current == null ? 0 : getSizeRecursive(current.left) + 1 + getSizeRecursive(current.right);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsNodeRecursive(current.left, value) : containsNodeRecursive(current.left, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    public String traversePreOrder(Node node) {
        if (node == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());

        String pointerRight = "└──";
        String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }
            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }

    public void printTree(PrintStream os, Node node) {
        os.print(traversePreOrder(node));
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            visit(node.value);
            traverseInOrder(node.right);
        }
    }

    private void visit(int value) {
        System.out.print(" " + value);
    }

}

public class BinarySearchTree {

    public static void main(String[] args) {
            BinaryTree bt = new BinaryTree();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the nodes you want in your tree separated by a comma:");
            String nodes = input.nextLine();
            String[] sArray = nodes.split("[\\s,]+");

            int[] iArray = new int[sArray.length];
            for (int i = 0; i < sArray.length; i++) {
                iArray[i] = Integer.parseInt(sArray[i]);
                bt.add(iArray[i]);

                int choice;
                System.out.println("*******************");
                System.out.println("Please select one of the following options");
                System.out.println("1. Add a node.");
                System.out.println("2. Delete a node.");
                System.out.println("3. Print the three.");
                System.out.println("4. Exit.");
                choice = input.nextInt();

                if (choice == 1) {
                    System.out.println("Enter the node you want to add to the tree:");
                    int newNode = input.nextInt();
                    bt.add(newNode);
                    System.out.println();
                }
                else if (choice == 2) {
                    System.out.println("Enter the node you want to delete from your tree:");
                    int deleteNode = input.nextInt();
                    bt.delete(deleteNode);
                    System.out.println();
                }
                else if (choice == 3) {
                    bt.printTree(System.out, bt.root);
                    System.out.println();
                }
                else if (choice == 4) {
                    System.out.println("Thanks. See you later!");
                    System.out.println();
                }
                else {
                    System.out.println("Invalid option. Try again.");
                    System.out.println();
                }
            }
    }
}
