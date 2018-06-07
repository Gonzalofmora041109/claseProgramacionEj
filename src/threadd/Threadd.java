/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadd;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RLCR
 */
class corre implements Runnable{
        String e;
        public corre(String e) {
            this.e=e;
        }

        @Override
        public void run() {
            try {
                System.out.println(e +"proceso 1");
                Thread.sleep(5000);
                  System.out.println(e +"proceso 2");
                Thread.sleep(5000);
                  System.out.println("Final 1");
                
            } catch (InterruptedException ex) {
                //Logger.getLogger(Threadd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }

public class Threadd {

    
           
    public static void main(String[] args) {
        // TODO code application logic here
        
         corre e =new corre("hola");
         new Thread(e).start(); //objeto anonimo
        
        
        
        
        
        
    }
    
}
