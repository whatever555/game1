package com.games.mygame;

import processing.core.PImage;
import android.R.color;

public class Object {

	public float x, y, w, h, z, spx, spy;
	public color fcolor, bcolor;
	public String btextureLoc, ftextureLoc;
	public String shape = "circ";
	public Game parent;
	public PImage btexture, ftexture;
	public float rotSpeed = 0, rotAmount = 0;
	public float gravity = 0;

	public Object(Game parent, float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.parent = parent;
		int r = (int)parent.random(14)+2;
		this.w=r;
		this.h=r;	
	}
	
	public void moveMe(){
			
		gravity+=(gravity+0.02)/5;
		this.y+=gravity;
		setInBounds();
	}

	public void setInBounds(){
		
	}
	public void showMe() {
		int r = (int)parent.random(50);
		int r2 = (int)parent.random(255);
		moveMe();
		parent.fill(r,r2);
		
		if(shape.equals("circ")){
			parent.ellipse(x,y,w,h);
		}else{
			parent.rect(x,y,w,h,2);
		}
		
		
	}

	public void setTexture(String frontOrBack, String textureLoc) {
		if (frontOrBack.equals("b")) {
			this.btextureLoc = textureLoc;
			this.btexture = parent.loadImage(btextureLoc);
		} else {
			this.ftextureLoc = textureLoc;
			this.btexture = parent.loadImage(ftextureLoc);
		}

	}
	
	
	

}
