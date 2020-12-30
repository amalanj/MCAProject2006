/*
 * Frame.java
 *
 * Created on February 3, 2006, 4:40 PM
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.UIManager;


/**
 * 
 * @author Amalan
 */
public class Frame extends java.awt.Frame {

    /** Creates new form Frame */
    public Frame() {
        super("Encryption Parameter Configuration");
        try{
    		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	}catch(Exception e){
    	}
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */

    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        jTextField1 = new javax.swing.JTextField(20);
        jTextField2 = new javax.swing.JTextField(20);
        jTextField3 = new javax.swing.JTextField(20);
        jTextField4 = new javax.swing.JTextField(20);
        jTextField5 = new javax.swing.JTextField(20);
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        is_save = 0;

        setLayout(new java.awt.GridBagLayout());

        setBackground(new java.awt.Color(204, 204, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        label1.setText("Source Directory");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(label1, gridBagConstraints);

        label2.setText("Destination Directory");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(label2, gridBagConstraints);

        label3.setText("Encrypted File Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(label3, gridBagConstraints);

        label4.setText("Source File Required");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(label4, gridBagConstraints);

        label5.setText("Polling Interval");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(label5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jTextField1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jTextField2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jTextField3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jTextField4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jTextField5, gridBagConstraints);

        jRadioButton1.setText("Delete Source File");
        buttonGroup1.add(jRadioButton1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jRadioButton1, gridBagConstraints);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Save Source File");
        buttonGroup1.add(jRadioButton2);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jRadioButton2, gridBagConstraints);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jButton1, gridBagConstraints);

        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
        add(jButton4, gridBagConstraints);

        jButton5.setText("Browse");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);
        add(jButton5, gridBagConstraints);

        jButton2.setText("Save & Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jButton2, gridBagConstraints);

        jButton3.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        add(jButton3, gridBagConstraints);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pack();

        String line;
        byte[] readall;
        String value = null;

        try {
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
                    jTextField1.setText(val);
                    break;
                case 2:
                    jTextField2.setText(val);
                    break;
                case 3:
                    jTextField3.setText(val);
                    break;
                case 4:
                    jTextField4.setText(val);
                    break;
                case 5:
                    jTextField5.setText(val);
                    break;
                }
            }
        } catch (IOException e1) {
            // Generate error : Error in opening property file
            e1.printStackTrace();
        }
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextField4.setText("");
        validate();
    }

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        jTextField4.show();
        jButton1.show();
        validate();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
	try
	{
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int ret = fc.showDialog(this, null);
        jTextField4.setText(fc.getSelectedFile().getPath());
	}
	catch(Exception e)
	{
		System.out.print("FileChooser Error"+e);
	}
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        String in;

        in = jTextField4.getText();
        int len = in.length();
        if (len != 0)
            in = label1.getText() + "=" + jTextField1.getText() + ";"
                    + label2.getText() + "=" + jTextField2.getText() + ";"
                    + label3.getText() + "=" + jTextField3.getText() + ";"
                    + label4.getText() + "=" + jTextField4.getText() + ";"
                    + label5.getText() + "=" + jTextField5.getText();
        else
            in = label1.getText() + "=" + jTextField1.getText() + ";"
                    + label2.getText() + "=" + jTextField2.getText() + ";"
                    + label3.getText() + "=" + jTextField3.getText() + ";"
                    + label4.getText() + "=" + "null" + ";" + label5.getText()
                    + "=" + jTextField5.getText();
        try {
            DataOutputStream d = new DataOutputStream(new FileOutputStream(
                    "prop.txt"));            
            d.writeBytes(in);
            System.out.print("Written Succes");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.exit(0);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // Exit the application
        System.exit(0);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = fc.showDialog(this, null);
        jTextField1.setText(fc.getSelectedFile().getPath());
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // Add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int ret = fc.showDialog(this, null);
        jTextField2.setText(fc.getSelectedFile().getPath());
    }

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
        new Frame().show();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton2;

    private javax.swing.JButton jButton4;

    private javax.swing.JButton jButton5;

    private javax.swing.JRadioButton jRadioButton1;

    private javax.swing.JTextField jTextField3;

    private java.awt.Label label3;

    private javax.swing.JButton jButton1;

    private java.awt.Label label4;

    private javax.swing.JButton jButton3;

    private javax.swing.JTextField jTextField5;

    private java.awt.Label label5;

    private java.awt.Label label2;

    private javax.swing.ButtonGroup buttonGroup1;

    private javax.swing.JRadioButton jRadioButton2;

    private javax.swing.JTextField jTextField2;

    private javax.swing.JTextField jTextField1;

    private java.awt.Label label1;

    private javax.swing.JTextField jTextField4;

    static String enc_source;

    static String enc_dest;

    String dec_dest;

    public static int is_save;
    // End of variables declaration

}