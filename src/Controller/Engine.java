package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;

import Model.AbstractMonster;
import Model.AbstractPlayer;
import Model.ArmorDecorator;
import Model.ArmorGift;
import Model.Bomb;
import Model.Bomb1;
import Model.Bullet;
import Model.BulletGift;
import Model.Component;
import Model.ComponentFactory;
import Model.Door;
import Model.Flag;
import Model.Gift;
import Model.HealthGift;
import Model.Monster;
import Model.Monster2;
import Model.MovableComponents;
import Model.Player;
import Model.PowerBomb;
import Model.Stone;
import Model.Tree;

public class Engine {

	final int path= 0;
	final int stone= 1;
	final int tree= 2;
	final int start= 3;
	final int end= 4;
	final int flag=10;
	CareTaker ct;
//	private ArrayList<Component> mazeDraw;
    Point p1;
//	Bomb b;
	AbstractPlayer p;
	private Bullet b;
	public static Engine eng;
//	Stone s;
//private Graphics g;
//private ArrayList<Component> components;
int[] x=new int[5];
Component[][] array;
private boolean win;
public boolean isWin() {
	return win;
}

public void setWin(boolean win) {
	this.win = win;
}

private int[][] mazeArray = {
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,2,0,0,0,1,0,1,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,1,1,1,1,0,1,0,2,1,1,1,1,1,1,1,2,1,0,1,1,1,1,2,0,1,1,0,1},
		{1,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0,1},
		{1,0,1,0,1,1,1,1,1,1,0,0,0,1,2,1,1,1,1,1,1,1,0,1,0,0,1,1,1,1,0,1},
		{1,0,0,0,0,0,2,1,0,0,2,0,0,1,0,0,0,0,0,0,2,0,0,1,2,0,1,0,0,0,0,1},
		{1,2,1,0,1,2,1,1,1,1,1,1,1,0,1,1,1,2,1,1,1,1,1,1,0,1,1,2,1,1,0,1},
		{1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,2,1,0,2,0,0,0,0,0,1,0,0,1},
		{1,0,1,1,2,0,0,0,2,0,0,11,1,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1},
		{1,0,2,0,0,0,1,0,0,0,0,0,0,2,1,0,1,0,1,1,0,0,1,0,1,2,1,0,1,0,0,1},
		{1,1,1,0,0,0,1,0,1,0,1,1,1,1,1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1},
		{1,0,0,2,0,0,1,0,1,2,0,1,0,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,1,1,1,1},
		{1,1,0,1,1,2,1,0,1,0,0,1,2,0,0,0,1,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1},
		{1,0,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,0,2,1,1,1,1,1,1,0,0,0,0,0,0,1},
		{1,2,0,1,0,0,1,0,1,0,0,0,1,2,0,0,1,0,0,1,0,2,0,0,0,0,1,1,1,2,0,1},
		{1,0,0,1,0,0,1,0,0,1,1,0,1,0,0,0,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,1},
		{1,0,0,1,2,0,0,1,0,0,0,0,1,0,0,2,0,0,0,2,0,0,0,0,1,0,0,0,0,0,0,1},
		{1,2,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,11,0,2,1,1,0,0,1,1,1,1,1},
		{1,0,1,1,1,1,0,1,0,1,0,0,1,1,1,1,1,1,1,0,1,0,0,0,0,0,2,0,0,0,0,1},
		{1,0,0,0,0,0,2,0,0,1,0,0,0,0,0,0,1,2,1,0,0,1,0,0,2,0,0,0,1,0,0,1},
		{1,0,0,0,0,0,0,0,0,1,0,0,1,1,2,1,1,0,1,0,0,1,0,0,0,0,2,0,1,0,0,1},
		{1,0,2,0,1,1,1,1,1,1,0,0,1,0,0,0,1,0,0,0,0,1,2,0,1,1,1,1,1,0,0,1},
		{1,0,0,0,1,2,0,0,0,1,0,2,1,0,0,0,1,1,1,0,2,1,0,0,0,0,0,0,0,0,0,1},
		{1,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1,1,1,2,0,0,0,0,0,1},
		{1,1,2,1,0,0,0,0,0,1,0,1,1,0,0,1,2,0,0,0,0,2,0,0,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,1,0,2,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,1},
		{1,1,1,2,0,1,1,1,2,0,0,1,1,1,2,1,1,1,1,1,1,0,1,1,1,2,1,1,1,1,1,1},
		{1,0,0,0,0,2,1,0,0,0,0,0,0,0,0,1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,12,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		}; 

private Engine() {
	super();
win = false;
	ct=new CareTaker();
	//this.g = g;
//	p=new Player();
	p=Player.getPlayer();
	//randomPath();
	for(int i=0;i<50;i++) {
	randomGifts();
}
	array=new Component[32][32];
	getComponentArray();
//	components=new ArrayList<>();
//	s=new Stone(new Point(50, 50));
//	b=new Bomb1(new Point(100, 100)) ;
//	Stone s1=new Stone(new Point(70, 74));
//	components.add(p);
//	components.add(s);
//	components.add(b);
	//components.add(s1);
	saveCheckpoint();
}

/*
private void getComponentArray()
{
	ComponentFactory cp=new ComponentFactory();
		// p=new Point();
		 mazeDraw= new ArrayList<Component>();
		 Point p;
		 for(int i=0; i<29; i++)
		 {
			 for(int j=0; j<32; j++)
			 {
				 p=new Point();
				 p.x= j*16;
				 p.y= i*16;
				 mazeDraw.add(cp.getMazeComponent(mazeArray[i][j], p));

				 
			 }
		 }
}*/
/*
private void getComponentArray()
{
		// p=new Point();
	//	 Component c= null;
		 mazeDraw= new ArrayList<Component>();
		 ComponentFactory cp=new ComponentFactory();
		 for(int i=0; i<29; i++)
		 {
			 for(int j=0; j<32; j++)
			 {
				 p1=new Point();
				 p1.x= j*26;
				 p1.y= i*26;
				 if(mazeArray[i][j]!=0)
				 mazeDraw.add(cp.getMazeComponent(mazeArray[i][j], p1));
				 
			 }
		 }
} 
*/

public static Engine getEngine()
{
if(eng==null)
{ eng= new Engine();}
return eng;
}

private void getComponentArray()
{
	//	 Component c= null;
	//	 mazeDraw= new ArrayList<Component>();
		 ComponentFactory cp=new ComponentFactory();
		 for(int i=0; i<29; i++)
		 {
			 for(int j=0; j<32; j++)
			 {
				 p1=new Point();
				 p1.x= j*24;
				 p1.y= i*24;
				 array[i][j]=null;
				 if(mazeArray[i][j]!=0)
				 array[i][j]=cp.getMazeComponent(mazeArray[i][j], p1);
				 
			 }
		 }
} 


	public AbstractPlayer getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
/*	public ArrayList<Component> getComponents() {
		return components;
	}
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	} */
	/*
public Component collides(Component cmp) {
/*	for(Component c : mazeDraw) {
	if(c.collides(cmp)) return c;
	}
	return null;
	 
//	if(cmp instanceof Player) {
		System.out.println(cmp.getPosition());
		int startx=cmp.getPosition().x/25;
		startx-=4;
		if(startx<0) startx=0;
		int starty=cmp.getPosition().y/25;
		starty-=4;
		if(starty<0) starty=0;
		System.out.println(startx + " "+ starty);
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j++)
		 {
			 Component c=array[i][j];
			 if(c!=null)
				 if(c.collides(cmp)) {
					 if()
					 return c;
				 }
			 
		 }
	 }
//	}
	return null;
	
}*/
	
	public boolean collides(MovableComponents cmp) {
		 for(int i=0; i<29; i++)
		 {
			 for(int j=0; j<32; j++)
			 {
				 Component c=array[i][j];
				 if(c!=null) {
					 if(c.collides((Component) cmp)) {
						 if(cmp.hitComponent(c)) {
							 array[i][j]=null;
						 }
						 if(c instanceof ArmorGift && cmp instanceof AbstractPlayer) {
							 p=ArmorDecorator.getArmorDecorator(p);
						 }
						 if((c instanceof Bomb || c instanceof AbstractMonster) && cmp instanceof ArmorDecorator)
							 p=Player.getPlayer();
						 if (c instanceof Bomb1 && cmp instanceof AbstractPlayer) {
							 if(p.getScore()>10)	
							 p.setScore(p.getScore()- 10);
							}
						 if (c instanceof AbstractMonster && cmp instanceof AbstractPlayer) {
							 p.setScore(p.getScore()+20);
						 }
						 if (c instanceof PowerBomb && cmp instanceof Player) {
							 if(p.getScore()>20)	
							 p.setScore(p.getScore()- 20);
							}
						 
						if (c instanceof Bomb && cmp instanceof Bullet) {
							p.setScore(p.getScore()+ 10);
						}

						if(cmp instanceof AbstractPlayer && c instanceof Door) {
							//System.out.println("flag");
							setWin(true);
							//Point poi=new Point(24, 24);
							//p.setPosition(poi);
							//System.out.println(win);
						}
						if (c instanceof Tree && cmp instanceof Bullet) {
							p.setScore(p.getScore()+ 5);
						}
						if (c instanceof Gift && cmp instanceof Bullet) {
							p.setScore(p.getScore()+ 15);
						}
						if (c instanceof Flag && cmp instanceof AbstractPlayer)
						{
							array[i][j]=null;
							saveCheckpoint();
						}
						if (c instanceof Monster2 && cmp instanceof AbstractPlayer) {
							p.setHealth(0);
							returnToCheckpoint();
							
						}
						 b=null;
						 if(p.getHealth()<=0) returnToCheckpoint(); 
						 return true; 
					 }
			 }
		 }
		 }
		return false;
	}
public void moveRight() {
	p.MoveRight();
	collides(p);
}
public void moveLeft() {
	p.MoveLeft();
	collides(p);
	
}
public void moveUp() {
	p.MoveUP();
	collides(p);
}
public void moveDown() {
	p.MoveDown();
	collides(p);
}
/*
public void shoot(Graphics g) {
Bullet b=p.GetBulletPosition();
mazeDraw.add(b);
for(int i=0 ;i<300 ; i++) { //while !collides


}
mazeDraw.remove(b);
} 
*/
 
/*
public ArrayList<Component> getMazeDraw() {
	return mazeDraw;
}


public void setMazeDraw(ArrayList<Component> mazeDraw) {
	this.mazeDraw = mazeDraw;
} */

/*
public int randomNumberGeneration()
{ 
	int randomNumber;
 //ArrayList <Integer> randomInputs = new ArrayList<>();
 //  for(int i=0; i<10; i++) {
	  randomNumber= (int)(Math.random()* 10);
	  if(randomNumber<4) {
		  randomNumber+=4;
		  System.out.println(randomNumber);
	  }
	  return randomNumber;
	//  randomInputs.add(randomNumber);
}
 */

public void randomPath() {
	mazeArray=new int[29][32];
	Random r=new Random();
	int randomindexx;
	int randomindexy;
	int ourGift;
	//int[][] randomArray=new int[10][10];
	Point p;
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j++)
		 {
			 mazeArray[i][j]=r.nextInt((2-0)+2);
		 }
	 }
	 for(int i=0; i<29; i+=28)
	 {
		 for(int j=0; j<32; j++)
		 {
			 mazeArray[i][j]=1;
		 }
	 }
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j+=31)
		 {
			 mazeArray[i][j]=1;
		 }
	 }
	 mazeArray[1][1]=0;
	 mazeArray[1][2]=0;
	 mazeArray[2][1]=0;
	  mazeArray[27][30]=9;
} 
public void randomGifts() {
	Random r=new Random();
	int randomindexx;
	int randomindexy;
	int ourGift;
	//int[][] randomArray=new int[10][10];
	Point p;
			while(true) {
		   randomindexx=r.nextInt((27)+1)+1;
		   randomindexy=r.nextInt((30)+1)+1;
		   p=new Point();
		   p.x=randomindexx;
		   p.y=randomindexy;
		   if(mazeArray[randomindexx][randomindexy]==0  && ( randomindexx!=1 && randomindexy!=1 )) {
			//   System.out.println("flag");
			   ourGift= r.nextInt((10-4)+1)+4;
		       mazeArray[randomindexx][randomindexy]=ourGift;
			   return;
		   }
			}
	  
}

public void saveCheckpoint() {
	ct.addMemento( new Memento(p.getHealth(), p.getBullets(), p.getPosition()));
}

public void returnToCheckpoint() {
	
	
	p.setBullets(ct.getMemento().getBullets());
	p.setHealth(ct.getMemento().getHealth());
	p.setPosition(ct.getMemento().getPosition());
	//System.out.println(p.getPosition());
	p.setHealth(100);
	if(p instanceof ArmorDecorator) p=ArmorDecorator.getArmorDecorator(p);
//	System.out.println(p.getHealth());
}

public void removeB() {
	b=null;
}

public void setB(Bullet b) {
	this.b = b;
}
int k;
public void shiftUp(int i,int j ) {
	array[i][j].setPosition(new Point(j*24,(i-1)*24));
	//	array[i][j].setPosition(new Point(array[i][j].getPosition().x, array[i][j].getPosition().y-24));
		array[i-1][j]=array[i][j];
		array[i][j]=null;
	
}
public void shiftRight(int i,int j ) {
	array[i][j].setPosition(new Point((j+1)*24,(i)*24));
	//	array[i][j].setPosition(new Point(array[i][j].getPosition().x+24, array[i][j].getPosition().y));
		array[i][j+1]=array[i][j];
		array[i][j]=null;
	
}
public void shiftDown(int i,int j ) {
	array[i][j].setPosition(new Point((j)*24,(i+1)*24));
	//	array[i][j].setPosition(new Point(array[i][j].getPosition().x, array[i][j].getPosition().y+24));
		array[i+1][j]=array[i][j];
		array[i][j]=null;
	
}
public void shiftLeft(int i,int j ) {
array[i][j].setPosition(new Point((j-1)*24,(i)*24));
//	array[i][j].setPosition(new Point(array[i][j].getPosition().x-24, array[i][j].getPosition().y));
	array[i][j-1]=array[i][j];
	array[i][j]=null;
}
public synchronized void moveMonsters() {
	ArrayList<Component> moved=new ArrayList<>();
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j++)
		 {
			if(array[i][j] instanceof AbstractMonster) {
				if(moved.contains(array[i][j])) continue;
				moved.add(array[i][j]);
		//		System.out.println(array[i][j]);
				int n=0;
				if(array[i-1][j]==null || array[i][j-1]==null || array[i+1][j]==null || array[i][j+1]==null)
				while(true) {
					Random r=new Random();
					int rand=r.nextInt((4-1)+1)+1;
					if(rand==1 && array[i-1][j]==null) {
						shiftUp(i, j);
						if (array[i-1][j].collides(p)) { 
						p.hitMonster((AbstractMonster) array[i-1][j]);
						if(p instanceof ArmorDecorator) p=Player.getPlayer();
						shiftDown(i-1,j);
						
						}
						break;
					}
					else if(rand==2 && array[i][j+1]==null) {
						shiftRight(i, j);
						if (array[i][j+1].collides(p)) {p.hitMonster((AbstractMonster) array[i][j+1]);
						if(p instanceof ArmorDecorator) p=Player.getPlayer();
					//	else if(array[i][j+1] instanceof Mons)
						shiftLeft(i,j+1);
						}
						break;
					}
					else if(rand==3 && array[i+1][j]==null) {
						shiftDown(i, j);
						if (array[i+1][j].collides(p)) { p.hitMonster((AbstractMonster) array[i+1][j]);
						if(p instanceof ArmorDecorator) p=Player.getPlayer();
						shiftUp(i+1,j);
						}
						break;
					}
					else if(rand==4 && array[i][j-1]==null) {
						shiftLeft(i, j);
						if (array[i][j-1].collides(p)) { p.hitMonster((AbstractMonster) array[i][j-1]);
						if(p instanceof ArmorDecorator) p=Player.getPlayer();
						shiftDown(i,j-1);
						}
						break;
					}
				}
			}
				
		 }
	 }
	
}
public synchronized void updateMaze() {
	ArrayList<Component> moved=new ArrayList<>();
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j++)
		 {
			 mazeArray[i][j]=0;
			  if(array[i][j] instanceof Stone) {
					mazeArray[i][j]=1;
				 } 
			  else if(array[i][j] instanceof Tree) {
					mazeArray[i][j]=2;
				 } 
			  else if(array[i][j] instanceof ArmorGift) {
					mazeArray[i][j]=4;
				 } 
			  else if(array[i][j] instanceof Bomb1) {
					mazeArray[i][j]=5;
				 } 
			  else if(array[i][j] instanceof BulletGift) {
					mazeArray[i][j]=6;
				 } 
			  else if(array[i][j] instanceof HealthGift) {
					mazeArray[i][j]=7;
				 } 
			  else if(array[i][j] instanceof PowerBomb) {
					mazeArray[i][j]=8;
				 } 
			  else if(array[i][j] instanceof Monster ) {
				  mazeArray[i][j]=9;
				 } 
			  else if(array[i][j] instanceof Monster2 ) {
				  mazeArray[i][j]=10;
				 } 
			  else if(array[i][j] instanceof Door) {
					mazeArray[i][j]=12;
				 } 
			  else if(array[i][j] instanceof Flag) {
					mazeArray[i][j]=11;
				 } 
		 }
	 }
}
public synchronized void  save(String path,int count) {
	 {
			try {
				FileOutputStream fos=new FileOutputStream(path);  
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				updateMaze();
				oos.writeObject(mazeArray);
				oos.writeInt(count);
				oos.writeObject(ct.getMemento());
				oos.close();
				

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
}
}

public synchronized int load(String path) {
	try {
		
		FileInputStream fos=new FileInputStream(path);
		ObjectInputStream ois=new ObjectInputStream(fos);
		
		/* for(int i=0; i<29; i++)
		 {
			 for(int j=0; j<32; j++)
			 {
			System.out.print(""+mazeArray[i][j] + " ");
			 }
		 } */
		mazeArray=(int[][]) ois.readObject();
		getComponentArray();
		int count=ois.readInt();
		
		//System.out.print("flag2");
		Memento m=(Memento) ois.readObject();
		ct.addMemento(m);
		returnToCheckpoint();
		ois.close();
	return count;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
} 
public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	if(b!=null)
		g2.drawImage(b.getImage(), b.getPosition().x, b.getPosition().y, null);
	if (eng.getP().getCheck()== true) {
	g2.drawImage(p.getImage(), p.getPosition().x, p.getPosition().y, null);
	}
	 else {
		 eng.returnToCheckpoint();
		 g2.drawImage(p.getImage(), p.getPosition().x, p.getPosition().y, null);
	 }
	 for(int i=0; i<29; i++)
	 {
		 for(int j=0; j<32; j++)
		 {
			 Component c=array[i][j];
			 if(c!=null)
			 g2.drawImage(c.getImage(), c.getPosition().x, c.getPosition().y, null);
		 }
	 }
}
}