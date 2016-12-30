package com.kingam.cbits;
/*
*Ambuj Mishra
* Created:	24 August 2016
* Version:	1.0	(24 August 2016)
* Final Version: 30 Dec 2016
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;


public class StageScreen implements Screen{
	CbitsGame game;
	float x,y,w,h,pw,gap,w1,h1,pw1,y1,x1,y2,gap1,tsp,tbt;
	String line;		//line to be printed
	

	public StageScreen(CbitsGame gg) {
		game = gg;
		// Calculating characters and string positions on screen here, starting with STANDARD at middle top of screen
        w =game.w*0.035f;			//width of each character is being decided here
		h =game.h*0.028f;         //height of each character is being decided here
		pw=(float)Math.sqrt(w+h)*(w/h)*0.7f;
		gap=pw*(h/w);
		x=game.w-6*w-6*gap;	    //x coordinate of colors
		y=gap;
		
		w1=(game.w-2*gap)/5;
		h1=game.h-2*h-5*gap-4*pw;
		y1=game.h+2*h-5*gap-4*pw;
		x1=game.w*0.5f+gap+4*w;
		y2=y1/3+2*(2*gap+pw)-h1/6-8*gap;
	}

	@Override
	public void show() {
		
		//calculating total
		tbt=0;
		for(int i=1;i<=32;)
		{
			tbt=tbt+game.pref.getFloat(i+"best");
			i=i*2;
		}
		Gdx.graphics.setContinuousRendering(false);
		if(game.aoi.isNetConnected()) 
		{game.aoi.showBannerAd();}
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(game.CC.bgColor[0],game.CC.bgColor[1],game.CC.bgColor[2],game.CC.bgColor[3]);		// Back ground color    
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.camera.update();
		//30122016 update final update
		game.CD.stringDrawing(game.sr,gap,game.h-4*h-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"TOTAL SCORE:"+ tbt,game.CC.getCharColor());
		game.CD.stringDrawing(game.sr,game.w*0.5f-5*(w+gap),game.h-h-gap,w,h,pw,gap,"CATEGORIES",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,gap+2*pw+2*w,2*gap+2*pw,w,h,pw,gap,"LEADERS",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w-6*(h+gap)-2*pw,2*gap+2*pw,w,h,pw,gap,"SUBMIT",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,0,gap,game.w,2*pw,0,0,"_",game.CC.bitColor);		//hor 1
				
				game.CD.stringDrawing(game.sr,0,h+3*gap+2*pw,game.w,2*pw,0,0,"_",game.CC.bitColor); //ho2

				game.CD.stringDrawing(game.sr,0,game.h-3*gap-pw-4*h,game.w,2*pw,0,0,"_",game.CC.bitColor);  //top most hor
				game.CD.stringDrawing(game.sr,game.w*0.5f-pw,gap+2*pw,2*pw,game.h-4*h-3*gap-4*pw,0,0,"_",game.CC.bitColor); //middle ver
				
				game.CD.stringDrawing(game.sr,0,gap+2*pw,2*pw,game.h-4*h-3*gap-4*pw,0,0,"_",game.CC.bitColor);		//left ver
				game.CD.stringDrawing(game.sr,game.w-2*pw,gap+2*pw,2*pw,game.h-4*h-3*gap-4*pw,0,0,"_",game.CC.bitColor); //right ver
				
				game.CD.stringDrawing(game.sr,gap,y1/3+2*(2*gap+pw)-2*h,game.w-2*gap,2*pw,0,0,"_",game.CC.bitColor);//hor 3
				game.CD.stringDrawing(game.sr,gap,2*y1/3+2*(2*gap+pw-h)-2*h-gap,game.w-2*gap,2*pw,0,0,"_",game.CC.bitColor);//hor4
				
				game.CD.stringDrawing(game.sr,2*gap,y2-h-gap,w1,h1/6,6*pw,gap,"16",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,x1-4*w+3*gap,y2-h-gap,w1,h1/6,6*pw,gap*1.5f,"32",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,2*gap+pw,2*(h+3*gap+pw)-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("16maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,2*gap+pw,2*(h+3*gap+pw)-h-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("16best"),game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,2*(h+3*gap+pw)-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("32maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,2*(h+3*gap+pw)-h-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("32best"),game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,gap+4*w,y2+h1/3-2*h-gap,w1,h1/6,6*pw,0,"4",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,x1,y2+h1/3-2*h-gap,w1,h1/6,6*pw,0,"8",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,2*gap+pw,y1/3+2*(3*gap+pw)-h,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("4maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,2*gap+pw,y1/3+2*(3*gap+pw)-2*h,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("4best"),game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,y1/3+2*(3*gap+pw)-h,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("8maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,y1/3+2*(3*gap+pw)-2*h,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("8best"),game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,gap+4*w,y2+2*h1/3-3*h-gap,w1,h1/6,6*pw,0,"1",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,x1,y2+2*h1/3-3*h-gap,w1,h1/6,6*pw,0,"2",game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,2*gap+pw,2*y1/3+2*(3*gap+pw-h)-gap-h,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("1maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,2*gap+pw,2*y1/3+2*(3*gap+pw-h)-2*h-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("1best"),game.CC.getCharColor());
				
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,2*y1/3+2*(3*gap+pw-h)-gap-h,w*0.5f,h*0.5f,pw*0.5f,gap,"SPEED:"+(int)game.pref.getFloat("2maxsp")/200+"X",game.CC.getCharColor());
				game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,2*y1/3+2*(3*gap+pw-h)-2*h-gap,w*0.5f,h*0.5f,pw*0.5f,gap,"BEST:"+game.pref.getFloat("2best"),game.CC.getCharColor());
				
		if (Gdx.input.justTouched()) 
		{
		if(getStage(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.setScreen(game.BCS);
		}
		
		else if(getLead(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.aoi.showScore();
			//System.out.println("LeaderBoard");
		}
		else if(getSub(Gdx.input.getX(), Gdx.input.getY()))
		{
			game.aoi.submitScore(tbt, 0);
			//System.out.println("Submit All");
		}
		}
		else if (Gdx.input.isKeyPressed(Keys.BACK ) || Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!game.backpressed)
			{
			game.backpressed=true;
			game.setScreen(game.MOD);
			}
		}
		else if(game.backpressed)
			game.backpressed=false;
	}
	private boolean getSub(int x, int y)
	{
		 // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;
		
		if(y>gap && y<h+3*gap+2*pw && x>game.w*0.5f+pw)
			return true;
		
		return false;
	}
	private boolean getLead(int x, int y)
	{
		 // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;
		
		if(y>gap && y<h+3*gap+2*pw && x<game.w*0.5f-pw)
			return true;
		
		return false;
	}
	
	//14 Sep 2016 new update
	private boolean getStage(int x, int y) {		
        // To convert camera's y axis which starts from top corner to screens y axis which starts from bottom
		y=game.h-y;	
		//if(y>h+3*gap+4*pw && y<y1/3+2*(2*gap+pw)-h-gap)
			if(y>h+3*gap+4*pw && y<y1/3+2*(2*gap+pw)-2*h)
		{
			if(x<game.w*0.5f)
			{
				game.st=16;
				game.setMaxsp(1600);
				return true;
			}
			else
			{
				game.st=32;	
				game.setMaxsp(1600);
				return true;
			}
		}
		//else if (y>y1/3+2*(2*gap+2*pw)-h-gap && y<2*y1/3+2*(2*gap+pw-h)-h-gap)
			else if (y>y1/3+2*(2*gap+2*pw)-2*h && y<2*y1/3+2*(2*gap+pw-h)-2*h-gap)
		{
			if(x<game.w*0.5f)
			{
				game.st=4;
				game.setMaxsp(2400);
				return true;
			}
			else
			{
				game.st=8;	
				game.setMaxsp(2400);
				return true;
			}
		}
		//else if (y>2*y1/3+2*(2*gap+2*pw-h)-h-gap && y<game.h-2*gap-pw-2*h-h-gap)
			else if (y>2*y1/3+2*(2*gap+2*pw-h)-2*h-gap && y<game.h-2*gap-pw-3*h-h-gap)
		{
			if(x<game.w*0.5f)
			{
				game.st=1;
				game.setMaxsp(3200);
				return true;
			}
			else
			{
				game.st=2;	
				game.setMaxsp(3200);
				return true;
			}
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
		//game.pref.putBoolean("tip", false);
		Gdx.input.setInputProcessor(null);
		game.dispose();
		game.aoi.hideBannerAd();
		//13122015  removed 15012016
		//game.STS.dispose();
		
	}

	@Override
	public void dispose() {

	}

}
