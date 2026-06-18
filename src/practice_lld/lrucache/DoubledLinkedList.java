package practice_lld.lrucache;

public class DoubledLinkedList<K,V> {
    private final Node<K,V> left;
    private final Node<K,V> right;

    public DoubledLinkedList(){
        left=new Node<>(null, null);
        right=new Node<>(null, null);
        left.next = right;
        right.prev = left;
    }

    public void remove(Node<K,V> node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void insertFirst(Node<K,V> node){
        node.next = left.next;
        node.prev = left;
        left.next.prev = node;
        left.next = node;
    }

    public void moveFront(Node<K,V> node){
        remove(node);
        insertFirst(node);
    }

    public Node<K,V> removeLast(){
        if(right.prev==left){
            return null;
        }
        Node<K,V> node = right.prev;
        remove(node);
        return node;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();

        Node<K, V> curr = left.next; // assuming dummy head

        while (curr != right) {       // assuming dummy tail
            sb.append(curr.key)
                    .append("=")
                    .append(curr.value)
                    .append(" -> ");

            curr = curr.next;
        }

        return sb.toString();
    }

}
