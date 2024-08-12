//created by Timothy Polke

import java.lang.Thread;
import java.lang.Exception;
import java.lang.Runnable;

public class Main implements Runnable{

	private static String theme = null;
	private static String size = null;
	private static String orientation = null;
	private static int perThread = 0;
	private static int threads = 0;
	private static String path = null;
	
	public Main(){
		for (int i = 0; i < threads; i++){
			new Thread(this).start();
		}
	}
	
	public static void main(String[] args){
		theme = args[0];
		size = args[1];
		orientation = args[2];
		perThread = Integer.valueOf(args[3]);
		threads = Integer.valueOf(args[4]);
		path = args[5];
		
		Main mainObj=new Main();
	}
	
	public void run(){
		Generator[] generators=null;
		try{
			generators=new Generator[perThread];
			for (int i=0;i<generators.length;i++){
				generators[i]=new Generator(theme, size, orientation);
				generators[i].generate(path);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.exit(0);	
	}
}