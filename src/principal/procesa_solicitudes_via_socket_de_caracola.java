/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

 

/**
 *
 * @author am
 */
public class procesa_solicitudes_via_socket_de_caracola implements Runnable{
        Socket siq_socket;    
        BufferedReader  siq_leer_socket; 
        BufferedWriter  siq_escribe_socket ;
        List <cliente> user=new ArrayList<>();    
      procesa_solicitudes_via_socket_de_caracola(Socket siq_tmp_socket){
            siq_socket = siq_tmp_socket;  
            
            cliente user1=new cliente();
            user1.setNombre("abril");
            user1.setPass("pass");
            user.add(user1);
            
              cliente user2=new cliente();
            user1.setNombre("clau");
            user1.setPass("pass");
            user.add(user2);
            
            
            
        try {
            siq_leer_socket    = new BufferedReader(new InputStreamReader(siq_socket.getInputStream()));
        } catch (IOException ex) {
        //    Logger.getLogger(procesa_solicitudes_via_socket_de_caracola.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            siq_escribe_socket = new BufferedWriter(new OutputStreamWriter(siq_socket.getOutputStream()));
        } catch (IOException ex) {
           // Logger.getLogger(procesa_solicitudes_via_socket_de_caracola.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
      }//--------
    
    //
    //
    private void EnviaTcp(String siq_dato_a_enviar){    
                     try {
                             siq_escribe_socket.write(siq_dato_a_enviar+"\n");
                             siq_escribe_socket.flush();
                         } catch (IOException ex) {
                           //  Logger.getLogger(procesa_solicitudes_via_socket_de_caracola.class.getName()).log(Level.SEVERE, null, ex);
                         }    
    }
    
    //
    //
    private String RecibeTcp(){
        String retorno="";        
                     try {
                                 retorno = siq_leer_socket.readLine();
                         } catch (IOException ex) {
                           //  Logger.getLogger(procesa_solicitudes_via_socket_de_caracola.class.getName()).log(Level.SEVERE, null, ex);
                         }
            return retorno;         
    }
    
    
    @Override
    public void run() {        
        
        
        String siq_texto_leido=RecibeTcp();        
        System.out.println("sockt recebido:"+siq_texto_leido);        
            String [] siq_particionado =  siq_texto_leido.split("\\|" );
            
        if(siq_particionado.length<2){
            EnviaTcp("Falta parametros");
            
   
            try {
             
                  siq_socket.close();
                  return;
            } catch (IOException ex) {
              
            }
        } 
        int siq_codigo = 0;
               String numberAsString = siq_particionado[0];
              
        
        try{            
              siq_codigo = Integer.parseInt(numberAsString);
             
              
              
        }catch(NumberFormatException e){ }
        
        System.out.println("codigo:" + siq_codigo + " --- parametro:" + siq_particionado[1]);
             
         
          
            switch(siq_codigo){
                
                case servidor_caracola.SIQ_SOLICITUD_STATUS:{  
                        EnviaTcp("Alive");                            
                        break;
                        
                        
                }  //final solicita status  
                case servidor_caracola.gmm_PUERTO_PAR_IMPAR: {
                    String numero_entero=siq_particionado[1];
                    String retorno="";
                    try{
                        
                    int entero=Integer.parseInt(numero_entero);
                    if(entero %2==0){
                    retorno="par";
                    
                    }
                    else  retorno="impar";}
                    catch(Exception e){
                      retorno ="valor incorrecto";
                    }
                    
                    EnviaTcp(retorno); 
                        break ;}
                case servidor_caracola.gmm_comando3 :{
                    
                
                }
                        
                        
                    
           
            default:{//comando no conocido
                EnviaTcp("desconocido");                            
                        break;
                
                
            }
        
        }//final del switch
        
      
        try {
            
            siq_leer_socket.close();             
            siq_escribe_socket.close();
            siq_socket.close();
            
        } catch (IOException ex) {
              //    Logger.getLogger(procesa_solicitudes_via_socket_de_caracola.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }//final del run
}
