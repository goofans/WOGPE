package me.WatchingYoutube.ParticleEditor;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

public class FXGraphicsView {
	
	private static Canvas canvas = Main.canvas;
	
	private static Color color;
	
	private static int directionX = 1;
	
	private static int directionY = 1;
	
	private static int x;
	
	private static Random rand = new Random();
	
	private static int y;
	
	private final static int FPS = 10;
	
	private static Display display = Display.getDefault();
	
	public static void startAnimation() {
		
		setupContent();
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				
				animate();

				color = new Color(display,Main.CanvasColor,Main.CanvasColor,Main.CanvasColor);
				
				display.timerExec(FPS, this);
				
			}
			
		};
		
		
		display.timerExec(FPS, runnable);
		
		while (!Main.shlWorldOfGoo.isDisposed()) {
		      if (!display.readAndDispatch()) {
		        display.sleep();
		      } 

		
	}

		
	display.timerExec(-1, runnable);
		
	display.dispose();
	}
	public static void setupContent() {
		
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				
				GC gc = e.gc;
				
				color = new Color(display,Main.CanvasColor,Main.CanvasColor,Main.CanvasColor);
				canvas.setBackground(color);
				
				ArrayList<File> resources = new ArrayList<File>();
				
				InputStream is = null;
				
				ByteArrayInputStream os = null;
				
				ArrayList<Image> images = new ArrayList<Image>(); 
				
				
				
				for (int i = 0; i < Main.numberOfParticles; i++) {
					if (Main.imageIndex != null && Main.imageIndex.isEmpty() == false) {
						
						ArrayList<String> imageList = new ArrayList<String>(Arrays.asList(Main.imageIndex.get(i).replace(" ", "").split(",")));
						
						for (int i2 = 0; i2 < imageList.size(); i2++) {
							try {
								resources.add(new File(
										Main.worldofgoopath + ImageResource.getPathFromResource(Main.currentResources, imageList.get(i2)) + ".png\\")
												.getAbsoluteFile());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(resources.get(i).getAbsoluteFile().exists() == true) {
							try {
								is = new FileInputStream(resources.get(i).getAbsoluteFile());
								System.out.println("is created: " + resources.get(i));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							os = ImageResource.pngConvert(is);

							images.add(new Image(display, os));
							
							
							try {
								is.close();
								os.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							for(int i3 = 0; i3 < Main.maxparticles; i3++) {
								System.out.println(images);
								gc.drawImage(images.get(rand.nextInt(images.size())), x, y);
							}
							
						}
					}
				}
				
				//Cleanup
				for (int dispose = 0; dispose < images.size(); dispose++) {
					images.get(dispose).dispose();
				}
				
			}
			
		});
		
	}
	
	public static void animate() {
		
		 	x += directionX;
		    y += directionY;

		    // Determine out of bounds
		    Rectangle rect = canvas.getClientArea();
		    if (x < 0) {
		      x = 0;
		      directionX = 1;
		    } else if (x > rect.width) {
		      x = rect.width;
		      directionX = -1;
		    }
		    if (y < 0) {
		      y = 0;
		      directionY = 1;
		    } else if (y > rect.height) {
		      y = rect.height;
		      directionY = -1;
		    }
		
		canvas.redraw();
	}
}
