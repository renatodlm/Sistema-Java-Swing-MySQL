/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Font;
import javax.swing.ImageIcon;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 *
 * @author Work
 */
public class LoadingMode {

    public void Loading() {

        JLabel LOGO = new JLabel(new ImageIcon(getClass().getResource("cs.png")));
        LOGO.setBounds(423, 130, 128, 128);
        //getContentPane().add(LOGO);

        final JLabel car = new JLabel("CASA");
        //setTitle("SOFTWARE - CADASTRO DE CLIENTES - v.1.0 - JAVA SWING");
        //setSize(980, 528);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        //getContentPane().setLayout(null);
        setResizable(false);
        setVisible(true);

        final JLabel lblCarregandoAguarde = new JLabel("CARREGANDO");
        lblCarregandoAguarde.setHorizontalAlignment(SwingConstants.CENTER);
        lblCarregandoAguarde.setFont(new Font("Roboto Condensed", Font.PLAIN, 21));
        lblCarregandoAguarde.setBounds(325, 345, 323, 40);
        //getContentPane().add(lblCarregandoAguarde);

        final JProgressBar progressBar = new JProgressBar();
        progressBar.setBorderPainted(false);
        progressBar.setFont(new Font("Roboto Condensed", Font.BOLD, 16));
        progressBar.setStringPainted(true);
        progressBar.setBounds(325, 284, 323, 35);
        //getContentPane().add(progressBar);

        new Thread() {

            @Override
            public void run() {

                for (int i = 0; i < 101; i++) {
                    try {
                        sleep(60);
                        progressBar.setValue(i);

                        if (progressBar.getValue() <= 40) {
                            car.setText("CARREGANDO DADOS");

                        } else if (progressBar.getValue() <= 70) {
                            lblCarregandoAguarde.setText("CARREGANDO TABELAS");
                        } else {
                            lblCarregandoAguarde.setText("CARREGANDO SISTEMA");
                        }

                    } catch (InterruptedException ex) {
                        //Logger.getLogger(SplashScreen.class.getName()).log(Level.SEVERE, null, ex);

                    }

                }

                JPanel TrocarTela = null;
                TrocarTela = new JPanel();
                //getContentPane().removeAll();
                //getContentPane().add(TrocarTela);
                validate();

                //TelaInicial();
                //repaint();
            }

            private void validate() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        }.start();

    }

    private void setResizable(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object getContentPane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
