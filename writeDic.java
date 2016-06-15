import java.awt.*;
import java.io.*;
import java.util.*; 

public class writeDic{
	FileWriter writer;
	BufferedWriter bufferedWriter;
	writeDic(){
		try{
			writer = new FileWriter("input.txt");
			bufferedWriter = new BufferedWriter(writer);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void writedata(String line){
		try{
			bufferedWriter.write(line);
			bufferedWriter.write("\n");
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void closeFile(){
		try{
			bufferedWriter.close();
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}