package me.WatchingYoutube.ParticleEditor;

import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.Spring;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class ImageResource {

	public static String rescourcespath;

	public static String getPathFromResource(File file, String string) throws Exception {

		String foundPath = null;
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {
				NodeList nList = document.getElementsByTagName("Image");
				for (int i = 0; i < nList.getLength(); i++) {
					
					Node node = nList.item(i);
						Element eElement = (Element) node;
		                System.out.println(eElement.getAttribute("id"));
		                if (eElement.getAttribute("id").equals(string.replace(" ", ""))) {
		                	foundPath = eElement.getAttribute("path");
		                	System.out.println(foundPath);
		                }
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foundPath;

	}

	public static String getResourceFromPath(File file, String string) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {
				NodeList nList = document.getElementsByTagName("Image");
				for (int i = 0; i < nList.getLength(); i++) {
					
					Node node = nList.item(i);
					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
		                System.out.println(eElement.getAttribute("path"));
		                if (eElement.getAttribute("path").equals(string)) {
		                	
		                	
		                	String foundPath = eElement.getAttribute("id");
		                	System.out.println(foundPath);
		                	string = foundPath;
		                }
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
		
	}
	
	public static boolean checkImage(String string) throws IOException {
		
		String newWorldOfGooPath = null;
		
		if (Main.newWOG == true) {
			newWorldOfGooPath = Main.worldofgoopath + "game\\";
		} else if (Main.newWOG == false) {
			newWorldOfGooPath = Main.worldofgoopath;
		}
		
		File resourcesBin = new File(newWorldOfGooPath + "properties\\resources.xml.bin");
		
		File file = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPERESC.xml");
		
		XMLReader.writeBytesToFile(file, AESBin.decryptFile(resourcesBin));
		
		boolean fileFound = false;
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			
			

			if (file != null) {
				NodeList nList = document.getElementsByTagName("Image");
				for (int i = 0; i < nList.getLength(); i++) {
					
					Node node = nList.item(i);
					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
		                System.out.println(eElement.getAttribute("id"));
		                if (eElement.getAttribute("id").equals(string)) {
		                	
		                	fileFound = true;

		                }
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileFound;
		
	}

	public static void createNewResource(File file, String image, String path, String imageName) throws IOException, TransformerException {

		InputStream is = null;
		OutputStream os = null;
		
		String newWorldOfGooPath = null;
		
		String newWorldOfGooResource = null;
		
		if (Main.newWOG == true) {
			newWorldOfGooPath = Main.worldofgoopath + "game\\";
			newWorldOfGooResource = Main.propertiesPath + "resources.xml";
		} else if (Main.newWOG == false) {
			newWorldOfGooPath = Main.worldofgoopath;
			newWorldOfGooResource = Main.propertiesPath + "resources.xml.bin";
		}
		
		File resourceFile = new File(newWorldOfGooResource);
		
		File currentResources = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPERESC.xml");
		
		if (resourceFile.getAbsolutePath().endsWith(".bin")) {
			byte[] decodedResourceBytes = AESBin.decryptFile(resourceFile);
			
			XMLReader.writeBytesToFile(currentResources, decodedResourceBytes);
			
			boolean foundAtr = false;
			
			try {

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				Document document = builder.parse(currentResources);

				document.getDocumentElement().normalize();

				Element root = document.getDocumentElement();
				System.out.println(root.getNodeName());

				if (file != null) {
					
					NodeList nList = document.getElementsByTagName("Image");
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
						
						Node nNode = nList.item(temp);
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;
							
							if (eElement.getAttribute("id").equals(image)) {
								foundAtr = true;
							}
						}
					}
					
					if (foundAtr == false) {
						
						NodeList nList2 = document.getElementsByTagName("Resources");
						
						for (int i = 0; i < nList2.getLength(); i++) {
						
						Element resourcesElement = (Element) nList2.item(i);
						
						if (resourcesElement.getAttribute("id").equals("common")) {
						
						Element newImage = document.createElement("Image");
						
						resourcesElement.appendChild(newImage);
						
						newImage.setAttribute("id", image.replace(" ", ""));
						
						newImage.setAttribute("path", path);
							}
						}
					
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
			         Transformer transformer = transformerFactory.newTransformer();
			         DOMSource source = new DOMSource(document);
			         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			         transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			         StreamResult newCurrentXML = new StreamResult(currentResources);
			         transformer.transform(source, newCurrentXML);
			         
			         byte[] newResourcesBytes = AESBin.encryptFile(currentResources);
			         
			         XMLReader.writeBytesToFile(resourceFile, newResourcesBytes);
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		File checkThis = new File(newWorldOfGooPath + "res\\images\\fx\\" + imageName + ".png");
		
		if (checkThis.getAbsoluteFile() == null) {
			
		try {
			
			is = new FileInputStream(file);
			os = new FileOutputStream(newWorldOfGooPath + "res\\images\\fx\\" + imageName + ".png");
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
		} finally {
			is.close();
			os.close();
		}}
		
	}

	public static ImageData convertToSWT(BufferedImage bufferedImage) {
		if (bufferedImage.getColorModel() instanceof DirectColorModel) {

			DirectColorModel colorModel = (DirectColorModel) bufferedImage.getColorModel();
			PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(),
					colorModel.getBlueMask());
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(),
					colorModel.getPixelSize(), palette);
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					int rgb = bufferedImage.getRGB(x, y);
					int pixel = palette.getPixel(new RGB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF));
					data.setPixel(x, y, pixel);
					if (colorModel.hasAlpha()) {
						data.setAlpha(x, y, (rgb >> 24) & 0xFF);
					}
				}
			}
			return data;
		} else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
			IndexColorModel colorModel = (IndexColorModel) bufferedImage.getColorModel();
			int size = colorModel.getMapSize();
			byte[] reds = new byte[size];
			byte[] greens = new byte[size];
			byte[] blues = new byte[size];
			colorModel.getReds(reds);
			colorModel.getGreens(greens);
			colorModel.getBlues(blues);
			RGB[] rgbs = new RGB[size];
			for (int i = 0; i < rgbs.length; i++) {
				rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
			}
			PaletteData palette = new PaletteData(rgbs);
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(),
					colorModel.getPixelSize(), palette);
			data.transparentPixel = colorModel.getTransparentPixel();
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[1];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					raster.getPixel(x, y, pixelArray);
					data.setPixel(x, y, pixelArray[0]);
				}
			}
			return data;
		} else if (bufferedImage.getColorModel() instanceof ComponentColorModel) {
			ComponentColorModel colorModel = (ComponentColorModel) bufferedImage.getColorModel();
			// ASSUMES: 3 BYTE BGR IMAGE TYPE
			PaletteData palette = new PaletteData(0x0000FF, 0x00FF00, 0xFF0000);
			ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(),
					colorModel.getPixelSize(), palette);

			data.transparentPixel = -1;
			WritableRaster raster = bufferedImage.getRaster();
			int[] pixelArray = new int[3];
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					raster.getPixel(x, y, pixelArray);
					int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
					data.setPixel(x, y, pixel);
				}
			}
			return data;
		}
		return null;
	}
	
	public static ByteArrayInputStream pngConvert(InputStream is) {

		byte[] data = null;
		
		try {
		
        BufferedImage inputImage = ImageIO.read(is);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        System.out.println(inputImage);
        
        ImageIO.write(inputImage, "png", baos);
        
        data = baos.toByteArray();
        
		} catch(IOException e) {
			e.printStackTrace();
		}
		
        
        
		return new ByteArrayInputStream(data);

	}

	public static boolean checkPath(String string) throws IOException {
		
		String newWorldOfGooPath = null;
		
		if (Main.newWOG == true) {
			newWorldOfGooPath = Main.worldofgoopath + "game\\";
		} else if (Main.newWOG == false) {
			newWorldOfGooPath = Main.worldofgoopath;
		}
		
		File resourcesBin = new File(newWorldOfGooPath + "properties\\resources.xml.bin");
		
		File file = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPERESC.xml");
		
		XMLReader.writeBytesToFile(file, AESBin.decryptFile(resourcesBin));
		
		boolean fileFound = false;
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());
			
			

			if (file != null) {
				NodeList nList = document.getElementsByTagName("Image");
				for (int i = 0; i < nList.getLength(); i++) {
					
					Node node = nList.item(i);
					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
		                System.out.println(eElement.getAttribute("path"));
		                if (eElement.getAttribute("path").equals(string)) {
		                	
		                	fileFound = true;

		                }
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileFound;
		
	}

}
