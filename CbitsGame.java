package com.kingam.cbits;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CbitsGame extends Game{

	AndroidInterfaces aoi;
	
	public CbitsGame(AndroidInterfaces maoi)
	{
		aoi=maoi;
	}
		public CbitsGame()
		{
			
		}
		public enum Gravity
		{
			ZERO,
			UP,
			DOWN,
			RIGHT,
			LEFT;
		}

	public float maxsp;
	public boolean backpressed=false;
	public boolean strt=false;
	public boolean pau=false;
	//08012016: Added scoring integer
	public int scor=0;
	public int bestscor=0;
	public int st,mul;
	public int pscr[];
	
	public float getMaxsp()
	{
		return maxsp;
	}
	public void setMaxsp(float sp)
	{
		maxsp=sp;
	}
	public void newMul()
	{
		pscr=new int[mul];
	}
	private Gravity g[]={Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,
			Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO,Gravity.ZERO};
	
	//19122015: Preferences to save standard events for example cleared stages
	Preferences pref;

			int w,h; 		//we can skip static as their won't be more than one instance of this class. 
		   // float ar;
			float den;
			
	ShapeRenderer sr;

	MainMenuScreen MMS;
	//InputScreen INS;
	BitDrawing BD;
	CharDrawing CD;
	ColorContainer CC;
	BitCScreen BCS;
	BitCScreenMul BCM;
	StageScreen STS;
    AbtScreen ABT;
    ColScreen COL;
    ModeScreen MOD;
	public FPSLogger fpslog;
	public OrthographicCamera camera;

	//private int animaspeed=600;
	
	private boolean input=true;
	public boolean rate=false;
	
	public boolean getInput()
	{
		return input;
	}
	public void setInput(boolean i)
	{
		input=i;
	}
	/* public int getAnimaSpeed()
	 {
		 return animaspeed;
	 }*/
	/* public void setAnimaSpeed(int s)
	 {
		 if (s<200)
			 animaspeed=200;
		 else if (s>1800)
			 animaspeed=1800;
		 else
		 animaspeed=s;
	 }*/
	
	public void setGravity(Gravity g,int p)
	{
		this.g[p]=g;
	}
	
	public Gravity getGravity(int p)
	{
		return g[p];
	}

    public void create() {
    	//15012016
    	Gdx.input.setCatchBackKey(true);
		//19122015: preferences
    	pref= Gdx.app.getPreferences("CBitsPreferences");
    	//pref.clear();
    	sr=new ShapeRenderer();
    	//Device width and height
    	w=Gdx.graphics.getWidth();
		h=Gdx.graphics.getHeight();
		//ar=(float)h/w;		
		//System.out.println("ar:"+ar);
		den=Gdx.graphics.getDensity();
		System.out.println("ar:"+den);
    	camera = new OrthographicCamera();
    	camera.setToOrtho(false, w, h);		// it is same as camera .setToOrtho(false);
    	BD=new BitDrawing(this);
    	CD=new CharDrawing();
    	CC=new ColorContainer();
    	MMS =new MainMenuScreen(this);
    	MOD=new ModeScreen(this);
    	//INS=new InputScreen(this);
    	STS=new StageScreen(this);
    	BCS=new BitCScreen (this);
    	BCM=new BitCScreenMul(this);
    	ABT=new AbtScreen(this);
    	COL=new ColScreen(this);
    	CC.setNewColor(CC.charColorColl[pref.getInteger("color")], "ch");
      	//fpslog=new FPSLogger();
        setScreen(MMS);
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
    	sr.dispose();
    	sr=new ShapeRenderer();
		//Gdx.app.exit();
    }
}
