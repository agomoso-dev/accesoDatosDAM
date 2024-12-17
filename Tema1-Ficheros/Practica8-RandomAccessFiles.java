package accesoAdatos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ejercicio8 {
	 	private File f;
	    private List<String> campos;
	    private List<Integer> camposLength; // V2
	    private long longReg;       // Bytes por registro >>> LINEA
	    private long numReg = 0;    // Número de registros dentro del fichero
	    
	    Ejercicio8( String path, List<String> campos, List<Integer> camposLength ) throws IOException
	    {
	        this.campos = campos;
	        this.camposLength = camposLength; /// V2
	        
	        this.f = new File( path );
	        this.longReg = 0;
	        
	        for( Integer campo: camposLength ){
	            System.out.print("*"+campo+"*");
	            this.longReg += campo;
	        }
	        
	        if( f.exists() ){
	            this.numReg = f.length() / this.longReg;
	        }
	    }
	    
	    public long getNumReg()
	    {
	        return numReg;
	    }
	    
	    public void insertar( Map<String,String> reg ) throws IOException
	    {
	        insertar( reg, this.numReg++);        
	    }
	    
	    public void insertar( Map<String,String> reg, long pos )
	    {
	        // ABRIR ARCHIVO BINARIO
	        try( RandomAccessFile rndFile = new RandomAccessFile( this.f, "rws" ) ) {
	            
	            // POSICIONARNOS PARA ESCRIBIR
	            rndFile.seek( pos * this.longReg );
	            
	            int total = campos.size();
	            for( int i =0; i<total; i++ )
	            // for( String campo : campos )
	            {
	                // Nombre Columna
	                String nomCampo = campos.get(i);
	                
	                // Tamaño Columna
	                Integer longCampo = camposLength.get(i);
	                
	                // VALOR Columna
	                String valorCampo = reg.get(nomCampo);
	                        
	                if( valorCampo == null )
	                {
	                    valorCampo = "";
	                }
	                
	                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo );
	                
	                System.out.print(valorCampoForm);
	                
	                rndFile.write(valorCampoForm.getBytes("UTF-8"),0, longCampo);
	            }  
	        }
	        catch( Exception ex )
	        {
	        
	        }
	    }
	    
	    public static void main(String args[])
	    {
	        
	        System.out.print("111");
	                
	        List campos = new ArrayList();
	        List camposLength = new ArrayList();
	        
	        campos.add( "ID" );
	        campos.add( "APELLIDOS" );
	        campos.add( "FECHA" );
	        campos.add( "DIRECCION" );
	        
	        camposLength.add( 5 );
	        camposLength.add( 32 );
	        camposLength.add( 32 );
	        camposLength.add( 32 );
	        
	        try {
	            
	            Ejercicio8 faa = new Ejercicio8("file_binario_2.dat", campos, camposLength );
	            
	            Map reg = new HashMap();
	            
	            // PRIMER REGISTRO
	            reg.put("ID", "12345");
	            reg.put("APELLIDOS", "Gomez Osorio");            
	            reg.put("FECHA", "01/01/1999");
	            reg.put("DIRECCION", "Calle pareja yebenes 10");
	            faa.insertar(reg,10);
	            reg.clear();
	            
	            // SEGUNDO REGISTRO
	            reg.put("ID", "66663");
	            reg.put("APELLIDOS", "Jamel potur");            
	            reg.put("FECHA", "01/02/2000");
	            reg.put("DIRECCION", "Calle pareja yebenes 10");
	            faa.insertar(reg,9);
	            reg.clear();
	            
	            // TERCER REGISTRO
	            reg.put("ID", "33333");
	            reg.put("APELLIDOS", "Popo popo");            
	            reg.put("FECHA", "01/05/2001");
	            reg.put("DIRECCION", "Calle Pinto patur 2 Nº4");
	            faa.insertar(reg,0);
	            reg.clear();
	                        
	            
	        }
	        catch( IOException e )
	        {
	            System.out.print("EXCE" + e.getMessage() );
	        }
	        catch( Exception e )
	        {
	            System.out.print("EXCE" + e.getMessage() );
	        }
	    }
	        
}

