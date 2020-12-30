
import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;

/*
 * Created on Jan 28, 2000
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Jagan
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

class Daemon1 extends Thread {
    private Thread t = new Thread();

    public Daemon1() {
        setDaemon(true);
        start();
    }

    public void run() {
        des td = new des();
        String line;
        long poll_int = 0;
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(
                    "prop.txt"));
            line = dis.readLine();
            StringTokenizer st = new StringTokenizer(line, "=;");
            int i, cnt = 0;

            while (st.hasMoreTokens()) {
                i = cnt++;
                String key = st.nextToken();
                String val = st.nextToken();
                if (cnt == 5)
                    poll_int = Long.parseLong(val);
            }
            poll_int = poll_int * 1000;
            System.out.println("Polling Interval : " + poll_int);
        } catch (Exception e) {
            System.out.println("Error opening property file");
        }
        while (true) {

            try {
                td.tdes();
            } catch (InvalidKeyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BadPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                Thread.sleep(poll_int);
                //yield();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}

public class enc_daemon {
    static Logger logger = Logger.getLogger(Daemon1.class);

    public static void main(String[] args) throws IOException {
        HTMLLayout layout = new HTMLLayout();
        WriterAppender healthappender = null;
        try {
            FileOutputStream health = new FileOutputStream("logs/health.html",
                    true);
            healthappender = new WriterAppender(layout, health);
            logger.addAppender(healthappender);
            logger.setLevel((Level) Level.DEBUG);

        } catch (FileNotFoundException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        Thread d = new Daemon1();
        Date dte = new Date();
        logger.info("Daemon Started @ : " + dte);
        System.out.println("d.isDaemon() = " + d.isDaemon());
        // Allow the daemon threads to
        // finish their startup processes:
        System.out.println("Press any key");
        int stp = System.in.read();
        dte = new Date();
        logger.info("Daemon Closed @ : " + dte);
    }
}