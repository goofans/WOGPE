package me.WatchingYoutube.ParticleEditor;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.FileChooserUI;
import javax.xml.transform.TransformerException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class Main {

	private static SelectionListener cls;
	static int CanvasColor = 255;
	private static int CanvasScale = 1;
	private static Image cvImage;

	public static Display display;

	private static int cvw;

	private static int cvh;

	private static Composite group_1;

	private static Composite group_2;

	public static String windowtitle = "World Of Goo Particle Editor | No Effect Open";

	public static File openedFile;
	public static File currentXML = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPEXML.xml");
	public static File currentResources = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPERESC.xml");

	public static ArrayList<String> fullparticleslist = new ArrayList<String>();

	public static ArrayList<File> renderedImages = new ArrayList<File>();

	public static boolean isRunning;

	public static String worldofgoopath;
	public static String propertiesPath;

	public static File fxFile;

	public static boolean newWOG;

	public static String type = "particleeffect"; // True for Particle Effect False for Ambient Effect
	public static int numberOfParticles = 1;
	public static ArrayList<Integer> numberOfAxialSinOffset = new ArrayList<Integer>();
	public static String name = null;
	public static int maxparticles;
	public static float rate;
	public static int margin;

	public static ArrayList<String> image = new ArrayList<String>();
	public static ArrayList<String> path = new ArrayList<String>();
	public static ArrayList<String> imageIndex = new ArrayList<String>();
	public static ArrayList<Integer> pathIndex = new ArrayList<Integer>();

	public static ArrayList<Boolean> additive = new ArrayList<Boolean>(); // Single
	public static ArrayList<Float> dampening = new ArrayList<Float>(); // Single
	public static ArrayList<Boolean> directed = new ArrayList<Boolean>(); // Single
	public static ArrayList<Boolean> fade = new ArrayList<Boolean>(); // Single

	public static ArrayList<Float> lifespan = new ArrayList<Float>();

	public static ArrayList<String> singlelifespan = new ArrayList<String>();

	public static ArrayList<Float> scale = new ArrayList<Float>();

	public static ArrayList<String> singlescale = new ArrayList<String>();

	public static ArrayList<Float> finalscale = new ArrayList<Float>(); // Single

	public static ArrayList<Float> speed = new ArrayList<Float>();

	public static ArrayList<String> singlespeed = new ArrayList<String>();

	public static ArrayList<Float> acceleration = new ArrayList<Float>();

	public static ArrayList<String> singleacceleration = new ArrayList<String>();

	public static ArrayList<Integer> movedir = new ArrayList<Integer>(); // Single
	public static ArrayList<Integer> movedirvar = new ArrayList<Integer>(); // Single
	public static ArrayList<Float> rotation = new ArrayList<Float>();

	public static ArrayList<Float> rotspeed = new ArrayList<Float>();

	public static ArrayList<String> singlerotation = new ArrayList<String>();

	public static ArrayList<String> singlerotspeed = new ArrayList<String>();

	public static ArrayList<Integer> amp = new ArrayList<Integer>();

	public static ArrayList<String> singleamp = new ArrayList<String>();

	public static ArrayList<String> axis = new ArrayList<String>(); // Single

	public static ArrayList<Float> freq = new ArrayList<Float>();

	public static ArrayList<String> singlefreq = new ArrayList<String>();

	public static ArrayList<Float> phaseshift = new ArrayList<Float>();

	public static ArrayList<String> singlephaseshift = new ArrayList<String>();

	public static boolean currentEffectOpen = false;

	public static boolean createNewElements = false;

	public static Canvas canvas;

	public static ToolItem tltmNew;

	public static ToolItem tltmOpen;
	
	public static MenuItem mntmNewItem;
	public static MenuItem mntmSave;
	public static MenuItem mntmOpen;

	private static Table table;

	private static Table table2;

	private static Table table3;

	private static Table table4;

	private static Label lblNewLabel;

	// All Table Items

	// Particle Effect/Ambient Particle Effect

	public static TableItem tableItem; // Name

	public static TableItem tableItem2; // Max Particles

	public static TableItem tableItem3; // Rate

	public static TableItem tableItem4; // Margin

	// Each Particle Effect

	public static TableItem tableid; // ID

	public static TableItem tableImage; // Images

	public static TableItem tableItem5; // Additive

	public static TableItem tableItem6; // Dampening

	public static TableItem tableItem7; // Directed

	public static TableItem tableItem8; // fade

	public static TableItem tableItem9; // lifespan

	public static TableItem tableItem10; // scale

	public static TableItem tableItem11; // finalscale

	public static TableItem tableItem12; // speed

	public static TableItem tableItem13; // acceleration

	public static TableItem tableItem14; // movedir

	public static TableItem tableItem15; // movedirvar

	public static TableItem tableItem16; // rotation

	public static TableItem tableItem17; // rotspeed

	// Axis

	public static TableItem tableItem18; // amp

	public static TableItem tableItem19; // axis

	public static TableItem tableItem20; // freq

	public static TableItem tableItem21; // phaseshift

	// Images

	public static TableItem tableItem22; // Image

	public static TableItem tableItem23; // Path

	// Tree

	public static Tree tree;

	public static Tree tree2;

	public static TreeItem trtmParticleEffect;

	public static TreeItem trtmParticleImage;

	public static TreeItem trtmResources;

	public static TreeItem trtmImage;

	private static String selectedTreeItem;

	private static int selectedTreeItemid;

	public static Shell shlWorldOfGoo;

	// Aniamtion

	// The timer interval in milliseconds
	private static final int TIMER_INTERVAL = 10;
	protected TreeItem selectedTreeItemItem;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void runWOGPE() {

		display = Display.getDefault();
		shlWorldOfGoo = new Shell(SWT.SHELL_TRIM | SWT.BORDER);
		shlWorldOfGoo.setMinimumSize(new Point(1000, 588));
		shlWorldOfGoo.setSize(1000, 588);
		shlWorldOfGoo.setText(windowtitle);
		shlWorldOfGoo.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/worldofgooparticleeditor_icon_REy_icon.ico"));

		Runnable runnable = new Runnable() {
			public void run() {
				if (renderedImages != null) {
					// drawImagesToCanvas();
				}
				display.timerExec(TIMER_INTERVAL, this);
				
				

				/*
				 * display.asyncExec(new Runnable(){ public void run(){ // Draw the new data
				 * onto the image Image frameImage = new Image(display,nextFrameData);
				 * gc.drawImage(frameImage,nextFrameData.x,nextFrameData.y);
				 * frameImage.dispose(); canvas.redraw(); } });
				 */
			}
		};
		
		
		
		shlWorldOfGoo.setLayout(new BorderLayout(5, 5));

		Menu menu = new Menu(shlWorldOfGoo, SWT.BAR);
		shlWorldOfGoo.setMenuBar(menu);

		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewFX.openDialog();
			}
		});
		mntmNewItem.setEnabled(false);
		mntmNewItem.setText("New");

		mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					OpenFX.openDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmOpen.setEnabled(false);
		mntmOpen.setText("Open");

		mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Attempting to save: " + Main.name);

				boolean checkFile = false;

				byte[] newBytes;

				try {
					checkFile = XMLReader.CheckEntireFile(currentXML);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (checkFile == true) {

					System.out.println("Particle Found! Writing new values to " + currentXML.getName());

					try {

						File fileToEncode = SaveHandler.writeNewValuesToXML(currentXML);

						if (fxFile.getAbsolutePath().endsWith(".bin")) {
							System.out.println("Bin file detected");

							newBytes = AESBin.encryptFile(fileToEncode);

							XMLReader.writeBytesToFile(fxFile, newBytes);

							System.out.println("Task did not fail successfully :) - Wrote "
									+ currentXML.getAbsolutePath() + " to " + fxFile.getAbsolutePath());

							playSuccessAudio();

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (checkFile == false) {

					System.out.println("Particle Not Found!, Creating new particle");

					try {

						File fileToEncode = SaveHandler.writeNewParticleToXML(currentXML);

						System.out.println("Successfully wrote " + Main.name + " to " + currentXML.getName());

						if (fxFile.getAbsolutePath().endsWith(".bin")) {
							System.out.println("Bin file detected");

							newBytes = AESBin.encryptFile(fileToEncode);

							XMLReader.writeBytesToFile(fxFile, newBytes);

							System.out.println("Task did not fail successfully :) - Created " + Main.name
									+ " and Wrote " + currentXML.getAbsolutePath() + " to " + fxFile.getAbsolutePath());

							playSuccessAudio();

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		mntmSave.setEnabled(false);
		mntmSave.setText("Save");

		MenuItem mntmChangeFxDirectory = new MenuItem(menu_1, SWT.NONE);
		mntmChangeFxDirectory.setText("Change FX Directory");
		
		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlWorldOfGoo.dispose();
			}
		});
		mntmQuit.setText("Quit");

		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");

		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu, SWT.NONE);
		mntmAbout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					About.openAbout();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmAbout.setText("About");

		int col = 2;
		canvas = new Canvas(shlWorldOfGoo, SWT.BORDER | SWT.DOUBLE_BUFFERED);
		canvas.setLayoutData(BorderLayout.CENTER);
		canvas.setBackground(SWTResourceManager.getColor(230, 230, 230));

		Label lblNewLabel_1 = new Label(shlWorldOfGoo, SWT.NONE);
		lblNewLabel_1.setLayoutData(BorderLayout.SOUTH);
		lblNewLabel_1.setText(
				" You are running WOGPE Version 0.0.1.3 | WARNING: THIS SOFTWARE IS CURRENTLY IN AN UNSTABLE STATE, USE AT YOUR OWN RISK!");

		Composite group = new Composite(shlWorldOfGoo, SWT.BORDER);
		group.setLayoutData(BorderLayout.EAST);
		group.setLayout(new FillLayout(SWT.VERTICAL));

		TabFolder tabFolder = new TabFolder(group, SWT.NONE);

		TabItem tbtmParticles = new TabItem(tabFolder, SWT.NONE);
		tbtmParticles.setText("Particles");

		tree = new Tree(tabFolder, SWT.BORDER);
		tbtmParticles.setControl(tree);
		tree.setHeaderVisible(true);

		final StackLayout tableLayout = new StackLayout();

		TreeColumn trclmnElement = new TreeColumn(tree, SWT.LEFT);
		trclmnElement.setWidth(200);
		trclmnElement.setText("Element");

		TreeColumn trclmnIdOrName = new TreeColumn(tree, SWT.LEFT);
		trclmnIdOrName.setWidth(100);
		trclmnIdOrName.setText("Id Or Name");

		trtmParticleEffect = new TreeItem(tree, SWT.NONE);
		trtmParticleEffect.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));
		trtmParticleEffect.setText("None");
		trtmParticleEffect.setData("error", true);

		tree.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// TODO Auto-generated method stub

				System.out.println(event.item);

				if (event.item.getData("id") != null) {

					selectedTreeItemid = (int) event.item.getData("id");

				}

				selectedTreeItem = event.item.toString();

				selectedTreeItemItem = (TreeItem) event.item;

				System.out.println(event.item.toString());

				// Particle Effect select

				if (event.item == trtmParticleEffect) {

					System.out.println(trtmParticleEffect);

					if (trtmParticleEffect.getText(0) != "None") {

						table2.setVisible(false);
						table3.setVisible(false);
						table.setVisible(true);
						table4.setVisible(false);

						tableLayout.topControl = table;

						if (table.getItems() != null) {

							table.clearAll();
							table2.clearAll();
							table3.clearAll();

							if (tableItem == null) {
								tableItem = new TableItem(table, SWT.NONE);
							}
							tableItem.setText(0, "Name");
							tableItem.setGrayed(true);
							if (tableItem == null) {
								tableItem = new TableItem(table, SWT.NONE);
							}

							if (tableItem2 == null) {
								tableItem2 = new TableItem(table, SWT.NONE);
							}
							tableItem2.setText(0, "Max Particles");

							if (tableItem3 == null) {
								tableItem3 = new TableItem(table, SWT.NONE);
							}
							tableItem3.setText(0, "Rate");

							if (tableItem4 == null) {
								tableItem4 = new TableItem(table, SWT.NONE);
							}
							tableItem4.setText(0, "Margin");

							tableItem.setText(1, Main.name);
							tableItem2.setText(1, Integer.toString(Main.maxparticles));
							tableItem3.setText(1, Float.toString(Main.rate));
							if (type == "ambientparticleeffect") {
								tableItem4.setText(1, Integer.toString(Main.margin));
							} else {
								tableItem4.setText(1, "N/A");
							}

						}
					}
				}

				if (event.item.getData() == "particle") {

					System.out.println("particleImage");

					if (table.getItems() != null) {

						table.clearAll();
						table2.clearAll();
						table3.clearAll();
						table4.clearAll();

						table2.setVisible(true);
						table3.setVisible(false);
						table.setVisible(false);
						table4.setVisible(false);

						tableLayout.topControl = table2;

						if (tableid == null) {
							tableid = new TableItem(table2, SWT.NONE);
						}
						tableid.setText(0, "ID");
						tableid.setText(1, event.item.getData("id").toString());

						if (tableImage == null) {
							tableImage = new TableItem(table2, SWT.NONE);
						}
						tableImage.setText(0, "Image");
						tableImage.setData("id", (int) event.item.getData("id"));
						System.out.println(tableImage);
						if (Main.imageIndex.get((int) event.item.getData("id")) != null) {

							tableImage.setText(1, Main.imageIndex.get((int) event.item.getData("id")).toString());
							tableImage.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

						} else {
							tableImage.setText(1, "");
							tableImage.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
						}

						if (tableItem5 == null) {
							tableItem5 = new TableItem(table2, SWT.NONE);// Additive
						}
						tableItem5.setText(0, "Additive");
						tableItem5.setData("id", (int) event.item.getData("id"));
						if (!(Main.additive.isEmpty())) {
							if (Main.additive.get((int) event.item.getData("id")) != null) {
								tableItem5.setText(1, Main.additive.get((int) event.item.getData("id")).toString());
							} else {
								tableItem5.setText(1, "");
							}
						}
						if (tableItem6 == null) {
							tableItem6 = new TableItem(table2, SWT.NONE);// Additive
						} // Dampening
						tableItem6.setText(0, "Dampening");
						tableItem6.setData("id", (int) event.item.getData("id"));
						if (!(Main.dampening.isEmpty())) {
							if (Main.dampening.get((int) event.item.getData("id")) != null) {
								tableItem6.setText(1, Main.dampening.get((int) event.item.getData("id")).toString());
							} else {
								tableItem6.setText(1, "");
							}
						}
						if (tableItem7 == null) {
							tableItem7 = new TableItem(table2, SWT.NONE);// Additive
						} // Directed
						tableItem7.setText(0, "Directed");
						tableItem7.setData("id", (int) event.item.getData("id"));
						if (!(Main.directed.isEmpty())) {
							if (Main.directed.get((int) event.item.getData("id")) != null) {
								tableItem7.setText(1, Main.directed.get((int) event.item.getData("id")).toString());
							} else {
								tableItem7.setText(1, "");
							}
						}
						if (tableItem8 == null) {
							tableItem8 = new TableItem(table2, SWT.NONE);// Additive
						} // fade
						tableItem8.setText(0, "Fade");
						tableItem8.setData("id", (int) event.item.getData("id"));
						if (!(Main.fade.isEmpty())) {
							if (Main.fade.get((int) event.item.getData("id")) != null) {
								tableItem8.setText(1, Main.fade.get((int) event.item.getData("id")).toString());
							} else {
								tableItem8.setText(1, "");
							}
						}

						if (tableItem9 == null) {
							tableItem9 = new TableItem(table2, SWT.NONE);// Additive
						} // lifespan
						tableItem9.setText(0, "Lifespan");
						tableItem9.setData("id", (int) event.item.getData("id"));
						if (Main.singlelifespan.isEmpty() == false) {
							if (Main.singlelifespan.get((int) event.item.getData("id")) != null) {

								tableItem9.setText(1,
										Main.singlelifespan.get((int) event.item.getData("id")).toString());
							} else {
								tableItem9.setText(1, "");
							}
						}
						if (tableItem10 == null) {
							tableItem10 = new TableItem(table2, SWT.NONE);// Additive
						} // scale
						tableItem10.setText(0, "Scale");
						tableItem10.setData("id", (int) event.item.getData("id"));
						if (Main.singlescale.isEmpty() == false) {
							if (Main.singlescale.get((int) event.item.getData("id")) != null) {

								tableItem10.setText(1, Main.singlescale.get((int) event.item.getData("id")).toString());
								tableItem10.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

							} else {
								tableItem10.setText(1, "");
								tableItem10.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}
						if (tableItem11 == null) {
							tableItem11 = new TableItem(table2, SWT.NONE);// Additive
						} // finalscale
						tableItem11.setText(0, "Final Scale");
						tableItem11.setData("id", (int) event.item.getData("id"));
						if (Main.finalscale.isEmpty() == false) {
							if (Main.finalscale.get((int) event.item.getData("id")) != null) {

								tableItem11.setText(1, Main.finalscale.get((int) event.item.getData("id")).toString());

							} else {
								tableItem11.setText(1, "");
							}
						}
						if (tableItem12 == null) {
							tableItem12 = new TableItem(table2, SWT.NONE);// Additive
						} // speed
						tableItem12.setText(0, "Speed");
						tableItem12.setData("id", (int) event.item.getData("id"));
						if (Main.singlespeed.isEmpty() == false) {
							if (Main.singlespeed.get((int) event.item.getData("id")) != null) {

								tableItem12.setText(1, Main.singlespeed.get((int) event.item.getData("id")).toString());
								tableItem12.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
							} else {
								tableItem12.setText(1, "");
								tableItem12.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}

						if (tableItem13 == null) {
							tableItem13 = new TableItem(table2, SWT.NONE);// Additive
						} // acceleration
						tableItem13.setText(0, "Acceleration");
						tableItem13.setData("id", (int) event.item.getData("id"));
						if (Main.singleacceleration.isEmpty() == false) {
							if (Main.singleacceleration.get((int) event.item.getData("id")) != null) {

								tableItem13.setText(1,
										Main.singleacceleration.get((int) event.item.getData("id")).toString());

							} else {
								tableItem13.setText(1, "");
							}
						}
						if (tableItem14 == null) {
							tableItem14 = new TableItem(table2, SWT.NONE);// Additive
						} // movedir
						tableItem14.setText(0, "Move Direction");
						tableItem14.setData("id", (int) event.item.getData("id"));
						if (Main.movedir.isEmpty() == false) {
							if (Main.movedir.get((int) event.item.getData("id")) != null) {

								tableItem14.setText(1, Main.movedirvar.get((int) event.item.getData("id")).toString());

							} else {
								tableItem14.setText(1, "");
							}
						}
						if (tableItem15 == null) {
							tableItem15 = new TableItem(table2, SWT.NONE);// Additive
						} // movedirvar
						tableItem15.setText(0, "Move Direction Variation");
						tableItem15.setData("id", (int) event.item.getData("id"));
						if (Main.movedirvar.isEmpty() == false) {
							if (Main.movedirvar.get((int) event.item.getData("id")) != null) {

								tableItem15.setText(1, Main.movedirvar.get((int) event.item.getData("id")).toString());
								tableItem15.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

							} else {
								tableItem15.setText(1, "");
								tableItem15.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}

						if (tableItem16 == null) {
							tableItem16 = new TableItem(table2, SWT.NONE);// Additive
						} // rotation
						tableItem16.setText(0, "Rotation");
						tableItem16.setData("id", (int) event.item.getData("id"));
						if (Main.singlerotation.isEmpty() == false) {
							if (Main.singlerotation.get((int) event.item.getData("id")) != null) {

								tableItem16.setText(1,
										Main.singlerotation.get((int) event.item.getData("id")).toString());

							} else {
								tableItem16.setText(1, "");
							}
						}
						if (tableItem17 == null) {
							tableItem17 = new TableItem(table2, SWT.NONE);// Additive
						} // rotspeed
						tableItem17.setText(0, "Rotation Speed");
						tableItem17.setData("id", (int) event.item.getData("id"));
						if (Main.singlerotspeed.isEmpty() == false) {
							if (Main.singlerotspeed.get((int) event.item.getData("id")) != null) {

								tableItem17.setText(1,
										Main.singlerotspeed.get((int) event.item.getData("id")).toString());

							} else {
								tableItem17.setText(1, "");
							}
						}

					}
				}

				if (event.item.getData() == "axialsinoffset") {

					System.out.println("axialsinoffset");

					if (table.getItems() != null) {

						table.clearAll();
						table2.clearAll();
						table3.clearAll();
						table4.clearAll();

						table3.setVisible(true);
						table2.setVisible(false);
						table.setVisible(false);
						table4.setVisible(false);

						tableLayout.topControl = table3;

						if (tableItem18 == null) {
							tableItem18 = new TableItem(table3, SWT.NONE);
						}
						tableItem18.setText(0, "Amp");
						tableItem18.setData("aid", (int) event.item.getData("aid"));
						if (Main.singleamp.isEmpty() == false) {
							if (Main.singleamp.get((int) event.item.getData("aid")) != null) {

								tableItem18.setText(1, Main.singleamp.get((int) event.item.getData("aid")).toString());
								tableItem18.setForeground(0, display.getSystemColor(SWT.COLOR_RED));

							} else {
								tableItem18.setText(1, "");
								tableItem18.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}
						if (tableItem19 == null) {
							tableItem19 = new TableItem(table3, SWT.NONE);
						}
						tableItem19.setText(0, "Axis");
						tableItem19.setData("aid", (int) event.item.getData("aid"));
						if (Main.axis.isEmpty() == false) {
							if (Main.axis.get((int) event.item.getData("aid")) != null) {

								tableItem19.setText(1, Main.axis.get((int) event.item.getData("aid")).toString());
								tableItem19.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

							} else {
								tableItem19.setText(1, "");
								tableItem19.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}
						if (tableItem20 == null) {
							tableItem20 = new TableItem(table3, SWT.NONE);
						}
						tableItem20.setText(0, "Frequency");
						tableItem20.setData("aid", (int) event.item.getData("aid"));
						if (Main.singlefreq.isEmpty() == false) {
							if (Main.singlefreq.get((int) event.item.getData("aid")) != null) {

								tableItem20.setText(1, Main.singlefreq.get((int) event.item.getData("aid")).toString());
								tableItem20.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

							} else {
								tableItem20.setText(1, "");
								tableItem20.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}
						if (tableItem21 == null) {
							tableItem21 = new TableItem(table3, SWT.NONE);
						}
						tableItem21.setText(0, "Phase Shift");
						tableItem21.setData("aid", (int) event.item.getData("aid"));
						if (Main.singlephaseshift.isEmpty() == false) {
							if (Main.singlephaseshift.get((int) event.item.getData("aid")) != null) {

								tableItem21.setText(1,
										Main.singlephaseshift.get((int) event.item.getData("aid")).toString());
								tableItem21.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));

							} else {
								tableItem21.setText(1, "");
								tableItem21.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
							}
						}
					}
				}
			}
		});

		final Menu treemenu = new Menu(tree);

		tree.setMenu(treemenu);

		treemenu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				MenuItem[] items = treemenu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				if (selectedTreeItem.equals("TreeItem {Ambient Particle Effect}")
						|| selectedTreeItem.equals("TreeItem {Particle Effect}")) {
					MenuItem newItem = new MenuItem(treemenu, SWT.NONE);
					newItem.setText("Add new Particle");
					newItem.setImage(SWTResourceManager.getImage(Main.class,
							"/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));

					newItem.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							numberOfParticles++;
							createNewParticle();
						}
					});

				} else if (!(selectedTreeItem.equals("TreeItem {Ambient Particle Effect}")
						|| selectedTreeItem.equals("TreeItem {Particle Effect}")
						|| selectedTreeItem.equals("TreeItem {None}"))) {
					if (!(selectedTreeItem.equals("TreeItem {axialsinoffset}"))) {
						MenuItem newItem = new MenuItem(treemenu, SWT.NONE);
						newItem.setText("Add new AxialSinOffset");
						newItem.setImage(SWTResourceManager.getImage(Main.class,
								"/me/WatchingYoutube/ParticleEditor/assets/balloon-icon.png"));

						newItem.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {

								createNewAxialSinOffset(selectedTreeItemid, selectedTreeItemItem);

							}
						});
						
						MenuItem newItem1 = new MenuItem(treemenu, SWT.NONE);
						newItem1.setText("Delete Particle");
						newItem1.setImage(SWTResourceManager.getImage(Main.class,
								"/me/WatchingYoutube/ParticleEditor/assets/delete-icon.png"));

						newItem1.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {

								MessageBoxHelper.giveDeleteMessage(selectedTreeItemid);
								
								System.out.println(selectedTreeItemid);

							}
						});

					}
				}

			}

		});

		TabItem tbtmImages = new TabItem(tabFolder, SWT.NONE);
		tbtmImages.setText("Images");

		tree2 = new Tree(tabFolder, SWT.BORDER);
		tbtmImages.setControl(tree2);
		tree2.setHeaderVisible(true);
		tree2.setVisible(true);

		trtmResources = new TreeItem(tree2, SWT.NONE);
		trtmResources.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/Image-icon.png"));
		trtmResources.setText("Resources");

		tree2.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {

				if (event.item.getData() == "Image") {

					System.out.println("image");

					if (table.getItems() != null) {

						table.clearAll();
						table2.clearAll();
						table3.clearAll();
						table4.clearAll();

						table2.setVisible(false);
						table3.setVisible(false);
						table4.setVisible(true);
						table.setVisible(false);

						tableLayout.topControl = table4;

						if (tableid == null) {
							tableid = new TableItem(table4, SWT.NONE);
						}
						tableid.setText(0, "ID");
						tableid.setText(1, event.item.getData("id").toString());

						if (tableItem22 == null) {
							tableItem22 = new TableItem(table4, SWT.NONE);
						}
						tableItem22.setText(0, "Image");
						tableItem22.setData("id", (int) event.item.getData("id"));

						if (Main.image.get((int) event.item.getData("id")) != null) {

							tableItem22.setText(1, Main.image.get((int) event.item.getData("id")).toString());

						} else {
							tableImage.setText(1, "");
						}

						if (tableItem23 == null) {
							tableItem23 = new TableItem(table4, SWT.NONE);// Additive
						}
						tableItem23.setText(0, "Path");
						tableItem23.setData("id", (int) event.item.getData("id"));
						if (Main.path.get((int) event.item.getData("id")) != null) {
							tableItem23.setText(1, Main.path.get((int) event.item.getData("id")).toString());

						} else {
							tableItem23.setText(1, "");
						}
					}

				}

			}
		});

		TreeColumn trclmnElement_1 = new TreeColumn(tree2, SWT.LEFT);
		trclmnElement_1.setWidth(200);
		trclmnElement_1.setText("Element");

		TreeColumn trclmnIdOrName_1 = new TreeColumn(tree2, SWT.LEFT);
		trclmnIdOrName_1.setWidth(100);
		trclmnIdOrName_1.setText("Id Or Name");

		group_1 = new Composite(group, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		group_1.setLayout(tableLayout);

		table = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setVisible(true);

		tableLayout.topControl = table;

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(148);
		tblclmnNewColumn.setText("Name                ");
		tblclmnNewColumn.pack();

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(207);
		tblclmnNewColumn_1.setText("Value");

		final TableEditor editor = new TableEditor(table);

		// The editor must have the same size as the cell and must
		// not be any smaller than 50 pixels.
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN = 1;

		table2 = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);

		final TableEditor editor2 = new TableEditor(table2);

		// The editor must have the same size as the cell and must
		// not be any smaller than 50 pixels.
		editor2.horizontalAlignment = SWT.LEFT;
		editor2.grabHorizontal = true;
		editor2.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN2 = 1;

		table3 = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);

		final TableEditor editor3 = new TableEditor(table3);

		// The editor must have the same size as the cell and must
		// not be any smaller than 50 pixels.
		editor3.horizontalAlignment = SWT.LEFT;
		editor3.grabHorizontal = true;
		editor3.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN3 = 1;

		table4 = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);

		final TableEditor editor4 = new TableEditor(table4);

		// The editor must have the same size as the cell and must
		// not be any smaller than 50 pixels.
		editor2.horizontalAlignment = SWT.LEFT;
		editor2.grabHorizontal = true;
		editor2.minimumWidth = 50;
		// editing the second column
		final int EDITABLECOLUMN4 = 1;

		table2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				Control oldEditor3 = editor3.getEditor();
				Control oldEditor4 = editor4.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();
				if (oldEditor2 != null)
					oldEditor2.dispose();
				if (oldEditor3 != null)
					oldEditor3.dispose();
				if (oldEditor4 != null)
					oldEditor4.dispose();

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				if (item == null || item.equals(tableItem) || item.equals(tableid))
					return;
				// The control that will be the editor must be a child of the
				// Table
				Text newEditor = new Text(table2, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN2));
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent me) {
						if (currentEffectOpen != false) {
							Text text = (Text) editor2.getEditor();

							String selectedItem = item.getText();

							Menu popupMenu = new Menu(newEditor);

							if (selectedItem.equals("Fade")) {
								MenuItem popuptrue = new MenuItem(popupMenu, SWT.NONE);
								popuptrue.setText("True");
								MenuItem popupfalse = new MenuItem(popupMenu, SWT.NONE);
								popupfalse.setText("False");

							}

							newEditor.setMenu(popupMenu);
							// if (editor2.getItem().getText() != null) {
							// editor2.getItem().setText(EDITABLECOLUMN2, text.getText());
							// }
						}
					}
				});

				newEditor.addListener(SWT.FocusOut, new Listener() {

					public void handleEvent(Event event) {

						editor2.getItem().setText(EDITABLECOLUMN2, item.getText(1));
						System.out.println("You are out");

					}

				});

				newEditor.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event event) {
						if (event.detail == SWT.TRAVERSE_RETURN) {
							System.out.println("Enter pressed for: " + item.getText());

							System.out.println(newEditor.getText());

							String selectedItem = item.getText();

							if (selectedItem.equals("Image")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {
									int trueItems = 0;
									try {

										ArrayList<String> imagesToCheck = new ArrayList<String>(
												Arrays.asList(newEditor.getText().split(",")));

										for (int i = 0; i < imagesToCheck.size(); i++) {

											if (ImageResource.checkImage(imagesToCheck.get(i)) == true) {
												trueItems++;
											}
										}

										if (trueItems == imagesToCheck.size()) {
											imageIndex.set((int) item.getData("id"),
													imagesToCheck.toString().replace("[", "").replace("]", ""));
											System.out.println("Images have been checked and added successfully");
											item.setText(1, newEditor.getText());
											item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
										}
									} catch (NumberFormatException e) {
										e.printStackTrace();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}

							if (selectedItem.equals("Additive")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {
									if (newEditor.getText().equals("true") || newEditor.getText().equals("false"))

										try {
											if (additive.get((int) item.getData("id")) != null) {
												additive.set((int) item.getData("id"),
														Boolean.parseBoolean(newEditor.getText()));
												item.setText(1, newEditor.getText());
											}
										} catch (NumberFormatException e) {
											e.printStackTrace();
										}

								}
							}

							if (selectedItem.equals("Dampening")) {

								try {
									dampening.set((int) item.getData("id"), Float.parseFloat(newEditor.getText()));
									item.setText(1, newEditor.getText());
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										dampening.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Directed")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {
									if (newEditor.getText().equals("true") || newEditor.getText().equals("false"))

										try {
											if (directed.get((int) item.getData("id")) != null) {
												directed.set((int) item.getData("id"),
														Boolean.parseBoolean(newEditor.getText()));
												item.setText(1, newEditor.getText());
											}
										} catch (NumberFormatException e) {
											e.printStackTrace();
										}

								}
							}

							if (selectedItem.equals("Fade")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {
									if (newEditor.getText().equals("true") || newEditor.getText().equals("false"))

										try {
											if (fade.get((int) item.getData("id")) != null) {
												fade.set((int) item.getData("id"),
														Boolean.parseBoolean(newEditor.getText()));
												item.setText(1, newEditor.getText());
											}
										} catch (NumberFormatException e) {
											e.printStackTrace();
										}

								}
							}

							if (selectedItem.equals("Lifespan")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlelifespan.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlelifespan.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Scale")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlescale.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlescale.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Final Scale")) {

								try {
									finalscale.set((int) item.getData("id"), Float.parseFloat(newEditor.getText()));
									item.setText(1, newEditor.getText());
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										finalscale.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Speed")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlespeed.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlespeed.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Acceleration")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singleacceleration.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singleacceleration.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Move Direction")) {

								try {
									movedir.set((int) item.getData("id"), Integer.parseInt(newEditor.getText()));
									item.setText(1, newEditor.getText());
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										movedir.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Move Direction Variation")) {

								try {
									movedirvar.set((int) item.getData("id"), Integer.parseInt(newEditor.getText()));
									item.setText(1, newEditor.getText());
									item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										movedirvar.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Rotation")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Integer> newLifespanF = new ArrayList<Integer>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Integer.parseInt(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlerotation.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlerotation.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Rotation Speed")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlerotspeed.set((int) item.getData("id"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlerotspeed.set((int) item.getData("id"), null);
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}
							newEditor.dispose();
						}

					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor2.setEditor(newEditor, item, EDITABLECOLUMN2);
			}
		});
		table2.setLinesVisible(true);
		table2.setHeaderVisible(true);
		table2.setVisible(false);

		TableColumn tblclmnNewColumn_2 = new TableColumn(table2, SWT.NONE);
		tblclmnNewColumn_2.setWidth(148);
		tblclmnNewColumn_2.setText("Name                ");

		TableColumn tblclmnNewColumn_1_1 = new TableColumn(table2, SWT.NONE);
		tblclmnNewColumn_1_1.setWidth(207);
		tblclmnNewColumn_1_1.setText("Value");

		table3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				Control oldEditor3 = editor3.getEditor();
				Control oldEditor4 = editor4.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();
				if (oldEditor2 != null)
					oldEditor2.dispose();
				if (oldEditor3 != null)
					oldEditor3.dispose();
				if (oldEditor4 != null)
					oldEditor4.dispose();

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				if (item == null || item.equals(tableItem) || item.equals(tableid))
					return;
				// The control that will be the editor must be a child of the
				// Table
				Text newEditor = new Text(table3, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN3));
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent me) {
						if (currentEffectOpen != false) {
							Text text = (Text) editor3.getEditor();
							// editor3.getItem().setText(EDITABLECOLUMN3, text.getText());
						}
					}
				});
				newEditor.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event event) {
						if (event.detail == SWT.TRAVERSE_RETURN) {
							System.out.println("Enter pressed for: " + item.getText());

							String selectedItem = item.getText();

							if (selectedItem.equals("Amp")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Integer> newLifespanF = new ArrayList<Integer>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Integer.parseInt(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singleamp.set((int) item.getData("aid"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singleamp.set((int) item.getData("aid"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Axis")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {
									if (newEditor.getText().equals("x") || newEditor.getText().equals("y"))

										try {
											axis.set((int) item.getData("aid"), newEditor.getText());
											item.setText(1, newEditor.getText());
											item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
										} catch (NumberFormatException e) {
											e.printStackTrace();
											item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
										}

								}
							}

							if (selectedItem.equals("Frequency")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlefreq.set((int) item.getData("aid"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlefreq.set((int) item.getData("aid"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}

							if (selectedItem.equals("Phase Shift")) {

								try {
									ArrayList<String> newLifespanstr = new ArrayList<String>(
											Arrays.asList(newEditor.getText().split(",")));
									ArrayList<Float> newLifespanF = new ArrayList<Float>();

									for (int i = 0; i < newLifespanstr.size(); i++) {
										newLifespanF.add(Float.parseFloat(newLifespanstr.get(i)));
									}

									if (newLifespanF.size() <= 2) {

										singlephaseshift.set((int) item.getData("aid"),
												newLifespanF.toString().replace("[", "").replace("]", ""));

										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_BLACK));
									}
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										singlephaseshift.set((int) item.getData("aid"), null);
										item.setText(1, newEditor.getText());
										item.setForeground(0, display.getSystemColor(SWT.COLOR_RED));
									} else {
										e.printStackTrace();
									}
								}
							}
							newEditor.dispose();
						}

					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor3.setEditor(newEditor, item, EDITABLECOLUMN2);
			}
		});
		table3.setVisible(false);
		table3.setLinesVisible(true);
		table3.setHeaderVisible(true);

		TableColumn tblclmnNewColumn_2_1 = new TableColumn(table3, SWT.NONE);
		tblclmnNewColumn_2_1.setWidth(148);
		tblclmnNewColumn_2_1.setText("Name                ");

		TableColumn tblclmnNewColumn_1_1_1 = new TableColumn(table3, SWT.NONE);
		tblclmnNewColumn_1_1_1.setWidth(207);
		tblclmnNewColumn_1_1_1.setText("Value");

		table4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				Control oldEditor3 = editor3.getEditor();
				Control oldEditor4 = editor4.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();
				if (oldEditor2 != null)
					oldEditor2.dispose();
				if (oldEditor3 != null)
					oldEditor3.dispose();
				if (oldEditor4 != null)
					oldEditor4.dispose();

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				if (item == null || item.equals(tableItem) || item.equals(tableid))
					return;
				// The control that will be the editor must be a child of the
				// Table
				Text newEditor = new Text(table3, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN3));
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent me) {
						if (currentEffectOpen != false) {
							Text text = (Text) editor3.getEditor();
							// editor3.getItem().setText(EDITABLECOLUMN3, text.getText());
						}
					}
				});

				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(item.getText(1)),
						null);

				newEditor.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event event) {

						if (event.detail == SWT.TRAVERSE_RETURN) {
							System.out.println("Enter pressed for: " + item.getText());

							String selectedItem = item.getText();

							if (selectedItem.equals("Image")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {

									boolean isResource = false;
									try {
										isResource = ImageResource.checkImage(newEditor.getText());
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									if (isResource == true) {

										try {
											image.set((int) item.getData("id"), newEditor.getText());
											item.setText(1, newEditor.getText());
										} catch (NumberFormatException e) {
											e.printStackTrace();
										}

									}
								}
							}

							if (selectedItem.equals("Path")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {

									boolean isResource = false;
									try {
										isResource = ImageResource.checkPath(newEditor.getText());
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									if (isResource == true) {

										try {
											path.set((int) item.getData("id"), newEditor.getText());
											item.setText(1, newEditor.getText());
										} catch (NumberFormatException e) {
											e.printStackTrace();
										}

									}
								}
							}

							newEditor.dispose();
						}

					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor4.setEditor(newEditor, item, EDITABLECOLUMN4);
			}
		});
		table4.setVisible(false);
		table4.setLinesVisible(true);
		table4.setHeaderVisible(true);

		TableColumn tblclmnNewColumn_2_1_1 = new TableColumn(table4, SWT.NONE);
		tblclmnNewColumn_2_1_1.setWidth(148);
		tblclmnNewColumn_2_1_1.setText("Name                ");

		TableColumn tblclmnNewColumn_1_1_1_1 = new TableColumn(table4, SWT.NONE);
		tblclmnNewColumn_1_1_1_1.setWidth(207);
		tblclmnNewColumn_1_1_1_1.setText("Value");
		group_1.setTabList(new Control[] { table, table2, table3, table4 });

		Composite composite = new Composite(shlWorldOfGoo, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new FormLayout());

		ToolBar toolBar = new ToolBar(composite, SWT.RIGHT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0, 3);
		fd_toolBar.left = new FormAttachment(0, 3);
		toolBar.setLayoutData(fd_toolBar);

		tltmNew = new ToolItem(toolBar, SWT.NONE);
		tltmNew.setEnabled(false);
		tltmNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewFX.openDialog();
			}
		});
		tltmNew.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/Actions-document-new-icon.png"));

		tltmOpen = new ToolItem(toolBar, SWT.NONE);
		tltmOpen.setEnabled(false);
		tltmOpen.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					OpenFX.openDialog();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tltmOpen.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/Actions-document-open-icon.png"));

		ToolItem tltmClone = new ToolItem(toolBar, SWT.NONE);
		tltmClone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CloneFX.openDialog();
			}
		});
		tltmClone.setEnabled(false);
		tltmClone.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/Actions-edit-copy-icon.png"));

		ToolItem tltmSave = new ToolItem(toolBar, SWT.NONE);
		tltmSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				System.out.println("Attempting to save: " + Main.name);

				boolean checkFile = false;

				byte[] newBytes;

				try {
					checkFile = XMLReader.CheckEntireFile(currentXML);

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (checkFile == true) {

					System.out.println("Particle Found! Writing new values to " + currentXML.getName());

					try {

						File fileToEncode = SaveHandler.writeNewValuesToXML(currentXML);

						if (fxFile.getAbsolutePath().endsWith(".bin")) {
							System.out.println("Bin file detected");

							newBytes = AESBin.encryptFile(fileToEncode);

							XMLReader.writeBytesToFile(fxFile, newBytes);

							System.out.println("Task did not fail successfully :) - Wrote "
									+ currentXML.getAbsolutePath() + " to " + fxFile.getAbsolutePath());

							playSuccessAudio();

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (checkFile == false) {

					System.out.println("Particle Not Found!, Creating new particle");

					try {

						File fileToEncode = SaveHandler.writeNewParticleToXML(currentXML);

						System.out.println("Successfully wrote " + Main.name + " to " + currentXML.getName());

						if (fxFile.getAbsolutePath().endsWith(".bin")) {
							System.out.println("Bin file detected");

							newBytes = AESBin.encryptFile(fileToEncode);

							XMLReader.writeBytesToFile(fxFile, newBytes);

							System.out.println("Task did not fail successfully :) - Created " + Main.name
									+ " and Wrote " + currentXML.getAbsolutePath() + " to " + fxFile.getAbsolutePath());

							playSuccessAudio();

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		tltmSave.setEnabled(false);
		tltmSave.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/Actions-document-save-icon.png"));

		ToolItem tltmOpenImage = new ToolItem(toolBar, SWT.NONE);
		tltmOpenImage.setEnabled(false);
		tltmOpenImage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JFileChooser fd = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fd.setDialogTitle("Open PNG Images");
				fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fd.setMultiSelectionEnabled(false);

				FileNameExtensionFilter ff = new FileNameExtensionFilter("Portable Network Graphics", "png");

				fd.addChoosableFileFilter(ff);

				fd.setFileFilter(ff);

				int returnValue = fd.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String selected = fd.getSelectedFile().getAbsolutePath();
					System.out.println(selected);

					if (selected != null) {

						File openedFile = fd.getSelectedFile();
						System.out.println(openedFile);

						if (selected.endsWith(".png") || selected.endsWith(".PNG")) {

							Image ogImage = SWTResourceManager.getImage(selected);
							renderedImages.add(fd.getSelectedFile());
							System.out.println(fd.getSelectedFile().getName());
							String imageName = fd.getSelectedFile().getName().toUpperCase().replaceFirst(".PNG", "")
									.replaceAll("[-+.^:,]", "");
							System.out.println(imageName);

							image.add("IMAGE_FX_" + imageName.replace(" ", "_"));
							path.add("res/images/fx/"
									+ fd.getSelectedFile().getName().replace(" ", "_").replaceFirst(".png", ""));
							/*
							 * try { ImageResource.createNewResource(openedFile.getAbsoluteFile()); } catch
							 * (IOException e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
							 */
							System.out.println(image);
							System.out.println(path);

							for (int indexrange = 0; indexrange < image.size(); indexrange++) {

								if (image.get(indexrange).equals("IMAGE_FX_" + imageName.replace(" ", "_"))) {

									createNewImageElement(indexrange);
								}
							}

							try {
								ImageResource.createNewResource(openedFile.getAbsoluteFile(),
										"IMAGE_FX_" + imageName.replace(" ", "_"),
										"res/images/fx/"
												+ openedFile.getName().replace(" ", "_").replaceFirst(".png", ""),
										imageName.toLowerCase());
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (TransformerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							InputStream is = null;

							ImageLoader loader = new ImageLoader();
							try {
								is = new FileInputStream(fd.getSelectedFile());
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							Image imageLabel = new Image(display, ogImage.getImageData().scaledTo(54, 54));

							lblNewLabel.setImage(imageLabel);

							canvas.redraw();

							try {
								prepareImagesToCanvas();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}

				}
			}

		});

		tltmOpenImage.setImage(SWTResourceManager.getImage(Main.class,
				"/me/WatchingYoutube/ParticleEditor/assets/Actions-insert-image-icon.png"));

		ToolItem tltmPlayWOG = new ToolItem(toolBar, SWT.NONE);
		tltmPlayWOG.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				boolean hasErrors = false;
				
				String maxparticlesError = null;
				
				String errorMessage = "";
				
				if (maxparticles < 1) {
					
					hasErrors = true;
					maxparticlesError = "Max Particles must be at least 1.";
					
				}
				
				
				
				if (hasErrors == true) {
					
					if (maxparticlesError != null) {
						errorMessage = errorMessage + "Element: " + Main.name + System.lineSeparator() + maxparticlesError + System.lineSeparator() +System.lineSeparator();

					}
					
					try {
						MessageBoxHelper.givePlayErrorMessage(errorMessage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				else if (hasErrors == false) {

				if (newWOG == false) {

					String worldofgooexe = worldofgoopath + "WorldOfGoo.exe";

					String levelPath = worldofgoopath + "res\\levels\\WogPE\\";

					Path checkLevelPath = Paths.get(levelPath);

					if (Files.notExists(checkLevelPath)) {
						System.out.println("Level does not exist, creating new level");

						File directory = new File(levelPath);

						directory.mkdir();

						InputStream copyLevel = getClass().getResourceAsStream(
								"/me/WatchingYoutube/ParticleEditor/TestLevel/WogPE/WogPE.level.bin");
						InputStream copyScene = getClass().getResourceAsStream(
								"/me/WatchingYoutube/ParticleEditor/TestLevel/WogPE/WogPE.scene.bin");
						InputStream copyResrc = getClass().getResourceAsStream(
								"/me/WatchingYoutube/ParticleEditor/TestLevel/WogPE/WogPE.resrc.bin");

						try {

							Files.copy(copyLevel, (new File(checkLevelPath + "\\WogPE.level.bin")).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
							Files.copy(copyScene, (new File(checkLevelPath + "\\WogPE.scene.bin")).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
							Files.copy(copyResrc, (new File(checkLevelPath + "\\WogPE.resrc.bin")).toPath(),
									StandardCopyOption.REPLACE_EXISTING);

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

					File worldofgoofile = new File(worldofgooexe);

					String[] cmdlist = { worldofgooexe, "WogPE"/* , System.getProperty("user.dir") */ };

					File levelLevel = new File(levelPath + "WogPE.level.bin\\");

					File levelScene = new File(levelPath + "WogPE.scene.bin\\");

					File levelText = new File(propertiesPath + "text.xml.bin\\");

					File newLevel = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPELevel\\WogPE.level.xml");

					File newScene = new File(System.getProperty("user.home") + "\\WOGPE\\WOGPELevel\\WogPE.scene.xml");

					InputStream ISLevel = getClass()
							.getResourceAsStream("/me/WatchingYoutube/ParticleEditor/TestLevel/WogPE/WogPE.level.xml");
					InputStream ISScene = getClass()
							.getResourceAsStream("/me/WatchingYoutube/ParticleEditor/TestLevel/WogPE/WogPE.scene.xml");

					Path pathLevel = Paths.get(newLevel.getAbsolutePath());

					Path pathScene = Paths.get(newScene.getAbsolutePath());

					File preparedLevel;

					File preparedScene;
					

					try {
						newLevel.getParentFile().mkdirs();

						newScene.getParentFile().mkdirs();

						Files.copy(ISLevel, pathLevel, StandardCopyOption.REPLACE_EXISTING);

						Files.copy(ISScene, pathScene, StandardCopyOption.REPLACE_EXISTING);

						byte[] decryptedText = AESBin.decryptFile(levelText);

						preparedLevel = XMLReader.prepareLevel(newLevel);

						preparedScene = XMLReader.prepareScene(newScene);

						System.out.println(preparedLevel);

						byte[] encryptedLevel = AESBin.encryptFile(preparedLevel);

						byte[] encryptedScene = AESBin.encryptFile(preparedScene);

						XMLReader.writeBytesToFile(levelLevel, encryptedLevel);

						XMLReader.writeBytesToFile(levelScene, encryptedScene);

					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {

						ProcessBuilder process = new ProcessBuilder(cmdlist);

						process.directory(worldofgoofile.getAbsoluteFile().getParentFile());
						process.start();

						System.out.println(Arrays.toString(cmdlist));
						System.out.println(System.setProperty("user.dir", worldofgoopath));
						System.out.println(worldofgoofile.getAbsoluteFile().getParentFile());

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (newWOG == true) {
					String worldofgooexe = worldofgoopath + "WorldOfGoo.exe";

					String levelPath = worldofgoopath + "res\\levels\\WogPE\\";

					File worldofgoofile = new File(worldofgooexe);

					String[] cmdlist = { worldofgooexe, "GoingUp"/* , System.getProperty("user.dir") */ };

					try {

						ProcessBuilder process = new ProcessBuilder(cmdlist);

						process.directory(worldofgoofile.getAbsoluteFile().getParentFile());
						process.start();

						System.out.println(Arrays.toString(cmdlist));
						System.out.println(System.setProperty("user.dir", worldofgoopath));
						System.out.println(worldofgoofile.getAbsoluteFile().getParentFile());

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}}
		});
		tltmPlayWOG.setEnabled(false);
		tltmPlayWOG.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/play-icon.png"));

		ToolItem tltmPrintAll = new ToolItem(toolBar, SWT.NONE);
		tltmPrintAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Type: " + type);
				System.out.println("Name: " + name);
				System.out.println("Max Particles: " + maxparticles);
				System.out.println("Rate: " + rate);
				System.out.println("Margin: " + margin);
				System.out.println("Image: " + image);
				System.out.println("Path: " + path);
				System.out.println("ImageIndex: " + imageIndex);
				System.out.println("PathIndex: " + pathIndex);
				System.out.println("Additive: " + additive);
				System.out.println("Dampening: " + dampening);
				System.out.println("Directed: " + directed);
				System.out.println("Fade: " + fade);
				System.out.println("Lifespan: " + lifespan);
				System.out.println(singlelifespan);
				System.out.println("Scale: " + scale);
				System.out.println("FinalScale: " + finalscale);
				System.out.println("Speed: " + speed);
				System.out.println("Acceleration: " + acceleration);
				System.out.println("Movedir: " + movedir);
				System.out.println("Movedirvar: " + movedirvar);
				System.out.println("Rotation: " + rotation);
				System.out.println("Rotspeed: " + rotspeed);
				System.out.println("NumberOfParticles: " + numberOfParticles);
				System.out.println("numberOfAxialSinOffset: " + numberOfAxialSinOffset);
				System.out.println("amp: " + amp);
				System.out.println("singleamp: " + singleamp);
				System.out.println("axis: " + axis);
				System.out.println("freq" + freq);
				System.out.println("singlefreq: " + singlefreq);
				System.out.println("phaseshift: " + phaseshift);
				System.out.println("singlephaseshift: " + singlephaseshift);
				
				MessageBoxHelper.givePlayErrorMessage("tEST");

			}
		});
		tltmPrintAll.setText("Print All");

		group_2 = new Group(composite, SWT.NONE);
		RowLayout rl_group_2 = new RowLayout(SWT.HORIZONTAL);
		rl_group_2.marginRight = 0;
		rl_group_2.marginTop = 0;
		rl_group_2.marginBottom = 0;
		group_2.setLayout(rl_group_2);
		FormData fd_group_2 = new FormData();
		fd_group_2.bottom = new FormAttachment(toolBar, 0, SWT.BOTTOM);
		fd_group_2.top = new FormAttachment(0, -15);
		fd_group_2.right = new FormAttachment(100, -10);
		group_2.setLayoutData(fd_group_2);

		Scale scale_1 = new Scale(group_2, SWT.BORDER);
		scale_1.setLayoutData(new RowData(SWT.DEFAULT, 54));

		scale_1.setMaximum(255);
		scale_1.setSelection(25);

		lblNewLabel = new Label(group_2, SWT.BORDER | SWT.RIGHT);
		lblNewLabel.setLayoutData(new RowData(54, 54));
		lblNewLabel.setAlignment(SWT.RIGHT);

		scale_1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int perspectiveValue = scale_1.getMaximum() - scale_1.getSelection() + scale_1.getMinimum();
				CanvasColor = perspectiveValue;
				canvas.setBackground(SWTResourceManager.getColor(CanvasColor, CanvasColor, CanvasColor));
			}
		});

		group.setTabList(new Control[] { tabFolder });

		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				Control oldEditor2 = editor2.getEditor();
				Control oldEditor3 = editor3.getEditor();
				Control oldEditor4 = editor4.getEditor();
				if (oldEditor != null)
					oldEditor.dispose();
				if (oldEditor2 != null)
					oldEditor2.dispose();
				if (oldEditor3 != null)
					oldEditor3.dispose();
				if (oldEditor4 != null)
					oldEditor4.dispose();

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				if (item == null || item.equals(tableItem))
					return;
				if (item.equals(tableItem4) && type == "particleeffect")
					return;
				// The control that will be the editor must be a child of the
				// Table
				Text newEditor = new Text(table, SWT.NONE);
				newEditor.setText(item.getText(EDITABLECOLUMN));
				newEditor.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent me) {
						if (currentEffectOpen != false) {
							Text text = (Text) editor.getEditor();

							// editor.getItem().setText(EDITABLECOLUMN, text.getText());
						}
					}
				});
				newEditor.addListener(SWT.Traverse, new Listener() {
					public void handleEvent(Event event) {
						if (event.detail == SWT.TRAVERSE_RETURN) {
							System.out.println("Enter pressed for: " + item.getText());

							// PooPoo

							String selectedItem = item.getText();

							if (selectedItem.equals("Max Particles")) {
								if (!(newEditor.getText() == null || newEditor.getText() == "")) {

									try {
										maxparticles = Integer.parseInt(newEditor.getText());
										item.setText(1, newEditor.getText());
									} catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							}
							if (selectedItem.equals("Rate")) {

								try {
									rate = Float.parseFloat(newEditor.getText());
									item.setText(1, newEditor.getText());
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										rate = 0.0F;
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}
								}
							}
							if (selectedItem.equals("Margin")) {

								try {
									margin = Integer.parseInt(newEditor.getText());
									item.setText(1, newEditor.getText());
								} catch (NumberFormatException e) {
									if (newEditor.getText().length() <= 0) {
										margin = 0;
										item.setText(1, newEditor.getText());
									} else {
										e.printStackTrace();
									}

								}
							}

							newEditor.dispose();

						}
					}
				});
				newEditor.selectAll();
				newEditor.setFocus();
				editor.setEditor(newEditor, item, EDITABLECOLUMN);
			}
		});

		shlWorldOfGoo.open();
		shlWorldOfGoo.layout();

		class Open implements SelectionListener {
			public void widgetSelected(SelectionEvent event) {
				JFileChooser fd = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fd.setDialogTitle("Open FX XML or BIN");
				fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fd.setMultiSelectionEnabled(false);

				FileNameExtensionFilter ff = new FileNameExtensionFilter("Encrypted Bin Files", "bin");
				FileNameExtensionFilter ff2 = new FileNameExtensionFilter("Xml Files", "xml");

				fd.addChoosableFileFilter(ff);
				fd.addChoosableFileFilter(ff2);

				fd.setFileFilter(ff);

				int returnValue = fd.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					String selected = fd.getSelectedFile().getAbsolutePath();
					System.out.println(selected);

					if (selected != null) {

						File openedFile = fd.getSelectedFile();
						System.out.println(openedFile);

						if (selected.endsWith(".bin")) {

							currentXML.getParentFile().mkdirs();
							currentResources.getParentFile().mkdirs();
							try {
								currentXML.createNewFile();
								currentResources.createNewFile();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {
								byte[] bytes = AESBin.decryptFile(openedFile);
								currentXML.delete();
								XMLReader.writeBytesToFile(currentXML, bytes);
								XMLReader.XMLToParse = currentXML;
								XMLReader.ParseEntireFile(XMLReader.usingBufferedReader(currentXML.getAbsolutePath()));

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {

							XMLReader.XMLToParse = openedFile;

							try {
								currentXML.delete();
								XMLReader.XMLToParse = openedFile;
								XMLReader.ParseEntireFile(XMLReader.usingBufferedReader(openedFile.getAbsolutePath()));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						propertiesPath = openedFile.getPath().replace(fd.getSelectedFile().getName(), "");
						if (propertiesPath.contains("game\\properties\\")) {

							worldofgoopath = propertiesPath.replace("game\\properties\\", "");
							newWOG = true;

						} else {

							worldofgoopath = propertiesPath.replace("properties\\", "");
							newWOG = false;

						}

						fxFile = new File(openedFile.getAbsolutePath());

						tltmNew.setEnabled(true);
						tltmOpen.setEnabled(true);
						mntmNewItem.setEnabled(true);
						mntmOpen.setEnabled(true);
						mntmSave.setEnabled(true);

						ConfigReader.writeFxDir();

						System.out.println(openedFile);
						System.out.println(propertiesPath);
						System.out.println(worldofgoopath);
						System.out.println(newWOG);
						System.out.println("fxFile: " + fxFile);

					}
				}

			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		}

		mntmChangeFxDirectory.addSelectionListener(new Open());

		// Check the Config

		File config = new File(System.getProperty("user.home") + "\\WOGPE\\config.txt");

		InputStream internalConfig = null;

		if (config.exists() == false) {
			System.out.println("Config file does not exist creating new config!");
			try {

				internalConfig = Main.class.getClass()
						.getResourceAsStream("/me/WatchingYoutube/ParticleEditor/doc/config.txt");

				Files.copy(internalConfig, config.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (config.exists() == true) {
			ConfigReader.readAndApplyConfig();
			shlWorldOfGoo.update();
			shlWorldOfGoo.layout(true);
		}

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		display.timerExec(TIMER_INTERVAL, runnable);
		
		runnable.run();
		

		while (!shlWorldOfGoo.isDisposed()) {
			// trclmnIdOrName.setWidth(group.getSize().x);
			// tblclmnNewColumn_1.setWidth(group.getSize().x);
			// tblclmnNewColumn.setWidth(group.getSize().x);

			shlWorldOfGoo.update();
			shlWorldOfGoo.layout(true);
			shlWorldOfGoo.setText(windowtitle);

			isRunning = true;

			if (currentEffectOpen == false) {

				tltmSave.setEnabled(false);
				tltmClone.setEnabled(false);
				tltmOpenImage.setEnabled(false);
				tltmPlayWOG.setEnabled(false);
				mntmNewItem.setEnabled(false);
				mntmSave.setEnabled(false);
				mntmOpen.setEnabled(false);

			} else if (currentEffectOpen == true) {

				tltmSave.setEnabled(true);
				tltmClone.setEnabled(true);
				tltmOpenImage.setEnabled(true);
				tltmPlayWOG.setEnabled(true);
				mntmNewItem.setEnabled(true);
				mntmSave.setEnabled(true);
				mntmOpen.setEnabled(true);
			}

			if (Main.name != null) {

				if (type.equals("particleeffect")) {
					trtmParticleEffect.setText(0, "Particle Effect");

				} else if (type.equals("ambientparticleeffect")) {
					trtmParticleEffect.setText(0, "Ambient Particle Effect");
				}

				trtmParticleEffect.setText(1, Main.name);

			}

			if (type.equals("ambientparticleeffect")) {
				trtmParticleEffect.setImage(SWTResourceManager.getImage(Main.class,
						"/me/WatchingYoutube/ParticleEditor/assets/Status-weather-showers-icon.png"));
			} else if (type.equals("particleeffect")) {
				trtmParticleEffect.setImage(SWTResourceManager.getImage(Main.class,
						"/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));
			}

			if (!display.readAndDispatch()) {
				display.sleep();
			}
			/*
			for (int i = 0; i < tree.getItems().length; i++) {
				if (tree.getItem(i).getData("error") != null) {
					if (tree.getItem(i).getData("erroricon") == null) {
						tree.getItem(i).setData("erroricon", false);
					}
					if (tree.getItem(i).getData("error").equals(true) && tree.getItem(i).getData("erroricon").equals(false)) {
						System.out.println("Item: " + tree.getItem(i) + " has error!");
						
						//Overlays the current image and sets it
						
						ImageData treeImageData = tree.getItem(i).getImage().getImageData();
						
						java.awt.Image treeImage;
						try {
							treeImage = ImageIO.read(new ByteArrayInputStream(treeImageData.data));
							BufferedImage error = ImageIO.read(Main.class.getClass().getResource("/me/WatchingYoutube/ParticleEditor/assets/delete-icon.png"));
							BufferedImage newImage = new BufferedImage(48,48, BufferedImage.TYPE_INT_ARGB);
						
						Graphics g = newImage.getGraphics();
						
						g.drawImage(treeImage, 0, 0, null);
						g.drawImage(error, 0, 0, null);
						
						g.dispose();
						
						ImageData newIcon = ImageResource.convertToSWT(newImage);
						
						tree.getItem(i).setImage(new Image(display,newIcon));
						
						tree.getItem(i).setData("erroricon", true);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					}
				}
			}
	*/
		}

		display.timerExec(-1, runnable);
		display.dispose();

		if (shlWorldOfGoo.isDisposed()) {
			isRunning = false;
			System.out.println("Disposed");

			// Dispose all open windows

			if (About.shlAbout.isDisposed() == false) {
				About.shlAbout.dispose();
				About.shlAbout.getDisplay().dispose();
			}
			Display.getDefault().dispose();
			display.dispose();

		}

	}

	public static void createNewParticle() {
		TreeItem trtmParticleImage = new TreeItem(trtmParticleEffect, SWT.NONE);
		trtmParticleImage.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));
		trtmParticleImage.setText(0, "particle");
		trtmParticleImage.setText(1, Integer.toString(numberOfParticles - 1));
		trtmParticleEffect.setExpanded(true);
		trtmParticleImage.setData("particle");
		trtmParticleImage.setData("id", numberOfParticles - 1);
		trtmParticleImage.setData("error", true);

		additive.add(false);
		dampening.add(null);
		directed.add(false);
		fade.add(false);
		singlelifespan.add(null);
		singlescale.add(null);
		finalscale.add(null);
		singlespeed.add(null);
		singleacceleration.add(null);
		movedir.add(null);
		movedirvar.add(null);
		singlerotation.add(null);
		singlerotspeed.add(null);

		imageIndex.add(null);

	}
	
	public static void deleteParticle(int i) {
		
		if (trtmParticleEffect.getItem(i) != null) {
		
		System.out.println("Attempting to delete particle: " + trtmParticleEffect.getItem(i));
		
		additive.remove(i);
		dampening.remove(i);
		directed.remove(i);
		fade.remove(i);
		singlelifespan.remove(i);
		singlescale.remove(i);
		finalscale.remove(i);
		singlespeed.remove(i);
		singleacceleration.remove(i);
		movedir.remove(i);
		movedirvar.remove(i);
		singlerotation.remove(i);
		singlerotspeed.remove(i);

		imageIndex.remove(i);
		
		if(trtmParticleEffect.getItem(i).getItems().length > 0) {
			for(int axial = 0; axial < trtmParticleEffect.getItem(i).getItems().length; axial++) {
				trtmParticleEffect.getItem(i).getItem(axial).dispose();
			}
		}
		
		trtmParticleEffect.getItem(i).dispose();
		
		System.out.println("Values successfully removed, removing and reordering in tree");
		
		for(int items = i; items < trtmParticleEffect.getItems().length; items++) {
			
			if (trtmParticleEffect.getItem(items).getData("id") != null) {
			
			System.out.println(trtmParticleEffect.getItem(items).getData("id"));
			
			trtmParticleEffect.getItem(items).setData("id",items - 1);
			
			
			}
		}

		
		numberOfParticles--;
		
		}
		
		
	}

	public static void createNewAxialSinOffset(int index, TreeItem selectedTreeItemItem2) {

		TreeItem trtmAxialSinOffset = new TreeItem(selectedTreeItemItem2, SWT.NONE);
		trtmAxialSinOffset.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/balloon-icon.png"));
		trtmAxialSinOffset.setText(0, "axialsinoffset");
		trtmAxialSinOffset.setText(1, Integer.toString(numberOfAxialSinOffset.get(index)));
		selectedTreeItemItem2.setExpanded(true);
		trtmAxialSinOffset.setData("axialsinoffset");
		trtmAxialSinOffset.setData("aid", numberOfAxialSinOffset.get(index));
		trtmAxialSinOffset.setData("error", true);

		numberOfAxialSinOffset.set(index, numberOfAxialSinOffset.get(index) + 1);

		singleamp.add(null);
		axis.add(null);
		freq.add(null);
		singlefreq.add(null);
		phaseshift.add(null);
		singlephaseshift.add(null);

	}

	public static void prepareImagesToCanvas() throws Exception {

		renderedImages.clear();

		for (int i = 0; i < path.size(); i++) {

			if (newWOG == false) {
				System.out.println(path + " " + pathIndex);

				File encodedResources = new File(propertiesPath + "resources.xml.bin");

				byte[] decodedResourceBytes = AESBin.decryptFile(encodedResources);

				XMLReader.writeBytesToFile(currentResources, decodedResourceBytes);

				renderedImages.add(new File(
						worldofgoopath + ImageResource.getPathFromResource(currentResources, image.get(i)) + ".png\\")
								.getAbsoluteFile());
			}
		}

		System.out.println(image);
		System.out.println(path);

		//drawImagesToCanvas();

	}

	static boolean paintListenerSet = false;

	public static void drawImagesToCanvas() {

		if (paintListenerSet == false) {

			canvas.addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent event) {
					// TODO Auto-generated method stub

					InputStream is = null;

					ByteArrayInputStream os = null;

					ArrayList<String> imageText = new ArrayList<String>(image);

					for (int i = 0; i < renderedImages.size(); i++) {

						System.out.println(renderedImages);

						if (renderedImages != null && renderedImages.isEmpty() == false) {
							if (renderedImages.get(i).getAbsoluteFile().exists() == true) {

								try {
									is = new FileInputStream(renderedImages.get(i).getAbsoluteFile());
									System.out.println("is created: " + renderedImages.get(i));
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								os = ImageResource.pngConvert(is);

								cvImage = new Image(display, os);

								// int cvw = Math.round(cvImage.getImageData().width * scale.get(0));

								// int cvh = Math.round(cvImage.getImageData().width * scale.get(0));

								cvw = Math.round(cvImage.getImageData().width / 2);

								cvh = Math.round(cvImage.getImageData().height / 2);

								Image scaledImage = new Image(display,
										cvImage.getImageData().scaledTo(cvw + 1, cvh + 1));

								event.gc.drawImage(scaledImage, (i * (canvas.getSize().x / renderedImages.size())),
										canvas.getSize().y / 2);
								if(imageText.isEmpty() == false) {
								if (i % 2 == 0) {

									event.gc.drawText(imageText.get(i),
											i * (canvas.getSize().x / renderedImages.size()), canvas.getSize().y / 4);

								} else {
									event.gc.drawText(imageText.get(i),
											i * (canvas.getSize().x / renderedImages.size()), canvas.getSize().y / 6);
								}
								}
								// Cleanup

								try {
									is.close();
									os.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								scaledImage.dispose();

								cvImage.dispose();

								System.out.println("Created Image");

							}
						}
					}
				}
			});

			paintListenerSet = true;

		}
	}

	public static void cleanUpTree() {

		if (tree.getItems() != null) {

			for (int itemsToDispose = 0; itemsToDispose < tree.getItems().length; itemsToDispose++) {

				System.out.println(table.getItems().length);
				// if (tree.getItem(itemsToDispose) != trtmParticleEffect) {

				if (tree.getItem(itemsToDispose).getItems().length > 0) {
					for (int itemsToDispose2 = 0; itemsToDispose2 < tree.getItem(itemsToDispose)
							.getItems().length; itemsToDispose2++) {
						tree.getItem(itemsToDispose).getItem(itemsToDispose2).dispose();
					}
				}

				tree.getItem(itemsToDispose).dispose();
			}
			// }

			trtmParticleEffect = new TreeItem(tree, SWT.NONE);

		}

		if (tree2.getItems() != null) {

			for (int itemsToDispose = 0; itemsToDispose < tree2.getItems().length; itemsToDispose++) {

				System.out.println(table.getItems().length);
				// if (tree.getItem(itemsToDispose) != trtmParticleEffect) {

				tree2.getItem(itemsToDispose).dispose();
			}
			// }

		}

	}

	public static void createNewImageElement(int i2) {

		if (trtmResources == null) {
			trtmResources = new TreeItem(tree2, SWT.NONE);
			trtmResources.setImage(SWTResourceManager.getImage(Main.class,
					"/me/WatchingYoutube/ParticleEditor/assets/Image-icon.png"));
			trtmResources.setText("Resources");
		}

		trtmImage = new TreeItem(trtmResources, SWT.NONE);
		trtmImage.setImage(
				SWTResourceManager.getImage(Main.class, "/me/WatchingYoutube/ParticleEditor/assets/Image-icon.png"));
		trtmImage.setText(0, image.get(i2));
		trtmImage.setText(1, Integer.toString(i2));
		trtmImage.setData("Image");
		trtmImage.setData("id", i2);
	}

	public static void createNewElements() {
		if (createNewElements == true) {

			for (int i = 0; i < numberOfParticles; i++) {
				TreeItem trtmParticleImage = new TreeItem(trtmParticleEffect, SWT.NONE);
				trtmParticleImage.setImage(SWTResourceManager.getImage(Main.class,
						"/me/WatchingYoutube/ParticleEditor/assets/fire-icon.png"));
				trtmParticleImage.setText(0, "particle");
				trtmParticleImage.setText(1, Integer.toString(i));
				trtmParticleEffect.setExpanded(true);
				trtmParticleImage.setData("particle");
				trtmParticleImage.setData("id", i);

				if (numberOfAxialSinOffset.isEmpty() == false) {
					if (numberOfAxialSinOffset.size() > 0) {
						for (int i2 = 0; i2 < numberOfAxialSinOffset.get(i); i2++) {
							if (numberOfAxialSinOffset.get(i) != null) {

								TreeItem trtmAxialSinOffset = new TreeItem(trtmParticleImage, SWT.NONE);
								trtmAxialSinOffset.setImage(SWTResourceManager.getImage(Main.class,
										"/me/WatchingYoutube/ParticleEditor/assets/balloon-icon.png"));
								trtmAxialSinOffset.setText(0, "axialsinoffset");
								trtmAxialSinOffset.setText(1, Integer.toString(i2));
								trtmParticleImage.setExpanded(true);
								trtmAxialSinOffset.setData("axialsinoffset");
								trtmAxialSinOffset.setData("aid", i2);

							}
						}
					}
				}
				shlWorldOfGoo.redraw();
				createNewElements = false;
			}

			trtmResources = new TreeItem(tree2, SWT.NONE);
			trtmResources.setImage(SWTResourceManager.getImage(Main.class,
					"/me/WatchingYoutube/ParticleEditor/assets/Image-icon.png"));
			trtmResources.setText("Resources");

			for (int i2 = 0; i2 < image.size(); i2++) {
				trtmImage = new TreeItem(trtmResources, SWT.NONE);
				trtmImage.setImage(SWTResourceManager.getImage(Main.class,
						"/me/WatchingYoutube/ParticleEditor/assets/Image-icon.png"));
				trtmImage.setText(0, image.get(i2));
				trtmImage.setText(1, Integer.toString(i2));
				trtmImage.setData("Image");
				trtmImage.setData("id", i2);
			}
		}
	}

	public static void playSuccessAudio() {
		InputStream audio = Main.class.getClass()
				.getResourceAsStream("/me/WatchingYoutube/ParticleEditor/assets/save_success.wav");

		InputStream bufferedIn = new BufferedInputStream(audio);

		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(bufferedIn);

			Clip clip = AudioSystem.getClip();

			clip.open(as);

			clip.start();

		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Thread t = new Thread() {
			public void run() {
				new Main().runWOGPE();
			}
		};

		t.start();
	}
}