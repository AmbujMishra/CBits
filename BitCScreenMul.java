package com.kingam.cbits;
/*
*Ambuj Mishra
* Created:	25 September 2016
* Version:	1.0	(25 September 2016)
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.kingam.cbits.CbitsGame.Gravity;

public class BitCScreenMul implements Screen {

	CbitsGame game;
	float x[],y[],x1[],y1[],w,h,w1,h1,pw,gap,x3,y3;
	double a[];
	float speed,hold,time,bt,sp, tshow;
	boolean caught[],go,scr,scrt,/*nb,*/tou,banner;
	int t[]/*,pscr[]*/;

	
	public BitCScreenMul(CbitsGame gg) {
		game = gg;
		// w1=game.w*0.035f;			
		// h1=game.h*0.028f;     
		w1=game.w*0.030f;			
		h1=game.h*0.024f;
		pw=(float)Math.sqrt(w1+h1)*(w1/h1)*0.7f;
		gap=pw*(h1/w1);
		//x3=game.w*0.5f-5*(w1+gap);
	}

	@Override
	public void show() {
		game.aoi.hideBannerAd();
		
		w=game.w-game.BD.bitlen-2*game.BD.bitgap;
		h=game.h-game.BD.bitlen-2*game.BD.bitgap;
		Gdx.graphics.setContinuousRendering(true);
		//game.st=32;
		x=new float[game.st];
		y= new float[game.st];
		x1=new float[game.st];
		y1= new float[game.st];
		a=new double[game.st];
		t= new int[2];
		//pscr=new int[game.mul];
		caught= new boolean[game.st];
		speed=speed+500;
		go=true;
		//scr=true;
		//scrt=false;
	//	nb=false;
		tou=false;
		banner=false;
		hold=0;
		time=0;
		//bt=0;
		tshow=0;
		//shti=0;
		//sp=game.pref.getFloat(game.st+"maxsp");
		//strttime=System.currentTimeMillis();
		for (int i=0;i<game.st;i++)
		{
			caught[i]=false;
			x[i]=MathUtils.random(0, w);
			y[i]=MathUtils.random(0, h);
			game.setGravity(Gravity.ZERO,i);
			getNextRandomPoint(x[i],y[i],i);
		}	
		
	}
	@Override
	public void render(float d) {
		Gdx.gl.glClearColor(game.CC.bgColor[0],game.CC.bgColor[1],game.CC.bgColor[2],game.CC.bgColor[3]);		// Back ground color
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.camera.update();		
		
		switch(game.mul){
		case 2:
			game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,"P1|"+game.pscr[0],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"P2|"+game.pscr[1],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,0,game.h*0.5f-pw,game.w,2*pw,0,0,"_",game.CC.bitColor);
			break;
		case 3:
			game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,"P1|"+game.pscr[0],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,gap,game.h/3,w1,h1,pw,gap,"P2|"+game.pscr[1],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"P3|"+game.pscr[2],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,0,game.h/3-4*pw,game.w,2*pw,0,0,"_",game.CC.bitColor);
			game.CD.stringDrawing(game.sr,0,2*game.h/3+2*pw,game.w,2*pw,0,0,"_",game.CC.bitColor);
			break;
		case 4:
			game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,"P1|"+game.pscr[0],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,game.h-h1-gap,w1,h1,pw,gap,"P2|"+game.pscr[1],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"P3|"+game.pscr[2],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,game.w*0.5f+pw+gap,gap,w1,h1,pw,gap,"P4|"+game.pscr[3],game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,0,game.h*0.5f-pw,game.w,2*pw,0,0,"_",game.CC.bitColor);
			game.CD.stringDrawing(game.sr,game.w*0.5f-pw,0,2*pw,game.h,0,0,"_",game.CC.bitColor);
			break;

		}
		//game.CD.stringDrawing(game.sr,0,game.h*0.5f-pw,game.w,2*pw,0,0,"_",game.CC.bitColor);
		if(game.strt)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
		    game.sr.begin(ShapeType.Filled);		    
		    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
		    game.sr.rect(0,0,game.w,game.h);
		    game.sr.end();
		    Gdx.gl.glDisable(GL20.GL_BLEND);
		    game.CC.setNewColor(game.CC.blackColor, "ch");
		    game.CD.stringDrawing(game.sr,game.w*0.5f-6*w1-6*gap,game.h-8*h1,2*w1,2*h1,2*pw,gap,"START",game.CC.getCharColor());
		    game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"TOUCH ANYWHERE TO START",game.CC.getCharColor());
		    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch"); 
		}
		else if(game.pau)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
		    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
		    game.sr.begin(ShapeType.Filled);		    
		    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
		    game.sr.rect(0,0,game.w,game.h);
		    game.sr.end();
		    Gdx.gl.glDisable(GL20.GL_BLEND);
		    game.CC.setNewColor(game.CC.blackColor, "ch");
		    game.CD.stringDrawing(game.sr,game.w*0.5f-6*w1-6*gap,game.h-8*h1,2*w1,2*h1,2*pw,gap,"PAUSED",game.CC.getCharColor());
		    game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"TOUCH ANYWHERE TO RESUME",game.CC.getCharColor());
		    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch"); 
		}
		else
		{
		for(int i=0;i<game.st;i++)
		{
		if(!caught[i])
		{
			game.BD.draw(game.sr,0,0,x[i],y[i], '1');
			go=false;
			if(game.getGravity(i).equals(Gravity.DOWN))
			{
				if(y[i]>y1[i])
				{
					if(x[i]<=x1[i])
					x[i]=(float) (x[i] + game.den*d*speed* Math.cos(a[i]));
					else
					x[i]=(float) (x[i] - game.den*d*speed* Math.cos(a[i]));

					y[i]=(float) (y[i] - game.den*d*speed* Math.sin(a[i]));
				}
				else
				{
					if(x1[i]<0)
						x1[i]=0;
					else
						x1[i]=x[i]; 
					if(y1[i]<0)
						y1[i]=0;
					else
						y1[i]=y[i];
					getNextRandomPoint(x1[i],y1[i],i);
				}
			}
			else if (game.getGravity(i).equals(Gravity.UP))
			{
				if(y[i]<y1[i])
				{
					if(x[i]<=x1[i])
					x[i]=(float) (x[i] + game.den*d*speed* Math.cos(a[i]));
					else
					x[i]=(float) (x[i] - game.den*d*speed* Math.cos(a[i]));

					y[i]=(float) (y[i] + game.den*d*speed* Math.sin(a[i]));
				}
				else
				{
					if(x1[i]<0)
						x1[i]=0;
					else
						x1[i]=x[i]; 
					if(y1[i]<0)
						y1[i]=0;
					else
						y1[i]=y[i];
					getNextRandomPoint(x1[i],y1[i],i);
				}
			}
			else if (game.getGravity(i).equals(Gravity.LEFT))
			{
				if(x[i]>x1[i])
				{
					x[i]=(float) (x[i] - game.den*d*speed* Math.cos(a[i]));
					
					if(y[i]<=y1[i])
					y[i]=(float) (y[i] + game.den*d*speed* Math.sin(a[i]));
					else
					y[i]=(float) (y[i] - game.den*d*speed* Math.sin(a[i]));	
				}
				else
				{
					if(x1[i]<0)
						x1[i]=0;
					else
						x1[i]=x[i]; 
					if(y1[i]<0)
						y1[i]=0;
					else
						y1[i]=y[i];
					getNextRandomPoint(x1[i],y1[i],i);
				}
			}
			else if (game.getGravity(i).equals(Gravity.RIGHT))
			{
				if(x[i]<x1[i])
				{
					x[i]=(float) (x[i] + game.den*d*speed* Math.cos(a[i]));
					
					if(y[i]<=y1[i])
					y[i]=(float) (y[i] + game.den*d*speed* Math.sin(a[i]));
					else
					y[i]=(float) (y[i] - game.den*d*speed* Math.sin(a[i]));
				}
				else
				{
					if(x1[i]<0)
						x1[i]=0;
					else
						x1[i]=x[i]; 
					if(y1[i]<0)
						y1[i]=0;
					else
						y1[i]=y[i];
					getNextRandomPoint(x1[i],y1[i],i);
				}
			}
			
			//game.BD.draw(game.sr,0,0,x[i],y[i], '1');
		}
		else
		{
			game.BD.draw(game.sr,0,0,x[i],y[i], '0');
		}
		}
		//after completing for loop if go=true that means go
		if(go)
		{
			if(hold<3)
			{
				if(hold==0)
				game.aoi.toast("HOLD");
				
				Gdx.gl.glEnable(GL20.GL_BLEND);
			    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
			    game.sr.begin(ShapeType.Filled);		    
			    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
			    game.sr.circle(t[0], game.h-t[1], 3*pw);
			    game.sr.end();
			    Gdx.gl.glDisable(GL20.GL_BLEND);
			    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch");
			    
			hold=hold+d;
			}
			else if(game.st==1)
			{
				if(speed<game.getMaxsp())
					{
					if(speed==game.getMaxsp()-500)
					{
						game.aoi.toast("Speeding UP "+(int)speed/500+"X");
						game.aoi.toast("Last Round");
					}
					else
					game.aoi.toast("Speeding UP "+(int)speed/500+"X");
					
					show();
					}
				else
					{
					int w=game.pscr[0];
					int wp=1;
					for(int i=1;i<game.mul;i++)
					{
						if(w<game.pscr[i])
							{
							w=game.pscr[i];
							wp=i+1;
							}
							
					}
					System.out.println("winner"+wp);
					Gdx.gl.glEnable(GL20.GL_BLEND);
				    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
				    game.sr.begin(ShapeType.Filled);		    
				    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
				    game.sr.rect(0,0,game.w,game.h);
				    game.sr.end();
				    Gdx.gl.glDisable(GL20.GL_BLEND);
				    game.CC.setNewColor(game.CC.blackColor, "ch");
				    game.CD.stringDrawing(game.sr,game.w*0.5f-6*w1-6*gap,game.h-8*h1,2*w1,2*h1,2*pw,gap,"WINNER",game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,game.w*0.5f-4*w1-4*gap,game.h-14*h1,4*w1,4*h1,4*pw,gap,"P"+wp,game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,game.w*0.5f-9*w1-9*gap,gap,3*w1,3*h1,3*pw,gap,"REMATCH",game.CC.getCharColor());
				    
				    /* game.CD.stringDrawing(game.sr,gap,game.h-gap-9*h1,w1,h1,pw,gap,"MAX SPEED :"+(int)speed/200+"X",game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,gap,game.h-gap-11*h1,w1,h1,pw,gap,"NEW BEST  :"+time,game.CC.getCharColor());				 
				    game.CD.stringDrawing(game.sr,gap,game.h-gap-13*h1,w1,h1,pw,gap,"OLD RECORD:"+bt,game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,game.w*0.5f-3*(gap+w1),4*(h1+gap),w1*1.5f,h1*1.5f,pw*1.5f,gap,"SHARE",game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"LEADERBOARD",game.CC.getCharColor());
				    game.CD.stringDrawing(game.sr,game.w-6*w1-6*gap,gap,w1,h1,pw,gap,"SUBMIT",game.CC.getCharColor());*/
				    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch"); 
					}
			}
			else
			{
				game.aoi.toast("Bit Fusion");
				game.st=game.st/2;
				game.aoi.toast("Speeding UP"+(int)speed/500+"X");
				show();
			}
		}
		}
		if(Gdx.input.justTouched())
		{
			//System.out.println("yes");
			if(game.strt || game.pau)
				{
				game.strt=false;
				game.pau=false;
				}
			else if (go && speed==game.getMaxsp())
			{
				speed=0;
				game.newMul();
				game.st=4;
				show();
			}
			else
			{
				if(!go)
			{tou=true;
			tshow=0;
			t[0]=Gdx.input.getX();
			t[1]=Gdx.input.getY();
			}
			/*if(scrt)
			{scrt=false;
			 scr=true;
			// nb=false;
			banner=false;
			//strttime=System.currentTimeMillis();
			if(speed==4000)
			{
				if(game.st==32)
				{
					game.st=1;
				}
				else
				game.st=game.st*2;
				
				show();
			}
			game.aoi.hideBannerAd();
			}*/
			if(caught(Gdx.input.getX(),Gdx.input.getY(),d))
			{
			go=true;
			t[0]=Gdx.input.getX();
			t[1]=Gdx.input.getY();
			}	
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!game.pau && !game.strt && !(go && speed==game.getMaxsp()))
				{game.pau=true;
				game.backpressed=true;
				}
			else if(!game.backpressed)
			{
				game.backpressed=true;
				game.pau=false;
				game.setScreen(game.MOD);
			}
		}
		else if(game.backpressed)
			game.backpressed=false;
	
		if(tou & !go)
		{
			if(tshow<0.20)
			{
				tshow=tshow+d;
				//System.out.println(tshow);
				Gdx.gl.glEnable(GL20.GL_BLEND);
			    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
			    game.sr.begin(ShapeType.Filled);		    
			    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
			    game.sr.circle(t[0], game.h-t[1], 3*pw);
			    game.sr.end();
			    Gdx.gl.glDisable(GL20.GL_BLEND);
			    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch");
			}
			else
				{tou=false;
				tshow=0;
				}
		}
	}
/*private boolean lead(int x, int y) {
		
		y=game.h-y;
		if(x<11*(gap+w1) && y<h1+2*gap)
		{
			return true;
		}
		return false;
	}
private boolean sub(int x, int y) {
	
	y=game.h-y;
	if(x>game.w-6*(gap+w1) && y<h1+2*gap)
	{
		return true;
	}
	return false;
}
	private boolean share(int x, int y) {
		
		y=game.h-y;
		if(x>game.w*0.5f-3*(gap+w1) && x<game.w*0.5f+4*(gap+w1) && y>4*(h1+gap) && y<4*(h1+gap)+1.5f*h1)
		{
			return true;
		}
		return false;
	}
*/
	private boolean caught(int x, int y, float d) 
	{
		boolean c=false;
		int p=0;
		y=game.h-y;
		for(int i=0;i<game.st;i++)
		{
		if(x>this.x[i]+game.BD.bitgap && x<this.x[i]+game.BD.bitgap+game.BD.bitlen  && y>this.y[i]+game.BD.bitgap && y <this.y[i]+game.BD.bitgap+game.BD.bitlen )
		{
			//Checking which player touches?
			switch(game.mul)
			{
			case 2:
				if(y>game.h*0.5f)
					p=0;
				else
					p=1;
				break;
			case 3:
				if(y>2*game.h/3+2*pw)
					p=0;
				else if (y<game.h/3-4*pw)
					p=2;
				else
					p=1;
				break;
			case 4:
				if(y>game.h*0.5f)
					{
					if(x<game.w*0.5f)
						p=0;
					else
						p=1;
					}
				else
				{
					if(x<game.w*0.5f)
						p=2;
					else
						p=3;
				}
				break;
			}
			if(caught[i])
			{
				caught[i]=false;
				hold=0;
				game.pscr[p]=game.pscr[p]-1;
			}
			else
			{
				caught[i]=true;
				game.pscr[p]=game.pscr[p]+1;
			}
			c=true;
			//return true;
		}
		}
		if(c)
			return true;
		else
		return false;
	}
	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	private void getNextRandomPoint(float x, float y,int i) {
		this.x[i]=x;
		this.y[i]=y;
		if(MathUtils.randomBoolean())    //for true we will choose x otherwise y
		{
			x1[i]=MathUtils.random(0, w);
			if(MathUtils.randomBoolean())
			{
			y1[i]=0;	
			game.setGravity(Gravity.DOWN,i);
			if(x1[i]>=x)
			a[i]=Math.atan2(y-y1[i], x1[i]-x);
			else
			a[i]=Math.atan2(y-y1[i], x-x1[i]);	
			}
			else
			{
				y1[i]=h;
				game.setGravity(Gravity.UP,i);
				if(x1[i]>=x)
				a[i]=Math.atan2(y1[i]-y, x1[i]-x);
				else
				a[i]=Math.atan2(y1[i]-y, x-x1[i]);
			}
		}
		else
		{
			y1[i]=MathUtils.random(0, h);
			if(MathUtils.randomBoolean())
			{
			x1[i]=0;	
			game.setGravity(Gravity.LEFT,i);
			if(y1[i]<=y)
			a[i]=Math.atan2(y-y1[i], x-x1[i]);
			else
			a[i]=Math.atan2(y1[i]-y, x-x1[i]);	
			}
			else
			{
				x1[i]=w;
				game.setGravity(Gravity.RIGHT,i);
				if(y1[i]<=y)
				a[i]=Math.atan2(y-y1[i], x1[i]-x);
				else
				a[i]=Math.atan2(y1[i]-y, x1[i]-x);	
			}
		}
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		speed=0;
		game.setMaxsp(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}
