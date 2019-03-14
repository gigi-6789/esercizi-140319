package corsacavalli2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThCorsa extends Thread {

    private int nThread;
    private DatiCondivisi ptrDati;
    private Semaforo ptrSincro1;
    private Semaforo ptrSincro2;

    public ThCorsa(int nT, DatiCondivisi dati, Semaforo sincro1, Semaforo sincro2) {
        this.nThread = nT;
        this.ptrDati = dati;
        this.ptrSincro1 = sincro1;
        this.ptrSincro2 = sincro2;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {

                ptrSincro2.Wait();
                switch (this.nThread) {
                    case 1:
                        ptrDati.setnClop1(i);
                        break;

                    case 2:
                        ptrDati.setnClop2(i);
                        break;

                    case 3:
                        ptrDati.setnClop3(i);
                        break;

                    case 4:
                        ptrDati.setnClop4(i);
                        break;

                    case 5:
                        ptrDati.setnClop5(i);
                        break;
                }
                ptrSincro1.Signal();

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                Thread.sleep((int) (Math.random() * 10));

            }
            if (!Thread.currentThread().isInterrupted()) {
                //segnalo al main la fine della corsa
                ptrDati.signalFinito();
                ptrDati.setVincitore(nThread);
            }

        } catch (InterruptedException ex) {
            //Logger.getLogger(ThCorsa.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Fine corsa Clop" + nThread);
        }

        switch (this.nThread) {
            case 1:
                ptrDati.signalSem1();
                break;

            case 2:
                ptrDati.signalSem2();
                break;

            case 3:
                ptrDati.signalSem3();
                break;

            case 4:
                ptrDati.signalSem4();
                break;

            case 5:
                ptrDati.signalSem5();
                break;
        }

    }

}
