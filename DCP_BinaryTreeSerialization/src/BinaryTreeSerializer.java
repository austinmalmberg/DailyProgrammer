import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BinaryTreeSerializer {

	public BinaryTreeSerializer() {
		
	}
	
	public static String serialize(Node  root) {
		String serializedObject = "";
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			
			oos.writeObject(root);
			oos.flush();
			
			serializedObject = baos.toString();
		} catch (IOException io) {
			System.out.println(io.toString());
		}
		
		return serializedObject;
	}
	
	public static Node deserialize(String s) {
		Node obj = null;
		
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
			ObjectInputStream si = new ObjectInputStream(bais);
			
			obj = (Node) si.readObject();
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return obj;
	}
	
	public static void main(String[] args) {
		
		Node root = new Node("root");
		
		root.left = new Node("left");
		root.left.left = new Node("left");
		
		root.right = new Node("right");
		
		System.out.println(deserialize(serialize(root)).left.left.val);
	}
}

class Node implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -77354882159785446L;
	
	String val;
	Node left;
	Node right;
	
	Node(String val) {
		this.val = val;
	}
	
	Node(String val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
