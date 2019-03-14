
package corsacavalli2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThVisualizza extends Thread {

    
    private DatiCondivisi ptrDati;
    private Semaforo ptrSincro1;
    private Semaforo ptrSincro2;
    
    public ThVisualizza(DatiCondivisi ptrDati, Semaforo sincro1, Semaforo sincro2) {
        this.ptrDati = ptrDati;
        this.ptrSincro1 = sincro1;
        this.ptrSincro2 = sincro2;
    }
    
    public void run() {

        while (true) {
            ptrSincro1.Wait();           
            
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            ptrDati.visualizzaCavalli();
            
            ptrSincro2.Signal();
        }
    }
}
