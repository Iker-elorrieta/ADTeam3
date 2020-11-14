package modelo;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileSystemView;

public class EncontrarFichero extends Thread {
	
	private String fichero;
	private String estado = "buscando";
	private String result;
	private int lugarProgreso;
	
	public EncontrarFichero(String fichero)
	{
		this.fichero = fichero;
	}
	
	public void run()
	{
		result = econtrarFichero(fichero);
		if(result.equals(""))
		{
			result = "no se encontro el fichero";
			estado = "esperando recogida";
		}
		else
		{
			estado = "esperando recogida";
		}
		
		while(estado.equals("esperando recogida"))
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<String> encontrarEspaciosAlmacenamiento()
	{
		ArrayList<String> result = new ArrayList<String>();
		/*
			//System.out.println("Drive Letter: " + aDrive); ruta del dispositivo
			//System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive)); nombre del dispositivo
			//System.out.println();
		 * 
		 	https://stackoverflow.com/questions/21059703/how-can-a-java-program-list-all-partitions-and-get-the-free-space-of-them-on-lin
		 */
		 FileSystemView fsv = FileSystemView.getFileSystemView();
		 File[] drives = File.listRoots();
		 
		 if (drives != null && drives.length > 0) {
		        for (File aDrive : drives) {
		        	result.add(aDrive.toString());
		        }
		 }
		 return result;
	}
	
	public String econtrarFichero(String fichero)
	{
		ArrayList<String> discos = encontrarEspaciosAlmacenamiento();
		for(int i = 0 ; i < discos.size() ; i++)
		{
			String ruta = subdirectoriosRecursivos(discos.get(i), fichero);
			lugarProgreso = i;
			if(!ruta.equals(""))
			{
				i = discos.size();
				lugarProgreso = i;
				return ruta;
			}
//			System.out.println("");
//			System.out.println("No se encontro el archivo en " + discos.get(i) );
		}
		return "";
	}
	
	public String subdirectoriosRecursivos(String url, String nombrefichero)
	{
		String result = "";
		File f = new File(url);
		File[] files;
		if(f.listFiles() != null)
		{
			files = f.listFiles();
		}
		else
		{
			result = "";
			return result;
		}
         
		int y = 0;
        int i = 1;
        while (y < files.length) {
        	if(Character.isLetter(files[y].getName().charAt(0)) || Character.isDigit(files[y].getName().charAt(0)))
        	{
        		if(files[y].isDirectory())
        		{
        			File nombre = files[y];
	        		result = subdirectoriosRecursivos(files[y].getAbsolutePath(),nombrefichero);
        		}
        		else
        		{
        			if(files[y].getName().equals(nombrefichero))
        			{
        				return files[y].getAbsolutePath();
        			}
        		}
        	}
        	
        	if(!result.equals("") && !result.equals("no hay mas elementos aqui"))
        		return result;
        	y++;
        }
        
        return result;
	}
	
	public String recogerResult()
	{
		estado = "terminado";
		return result;
	}

	public String getEstado()
	{
		return estado;
	}

	public int getProgreso()
	{
		return lugarProgreso;
	}
	
	public int limiteProgreso()
	{
		return encontrarEspaciosAlmacenamiento().size();
	}
	
}
