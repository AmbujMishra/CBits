package com.kingam.cbits;
/*
*Ambuj Mishra
* Created:	20 September 2016
* Version:	1.0	(24 September 2016)
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;


public class ModeScreen implements Screen{
	CbitsGame game;
	float x,y,w,h,pw,gap,w1,h1,pw1,y1,x1,y2,gap1;
	String line;		//line to be printed
	

	public ModeScreen(CbitsGame gg) {
		game = gg;
		// Calculating characters and string positions on screen here, starting with STANDARD at middle top of screen
        w =game.w*0.035f;			//width of each character is being decided here
		h =game.h*0.028f;         //height of each character is being decided here
		pw=(float)Math.sqrt(w+h)*(w/h)*0.7f;
		gap=pw*(h/w);
		x=game.w*0.5f-3*w;
		y=game.h-6*h;
		
	}

	@Override
	public void show() {
		Gdx.graphics.setContinuousRendering(false);
		if(game.aoi.isNetConnected()) 
		{game.aoi.showBannerAd();}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(game.CC.bgColor[0],game.CC.bgColor[1],game.CC.bgColor[2],game.CC.bgColor[3]);		// Back ground color    
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.camera.update();

				game.CD.stringDrawing(game.sr,game.w*0.5f-6*(w+gap),game.h-h-gap,w,h,pw,gap,"SELECT MODE",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,gap,gap,6*w-gap,h+2*gap,pw,gap,"_",game.CC.bitColor);
				game.CD.stringDrawing(game.sr,2*gap,2*gap,w,h,pw,gap,"RATE",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w-6*w-8*gap,gap,8*w+gap-pw,h+2*gap,pw,gap,"_",game.CC.bitColor);
				game.CD.stringDrawing(game.sr,game.w-6*w-7*gap,2*gap,w,h,pw,gap,"COLORS",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,gap,6*gap+h,game.w-2*gap,h+2*gap,pw,gap,"_",game.CC.bitColor);
				game.CD.stringDrawing(game.sr,game.w*0.5f-6*w-5*gap,7*gap+h,w,h,pw,gap,"LEADERBOARD",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,gap,y,game.w-2*gap,h+2*gap,0,0,"_",game.CC.bitColor); //single player strip
				game.CD.stringDrawing(game.sr,game.w*0.5f-7*w-5*gap,y+gap,w,h,pw,gap,"SINGLE PLAYER",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,x,y-7*h,6*w,6*h,6*0,0,"triE",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,gap,y-9*h-gap,game.w-2*gap,h+2*gap,0,0,"_",game.CC.bitColor); //multi player strip
				game.CD.stringDrawing(game.sr,game.w*0.5f-6*w-7*gap,y-9*h,w,h,pw,gap,"MULTI PLAYER",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,x,y-16*h,6*w,6*h,6*pw,gap,"2",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,3*w,y-25*h,6*w,6*h,6*pw,gap,"3",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w-9*w,y-25*h,6*w,6*h,6*pw,gap,"4",game.CC.getCharColor());

		if (Gdx.input.justTouched()) 
		{
		if(getStage(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.setScreen(game.STS);
		}
		else if(getMulti(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.newMul();
			game.st=32;		//corrected for final release on 29 12 2016
			game.setMaxsp(2400); //corrected for final release on 29 12 2016
			game.strt=true;
			game.setScreen(game.BCM);
		}
		else if(getAbt(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.aoi.rate();
			game.setScreen(game.ABT);
		}	
		else if(getCol(Gdx.input.getX(), Gdx.input.getY()))
		{
			System.out.println("colors");
			game.setScreen(game.COL);
		}
		else if(getLead(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.aoi.showScore();
			//System.out.println("LeaderBoard");
		}
		}
		else if (Gdx.input.isKeyPressed(Keys.BACK)|| Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!game.backpressed)
			{
			game.backpressed=true;
			game.setScreen(game.MMS);
			}
		}
		else if(game.backpressed)
			game.backpressed=false;

	}

	private boolean getMulti(int x, int y) {
		// To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
				y=game.h-y;
				if(x>this.x && x< this.x+6*w && y>this.y-16*h && y<this.y-10*h)
					{
					game.mul=2;
					return true;	
					}
				else if(x>3*w && x< 9*w && y>this.y-25*h && y<this.y-19*h)
				{
					game.mul=3;
					return true;	
					}
				else if(x>game.w-9*w && x< game.w-3*w && y>this.y-25*h && y<this.y-19*h)
				{
					game.mul=4;
					return true;	
					}
				
				return false;
	}

	private boolean getLead(int x, int y)
	{
		 // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;
		
		if(y>6*gap+h && y<2*h+8*gap)
			return true;
		
		return false;
	}
	private boolean getAbt(int x, int y)
	{
		 // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;
		
		if(y<3*gap+h && x<5*(gap+w))
			return true;
		
		return false;
	}
	private boolean getCol(int x, int y)
	{
		 // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;
		
		if(y<3*gap+h && x>game.w-8*gap-6*w)
			return true;
		
		return false;
	}

	private boolean getStage(int x, int y) {		
        // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;	
		if(y>this.y-7*h && y<this.y-h)
		{
			return true;
		}
		return false;		
	}

	@Override
	public void resize(int width, int height) {
		//this.render(0.03f);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		game.dispose();
		game.aoi.hideBannerAd();	
	}

	@Override
	public void dispose() {

	}

}

