package com.games.mygame;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import android.view.MotionEvent;

public class Game extends PApplet {
	PImage bgImage;
	PImage winImage;
	
	ArrayList<Object> objects;
	float gravity = 2;
	
	int x = 0;
	int y = 0;
	int spx = 1;
	int spy = 1;

float offy;
float offx;
	
	public Game(){
	}
	int TouchEvents;
	float xTouch[];
	float yTouch[];
	int currentPointerId = 0;
	boolean printFPS;


	//-----------------------------------------------------------------------------------------

	public void setup() {
		 offy = displayHeight/10;
		 offx = displayWidth/80; 
		winImage=loadImage("win.jpg");
	bgImage = loadImage("https://www.lottery.ie/Lottery/media/LotteryMediaLibrary/Games/GameInfo/Scratchcards/allcashgold_dec14.gif");
	  objects = new ArrayList<Object>();
	  size(displayWidth, displayHeight);
	  orientation(LANDSCAPE);
	  fill(0, 0, 244);
	  rect(100, 100, 100, 100);
	  stroke(255);
	  // Initialize Multitouch x y arrays
	  xTouch = new float [10];
	  yTouch = new float [10]; // Don't use more than ten fingers!
   noStroke();
	}

	//-----------------------------------------------------------------------------------------

	public void draw() {
image(winImage,((displayWidth/2)+offx/2),offy/2,displayWidth/2-(offx*2),displayHeight-(offy*2));
image(bgImage,0,0,displayWidth,displayHeight);
	
	
	  for(int i = 0; i<objects.size(); i++){
		  objects.get(i).showMe();
	  }
	

	}

	
	public void scratch(float xx, float yy){
float ypos = offy/2;
float yheight = ypos+displayHeight-(offy*2);

		float xpos = (displayWidth/2)+(offx/2);
		float xwidth = displayWidth/2-(offx*2);
		if(xx>xpos && xx < xpos + xwidth)
		{
			if(yy>ypos && yy < ypos + yheight)
			{
		xx=(xx/displayWidth)*bgImage.width;
		yy=(yy/displayHeight)*bgImage.height;
		
		bgImage.loadPixels();

		int scratchSize = 7;
		for(int xx2 = (int)(xx-scratchSize); xx2<xx+scratchSize;xx2++)
			for(int yy2 = (int)(yy-scratchSize); yy2<yy+scratchSize;yy2++){
				if(xx2>=0 && yy2>=0 && xx2<bgImage.width && yy2<bgImage.height){
		int loc = (int)((xx2*yy2)+xx2);

		bgImage.set((int)xx2,(int)yy2,color(0,0));
				}
			}
		bgImage.updatePixels();
			}
		}
	}
	//-----------------------------------------------------------------------------------------

	public boolean surfaceTouchEvent(MotionEvent event) {

	  // Number of places on the screen being touched:
	  TouchEvents = event.getPointerCount();

	  // If no action is happening, listen for new events else 
	  for (int i = 0; i < TouchEvents; i++) {
	    int pointerId = event.getPointerId(i);
	    xTouch[pointerId] = event.getX(i); 
	    yTouch[pointerId] = event.getY(i);
	    scratch(event.getX(i),event.getY(i));
	    objects.add(new Object(this, event.getX(i), event.getY(i), 12, 12));
	    
	    float siz = event.getSize(i);
	  }

	  // ACTION_DOWN 
	  if (event.getActionMasked() == 0 ) {
	    print("Initial action detected. (ACTION_DOWN)");
	    print("Action index: " +str(event.getActionIndex()));
	  } 
	  // ACTION_UP 
	  else if (event.getActionMasked() == 1) {
	    print("ACTION_UP");
	    print("Action index: " +str(event.getActionIndex()));
	  }
	  //  ACTION_POINTER_DOWN 
	  else if (event.getActionMasked() == 5) {
	    print("Secondary pointer detected: ACTION_POINTER_DOWN");
	    print("Action index: " +str(event.getActionIndex()));
	  }
	  // ACTION_POINTER_UP 
	  else if (event.getActionMasked() == 6) {
	    print("ACTION_POINTER_UP");
	    print("Action index: " +str(event.getActionIndex()));
	  }
	  // 
	  else if (event.getActionMasked() == 4) {

	  }

	  // If you want the variables for motionX/motionY, mouseX/mouseY etc.
	  // to work properly, you'll need to call super.surfaceTouchEvent().
	  return super.surfaceTouchEvent(event);
	}
		
}
