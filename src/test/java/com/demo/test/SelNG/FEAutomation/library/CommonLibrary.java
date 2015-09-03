package com.demo.test.SelNG.FEAutomation.library;

import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CommonLibrary {	
	/**
	 * Method to get path from XPathList.xml file
	 * @app=name of the app e.g Youtube
	 * @element=element to fetch e.g Playbutton
	 * @identifier=how to identify element e.g Link,id etc 
	 **/
	
	public static String getPath(String app,String element ,String identifier)
	{
		try
		{
			//create Document object using XPathList.xml file
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.parse(new File("src/test/resources/Selenium/data/objectdata/xpathList.xml"));
			
			//get app node list
			NodeList nodeList=doc.getElementsByTagName(app);
			//getting first node from list
			Node node=nodeList.item(0);
			//if node is element node
			if(node.getNodeType()==Node.ELEMENT_NODE)
			{
				
				Element elementNode=(Element)node; 
				//get node list given by @element
				NodeList childNodes=elementNode.getElementsByTagName(element);
				//loop through all the @element nodes 
				for(int i=0; i<childNodes.getLength();i++)
				{	
					//fetch @element node
					Node childNode=childNodes.item(i);
					//if @element node is element node
					if(childNode.getNodeType()==Node.ELEMENT_NODE)
					{
							//fetch all the attributes nodes of @element node
							NamedNodeMap attributeNodes=childNode.getAttributes();
							//loop through all the attributes nodes
							for(int j=0; j<attributeNodes.getLength();j++)
							{
								//fetch attribute node
								Node attributeNode=attributeNodes.item(j);
								//if attributeNode is attribute node
								if(attributeNode.getNodeType()==Node.ATTRIBUTE_NODE)
								{		
									//check node name with @identifier and return childNode value
									if(attributeNode.getNodeValue().equals(identifier))
									{
										return childNode.getTextContent();
									}
								}
								//if atttributeNode is not an attribute node, print on console and exit
								else
								{
									System.out.println("#####"+attributeNode+"is not a attribute node"+"#######");
								}
							}
					}
					//if @element node is not an element node, print on console and exit
					else
					{
						System.out.println("#####"+childNode+"is not a element node"+"#######");
					}
				}
				
			}
			//if node is not an element node, print on console and exit
			else
			{
				System.out.println("#####"+node+"is not a element node"+"#######");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
    public static void openUrlIfNeeded(WebDriver driver, String url) throws InterruptedException {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }
	
}
