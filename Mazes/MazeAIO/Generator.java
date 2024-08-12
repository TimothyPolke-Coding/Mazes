//created by TimothyPolke

import java.awt.Color;
import java.security.SecureRandom;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Generator{
	private String theme=null;
	private String size=null;
	private String orientation=null;

	public Generator(String theme,String size,String orientation){
		setTheme(theme);
		setSize(size);
		setOrientation(orientation);
	}
	
	public void setTheme(String theme){
		this.theme=theme;
	}
	
	public String getTheme(){
		return theme;
	}
	
	public void setSize(String size){
		this.size=size;
	}
	
	public String getSize(){
		return size;
	}
	
	public void setOrientation(String orientation){
		this.orientation=orientation;
	}
	
	public String getOrientation(){
		return orientation;
	}
		
	public void generate(String path){
		String filename=null;
		
		int cell=10;
		int wall=1;
		
		int x=0;
		int y=0;
		
		int dimension=0;
		
		int smallDimension=0;
		int largeDimension=0;
		
		Color white=new Color(255,255,255);
		Color black=new Color(0,0,0);
		Color highlight=new Color(255,255,0);
		
		Color foreground=null;
		Color background=null;
		
		filename=randomize(48,"abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789".toCharArray());
		
		if(theme.toLowerCase().equals("light")){
			foreground=black;
			background=white;
		}
		else if(theme.toLowerCase().equals("dark")){
			foreground=white;
			background=black;
		}
		
		if(size.toLowerCase().equals("small")){
			dimension=10;
		}
		else if(size.toLowerCase().equals("medium")){
			dimension=50;
		}
		else if(size.toLowerCase().equals("large")){
			dimension=100;
		}
		largeDimension=dimension;
		smallDimension=dimension/2;
		
		if(orientation.toLowerCase().equals("equal")){
			x=dimension;
			y=dimension;
		}
		else if(orientation.toLowerCase().equals("horizontal")){
			x=largeDimension;
			y=smallDimension;
		}
		else if(orientation.toLowerCase().equals("vertical")){
			x=smallDimension;
			y=largeDimension;
		}
		
		Maze maze=new Maze(x,y,cell,wall);
		
		maze.redraw(foreground,background,background);
		File outputFolderPuzzles=new File(path+"/"+"MAZES_"+/*randomID+*/"/"+theme.toUpperCase()+"/"+size.toUpperCase()+"/"+orientation.toUpperCase()+"/"+"PUZZLES");
		try{
			if(!outputFolderPuzzles.exists()){
				outputFolderPuzzles.mkdirs();
			}			
			ImageIO.write(maze,"jpg",new File(outputFolderPuzzles+"/"+"PUZZLE_"+filename+".jpg"));
		}
		catch(IOException e){
		}
		
		maze.redraw(foreground,background,new Color(255,255,0));
		File outputFolderSolutions=new File(path+"/"+"MAZES_"+/*randomID+*/"/"+theme.toUpperCase()+"/"+size.toUpperCase()+"/"+orientation.toUpperCase()+"/"+"SOLUTIONS");
		try{
			if(!outputFolderSolutions.exists()){
				outputFolderSolutions.mkdirs();
			}			
			ImageIO.write(maze,"jpg",new File(outputFolderSolutions+"/"+"SOLUTION_"+filename+".jpg"));
		}
		catch(IOException e){
		}
	}
	
	public static String randomize(int length,char[] charactersAllowed){		
		SecureRandom random=new SecureRandom();
		StringBuilder code=new StringBuilder();	
		for(int i=0;i<length;i++){
			code.append(charactersAllowed[random.nextInt(charactersAllowed.length)]);
		}
		return code.toString();
	}
}