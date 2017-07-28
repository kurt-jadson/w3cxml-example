package org.gradle;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlApp {

	private static final ClassLoader LOADER = XmlApp.class.getClassLoader();

	public static void main(String[] args) {
		try (InputStream input = LOADER.getResourceAsStream("org/gradle/resource.xml")) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// String xml = "<a>10</a>";
			// InputSource inputSource = new InputSource(new StringReader(xml));
			
			InputSource inputSource = new InputSource(input);

			Document document = builder.parse(inputSource);

			System.out.println("Tag cUF: " + getValueFromATag(document.getDocumentElement(), "cUF"));
			System.out.println("Attribute infNFe Id: " + getValueFromAttributeInATag(document.getDocumentElement(), "infNFe", "Id"));
			
			Node totalTag = getNodesByTagName(document.getDocumentElement(), "total").item(0); // Get total tag
			Node icmsTag = getNodesByTagName((Element) totalTag, "ICMSTot").item(0); // Get icms tag
			System.out.println("Tag in Tags vNF: " + getValueFromATag((Element) icmsTag, "vNF"));

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static NodeList getNodesByTagName(Element document, String tagName) {
		return document.getElementsByTagName(tagName);
	}
	
	private static String getValueFromATag(Element document, String tagName) {
		return getNodesByTagName(document, tagName).item(0).getTextContent();
	}
	
	private static String getValueFromAttributeInATag(Element document, String tagName, String attributeName) {
		NodeList list = getNodesByTagName(document, tagName);
		NamedNodeMap attributes = list.item(0).getAttributes();
		return attributes.getNamedItem(attributeName).getTextContent();
	}

}
