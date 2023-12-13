package me.WatchingYoutube.ParticleEditor;

import org.eclipse.swt.widgets.Table;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReader {

	public static File XMLToParse;
	public static File Resources;

	public static Node resourcesNode;

	private static boolean isRequestEffect = false;

	// Single Effect Values

	private static boolean name;
	private static boolean maxparticles;
	private static boolean rate;
	private static boolean margin;

	private static boolean image;
	private static boolean path;

	private static boolean additive;
	private static boolean dampening;
	private static boolean directed;
	private static boolean fade;
	private static boolean lifespan;
	private static boolean scale;
	private static boolean finalscale;
	private static boolean speed;
	private static boolean acceleration;
	private static boolean movedir;
	private static boolean movedirvar;
	private static boolean rotation;
	private static boolean rotspeed;

	private static boolean amp;
	private static boolean axis;
	private static boolean freq;
	private static boolean phaseshift;

	private static String resourcesLocation;

	public static void ParseEntireFile(String string) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(XMLToParse);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			NodeList nList = document.getElementsByTagName("particleeffect");
			NodeList nListA = document.getElementsByTagName("ambientparticleeffect");
			NodeList nList2 = document.getElementsByTagName("particle");

			Main.fullparticleslist.clear();

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				Node nNode2 = nList2.item(temp);

				// particleeffects
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Element eElement2 = (Element) nNode2;

					System.out.println("\nCurrent Element : " + nNode.getNodeName());

					System.out.println("Name : " + eElement.getAttribute("name"));
					Main.fullparticleslist.add(eElement.getAttribute("name"));

					System.out.println("MaxParticles : " + eElement.getAttribute("maxparticles"));

					System.out.println("Rate : " + eElement.getAttribute("rate"));

					System.out.println("\nCurrent Element : " + nNode2.getNodeName());

					System.out.println("Particle Image : " + eElement2.getAttribute("image"));

					System.out.println("Rotation : " + eElement2.getAttribute("rotation"));

					System.out.println("Rotspeed : " + eElement2.getAttribute("rotspeed"));

					System.out.println("Directed : " + eElement2.getAttribute("directed"));

					System.out.println("Scale : " + eElement2.getAttribute("scale"));

					System.out.println("FinalScale : " + eElement2.getAttribute("finalscale"));

					System.out.println("Fade : " + eElement2.getAttribute("fade"));

					System.out.println("Additive : " + eElement2.getAttribute("additive"));

					System.out.println("Lifespan : " + eElement2.getAttribute("lifespan"));

					System.out.println("Speed : " + eElement2.getAttribute("speed"));

					System.out.println("Movedir : " + eElement2.getAttribute("movedir"));

					System.out.println("MovedirVar : " + eElement2.getAttribute("movedirvar"));

					System.out.println("Acceleration : " + eElement2.getAttribute("acceleration"));

				}

			}

			for (int temp = 0; temp < nListA.getLength(); temp++) {

				Node nNodeA = nListA.item(temp);
				Node nNode2 = nList2.item(temp);

				// ambientparticleeffect
				if (nNodeA.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNodeA;
					Element eElement2 = (Element) nNode2;

					System.out.println("\nCurrent Element : " + nNodeA.getNodeName());

					System.out.println("Name : " + eElement.getAttribute("name"));
					Main.fullparticleslist.add(eElement.getAttribute("name"));

					System.out.println("MaxParticles : " + eElement.getAttribute("maxparticles"));

					System.out.println("Rate : " + eElement.getAttribute("rate"));

					System.out.println("\nCurrent Element : " + nNode2.getNodeName());

					System.out.println("Particle Image : " + eElement2.getAttribute("image"));

					System.out.println("Rotation : " + eElement2.getAttribute("rotation"));

					System.out.println("Rotspeed : " + eElement2.getAttribute("rotspeed"));

					System.out.println("Directed : " + eElement2.getAttribute("directed"));

					System.out.println("Scale : " + eElement2.getAttribute("scale"));

					System.out.println("FinalScale : " + eElement2.getAttribute("finalscale"));

					System.out.println("Fade : " + eElement2.getAttribute("fade"));

					System.out.println("Additive : " + eElement2.getAttribute("additive"));

					System.out.println("Lifespan : " + eElement2.getAttribute("lifespan"));

					System.out.println("Speed : " + eElement2.getAttribute("speed"));

					System.out.println("Movedir : " + eElement2.getAttribute("movedir"));

					System.out.println("MovedirVar : " + eElement2.getAttribute("movedirvar"));

					System.out.println("Acceleration : " + eElement2.getAttribute("acceleration"));

				}

			}

		} catch (Exception e) {
			MessageBoxHelper.giveErrorMessage("Failed to Parse the XML file. Make sure it is a proper fx XML.");
			e.printStackTrace();
		}

	}

	public static void ParseSingleEffect(String string) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(XMLToParse);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (XMLToParse != null) {

				if (Main.newWOG == true) {
					resourcesLocation = "resources.xml";
				} else if (Main.newWOG == false) {
					resourcesLocation = "resources.xml.xml";
				}

				NodeList nList = document.getElementsByTagName("particleeffect");
				NodeList nListA = document.getElementsByTagName("ambientparticleeffect");

				boolean dontTryMe = false;

				for (int i = 0; i < nList.getLength(); i++) {

					Node node = nList.item(i);

					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
						System.out.println(eElement.getAttribute("name"));
						if (eElement.getAttribute("name").equals(string)) {

							dontTryMe = true;

							// Clear existing arrays (Only those need to be cleared)

							Main.additive.clear();
							Main.dampening.clear();
							Main.directed.clear();
							Main.fade.clear();
							Main.lifespan.clear();
							Main.singlelifespan.clear();
							Main.finalscale.clear();
							Main.singlescale.clear();
							Main.movedir.clear();
							Main.movedirvar.clear();
							Main.acceleration.clear();
							Main.scale.clear();
							Main.singleacceleration.clear();
							Main.rotation.clear();
							Main.singlerotation.clear();
							Main.rotspeed.clear();
							Main.singlerotspeed.clear();
							Main.speed.clear();
							Main.singlespeed.clear();

							Main.amp.clear();
							Main.singleamp.clear();
							Main.freq.clear();
							Main.singlefreq.clear();
							Main.phaseshift.clear();
							Main.singlephaseshift.clear();
							Main.axis.clear();

							Main.image.clear();
							Main.path.clear();

							Main.imageIndex.clear();
							Main.pathIndex.clear();

							Main.numberOfParticles = 0;
							Main.numberOfAxialSinOffset.clear();

							// Main Values

							Main.type = eElement.getNodeName();

							Main.maxparticles = Integer.parseInt(eElement.getAttribute("maxparticles"));
							if (eElement.getAttribute("rate") != null && eElement.getAttribute("rate").length() > 0) {
								Main.rate = Float.parseFloat(eElement.getAttribute("rate"));
							}
							if (eElement.getAttribute("margin") != null
									&& eElement.getAttribute("margin").length() > 0) {
								Main.margin = Integer.parseInt(eElement.getAttribute("margin"));
							}

							// The other stuff or as I like to call it the "fun stuff" D:

							NodeList nList2 = eElement.getChildNodes();

							// Images and their Paths

							for (int i2 = 0; i2 < nList2.getLength(); i2++) {

								Node node2 = nList2.item(i2);

								if (node2.getNodeType() == Element.ELEMENT_NODE) {
									Element eElement2 = (Element) node2;

									if (eElement.getAttribute("name") != null) {
										Main.numberOfParticles++;
									}
									

									if (eElement2.getAttribute("image") != null
											&& eElement2.getAttribute("image").length() > 0) {

										System.out.println(eElement2.getAttribute("image"));

										String[] imagesToAdd = eElement2.getAttribute("image").split(",");

										Main.imageIndex.add(eElement2.getAttribute("image"));

										List<String> fixedLenghtList = Arrays.asList(imagesToAdd);

										ArrayList<String> listOfImages = new ArrayList<String>(fixedLenghtList);

										for (int numimage = 0; numimage < listOfImages.size(); numimage++) {

											Main.image.add(listOfImages.get(numimage).replace(" ", ""));

										}

										System.out.println(Main.imageIndex);

									} else {
										Main.imageIndex.add(null);
										Main.image.add(null);
									}

									if (eElement2.getAttribute("image") != null
											&& eElement2.getAttribute("image").length() > 0) {

										String[] pathsToAdd = eElement2.getAttribute("image").split(",");

										List<String> fixedLenghtList2 = Arrays.asList(pathsToAdd);

										ArrayList<String> listOfPaths = new ArrayList<String>(fixedLenghtList2);

										for (int numpath = 0; numpath < listOfPaths.size(); numpath++) {

											Main.path.add(ImageResource.getPathFromResource(
													new File(Main.propertiesPath + resourcesLocation),
													listOfPaths.get(numpath).replace(" ", "")));
										}

										int sum = 0;

										for (int i1 = 0; i1 < Main.pathIndex.size(); i1++) {
											sum += Main.pathIndex.get(i1);
										}

										Main.pathIndex.add(listOfPaths.size() + sum);

										System.out.println(Main.pathIndex);
										

									} else {
										Main.path.add(null);
										Main.pathIndex.add(null);
									}

									// The Particle Values, prepare yourselves D: D: D:
									if (eElement2.getAttribute("additive") != null
											&& eElement2.getAttribute("additive").length() > 0) {
										Main.additive.add(Boolean.parseBoolean(eElement2.getAttribute("additive")));
									} else {
										Main.additive.add(null);
									}
									if (eElement2.getAttribute("dampening") != null
											&& eElement2.getAttribute("dampening").length() > 0) {

										Main.dampening.add(Float.parseFloat(eElement2.getAttribute("dampening")));

									} else {
										Main.dampening.add(null);
									}

									if (eElement2.getAttribute("directed") != null
											&& eElement2.getAttribute("directed").length() > 0) {
										Main.directed.add(Boolean.parseBoolean(eElement2.getAttribute("directed")));
									} else {
										Main.directed.add(null);
									}
									if (eElement2.getAttribute("fade") != null
											&& eElement2.getAttribute("fade").length() > 0) {
										Main.fade.add(Boolean.parseBoolean(eElement2.getAttribute("fade")));
									} else {
										Main.fade.add(false);
									}

									if (eElement2.getAttribute("lifespan") != null
											&& eElement2.getAttribute("lifespan").length() > 0) {

										String[] lifespanToAdd = (eElement2.getAttribute("lifespan").split(","));

										Main.singlelifespan.add(eElement2.getAttribute("lifespan"));

										for (int numls = 0; numls < lifespanToAdd.length; numls++) {

											Main.lifespan.add(Float.parseFloat(lifespanToAdd[numls]));

										}
									} else {
										Main.singlelifespan.add(null);
										Main.lifespan.add(null);
									}

									if (eElement2.getAttribute("scale") != null
											&& eElement2.getAttribute("scale").length() > 0) {

										String[] scaleToAdd = (eElement2.getAttribute("scale").split(","));

										Main.singlescale.add(eElement2.getAttribute("scale"));

										for (int numls = 0; numls < scaleToAdd.length; numls++) {

											Main.scale.add(Float.parseFloat(scaleToAdd[numls]));

										}
									} else {
										Main.singlescale.add(null);
										Main.scale.add(null);
									}

									if (eElement2.getAttribute("finalscale") != null
											&& eElement2.getAttribute("finalscale").length() > 0) {

										Main.finalscale.add(Float.parseFloat(eElement2.getAttribute("finalscale")));

									} else {
										Main.finalscale.add(null);
									}

									if (eElement2.getAttribute("speed") != null
											&& eElement2.getAttribute("speed").length() > 0) {

										String[] speedToAdd = (eElement2.getAttribute("speed").split(","));

										Main.singlespeed.add(eElement2.getAttribute("speed"));

										for (int numls = 0; numls < speedToAdd.length; numls++) {

											Main.speed.add(Float.parseFloat(speedToAdd[numls]));

										}

									} else {
										Main.singlespeed.add(null);
										Main.speed.add(null);
									}

									if (eElement2.getAttribute("acceleration") != null
											&& eElement2.getAttribute("acceleration").length() > 0) {

										String[] accelerationToAdd = (eElement2.getAttribute("acceleration")
												.split(","));
										Main.singleacceleration.add(eElement2.getAttribute("acceleration"));

										for (int numls = 0; numls < accelerationToAdd.length; numls++) {

											Main.acceleration.add(Float.parseFloat(accelerationToAdd[numls]));

										}
									} else {
										Main.singleacceleration.add(null);
										Main.acceleration.add(null);
									}

									if (eElement2.getAttribute("movedir") != null
											&& eElement2.getAttribute("movedir").length() > 0) {

										Main.movedir.add(Integer.parseInt(eElement2.getAttribute("movedir")));

									} else {
										Main.movedir.add(null);
									}

									if (eElement2.getAttribute("movedirvar") != null
											&& eElement2.getAttribute("movedirvar").length() > 0) {

										Main.movedirvar.add(Integer.parseInt(eElement2.getAttribute("movedirvar")));

									} else {
										Main.movedirvar.add(null);
									}

									if (eElement2.getAttribute("rotation") != null
											&& eElement2.getAttribute("rotation").length() > 0) {

										String[] rotationToAdd = (eElement2.getAttribute("rotation").split(","));

										Main.singlerotation.add(eElement2.getAttribute("rotation"));

										for (int numls = 0; numls < rotationToAdd.length; numls++) {

											Main.rotation.add(Float.parseFloat(rotationToAdd[numls]));

										}
									} else {
										Main.rotation.add(null);

										Main.singlerotation.add(null);
									}

									if (eElement2.getAttribute("rotspeed") != null
											&& eElement2.getAttribute("rotspeed").length() > 0) {

										String[] rotspeedToAdd = (eElement2.getAttribute("rotspeed").split(","));
										Main.singlerotspeed.add(eElement2.getAttribute("rotspeed"));

										for (int numls = 0; numls < rotspeedToAdd.length; numls++) {

											Main.rotspeed.add(Float.parseFloat(rotspeedToAdd[numls]));

										}
									} else {
										Main.rotspeed.add(null);

										Main.singlerotspeed.add(null);
									}

									// Axis or whatever its called

									NodeList nList3 = eElement2.getChildNodes();

									for (int axisI = 0; axisI < nList3.getLength(); axisI++) {

										Node node3 = nList3.item(axisI);

										if (node3.getNodeType() == Element.ELEMENT_NODE) {
											Element eElement3 = (Element) node3;

											if (eElement3.getAttribute("amp") != null
													&& eElement3.getAttribute("amp").length() > 0) {

												String[] ampToAdd = (eElement3.getAttribute("amp").split(","));

												Main.singleamp.add(eElement3.getAttribute("amp"));

												for (int numls = 0; numls < ampToAdd.length; numls++) {

													Main.amp.add(Integer.parseInt(ampToAdd[numls]));
												}
											} else {
												Main.singleamp.add("1,2");
												Main.amp.add(1);
												Main.amp.add(2);
											}

											if (eElement3.getAttribute("axis") != null
													&& eElement3.getAttribute("axis").length() > 0) {

												Main.axis.add(eElement3.getAttribute("axis"));

											} else {
												Main.axis.add(null);
											}

											if (eElement3.getAttribute("freq") != null
													&& eElement3.getAttribute("freq").length() > 0) {

												String[] freqToAdd = (eElement3.getAttribute("freq").split(","));

												Main.singlefreq.add(eElement3.getAttribute("freq"));

												for (int numls = 0; numls < freqToAdd.length; numls++) {

													Main.freq.add(Float.parseFloat(freqToAdd[numls]));
												}
											} else {
												Main.freq.add(null);
												
												Main.singlefreq.add(null);
											}

											if (eElement3.getAttribute("phaseshift") != null
													&& eElement3.getAttribute("phaseshift").length() > 0) {

												String[] phaseshiftToAdd = (eElement3.getAttribute("phaseshift")
														.split(","));

												Main.singlephaseshift.add(eElement3.getAttribute("phaseshift"));

												for (int numls = 0; numls < phaseshiftToAdd.length; numls++) {

													Main.phaseshift.add(Float.parseFloat(phaseshiftToAdd[numls]));
												}

											} else {
												Main.phaseshift.add(null);
												
												Main.singlephaseshift.add(null);
											}
										}
									}
									
									Main.numberOfAxialSinOffset.add(Main.singleamp.size());

								}
							}
						}
					}
				}

				for (int i = 0; i < nListA.getLength(); i++) {

					Node node = nListA.item(i);

					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
						System.out.println(eElement.getAttribute("name"));
						if (eElement.getAttribute("name").equals(string)) {

							if (dontTryMe == false) {

								// Clear existing arrays (Only those need to be cleared)

								Main.additive.clear();
								Main.dampening.clear();
								Main.directed.clear();
								Main.fade.clear();
								Main.lifespan.clear();
								Main.singlelifespan.clear();
								Main.finalscale.clear();
								Main.singlescale.clear();
								Main.movedir.clear();
								Main.movedirvar.clear();
								Main.acceleration.clear();
								Main.scale.clear();
								Main.singleacceleration.clear();
								Main.rotation.clear();
								Main.singlerotation.clear();
								Main.rotspeed.clear();
								Main.singlerotspeed.clear();
								Main.speed.clear();
								Main.singlespeed.clear();

								Main.amp.clear();
								Main.singleamp.clear();
								Main.freq.clear();
								Main.singlefreq.clear();
								Main.phaseshift.clear();
								Main.singlephaseshift.clear();
								Main.axis.clear();

								Main.image.clear();
								Main.path.clear();

								Main.imageIndex.clear();
								Main.pathIndex.clear();

								Main.numberOfParticles = 0;
								Main.numberOfAxialSinOffset.clear();

								// Main Values

								Main.type = eElement.getNodeName();

								System.out.println(eElement.getNodeName());

								Main.maxparticles = Integer.parseInt(eElement.getAttribute("maxparticles"));
								if (eElement.getAttribute("rate") != null
										&& eElement.getAttribute("rate").length() > 0) {
									Main.rate = Float.parseFloat(eElement.getAttribute("rate"));
								}
								if (eElement.getAttribute("margin") != null
										&& eElement.getAttribute("margin").length() > 0) {
									Main.margin = Integer.parseInt(eElement.getAttribute("margin"));
								}

								// The other stuff or as I like to call it the "fun stuff" D:

								NodeList nList2 = eElement.getChildNodes();

								// Images and their Paths

								for (int i2 = 0; i2 < nList2.getLength(); i2++) {

									Node node2 = nList2.item(i2);

									if (node2.getNodeType() == Element.ELEMENT_NODE) {
										Element eElement2 = (Element) node2;
										
										if (eElement.getAttribute("name") != null) {
											Main.numberOfParticles++;
										}

										if (eElement2.getAttribute("image") != null
												&& eElement2.getAttribute("image").length() > 0) {

											System.out.println(eElement2.getAttribute("image"));

											String[] imagesToAdd = eElement2.getAttribute("image").split(",");

											Main.imageIndex.add(eElement2.getAttribute("image"));

											List<String> fixedLenghtList = Arrays.asList(imagesToAdd);

											ArrayList<String> listOfImages = new ArrayList<String>(fixedLenghtList);

											for (int numimage = 0; numimage < listOfImages.size(); numimage++) {

												Main.image.add(listOfImages.get(numimage));

											}

											System.out.println(Main.imageIndex);

										} else {
											Main.imageIndex.add(null);
											Main.image.add(null);
										}

										if (eElement2.getAttribute("image") != null
												&& eElement2.getAttribute("image").length() > 0) {

											String[] pathsToAdd = eElement2.getAttribute("image").split(",");

											List<String> fixedLenghtList2 = Arrays.asList(pathsToAdd);

											ArrayList<String> listOfPaths = new ArrayList<String>(fixedLenghtList2);
											
											System.out.println("List of paths: " +listOfPaths);

											for (int numpath = 0; numpath < listOfPaths.size(); numpath++) {

												Main.path.add(ImageResource.getPathFromResource(
														new File(Main.propertiesPath + resourcesLocation),
														listOfPaths.get(numpath)).replace(" ", ""));
											}

											
											
											int sum = 0;

											for (int i1 = 0; i1 < Main.pathIndex.size(); i1++) {
												sum += Main.pathIndex.get(i1);
											}

											Main.pathIndex.add(listOfPaths.size() + sum);

											System.out.println(Main.pathIndex);

										} else {
											Main.path.add(null);
											Main.pathIndex.add(null);
										}

										// The Particle Values, prepare yourselves D: D: D:
										if (eElement2.getAttribute("additive") != null
												&& eElement2.getAttribute("additive").length() > 0) {
											Main.additive.add(Boolean.parseBoolean(eElement2.getAttribute("additive")));
										} else {
											Main.additive.add(null);
										}
										if (eElement2.getAttribute("dampening") != null
												&& eElement2.getAttribute("dampening").length() > 0) {

											Main.dampening.add(Float.parseFloat(eElement2.getAttribute("dampening")));

										} else {
											Main.dampening.add(null);
										}

										if (eElement2.getAttribute("directed") != null
												&& eElement2.getAttribute("directed").length() > 0) {
											Main.directed.add(Boolean.parseBoolean(eElement2.getAttribute("directed")));
										} else {
											Main.directed.add(null);
										}
										if (eElement2.getAttribute("fade") != null
												&& eElement2.getAttribute("fade").length() > 0) {
											Main.fade.add(Boolean.parseBoolean(eElement2.getAttribute("fade")));
										} else {
											Main.fade.add(false);
										}

										if (eElement2.getAttribute("lifespan") != null
												&& eElement2.getAttribute("lifespan").length() > 0) {

											String[] lifespanToAdd = (eElement2.getAttribute("lifespan").split(","));

											Main.singlelifespan.add(eElement2.getAttribute("lifespan"));

											for (int numls = 0; numls < lifespanToAdd.length; numls++) {

												Main.lifespan.add(Float.parseFloat(lifespanToAdd[numls]));

											}
										} else {
											Main.singlelifespan.add(null);
											Main.lifespan.add(null);
										}

										if (eElement2.getAttribute("scale") != null
												&& eElement2.getAttribute("scale").length() > 0) {

											String[] scaleToAdd = (eElement2.getAttribute("scale").split(","));

											Main.singlescale.add(eElement2.getAttribute("scale"));

											for (int numls = 0; numls < scaleToAdd.length; numls++) {

												Main.scale.add(Float.parseFloat(scaleToAdd[numls]));

											}
										} else {
											Main.singlescale.add(null);
											Main.scale.add(null);
										}

										if (eElement2.getAttribute("finalscale") != null
												&& eElement2.getAttribute("finalscale").length() > 0) {

											Main.finalscale.add(Float.parseFloat(eElement2.getAttribute("finalscale")));

										} else {
											Main.finalscale.add(null);
										}

										if (eElement2.getAttribute("speed") != null
												&& eElement2.getAttribute("speed").length() > 0) {

											String[] speedToAdd = (eElement2.getAttribute("speed").split(","));

											Main.singlespeed.add(eElement2.getAttribute("speed"));

											for (int numls = 0; numls < speedToAdd.length; numls++) {

												Main.speed.add(Float.parseFloat(speedToAdd[numls]));

											}

										} else {
											Main.singlespeed.add(null);
											Main.speed.add(null);
										}

										if (eElement2.getAttribute("acceleration") != null
												&& eElement2.getAttribute("acceleration").length() > 0) {

											String[] accelerationToAdd = (eElement2.getAttribute("acceleration")
													.split(","));
											Main.singleacceleration.add(eElement2.getAttribute("acceleration"));

											for (int numls = 0; numls < accelerationToAdd.length; numls++) {

												Main.acceleration.add(Float.parseFloat(accelerationToAdd[numls]));

											}
										} else {
											Main.singleacceleration.add(null);
											Main.acceleration.add(null);
										}

										if (eElement2.getAttribute("movedir") != null
												&& eElement2.getAttribute("movedir").length() > 0) {

											Main.movedir.add(Integer.parseInt(eElement2.getAttribute("movedir")));

										} else {
											Main.movedir.add(null);
										}

										if (eElement2.getAttribute("movedirvar") != null
												&& eElement2.getAttribute("movedirvar").length() > 0) {

											Main.movedirvar.add(Integer.parseInt(eElement2.getAttribute("movedirvar")));

										} else {
											Main.movedirvar.add(null);
										}

										if (eElement2.getAttribute("rotation") != null
												&& eElement2.getAttribute("rotation").length() > 0) {

											String[] rotationToAdd = (eElement2.getAttribute("rotation").split(","));

											Main.singlerotation.add(eElement2.getAttribute("rotation"));

											for (int numls = 0; numls < rotationToAdd.length; numls++) {

												Main.rotation.add(Float.parseFloat(rotationToAdd[numls]));

											}
										} else {
											Main.rotation.add(null);

											Main.singlerotation.add(null);
										}

										if (eElement2.getAttribute("rotspeed") != null
												&& eElement2.getAttribute("rotspeed").length() > 0) {

											String[] rotspeedToAdd = (eElement2.getAttribute("rotspeed").split(","));
											Main.singlerotspeed.add(eElement2.getAttribute("rotspeed"));

											for (int numls = 0; numls < rotspeedToAdd.length; numls++) {

												Main.rotspeed.add(Float.parseFloat(rotspeedToAdd[numls]));

											}
										} else {
											Main.rotspeed.add(null);

											Main.singlerotspeed.add(null);
										}

										// Axis or whatever its called

										NodeList nList3 = eElement2.getChildNodes();

										for (int axisI = 0; axisI < nList3.getLength(); axisI++) {

											Node node3 = nList3.item(axisI);

											if (node3.getNodeType() == Element.ELEMENT_NODE) {
												Element eElement3 = (Element) node3;

												if (eElement3.getAttribute("amp") != null
														&& eElement3.getAttribute("amp").length() > 0) {

													String[] ampToAdd = (eElement3.getAttribute("amp").split(","));

													Main.singleamp.add(eElement3.getAttribute("amp"));

													for (int numls = 0; numls < ampToAdd.length; numls++) {

														Main.amp.add(Integer.parseInt(ampToAdd[numls]));
													}
												} else {
													Main.singleamp.add("1,2");
													Main.amp.add(1);
													Main.amp.add(2);
												}

												if (eElement3.getAttribute("axis") != null
														&& eElement3.getAttribute("axis").length() > 0) {

													Main.axis.add(eElement3.getAttribute("axis"));

												} else {
													Main.axis.add(null);
												}

												if (eElement3.getAttribute("freq") != null
														&& eElement3.getAttribute("freq").length() > 0) {

													String[] freqToAdd = (eElement3.getAttribute("freq").split(","));

													Main.singlefreq.add(eElement3.getAttribute("freq"));

													for (int numls = 0; numls < freqToAdd.length; numls++) {

														Main.freq.add(Float.parseFloat(freqToAdd[numls]));
													}
												} else {
													Main.freq.add(null);
													
													Main.singlefreq.add(null);
												}

												if (eElement3.getAttribute("phaseshift") != null
														&& eElement3.getAttribute("phaseshift").length() > 0) {

													String[] phaseshiftToAdd = (eElement3.getAttribute("phaseshift")
															.split(","));

													Main.singlephaseshift.add(eElement3.getAttribute("phaseshift"));

													for (int numls = 0; numls < phaseshiftToAdd.length; numls++) {

														Main.phaseshift.add(Float.parseFloat(phaseshiftToAdd[numls]));

													}
												} else {
													Main.phaseshift.add(null);
													
													Main.singlephaseshift.add(null);
												}
											}
										}
										
										Main.numberOfAxialSinOffset.add(Main.singleamp.size());

									}
								}
							}
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
	}

	/*
	 * Iteration #2 doesn't work try {
	 * 
	 * XMLInputFactory factory = XMLInputFactory.newInstance(); XMLEventReader
	 * eventReader = factory.createXMLEventReader(new FileReader(XMLToParse));
	 * 
	 * String requestedEffect = string;
	 * 
	 * while (eventReader.hasNext()) { XMLEvent event = eventReader.nextEvent();
	 * 
	 * switch (event.getEventType()) { case XMLStreamConstants.START_ELEMENT:
	 * StartElement startElement = event.asStartElement(); String name =
	 * startElement.getName().getLocalPart();
	 * 
	 * if (name.equals("particleeffect") || name.equals("ambientparticleeffect")) {
	 * System.out.println(name); Iterator<Attribute> attributes =
	 * startElement.getAttributes(); String reqFX = attributes.next().getValue();
	 * 
	 * if (attributes.next().getValue().equals(Main.name)) {
	 * System.out.println("Start Element : " + name);
	 * System.out.println("Particle Image"); isRequestEffect = true; if
	 * (name.equalsIgnoreCase("maxparticles")) {
	 * System.out.println(Main.maxparticles); maxparticles = true; } else if
	 * (name.equalsIgnoreCase("rate")) { rate = true; } else if
	 * (name.equalsIgnoreCase("margin")) { margin = true; }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * if (name.equalsIgnoreCase("image")) { image = true; } else if
	 * (name.equalsIgnoreCase("path")) { path = true; } else if
	 * (name.equalsIgnoreCase("additive")) { additive = true; } else if
	 * (name.equalsIgnoreCase("dampening")) { dampening = true; } else if
	 * (name.equalsIgnoreCase("directed")) { directed = true; } else if
	 * (name.equalsIgnoreCase("fade")) { fade = true; } else if
	 * (name.equalsIgnoreCase("lifespan")) { lifespan = true; } else if
	 * (name.equalsIgnoreCase("scale")) { scale = true; } else if
	 * (name.equalsIgnoreCase("finalscale")) { finalscale = true; } else if
	 * (name.equalsIgnoreCase("speed")) { speed = true; } else if
	 * (name.equalsIgnoreCase("acceleration")) { acceleration = true; } else if
	 * (name.equalsIgnoreCase("movedir")) { movedir = true; } else if
	 * (name.equalsIgnoreCase("movedirvar")) { movedirvar = true; } else if
	 * (name.equalsIgnoreCase("rotation")) { rotation = true; } else if
	 * (name.equalsIgnoreCase("rotspeed")) { rotspeed = true; }
	 * 
	 * // Axises
	 * 
	 * else if (name.equalsIgnoreCase("amp")) { amp = true; } else if
	 * (name.equalsIgnoreCase("axis")) { axis = true; } else if
	 * (name.equalsIgnoreCase("freq")) { freq = true; } else if
	 * (name.equalsIgnoreCase("phaseshift")) { phaseshift = true; } } break;
	 * 
	 * case XMLStreamConstants.CHARACTERS: Characters characters =
	 * event.asCharacters();
	 * 
	 * if (maxparticles && isRequestEffect) { System.out.println("MaxParticles : " +
	 * characters.getData()); Main.maxparticles =
	 * (Integer.parseInt(characters.getData())); maxparticles = false; } if (rate &&
	 * isRequestEffect) { System.out.println("rate : " + characters.getData());
	 * Main.rate = (Integer.parseInt(characters.getData())); rate = false; } if
	 * (margin && isRequestEffect) { System.out.println("margin : " +
	 * characters.getData()); Main.margin =
	 * (Integer.parseInt(characters.getData())); margin = false; } if (image &&
	 * isRequestEffect) { System.out.println("image : " + characters.getData());
	 * Main.image.add((characters.getData())); image = false; } if (path &&
	 * isRequestEffect) { System.out.println("path : " + characters.getData());
	 * Main.path.add((characters.getData())); path = false; } if (additive &&
	 * isRequestEffect) { System.out.println("additive : " + characters.getData());
	 * Main.additive = (Boolean.parseBoolean(characters.getData())); additive =
	 * false; } if (dampening && isRequestEffect) {
	 * System.out.println("dampening : " + characters.getData()); Main.dampening =
	 * (Integer.parseInt(characters.getData())); dampening = false; } if (directed
	 * && isRequestEffect) { System.out.println("directed : " +
	 * characters.getData()); Main.directed =
	 * (Boolean.parseBoolean(characters.getData())); directed = false; } if (fade &&
	 * isRequestEffect) { System.out.println("fade : " + characters.getData());
	 * Main.fade = (Boolean.parseBoolean(characters.getData())); fade = false; } if
	 * (lifespan && isRequestEffect) { System.out.println("lifespan : " +
	 * characters.getData());
	 * Main.lifespan.add(Float.parseFloat(characters.getData())); lifespan = false;
	 * } if (scale && isRequestEffect) { System.out.println("scale : " +
	 * characters.getData());
	 * Main.scale.add(Float.parseFloat(characters.getData())); scale = false; } if
	 * (finalscale && isRequestEffect) { System.out.println("finalscale : " +
	 * characters.getData()); Main.finalscale =
	 * (Integer.parseInt(characters.getData())); finalscale = false; } if (speed &&
	 * isRequestEffect) { System.out.println("speed : " + characters.getData());
	 * Main.speed.add(Float.parseFloat(characters.getData())); speed = false; } if
	 * (acceleration && isRequestEffect) { System.out.println("acceleration : " +
	 * characters.getData());
	 * Main.acceleration.add(Float.parseFloat(characters.getData())); acceleration =
	 * false; } if (movedir && isRequestEffect) { System.out.println("movedir : " +
	 * characters.getData()); Main.movedir =
	 * (Integer.parseInt(characters.getData())); movedir = false; } if (movedirvar
	 * && isRequestEffect) { System.out.println("movedirvar : " +
	 * characters.getData()); Main.movedirvar =
	 * (Integer.parseInt(characters.getData())); movedirvar = false; } if (rotation
	 * && isRequestEffect) { System.out.println("rotation : " +
	 * characters.getData());
	 * Main.rotation.add(Float.parseFloat(characters.getData())); rotation = false;
	 * } if (rotspeed && isRequestEffect) { System.out.println("rotspeed : " +
	 * characters.getData());
	 * Main.rotspeed.add(Float.parseFloat(characters.getData())); rotspeed = false;
	 * } if (amp && isRequestEffect) { System.out.println("amp : " +
	 * characters.getData()); Main.amp.add(Integer.parseInt(characters.getData()));
	 * amp = false; } if (axis && isRequestEffect) { System.out.println("axis : " +
	 * characters.getData()); Main.axis = (characters.getData()); axis = false; } if
	 * (freq && isRequestEffect) { System.out.println("freq : " +
	 * characters.getData()); Main.freq.add(Float.parseFloat(characters.getData()));
	 * freq = false; } if (phaseshift && isRequestEffect) {
	 * System.out.println("phaseshift : " + characters.getData());
	 * Main.phaseshift.add(Float.parseFloat(characters.getData())); phaseshift =
	 * false; }
	 * 
	 * break;
	 * 
	 * case XMLStreamConstants.END_ELEMENT: EndElement endElement =
	 * event.asEndElement();
	 * 
	 * if (endElement.getName().getLocalPart().equalsIgnoreCase(
	 * "ambientparticleeffect") ||
	 * (endElement.getName().getLocalPart().equalsIgnoreCase("particleeffect") &&
	 * isRequestEffect)) { System.out.println("End Element : ");
	 * System.out.println(maxparticles); isRequestEffect = false; } break;
	 * 
	 * } } } catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (XMLStreamException e) { e.printStackTrace(); }
	 * 
	 * } /* for (int temp = 0; temp < nList.getLength(); temp++) {
	 * 
	 * Node nNodeA = nListA.item(temp); Node nNode2 = nList2.item(temp);
	 * 
	 * // Currently only supports particleeffects and not ambientparticleeffect if
	 * (nNodeA.getNodeType() == Node.ELEMENT_NODE) {
	 * 
	 * Element eElement = (Element) nNodeA; Element eElement2 = (Element) nNode2;
	 * 
	 * System.out.println("\nCurrent Element : " + nNodeA.getNodeName());
	 * 
	 * System.out.println("Name : " + eElement.getAttribute("name"));
	 * 
	 * // This shit doesn't work :( if (eElement.getAttribute("maxparticles") !=
	 * null && eElement.getAttribute("maxparticles").length() > 0) {
	 * System.out.println("MaxParticles : " +
	 * eElement.getAttribute("maxparticles")); Main.maxparticles =
	 * (Integer.parseInt(eElement.getAttribute("maxparticles"))); }
	 * 
	 * if (eElement.getAttribute("rate") != null &&
	 * eElement.getAttribute("rate").length() > 0) { System.out.println("Rate : " +
	 * eElement.getAttribute("rate")); Main.rate =
	 * Float.parseFloat(eElement.getAttribute("rate")); }
	 * 
	 * System.out.println("\nCurrent Element : " + nNode2.getNodeName());
	 * 
	 * if (eElement.getAttribute("image") != null &&
	 * eElement.getAttribute("image").length() > 0) {
	 * System.out.println("Particle Image : " + eElement2.getAttribute("image"));
	 * Main.image.add(eElement.getAttribute("image")); } if
	 * (eElement.getAttribute("rotation") != null &&
	 * eElement.getAttribute("rotation").length() > 0) {
	 * System.out.println("Rotation : " + eElement2.getAttribute("rotation"));
	 * Main.rotation.add(new Float(eElement.getAttribute("rotation"))); } if
	 * (eElement.getAttribute("rotspeed") != null &&
	 * eElement.getAttribute("rotspeed").length() > 0) {
	 * System.out.println("Rotspeed : " + eElement2.getAttribute("rotspeed"));
	 * Main.rotspeed.add(new Float(eElement.getAttribute("rotspeed"))); } if
	 * (eElement.getAttribute("directed") != null &&
	 * eElement.getAttribute("directed").length() > 0) {
	 * System.out.println("Directed : " + eElement2.getAttribute("directed"));
	 * 
	 * if (eElement.getAttribute("directed") == "true") { Main.directed = true; }
	 * else { Main.directed = false; } } if (eElement.getAttribute("scale") != null
	 * && eElement.getAttribute("scale").length() > 0) {
	 * System.out.println("Scale : " + eElement2.getAttribute("scale"));
	 * Main.scale.add(new Float(eElement.getAttribute("scale"))); } if
	 * (eElement.getAttribute("finalscale") != null &&
	 * eElement.getAttribute("finalscale").length() > 0) {
	 * System.out.println("FinalScale : " + eElement2.getAttribute("finalscale"));
	 * Main.finalscale = (Float.parseFloat(eElement.getAttribute("finalscale"))); }
	 * if (eElement.getAttribute("fade") != null &&
	 * eElement.getAttribute("fade").length() > 0) { System.out.println("Fade : " +
	 * eElement2.getAttribute("fade")); if (eElement.getAttribute("fade") == "true")
	 * { Main.fade = true; } else { Main.fade = false; } } if
	 * (eElement.getAttribute("additive") != null &&
	 * eElement.getAttribute("additive").length() > 0) {
	 * System.out.println("Additive : " + eElement2.getAttribute("additive")); if
	 * (eElement.getAttribute("additive") == "true") { Main.additive = true; } else
	 * { Main.additive = false; } } if (eElement.getAttribute("lifespan") != null &&
	 * eElement.getAttribute("lifespan").length() > 0) {
	 * System.out.println("Lifespan : " + eElement2.getAttribute("lifespan"));
	 * Main.lifespan.add(new Float(eElement.getAttribute("lifespan"))); } if
	 * (eElement.getAttribute("speed") != null &&
	 * eElement.getAttribute("speed").length() > 0) { System.out.println("Speed : "
	 * + eElement2.getAttribute("speed")); Main.speed.add(new
	 * Float(eElement.getAttribute("speed"))); } if
	 * (eElement.getAttribute("movedir") != null &&
	 * eElement.getAttribute("movedir").length() > 0) {
	 * System.out.println("Movedir : " + eElement2.getAttribute("movedir"));
	 * Main.movedir = (Integer.parseInt(eElement.getAttribute("movedir"))); } if
	 * (eElement.getAttribute("movedirvar") != null &&
	 * eElement.getAttribute("movedirvar").length() > 0) {
	 * System.out.println("MovedirVar : " + eElement2.getAttribute("movedirvar"));
	 * Main.movedirvar = (Integer.parseInt(eElement.getAttribute("movedirvar"))); }
	 * if (eElement.getAttribute("acceleration") != null &&
	 * eElement.getAttribute("acceleration").length() > 0) {
	 * System.out.println("Acceleration : " +
	 * eElement2.getAttribute("acceleration")); Main.acceleration.add(new
	 * Float(eElement.getAttribute("acceleration"))); }
	 * 
	 * }
	 */

	public static void ParseResourceFile(String string) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(XMLToParse);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			NodeList nList = document.getElementsByTagName("Image");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				// Currently only supports particleeffects and not ambientparticleeffect
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("\nCurrent Element : " + nNode.getNodeName());

					System.out.println("ID : " + eElement.getAttribute("id"));

					if (Main.image.indexOf(eElement.getAttribute("id") == "") != -1)

						System.out.println("Path : " + eElement.getAttribute("path"));

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static File prepareLevel(File file) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {

				NodeList nList = document.getElementsByTagName("fire");

				for (int i = 0; i < nList.getLength(); i++) {

					Node node = nList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						System.out.println("\nCurrent Element : " + node.getNodeName());

						if (eElement.getAttribute("particles") != null
								&& eElement.getAttribute("particles").length() > 0) {

							System.out.println(eElement.getAttribute("particles"));

							eElement.setAttribute("particles", Main.name);

							if (Main.type == "ambientparticleeffect") {
								eElement.setAttribute("particles", "fireBallBurn");
							}
							System.out.print(" --> " + eElement.getAttribute("particles"));

						}

					}
				}

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;

	}

	public static File prepareScene(File file) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {

				NodeList nList = document.getElementsByTagName("particles");

				for (int i = 0; i < nList.getLength(); i++) {

					Node node = nList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						System.out.println("\nCurrent Element : " + node.getNodeName());

						if (eElement.getAttribute("effect") != null && eElement.getAttribute("effect").length() > 0) {

							System.out.println(eElement.getAttribute("effect"));

							if (eElement.getAttribute("effect") != null) {

								eElement.setAttribute("effect", Main.name);

							}

							System.out.print(" --> " + eElement.getAttribute("effect"));

						}

					}
				}

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static File prepareText(File file) throws Exception {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			boolean TextExists = false;

			if (file != null) {

				NodeList nList = document.getElementsByTagName("strings");

				NodeList nList2 = document.getElementsByTagName("string");

				for (int i = 0; i < nList.getLength(); i++) {

					Node node = nList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						System.out.println("\nCurrent Element : " + node.getNodeName());

						for (int i2 = 0; i2 < nList.getLength(); i++) {

							Node node2 = nList2.item(i);

							if (node2.getNodeType() == Node.ELEMENT_NODE) {

								Element eElement2 = (Element) node2;

								System.out.println("\nCurrent Element : " + node.getNodeName());

								if (eElement2.getAttribute("id") != null && eElement2.getAttribute("id").length() > 0) {

									System.out.println(eElement2.getAttribute("id"));

									if (eElement2.getAttribute("id") == "TEXT_WOGPE_EFFECTNAME") {
										TextExists = true;
									}
								}
							}
						}

					}
				}

				if (TextExists == false) {
					System.out.println("Text does not exist creating new text");

					NodeList strings = document.getElementsByTagName("strings");
							
					for (int temp = 0; temp < strings.getLength(); temp++) {
						
					Element element = (Element) strings.item(temp);

					Node newText = document.createElement("string");

					Node newFire = document.createElement("string");
					
					element.appendChild(newText);
					
					element.appendChild(newFire);

					Attr newTextID = document.createAttribute("id");

					newTextID.setValue("TEXT_WOGPE_EFFECTNAME");

					Attr newFireID = document.createAttribute("id");

					newFireID.setValue("TEXT_WOGPE_FIRE");

					Attr newTextText = document.createAttribute("text");

					newTextText.setValue("Currently Testing: " + Main.name);

					Attr newFireText = document.createAttribute("text");

					newFireText.setValue("Fire");

					Element textElement = (Element) newText;

					Element fireElement = (Element) newText;

					textElement.setAttributeNode(newTextID);

					fireElement.setAttributeNode(newFireID);

					textElement.setAttributeNode(newTextText);

					fireElement.setAttributeNode(newFireText);

					element.appendChild(newText);

					element.appendChild(newFire);

				}

				for (int i = 0; i < nList2.getLength(); i++) {

					Node node = nList2.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) node;

						System.out.println("\nCurrent Element : " + node.getNodeName());

						if (eElement.getAttribute("id") != null && eElement.getAttribute("id").length() > 0) {

							System.out.println(eElement.getAttribute("id"));

							if (eElement.getAttribute("id") == "TEXT_WOGPE_EFFECTNAME") {
								eElement.setAttribute("text", "Currently Testing: " + Main.name);
							}

						}

					}
				}}

				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
				DOMSource source = new DOMSource(document);
				StreamResult result = new StreamResult(file);
				transformer.transform(source, result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * public static void writeToResourceXML(String string) {
	 * 
	 * try {
	 * 
	 * String filepath = Main.propertiesPath + "resources.xml.xml";
	 * 
	 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder builder = factory.newDocumentBuilder();
	 * 
	 * Document document = builder.parse(XMLToParse);
	 * 
	 * document.getDocumentElement().normalize();
	 * 
	 * Element root = document.getDocumentElement();
	 * System.out.println(root.getNodeName()); if
	 * (document.getElementById("commom").getNodeName() == "Resources") {
	 * resourcesNode = document.getElementById("commom"); }
	 * 
	 * for (int temp = 0; temp < resourcesNode.getLength(); temp++) {
	 * 
	 * Node nNode = images.item(temp); if (nNode.getNodeType() == Node.ELEMENT_NODE)
	 * {
	 * 
	 * Element image = document.createElement("Image");
	 * resourcesNode.appendChild(document.createTextNode(.getName()));
	 * root.appendChild(resourcesNode);
	 * 
	 * 
	 * 
	 * 
	 * //Write to XML
	 * 
	 * if (eElement.getAttribute("id") != null) {
	 * 
	 * TransformerFactory transformerFactory = TransformerFactory.newInstance();
	 * Transformer transformer = transformerFactory.newTransformer(); DOMSource
	 * source = new DOMSource(document); StreamResult result = new StreamResult(new
	 * File(filepath)); transformer.transform(source, result);
	 * 
	 * catch (ParserConfigurationException pce) { pce.printStackTrace(); } catch
	 * (TransformerException tfe) { tfe.printStackTrace(); } catch (IOException ioe)
	 * { ioe.printStackTrace(); } catch (SAXException sae) { sae.printStackTrace();
	 * }
	 * 
	 * }
	 */

	public static void writeBytesToFile(File file, byte[] bytes) throws IOException {

		OutputStream os = new FileOutputStream(file, false);

		try {

			os.write(bytes);

		} finally {

			os.close();
		}
	}

	public static String usingBufferedReader(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				contentBuilder.append(sCurrentLine).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}

	public static void printDocument(Document doc, OutputStream out) throws IOException, TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		transformer.transform(new DOMSource(doc), new StreamResult(new OutputStreamWriter(out, "UTF-8")));

		System.out.println(out);

	}
	
	public static boolean CheckEntireFile(File file) throws Exception {
		
		boolean matchedname = false;
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			NodeList nList = document.getElementsByTagName("particleeffect");
			NodeList nListA = document.getElementsByTagName("ambientparticleeffect");
			
			matchedname = false;

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				// particleeffects
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("\nCurrent Element : " + nNode.getNodeName());

					System.out.println("Name : " + eElement.getAttribute("name"));
					
					if (eElement.getAttribute("name").equals(Main.name)) {
						matchedname = true;
					}


				}

			}

			for (int temp = 0; temp < nListA.getLength(); temp++) {

				Node nNodeA = nListA.item(temp);

				// ambientparticleeffect
				if (nNodeA.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNodeA;

					System.out.println("\nCurrent Element : " + nNodeA.getNodeName());

					System.out.println("Name : " + eElement.getAttribute("name"));
					
					if (eElement.getAttribute("name").equals(Main.name)) {
						matchedname = true;
					}


				}

			}

		} catch (Exception e) {
			MessageBoxHelper.giveErrorMessage("Failed to Parse the XML file. Make sure it is a proper fx XML.");
			e.printStackTrace();
		}
		return matchedname;

	}
}
