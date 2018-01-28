package com.florianingerl.xml.parsing;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.florianingerl.util.regex.CaptureTreeNode;
import com.florianingerl.util.regex.Matcher;
import com.florianingerl.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		String xml = IOUtils.toString(Main.class.getClassLoader().getResourceAsStream("sample.xml"));
		
		System.out.println(xml);
		
		Pattern p = Pattern
				.compile(IOUtils.toString(Main.class.getClassLoader().getResourceAsStream("xml.regex")));
	
		Matcher m = p.matcher(xml);
		m.setMode(Matcher.CAPTURE_TREE);
		
		if(!m.matches()) {
			System.out.println("This isn't valid xml!");
			return;
		}
			
		printTreeNode(m.captureTree().getRoot(),0);
	}
	
	private static void printTreeNode(CaptureTreeNode node, int tabs) {
		for(int i=0; i < tabs; ++i) System.out.print("\t");
		if(node.getGroupName()!=null) System.out.print(node.getGroupName());
		else System.out.print(node.getGroupNumber());
		if(node.getChildren().isEmpty() ) System.out.print("=" + node.getCapture().getValue());
		
		System.out.println();
		for(CaptureTreeNode child : node.getChildren() ) {
			printTreeNode(child, tabs + 1);
		}
	}

}
