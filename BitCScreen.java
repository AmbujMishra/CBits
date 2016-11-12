package com.kingam.cbits;
/*
*Ambuj Mishra
* Created:	24 August 2016
* Version:	1.0	(24 August 2016)
* 12 Nov 2016: Time attack mode implementation starting
*/
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.kingam.cbits.CbitsGame.Gravity;

public class BitCScreen implements Screen {

	CbitsGame game;
	float x[],y[],x1[],y1[],w,h,w1,h1,pw,gap,x3,y3;
	double a[];
	float speed,hold,time,bt,sp, tshow;
	boolean caught[],go,scr,scrt,nb,tou,banner;
	//long strttime;
	int t[];
	//boolean caught2=false;
	
	public BitCScreen(CbitsGame gg) {
		game = gg;
		// w1=game.w*0.035f;			
		// h1=game.h*0.028f;     
		w1=game.w*0.030f;			
		h1=game.h*0.024f;
		 pw=(float)Math.sqrt(w1+h1)*(w1/h1)*0.7f;
		 gap=pw*(h1/w1);
		 x3=game.w*0.5f-5*(w1+gap);
	}

	@Override
	public void show() {
		game.aoi.hideBannerAd();
		//game.BD.bitgap=game.w*0.02f;   // 2 % of width
		//game.BD.bitlen=game.w*0.12f;
		//game.BD.cc=game.BD.bitlen*0.05f;       //5 % of bit length
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
		caught= new boolean[game.st];
		speed=200;
		go=true;
		scr=true;
		scrt=false;
		nb=false;
		tou=false;
		banner=false;
		hold=0;
		time=0;
		//21 Nov 2016
		time=game.st*1*3;
		bt=0;
		tshow=0;
		//shti=0;
		sp=game.pref.getFloat(game.st+"maxsp");
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
		if(time<=0)
		{
			go=true;
		}
		if(!go || !(nb||sp==game.getMaxsp()))
			//12 Nov 2016
			//if(!go || !(nb||sp==game.getMaxsp()) || time>0)
		{
			//time=time+d;
			//12 Nov 2016
			time=time-d;
			time=Math.round(time*100)/100f;
		//game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,(int)speed/200+"X"+"|"+Float.toString(time),game.CC.getCharColor());
		game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,Float.toString(time),game.CC.getCharColor());
		game.CD.stringDrawing(game.sr,game.w-4*w1-gap,game.h-h1-gap,w1,h1,pw,gap,(int)speed/200+"X",game.CC.getCharColor());
		}
		else
		{
		//game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,(int)speed/200+"X"+"|"+Float.toString(time),game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,gap,game.h-h1-gap,w1,h1,pw,gap,Float.toString(time),game.CC.getCharColor());
			game.CD.stringDrawing(game.sr,game.w-4*w1-gap,game.h-h1-gap,w1,h1,pw,gap,(int)speed/200+"X",game.CC.getCharColor());
		}
		/*if(Gdx.input.justTouched())
		{
		if(caught(Gdx.input.getX(),Gdx.input.getY()))
		{
			go=true;
		}
		}
		else if (Gdx.input.isKeyPressed(Keys.BACK)){
			if(!game.backpressed)
			{
			game.backpressed=true;
			//System.out.println("SCORE:"+(float)(System.currentTimeMillis()-strttime)/1000);
			game.aoi.toast("SCORE:"+(float)(System.currentTimeMillis()-strttime)/1000);
			game.setScreen(game.STS);
			}
		}
		else if(game.backpressed)
			game.backpressed=false;
		*/
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
		//after completeing for loop if gp=true that means go
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
			else if(scr)
			{
				if(!scrt)
				{ scrt=true;
				//System.out.println("prev time "+time);
				//time=time+(float)(System.currentTimeMillis()-strttime)/1000;
				//System.out.println("added time "+time);
			//	game.aoi.toast("TIME:"+time);
				bt=game.pref.getFloat(game.st+"best"+speed);
				sp=game.pref.getFloat(game.st+"maxsp");
				if(bt==0 || bt>time )
				{
					//bt=time;
					game.pref.putFloat(game.st+"best"+speed, time);
					if(speed>=sp)
					{
						game.pref.putFloat(game.st+"maxsp", speed);
						game.pref.putFloat(game.st+"best", time);
						nb=true;
					}
					game.pref.flush();
				}
				}
				if(!nb && speed!=game.getMaxsp())
				{
					scr=false;
				}
				//hold=0;
				if(nb||speed==game.getMaxsp())
				{
				Gdx.gl.glEnable(GL20.GL_BLEND);
			    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); 
			    game.sr.begin(ShapeType.Filled);		    
			    game.sr.setColor(game.CC.transColor[0],game.CC.transColor[1],game.CC.transColor[2],game.CC.transColor[3]);
			    game.sr.rect(0,0,game.w,game.h);
			    game.sr.end();
			    Gdx.gl.glDisable(GL20.GL_BLEND);
			    game.CC.setNewColor(game.CC.blackColor, "ch");
			    if(nb)
			    game.CD.stringDrawing(game.sr,x3,game.h-gap-5*h1,w1,h1,pw,gap,"NEW RECORD",game.CC.getCharColor());
			    game.CD.stringDrawing(game.sr,gap,game.h-gap-9*h1,w1,h1,pw,gap,"MAX SPEED :"+(int)speed/200+"X",game.CC.getCharColor());
			    if(nb)
			    game.CD.stringDrawing(game.sr,gap,game.h-gap-11*h1,w1,h1,pw,gap,"NEW BEST  :"+time,game.CC.getCharColor());
			    if(bt!=0)
			    game.CD.stringDrawing(game.sr,gap,game.h-gap-13*h1,w1,h1,pw,gap,"OLD RECORD:"+bt,game.CC.getCharColor());
			    game.CD.stringDrawing(game.sr,game.w*0.5f-3*(gap+w1),4*(h1+gap),w1*1.5f,h1*1.5f,pw*1.5f,gap,"SHARE",game.CC.getCharColor());
			    game.CD.stringDrawing(game.sr,gap,gap,w1,h1,pw,gap,"LEADERBOARD",game.CC.getCharColor());
			    game.CD.stringDrawing(game.sr,game.w-6*w1-6*gap,gap,w1,h1,pw,gap,"SUBMIT",game.CC.getCharColor());
			    game.CC.setNewColor(game.CC.charColorColl[game.pref.getInteger("color")], "ch"); 
			    if(game.aoi.isNetConnected() && !banner) 
			    {game.aoi.showBannerAd();
			    banner=true;
			    }
				}
			}
			else
			{
				go=false;
				speed=speed+200;
				//if(speed%200==0)
				 scrt=false;
				 scr=true;
				 nb=false;
				// strttime=System.currentTimeMillis();
				//if(bt<=time)
					//else
					//scr=false;
				game.aoi.toast("SPEEDING UP "+(int)speed/200+"X");
				System.out.println(speed);
				hold=0;
				for(int j=0;j<game.st;j++)
				{
					caught[j]=false;
					
					x[j]=MathUtils.random(0, w);
					y[j]=MathUtils.random(0, h);
					game.setGravity(Gravity.ZERO,j);
					getNextRandomPoint(x[j],y[j],j);
				}
				
				
			}
		}
		if(Gdx.input.justTouched())
		{
			System.out.println("yes");
			if(!go)
			{tou=true;
			tshow=0;
			t[0]=Gdx.input.getX();
			t[1]=Gdx.input.getY();
			}
			//game.BD.draw(game.sr,0,0,Gdx.input.getX(),game.h-Gdx.input.getY(), '2');
			if(go && nb && share(Gdx.input.getX(),Gdx.input.getY()))
			{
				game.aoi.share
				("TRY TO BEAT MY "+game.st+" BIT "+"CATCH RECORD"+"\n"+
				  "AT "+(int)speed/200+" X "+"SPEED IN "+time+" SECONDS"+"\n"+
				  "[Download the game from Play Store]"+"\n"+
				  "https://play.google.com/store/apps/details?id=com.kingam.cbits");
			}
			else if(go && nb && lead(Gdx.input.getX(),Gdx.input.getY()))
			{
				System.out.println("leaderboard");
				game.aoi.showScore();
			}
			else if(go && nb && sub(Gdx.input.getX(),Gdx.input.getY()))
			{
				System.out.println("submit your best");
				game.aoi.submitScore(62542, 1);
			}
			else if(scrt)
			{scrt=false;
			 scr=true;
			 nb=false;
			banner=false;
			//strttime=System.currentTimeMillis();
			if(speed==game.getMaxsp())
			{
				if(game.st==32)
				{
					game.st=1;
					game.setMaxsp(3200);
				}
				else
				{
					game.st=game.st*2;
					
					switch(game.st)
					{
					case 2:
						game.setMaxsp(3200);
						break;
					case 4:
					case 8:
						game.setMaxsp(2400);
						break;
					case 16:
					case 32:
						game.setMaxsp(1600);
						break;
						
					}
				}
				
				show();
			}
			game.aoi.hideBannerAd();
			}
			else if(caught(Gdx.input.getX(),Gdx.input.getY(),d))
			{
			go=true;
			t[0]=Gdx.input.getX();
			t[1]=Gdx.input.getY();
			}
		}
		else if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ENTER)){
			if(!game.backpressed)
			{
			game.backpressed=true;
			//System.out.println("SCORE:"+(float)(System.currentTimeMillis()-strttime)/1000);
			//game.aoi.toast("TIME:"+(time+((float)(System.currentTimeMillis()-strttime)/1000)));
			game.setScreen(game.STS);
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
private boolean lead(int x, int y) {
		
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

	private boolean caught(int x, int y, float d) 
	{
		boolean c=false;
		y=game.h-y;
		for(int i=0;i<game.st;i++)
		{
		if(x>this.x[i]+game.BD.bitgap && x<this.x[i]+game.BD.bitgap+game.BD.bitlen  && y>this.y[i]+game.BD.bitgap && y <this.y[i]+game.BD.bitgap+game.BD.bitlen )
		//	if(x>this.x[i]+game.BD.bitgap+d*speed* Math.cos(a[i]) && x<this.x[i]+game.BD.bitgap+game.BD.bitlen+d*speed* Math.cos(a[i])  && y>this.y[i]+game.BD.bitgap+d*speed* Math.sin(a[i]) && y <this.y[i]+game.BD.bitgap+game.BD.bitlen+d*speed* Math.sin(a[i]) )
			//if(x>this.x[i]+d*speed* Math.cos(a[i]) && x<this.x[i]+game.BD.bitlen+d*speed* Math.cos(a[i])  && y>this.y[i]+d*speed* Math.sin(a[i]) && y <this.y[i]+game.BD.bitlen+d*speed* Math.sin(a[i]) )
			//if(x>this.x[i] && x<this.x[i]+game.BD.bitlen && y>this.y[i] && y <this.y[i]+game.BD.bitlen)
			{
			if(caught[i])
			{
				caught[i]=false;
				hold=0;
			}
			else
			caught[i]=true;
			
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
		//x1=MathUtils.random(0, game.w-game.BD.bitlen-game.BD.bitgap);
		//y1=MathUtils.random(0, game.h-game.BD.bitlen-game.BD.bitgap);
		// a=Math.atan2(y1-y, x1-x);
		/*System.out.println("=============================");
		System.out.println("At:  "+game.getGravity());
		System.out.println("a:"+a);
		System.out.println("x:"+x+"  "+"y:"+y);
		System.out.println("x1:"+x1+"  "+"y1:"+y1);
		System.out.println("=============================");*/
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		game.st=0;
		game.setMaxsp(0);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
}
