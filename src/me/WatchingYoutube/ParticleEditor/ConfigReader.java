package me.WatchingYoutube.ParticleEditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	static File config = new File(System.getProperty("user.home") + "\\WOGPE\\config.txt");
	
	static Properties prop = new Properties();
	
	public static void writeFxDir() {
		
		InputStream is = null;
		
		try {
			is = new FileInputStream(config);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Setting Fx Dir");
		
		prop.setProperty("version", "0.0.1.3");
		prop.setProperty("fxdir", Main.fxFile.getAbsolutePath());
		
		try {
			FileWriter writer = new FileWriter(config);
			prop.store(writer, "WOGPE Config: You probably shouldn't be here.");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readAndApplyConfig() {
		
		InputStream is = null;
		
		try {
			is = new FileInputStream(config);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Loading Fx Dir");
	
		if (prop.getProperty("fxdir") == null | prop.getProperty("fxdir").equals("")) {
		
		System.out.println("No Fx dir has been set, please set one!");	
		
		} else {
			
			File selected = new File(prop.getProperty("fxdir"));
			
			if (selected != null) {

				System.out.println(selected);

				if (selected.getName().endsWith(".bin")) {
					
					Main.currentXML.getParentFile().mkdirs();
					Main.currentResources.getParentFile().mkdirs();
					try {
						Main.currentXML.createNewFile();
						Main.currentResources.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						byte[] bytes = AESBin.decryptFile(selected);
						Main.currentXML.delete();
						XMLReader.writeBytesToFile(Main.currentXML, bytes);
						XMLReader.XMLToParse = Main.currentXML;
						XMLReader.ParseEntireFile(XMLReader.usingBufferedReader(Main.currentXML.getAbsolutePath()));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {

					XMLReader.XMLToParse = selected;

					try {
						Main.currentXML.delete();
						XMLReader.XMLToParse = selected;
						XMLReader.ParseEntireFile(XMLReader.usingBufferedReader(selected.getAbsolutePath()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Main.propertiesPath = selected.getPath().replace(selected.getName(), "");
				if (Main.propertiesPath.contains("game\\properties\\")) {

					Main.worldofgoopath = Main.propertiesPath.replace("game\\properties\\", "");
					Main.newWOG = true;

				} else {

					Main.worldofgoopath = Main.propertiesPath.replace("properties\\", "");
					Main.newWOG = false;

				}

				Main.fxFile = new File(selected.getAbsolutePath());
				
				Main.tltmNew.setEnabled(true);
				Main.tltmOpen.setEnabled(true);
				Main.mntmNewItem.setEnabled(true);
				Main.mntmOpen.setEnabled(true);
				Main.mntmSave.setEnabled(true);
				
				System.out.println(selected);
				System.out.println(Main.propertiesPath);
				System.out.println(Main.worldofgoopath);
				System.out.println(Main.newWOG);
				System.out.println("fxFile: " + Main.fxFile);

			}
		}
		}
	}

