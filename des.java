
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.log4j.*;

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
public class des {

    /**
     *  
     */
    String line;
    String src_dir, dest_dir, enc_file, save_file;
    static int sl;
    static Logger logger = Logger.getLogger(des.class);
    FileOutputStream debug, status, stats;
    WriterAppender debugappender = null;
    WriterAppender statusappender = null;
    WriterAppender statsappender = null;
    des() {
        sl = 1;
    }

    public void tdes() throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalStateException,
            IllegalBlockSizeException, BadPaddingException, IOException {

        try {
            logger.removeAppender(statusappender);
            logger.removeAppender(statsappender);
            logger.removeAppender(debugappender);
            System.gc();

            HTMLLayout layout = new HTMLLayout();

            try {

                status = new FileOutputStream("logs/status.html", true);
                debug = new FileOutputStream("logs/debug.html", true);
                stats = new FileOutputStream("logs/stats.html", true);
                debugappender = new WriterAppender(layout, debug);
                statusappender = new WriterAppender(layout, status);
                statsappender = new WriterAppender(layout, stats);
            } catch (Exception e) {
                logger.removeAppender(statusappender);
                logger.removeAppender(statsappender);
                logger.addAppender(debugappender);
                logger.error(e);
            }

            logger.setLevel((Level) Level.DEBUG);

            DataInputStream dis = new DataInputStream(new FileInputStream(
                    "prop.txt"));

            line = dis.readLine();
            System.out.println(line);
            StringTokenizer st = new StringTokenizer(line, "=;");
            int i, cnt = 0;
            while (st.hasMoreTokens()) {
                i = cnt++;
                String key = st.nextToken();
                String val = st.nextToken();
                System.out.println("Val : " + val + "count : " + cnt);
                switch (cnt) {
                case 1:
                    src_dir = val;
                    break;
                case 2:
                    dest_dir = val;
                    break;
                case 3:
                    enc_file = val;
                    break;
                case 4:
                    save_file = val;
                    break;
                }
            }
        } catch (Exception e) {
            logger.removeAppender(statusappender);
            logger.removeAppender(statsappender);
            logger.addAppender(debugappender);
            logger.error(e);
            e.printStackTrace();
        }

        KeyGenerator keygen = KeyGenerator.getInstance("DESede");
        SecretKey desKey = keygen.generateKey();        
        byte[] b = new byte[24];
        byte[] ciphertext = null;
        byte[] c = null;
        byte[] cleartext1 = null;
                
        DataOutputStream f5 = null;
        File list = new File("src");
        String[] flist;
        flist = list.list();

        try {
            for (int size = 0; size < flist.length; size++) {
                Date d = new Date();
                long stime = d.getTime();
                FileInputStream f = new FileInputStream(src_dir + "/"
                        + flist[size]);
                System.out.println("File in use : " + src_dir + "/"
                        + flist[size]);
                logger.removeAppender(debugappender);
                logger.removeAppender(statsappender);
                logger.addAppender(statusappender);
                logger.info(src_dir + "/" + flist[size]
                        + "started encryption @ : " + d);

                int sa = f.available();
                System.out.println("size in bytes =" + sa);

                try {
                    c = new byte[sa];
                } catch (OutOfMemoryError e) {
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while encrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                } catch (Exception e) {
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while encrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                }

                f.read(c);

                Cipher desCipher = Cipher.getInstance("DESede");
                desCipher.init(Cipher.WRAP_MODE,desKey);
                byte[] key=desCipher.wrap(desKey);
                System.out.println("Wrapped Key : "+key+"  length : "+key.length);                
                desCipher.init(Cipher.ENCRYPT_MODE, desKey);
                try {
                    ciphertext = desCipher.doFinal(c);                    
                } catch (OutOfMemoryError e) {
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while encrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                } catch (Exception e) {
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while encrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                }

                try {
                    f5 = new DataOutputStream(new FileOutputStream(dest_dir
                            + "/" + enc_file + "." + sl));
                    DataOutputStream f6 = new DataOutputStream(new FileOutputStream("key.txt"));
                    f5.flush();
                    f5.write(ciphertext);
                    f6.write(key);
                } catch (IOException e) {
                    System.out.println("Error : " + e);
                } catch (Exception e) {
                    System.out.println("Error : " + e);
                }

                d = new Date();
                long etime = d.getTime();
                long time = etime - stime;
                logger.removeAppender(debugappender);
                logger.removeAppender(statsappender);
                logger.addAppender(statusappender);
                logger.info(src_dir + "/" + flist[size]
                        + "finished encryption @ : " + d);

                logger.removeAppender(debugappender);
                logger.removeAppender(statusappender);
                logger.addAppender(statsappender);
                logger.info("Time taken to encrypt " + src_dir + "/"
                        + flist[size] + " : " + time + " milliseconds");

                desCipher.init(Cipher.DECRYPT_MODE, desKey);
                d = new Date();
                stime = d.getTime();
                logger.removeAppender(debugappender);
                logger.removeAppender(statsappender);
                logger.addAppender(statusappender);
                logger.info(dest_dir + "/" + enc_file + "." + sl
                        + "started decrypting @ : " + d);
                try {
                    cleartext1 = desCipher.doFinal(ciphertext);
                } catch (OutOfMemoryError e) {
                    System.out.println("Out - Mem Error");
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while decrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                } catch (Exception e) {
                    logger.removeAppender(statusappender);
                    logger.removeAppender(statsappender);
                    logger.addAppender(debugappender);
                    logger.error(e + " - while decrypting " + src_dir + "/"
                            + flist[size]);
                    break;
                }

                DataOutputStream fo = new DataOutputStream(
                        new FileOutputStream(dest_dir + "/" + flist[size]));
                fo.flush();
                fo.write(cleartext1);
                d = new Date();
                etime = d.getTime();
                time = etime - stime;
                logger.removeAppender(debugappender);
                logger.removeAppender(statsappender);
                logger.addAppender(statusappender);
                logger.info(dest_dir + "/" + enc_file + "." + sl
                        + "finished decrypting @ : " + d);

                logger.removeAppender(debugappender);
                logger.removeAppender(statusappender);
                logger.addAppender(statsappender);
                logger.info("Time taken to decrypt " + dest_dir + "/"
                        + enc_file + "." + sl + " : " + time + " milliseconds");

                f.close();
                f5.close();
                fo.close();
                String n = "null";
                if (save_file.equals(n)) {
                    try {
                        File f1 = new File(src_dir + "/" + flist[size]);
                        f1.delete();
                    } catch (Exception e) {
                        logger.removeAppender(statusappender);
                        logger.removeAppender(statsappender);
                        logger.addAppender(debugappender);
                        logger.error(e);
                        System.out.println("Error in deleting file");
                    }
                } else {
                    try {
                        System.out.println("****** File swaped to *********"
                                + save_file);
                        DataOutputStream fos = new DataOutputStream(
                                new FileOutputStream(save_file + "/"
                                        + flist[size]));                        
                        fos.write(c);
                        File f1 = new File(src_dir + "/" + flist[size]);
                        f1.delete();
                    } catch (Exception e) {
                        logger.removeAppender(statusappender);
                        logger.removeAppender(statsappender);
                        logger.addAppender(debugappender);
                        logger.error(e);
                        System.out.println("Error in deleting file");
                    }
                }
                sl++;
            }
        } catch (Exception e) {
            System.out.println("Error opening file : " + e);
            logger.removeAppender(statusappender);
            logger.removeAppender(statsappender);
            logger.addAppender(debugappender);
            logger.error(e);
        }
    }
}