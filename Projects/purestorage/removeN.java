public class removeN{
    
    public static void main(String[] args){
        removeN test = new removeN();
        removeN.Node n1 = new removeN.Node(1, new removeN.Node(3, new removeN.Node(2, 
            new removeN.Node(3, new removeN.Node(3, null)))));
        System.out.println("Printing initial linkedlist");
        removeN.Node.printList(n1);
        System.out.println("Printing result");
        removeN.Node.printList(test.removeNList(n1, 3));
    }

    public static class Node{
        int val;
        Node next;

        public Node(int x, Node n){
            this.val = x;
            this.next = n;
        }

        public static void printList(Node node){
            while(node != null){
                System.out.print(node.val);
                System.out.print(" -> ");
                node = node.next;
            }
            System.out.println();
        }
    }

    public static Node removeNList(Node node, int x){
        Node prev = new Node(0, null);
        prev.next = node;
        Node current = node;
        Node head = prev;
        while(current != null){
            if(current.val != x){
                prev.next = current;
                prev = current;
            }else{
                prev.next = current.next;
            }
            current = current.next;
        }
        return head.next;
        // return node;
    }


}

