package br.ufmg.dcc.pm.uno.view.cards;

import java.io.File;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SvgUtil {

	public static Element getChildrenByTagName(Node node,String tag){
		NodeList children = node.getChildNodes();
		for(int i = 0;i<children.getLength();i++){
			Node n = children.item(i);
			if(n instanceof Element){
				if(tag.equals(((Element) n).getTagName())){
					return (Element) n;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		scale(0.4, new File("/home/jeronimo/codigos/Graduacao/uno-desktop/src/br/ufmg/dcc/pm/uno/view/cards"));
	}

	public static void scale(double factor,File dir) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		for(File fXmlFile:dir.listFiles()){
			if(fXmlFile.getName().endsWith("svg")){
				System.out.println(fXmlFile.getName());
				Document doc = dBuilder.parse(fXmlFile);
				
				Element g = doc.getDocumentElement();
				g.setAttribute("width",""+(Integer.parseInt(g.getAttribute("width"))*factor));
				g.setAttribute("height",""+(Integer.parseInt(g.getAttribute("height"))*factor));
				
				g = (Element) doc.getElementsByTagName("g").item(0);
				g.setAttribute("transform", MessageFormat.format("scale({0}) {1}", factor, g.getAttribute("transform")));
				
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(fXmlFile);
				transformer.transform(source, result);
			}
		}
	}
}
