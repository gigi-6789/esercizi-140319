package corsacavalli2;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CorsaCavalli {

    public static void main(String[] args) {

        try {
            DatiCondivisi dati = new DatiCondivisi();
            Semaforo sincro1 = new Semaforo(1);
            Semaforo sincro2 = new Semaforo(0);

            ThCorsa Clop1 = new ThCorsa(1, dati, sincro1, sincro2);
            ThCorsa Clop2 = new ThCorsa(2, dati, sincro1, sincro2);
            ThCorsa Clop3 = new ThCorsa(3, dati, sincro1, sincro2);
            ThCorsa Clop4 = new ThCorsa(4, dati, sincro1, sincro2);
            ThCorsa Clop5 = new ThCorsa(5, dati, sincro1, sincro2);
            ThVisualizza tv = new ThVisualizza(dati, sincro1, sincro2);

            tv.start();
            Clop1.start();
            Clop2.start();
            Clop3.start();
            Clop4.start();
            Clop5.start();

            dati.waitFinito();
            Clop1.interrupt();
            Clop2.interrupt();
            Clop3.interrupt();
            Clop4.interrupt();
            Clop5.interrupt();
            
            //attendi
            dati.waitSem1();
            dati.waitSem2();
            dati.waitSem3();
            dati.waitSem4();
            dati.waitSem5();
            
            if (ThVisualizza.currentThread().isAlive()) {
                sincro1.Signal();
                tv.interrupt();
            }
            
            System.out.println("Vincitore: Clop"+dati.getVincitore());

        } catch (InterruptedException ex) {
            Logger.getLogger(CorsaCavalli.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
