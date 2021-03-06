import java.util.*;
import java.io.*;
public class decoder{
	public Node rootNode = new Node(-1, -1);
	public void buildTree(String codeTable1) throws Exception{
		String[] string;
		BufferedReader decodeTable = new BufferedReader(new FileReader(new File(codeTable1)));
		String str = null;
		int number;
		char a;
		String code;
      	while((str = decodeTable.readLine()) != null) {
        	Node node = rootNode;
        	string = str.split(" ");
        	number = Integer.parseInt(string[0]);
        	code = string[1];
        	for (int i = 0; i < code.length() - 1; i++) {
        		if (code.charAt(i) == '0') {
        			if (node.leftChild == null) {
        				node.leftChild = new Node(-1, -1);
        			}
        			node = node.leftChild;
        		} else { 
        			if (node.rightChild == null)	{
        				node.rightChild = new Node(-1, -1);
        			}
        			node = node.rightChild;
        		}
        	}

        	if (code.charAt(code.length() - 1) == '0') {
        		node.leftChild = new Node(number, 0);
        	} else {
        		node.rightChild = new Node(number, 0);
        	}	
		}
	}

	public void decodeData(String encoded1) throws Exception{
		DataInputStream encoded2 = new DataInputStream( new BufferedInputStream( new FileInputStream( new File(encoded1))));
		File decoded = new File("decoded.txt");
		decoded.createNewFile();
		PrintWriter p = new PrintWriter(decoded);
		byte temp0;
		String temp;
		Node pointer = rootNode;
		while (encoded2.available() > 0) {
			temp0 = encoded2.readByte();
			temp = byteToBit(temp0);
			//System.out.println(temp);
			for (int i = 0; i < 8; i++) {
				if (temp.charAt(i) == '0') {
					pointer = pointer.leftChild;
					if (pointer.value == 0) {
						p.println(pointer.number + "");
						pointer = rootNode;
					}
				} else {
					pointer = pointer.rightChild;
					if (pointer.value == 0) {
						p.println(pointer.number + "");
						pointer = rootNode;
					}
				}
			}
		}
		p.close();
	}

	public static void main(String[] args) throws Exception{
		decoder d = new decoder();
		d.buildTree(args[1]);
		d.decodeData(args[0]);
	}
	//Byte to String
    public static String byteToBit(byte b) {  
        return ""  
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
    }  
}