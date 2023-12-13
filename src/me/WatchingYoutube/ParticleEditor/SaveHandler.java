package me.WatchingYoutube.ParticleEditor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SaveHandler {

	public static File writeNewValuesToXML(File file) throws TransformerException {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {

				NodeList nList = document.getElementsByTagName("particleeffect");
				NodeList nListA = document.getElementsByTagName("ambientparticleeffect");

				boolean dontTryMe = false;

				for (int i = 0; i < nList.getLength(); i++) {

					Node node = nList.item(i);

					if (node.getNodeType() == Element.ELEMENT_NODE) {
						Element eElement = (Element) node;
						System.out.println(eElement.getAttribute("name"));
						if (eElement.getAttribute("name").equals(Main.name)) {

							dontTryMe = true;

							// Main Values

							Main.type = eElement.getNodeName();

								eElement.setAttribute("maxparticles", String.valueOf(Main.maxparticles));

								eElement.setAttribute("rate", String.valueOf(Main.rate));

							// The other stuff or as I like to call it the "fun stuff" D:

							NodeList nList2 = eElement.getChildNodes();

							int indexAt = 0;

							// Images and their Paths

							for (int i2 = 0; i2 < nList2.getLength(); i2++) {

								Node node2 = nList2.item(i2);

								if (node2.getNodeType() == Element.ELEMENT_NODE) {
									if (node2.getNodeName().equals("particle")) {

										Element eElement2 = (Element) node2;

										System.out.println(nList2.getLength());

										if (eElement2.getAttribute("image") != null
												&& eElement2.getAttribute("image").length() > 0) {

											eElement2.setAttribute("image", Main.imageIndex.get(indexAt));

										} else {

											if (Main.imageIndex.get(indexAt) != null) {
												eElement2.setAttribute("image", Main.imageIndex.get(indexAt));
											}

										}
										// The Particle Values, prepare yourselves D: D: D:
										if (eElement2.getAttribute("additive") != null
												&& eElement2.getAttribute("additive").length() > 0) {

											eElement2.setAttribute("additive",
													String.valueOf(Main.additive.get(indexAt)));

										} else {

											if (Main.additive.get(indexAt) != null) {
												eElement2.setAttribute("additive",
														String.valueOf(Main.additive.get(indexAt)));
											} else {
												eElement2.removeAttribute("additive");
											}

										}
										if (eElement2.getAttribute("dampening") != null
												&& eElement2.getAttribute("dampening").length() > 0) {

											eElement2.setAttribute("dampening",
													String.valueOf(Main.dampening.get(indexAt)));

										} else {

											if (Main.dampening.get(indexAt) != null) {
												eElement2.setAttribute("dampening",
														String.valueOf(Main.dampening.get(indexAt)));
											} else {
												eElement2.removeAttribute("dampening");
											}

										}

										if (eElement2.getAttribute("directed") != null
												&& eElement2.getAttribute("directed").length() > 0) {

											eElement2.setAttribute("directed",
													String.valueOf(Main.directed.get(indexAt)));

										} else {

											if (Main.directed.get(indexAt) != null) {
												eElement2.setAttribute("directed",
														String.valueOf(Main.directed.get(indexAt)));
											} else {
												eElement2.removeAttribute("directed");
											}

										}
										if (eElement2.getAttribute("fade") != null
												&& eElement2.getAttribute("fade").length() > 0) {

											eElement2.setAttribute("fade", String.valueOf(Main.fade.get(indexAt)));

										} else {

											if (Main.fade.get(indexAt) != null) {
												eElement2.setAttribute("fade", String.valueOf(Main.fade.get(indexAt)));
											} else {
												eElement2.removeAttribute("fade");
											}

										}

										if (eElement2.getAttribute("lifespan") != null
												&& eElement2.getAttribute("lifespan").length() > 0) {

											eElement2.setAttribute("lifespan",
													String.valueOf(Main.singlelifespan.get(indexAt)));

										} else {

											if (Main.singlelifespan.get(indexAt) != null) {
												eElement2.setAttribute("lifespan",
														String.valueOf(Main.singlelifespan.get(indexAt)));
											} else {
												eElement2.removeAttribute("lifespan");
											}

										}

										if (eElement2.getAttribute("scale") != null
												&& eElement2.getAttribute("scale").length() > 0) {

											eElement2.setAttribute("scale",
													String.valueOf(Main.singlescale.get(indexAt)));

										} else {

											if (Main.singlescale.get(indexAt) != null) {
												eElement2.setAttribute("scale",
														String.valueOf(Main.singlescale.get(indexAt)));
											} else {
												eElement2.removeAttribute("scale");
											}

										}

										if (eElement2.getAttribute("finalscale") != null
												&& eElement2.getAttribute("finalscale").length() > 0) {

											eElement2.setAttribute("finalscale",
													String.valueOf(Main.finalscale.get(indexAt)));

										} else {

											if (Main.finalscale.get(indexAt) != null) {
												eElement2.setAttribute("finalscale",
														String.valueOf(Main.finalscale.get(indexAt)));
											} else {
												eElement2.removeAttribute("finalscale");
											}

										}

										if (eElement2.getAttribute("speed") != null
												&& eElement2.getAttribute("speed").length() > 0) {

											eElement2.setAttribute("speed",
													String.valueOf(Main.singlespeed.get(indexAt)));

										} else {

											if (Main.singlespeed.get(indexAt) != null) {
												eElement2.setAttribute("speed",
														String.valueOf(Main.singlespeed.get(indexAt)));
											} else {
												eElement2.removeAttribute("speed");
											}

										}

										if (eElement2.getAttribute("acceleration") != null
												&& eElement2.getAttribute("acceleration").length() > 0) {

											eElement2.setAttribute("acceleration",
													String.valueOf(Main.singleacceleration.get(indexAt)));

										} else {

											if (Main.singleacceleration.get(indexAt) != null) {
												eElement2.setAttribute("acceleration",
														String.valueOf(Main.singleacceleration.get(indexAt)));
											} else {
												eElement2.removeAttribute("acceleration");
											}

										}

										if (eElement2.getAttribute("movedir") != null
												&& eElement2.getAttribute("movedir").length() > 0) {

											eElement2.setAttribute("movedir",
													String.valueOf(Main.movedir.get(indexAt)));

										} else {

											if (Main.movedir.get(indexAt) != null) {
												eElement2.setAttribute("movedir",
														String.valueOf(Main.movedir.get(indexAt)));
											} else {
												eElement2.removeAttribute("movedir");
											}

										}

										if (eElement2.getAttribute("movedirvar") != null
												&& eElement2.getAttribute("movedirvar").length() > 0) {

											eElement2.setAttribute("movedirvar",
													String.valueOf(Main.movedirvar.get(indexAt)));

										} else {

											if (Main.movedir.get(indexAt) != null) {
												eElement2.setAttribute("movedirvar",
														String.valueOf(Main.movedirvar.get(indexAt)));
											} else {
												eElement2.removeAttribute("movedirvar");
											}

										}

										if (eElement2.getAttribute("rotation") != null
												&& eElement2.getAttribute("rotation").length() > 0) {

											eElement2.setAttribute("rotation",
													String.valueOf(Main.singlerotation.get(indexAt)));

										} else {

											if (Main.singlerotation.get(indexAt) != null) {
												eElement2.setAttribute("rotation",
														String.valueOf(Main.singlerotation.get(indexAt)));
											} else {
												eElement2.removeAttribute("rotation");
											}

										}

										if (eElement2.getAttribute("rotspeed") != null
												&& eElement2.getAttribute("rotspeed").length() > 0) {

											eElement2.setAttribute("rotspeed",
													String.valueOf(Main.singlerotspeed.get(indexAt)));

										} else {

											if (Main.singlerotspeed.get(indexAt) != null) {
												eElement2.setAttribute("rotspeed",
														String.valueOf(Main.singlerotspeed.get(indexAt)));
											} else {
												eElement2.removeAttribute("rotspeed");
											}

										}

										indexAt++;

										int indexAtA = 0;

										// Axis or whatever its called

										NodeList nList3 = eElement2.getChildNodes();

										for (int axisI = 0; axisI < nList3.getLength(); axisI++) {

											Node node3 = nList3.item(axisI);

											if (node3.getNodeType() == Element.ELEMENT_NODE) {
												if (node3.getNodeName().equals("axialsinoffset")) {
													Element eElement3 = (Element) node3;

													if (eElement2.getAttribute("amp") != null
															&& eElement2.getAttribute("amp").length() > 0) {

														eElement2.setAttribute("amp",
																String.valueOf(Main.singleamp.get(indexAtA)));

													} else {

														if (Main.singleamp.get(indexAtA) != null) {
															eElement2.setAttribute("amp",
																	String.valueOf(Main.amp.get(indexAtA)));
														} else {
															eElement2.removeAttribute("amp");
														}

													}

													if (eElement2.getAttribute("axis") != null
															&& eElement2.getAttribute("axis").length() > 0) {

														eElement2.setAttribute("axis",
																String.valueOf(Main.axis.get(indexAtA)));

													} else {

														if (Main.axis.get(indexAtA) != null) {
															eElement2.setAttribute("axis",
																	String.valueOf(Main.axis.get(indexAtA)));
														} else {
															eElement2.removeAttribute("axis");
														}

													}

													if (eElement2.getAttribute("freq") != null
															&& eElement2.getAttribute("freq").length() > 0) {

														eElement2.setAttribute("freq",
																String.valueOf(Main.singlefreq.get(indexAtA)));

													} else {

														if (Main.singlefreq.get(indexAtA) != null) {
															eElement2.setAttribute("freq",
																	String.valueOf(Main.singlefreq.get(indexAtA)));
														} else {
															eElement2.removeAttribute("freq");
														}

													}

													if (eElement2.getAttribute("phaseshift") != null
															&& eElement2.getAttribute("phaseshift").length() > 0) {

														eElement2.setAttribute("phaseshift",
																String.valueOf(Main.singlephaseshift.get(indexAtA)));

													} else {

														if (Main.singlephaseshift.get(indexAtA) != null) {
															eElement2.setAttribute("phaseshift", String
																	.valueOf(Main.singlephaseshift.get(indexAtA)));
														} else {
															eElement2.removeAttribute("phaseshift");
														}

													}

													indexAtA++;
												}
											}
										}

									}
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
						if (eElement.getAttribute("name").equals(Main.name)) {

							dontTryMe = true;

							// Main Values

							Main.type = eElement.getNodeName();

								eElement.setAttribute("maxparticles", String.valueOf(Main.maxparticles));

								eElement.setAttribute("rate", String.valueOf(Main.rate));
								
								eElement.setAttribute("margin", String.valueOf(Main.margin));

							// The other stuff or as I like to call it the "fun stuff" D:

							NodeList nList2 = eElement.getChildNodes();

							int indexAt = 0;

							// Images and their Paths

							for (int i2 = 0; i2 < nList2.getLength(); i2++) {

								Node node2 = nList2.item(i2);

								if (node2.getNodeType() == Element.ELEMENT_NODE) {
									if (node2.getNodeName().equals("particle")) {

										Element eElement2 = (Element) node2;

										System.out.println(nList2.getLength());

										if (eElement2.getAttribute("image") != null
												&& eElement2.getAttribute("image").length() > 0) {

											eElement2.setAttribute("image", Main.imageIndex.get(indexAt));

										} else {

											if (Main.imageIndex.get(indexAt) != null) {
												eElement2.setAttribute("image", Main.imageIndex.get(indexAt));
											}

										}
										// The Particle Values, prepare yourselves D: D: D:
										if (eElement2.getAttribute("additive") != null
												&& eElement2.getAttribute("additive").length() > 0) {

											eElement2.setAttribute("additive",
													String.valueOf(Main.additive.get(indexAt)));

										} else {

											if (Main.additive.get(indexAt) != null) {
												eElement2.setAttribute("additive",
														String.valueOf(Main.additive.get(indexAt)));
											} else {
												eElement2.removeAttribute("additive");
											}

										}
										if (eElement2.getAttribute("dampening") != null
												&& eElement2.getAttribute("dampening").length() > 0) {

											eElement2.setAttribute("dampening",
													String.valueOf(Main.dampening.get(indexAt)));

										} else {

											if (Main.dampening.get(indexAt) != null) {
												eElement2.setAttribute("dampening",
														String.valueOf(Main.dampening.get(indexAt)));
											} else {
												eElement2.removeAttribute("dampening");
											}

										}

										if (eElement2.getAttribute("directed") != null
												&& eElement2.getAttribute("directed").length() > 0) {

											eElement2.setAttribute("directed",
													String.valueOf(Main.directed.get(indexAt)));

										} else {

											if (Main.directed.get(indexAt) != null) {
												eElement2.setAttribute("directed",
														String.valueOf(Main.directed.get(indexAt)));
											} else {
												eElement2.removeAttribute("directed");
											}

										}
										if (eElement2.getAttribute("fade") != null
												&& eElement2.getAttribute("fade").length() > 0) {

											eElement2.setAttribute("fade", String.valueOf(Main.fade.get(indexAt)));

										} else {

											if (Main.fade.get(indexAt) != null) {
												eElement2.setAttribute("fade", String.valueOf(Main.fade.get(indexAt)));
											} else {
												eElement2.removeAttribute("fade");
											}

										}

										if (eElement2.getAttribute("lifespan") != null
												&& eElement2.getAttribute("lifespan").length() > 0) {

											eElement2.setAttribute("lifespan",
													String.valueOf(Main.singlelifespan.get(indexAt)));

										} else {

											if (Main.singlelifespan.get(indexAt) != null) {
												eElement2.setAttribute("lifespan",
														String.valueOf(Main.singlelifespan.get(indexAt)));
											} else {
												eElement2.removeAttribute("lifespan");
											}

										}

										if (eElement2.getAttribute("scale") != null
												&& eElement2.getAttribute("scale").length() > 0) {

											eElement2.setAttribute("scale",
													String.valueOf(Main.singlescale.get(indexAt)));

										} else {

											if (Main.singlescale.get(indexAt) != null) {
												eElement2.setAttribute("scale",
														String.valueOf(Main.singlescale.get(indexAt)));
											} else {
												eElement2.removeAttribute("scale");
											}

										}

										if (eElement2.getAttribute("finalscale") != null
												&& eElement2.getAttribute("finalscale").length() > 0) {

											eElement2.setAttribute("finalscale",
													String.valueOf(Main.finalscale.get(indexAt)));

										} else {

											if (Main.finalscale.get(indexAt) != null) {
												eElement2.setAttribute("finalscale",
														String.valueOf(Main.finalscale.get(indexAt)));
											} else {
												eElement2.removeAttribute("finalscale");
											}

										}

										if (eElement2.getAttribute("speed") != null
												&& eElement2.getAttribute("speed").length() > 0) {

											eElement2.setAttribute("speed",
													String.valueOf(Main.singlespeed.get(indexAt)));

										} else {

											if (Main.singlespeed.get(indexAt) != null) {
												eElement2.setAttribute("speed",
														String.valueOf(Main.singlespeed.get(indexAt)));
											} else {
												eElement2.removeAttribute("speed");
											}

										}

										if (eElement2.getAttribute("acceleration") != null
												&& eElement2.getAttribute("acceleration").length() > 0) {

											eElement2.setAttribute("acceleration",
													String.valueOf(Main.singleacceleration.get(indexAt)));

										} else {

											if (Main.singleacceleration.get(indexAt) != null) {
												eElement2.setAttribute("acceleration",
														String.valueOf(Main.singleacceleration.get(indexAt)));
											} else {
												eElement2.removeAttribute("acceleration");
											}

										}

										if (eElement2.getAttribute("movedir") != null
												&& eElement2.getAttribute("movedir").length() > 0) {

											eElement2.setAttribute("movedir",
													String.valueOf(Main.movedir.get(indexAt)));

										} else {

											if (Main.movedir.get(indexAt) != null) {
												eElement2.setAttribute("movedir",
														String.valueOf(Main.movedir.get(indexAt)));
											} else {
												eElement2.removeAttribute("movedir");
											}

										}

										if (eElement2.getAttribute("movedirvar") != null
												&& eElement2.getAttribute("movedirvar").length() > 0) {

											eElement2.setAttribute("movedirvar",
													String.valueOf(Main.movedirvar.get(indexAt)));

										} else {

											if (Main.movedir.get(indexAt) != null) {
												eElement2.setAttribute("movedirvar",
														String.valueOf(Main.movedirvar.get(indexAt)));
											} else {
												eElement2.removeAttribute("movedirvar");
											}

										}

										if (eElement2.getAttribute("rotation") != null
												&& eElement2.getAttribute("rotation").length() > 0) {

											eElement2.setAttribute("rotation",
													String.valueOf(Main.singlerotation.get(indexAt)));

										} else {

											if (Main.singlerotation.get(indexAt) != null) {
												eElement2.setAttribute("rotation",
														String.valueOf(Main.singlerotation.get(indexAt)));
											} else {
												eElement2.removeAttribute("rotation");
											}

										}

										if (eElement2.getAttribute("rotspeed") != null
												&& eElement2.getAttribute("rotspeed").length() > 0) {

											eElement2.setAttribute("rotspeed",
													String.valueOf(Main.singlerotspeed.get(indexAt)));

										} else {

											if (Main.singlerotspeed.get(indexAt) != null) {
												eElement2.setAttribute("rotspeed",
														String.valueOf(Main.singlerotspeed.get(indexAt)));
											} else {
												eElement2.removeAttribute("rotspeed");
											}

										}

										indexAt++;

										int indexAtA = 0;

										// Axis or whatever its called

										NodeList nList3 = eElement2.getChildNodes();

										for (int axisI = 0; axisI < nList3.getLength(); axisI++) {

											Node node3 = nList3.item(axisI);

											if (node3.getNodeType() == Element.ELEMENT_NODE) {
												if (node3.getNodeName().equals("axialsinoffset")) {
													Element eElement3 = (Element) node3;

													if (eElement2.getAttribute("amp") != null
															&& eElement2.getAttribute("amp").length() > 0) {

														eElement2.setAttribute("amp",
																String.valueOf(Main.singleamp.get(indexAtA)));

													} else {

														if (Main.singleamp.get(indexAtA) != null) {
															eElement2.setAttribute("amp",
																	String.valueOf(Main.amp.get(indexAtA)));
														} else {
															eElement2.removeAttribute("amp");
														}

													}

													if (eElement2.getAttribute("axis") != null
															&& eElement2.getAttribute("axis").length() > 0) {

														eElement2.setAttribute("axis",
																String.valueOf(Main.axis.get(indexAtA)));

													} else {

														if (Main.axis.get(indexAtA) != null) {
															eElement2.setAttribute("axis",
																	String.valueOf(Main.axis.get(indexAtA)));
														} else {
															eElement2.removeAttribute("axis");
														}

													}

													if (eElement2.getAttribute("freq") != null
															&& eElement2.getAttribute("freq").length() > 0) {

														eElement2.setAttribute("freq",
																String.valueOf(Main.singlefreq.get(indexAtA)));

													} else {

														if (Main.singlefreq.get(indexAtA) != null) {
															eElement2.setAttribute("freq",
																	String.valueOf(Main.singlefreq.get(indexAtA)));
														} else {
															eElement2.removeAttribute("freq");
														}

													}

													if (eElement2.getAttribute("phaseshift") != null
															&& eElement2.getAttribute("phaseshift").length() > 0) {

														eElement2.setAttribute("phaseshift",
																String.valueOf(Main.singlephaseshift.get(indexAtA)));

													} else {

														if (Main.singlephaseshift.get(indexAtA) != null) {
															eElement2.setAttribute("phaseshift", String
																	.valueOf(Main.singlephaseshift.get(indexAtA)));
														} else {
															eElement2.removeAttribute("phaseshift");
														}

													}

													indexAtA++;
												}
											}
										}

									}
								}
							}
						}
					}
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				System.out.println("-----------Modified File-----------");
				StreamResult consoleResult = new StreamResult(System.out);
				StreamResult newCurrentXML = new StreamResult(file);
				transformer.transform(source, consoleResult);
				transformer.transform(source, newCurrentXML);

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
		return file;
	}

	public static File writeNewParticleToXML(File file) throws TransformerException { //Fucking work you piece of shit

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(file);

			document.getDocumentElement().normalize();

			Element root = document.getDocumentElement();
			System.out.println(root.getNodeName());

			if (file != null) {
				
				NodeList nList = document.getElementsByTagName("effects");
				
				for (int temp = 0; temp < nList.getLength(); temp++) {
					
				Element effects = (Element) nList.item(temp);

				if (Main.type.equals("particleeffect")) {

					Element neweffect = document.createElement("particleeffect");
					
					neweffect.setAttribute("name", Main.name);
					
					neweffect.setAttribute("maxparticles", String.valueOf((Main.maxparticles)));
					
					if (Main.rate >= 0.00001F) {
					neweffect.setAttribute("rate", String.valueOf((Main.rate)));
					} else {
						neweffect.removeAttribute("rate");
					}
					effects.appendChild(neweffect);

					System.out.println("Created the particleeffect");

					for (int i = 0; i < Main.numberOfParticles; i++) {

						Element newparticle = document.createElement("particle");

						neweffect.appendChild(newparticle);
						
						System.out.println("Created the particle");

						if (Main.numberOfAxialSinOffset.isEmpty() == false) {
							if (Main.numberOfAxialSinOffset.get(i) != null) {
								for (int i2 = 0; i2 < Main.numberOfAxialSinOffset.get(i); i2++) {
									Element newaxialsinoffset = document.createElement("axialsinoffset");

									newaxialsinoffset.appendChild(newparticle);
									
									System.out.println("Created the axialsinoffset");
								}
							}
						}
					}

				} else if (Main.type.equals("ambientparticleeffect")) {

					Element neweffect = document.createElement("ambientparticleeffect");
					
					neweffect.setAttribute("name", Main.name);
					
					neweffect.setAttribute("maxparticles", String.valueOf((Main.maxparticles)));
					
					if (Main.rate >= 0.00001F) {
					neweffect.setAttribute("rate", String.valueOf((Main.rate)));
					} else {
						neweffect.removeAttribute("rate");
					}
					
					neweffect.setAttribute("margin", String.valueOf((Main.margin)));
					
					effects.appendChild(neweffect);

					for (int i = 0; i < Main.numberOfParticles; i++) {

						Element newparticle = document.createElement("particle");

						neweffect.appendChild(newparticle);

						if (Main.numberOfAxialSinOffset.isEmpty() == false) {
						if (Main.numberOfAxialSinOffset.get(i) != null) {
							for (int i2 = 0; i2 < Main.numberOfAxialSinOffset.get(i); i2++) {
								Element newaxialsinoffset = document.createElement("axialsinoffset");

								newaxialsinoffset.appendChild(newparticle);
							}
						}
					}
					}
				}
			}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty(OutputKeys.METHOD, "xml");
				System.out.println("-----------Modified WriteParticle File-----------");
				StreamResult consoleResult = new StreamResult(System.out);
				StreamResult newCurrentXML = new StreamResult(file);
				transformer.transform(source, consoleResult);
				transformer.transform(source, newCurrentXML);
				
				writeNewValuesToXML(file);
				
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

		return file;

	}
}
