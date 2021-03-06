
public class Node implements Comparable<Node>{
	public Node leftChild;
	public Node rightChild;
	public int value;
	public int number = -1;

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Node(int number, int value) {
		this.leftChild = null;
		this.rightChild = null;
		this.value = value;
		this.number = number;
	}

	public Node(Node left, Node right, int value) {
		this.leftChild = left;
		this.rightChild = right;
		this.value = value;
	}

	public static Node meld(Node node1, Node node2) {
		int temp = node1.value + node2.value;
		Node node = new Node(node1, node2, temp);
		return node;
	}

	@Override
	public int compareTo(Node node) {
		return (new Integer(value)).compareTo(new Integer(node.value));
	}
}