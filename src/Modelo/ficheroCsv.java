package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ficheroCsv {
	public static final String SEPARATOR=";";
	   public static final String QUOTE="\"";
	public void leerCsv(String rutaFichero) {
	   //public static void main(String[] args) { 
		BufferedReader br = null;
	      
	      try {
	         
	         //br =new BufferedReader(new FileReader("C:\\Users\\in2dam-b\\Desktop\\prueba.csv"));
	    	  br =new BufferedReader(new FileReader(rutaFichero));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            System.out.println(Arrays.toString(fields));
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         if (null!=br) {
	            try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	}
	}
	     
	}

