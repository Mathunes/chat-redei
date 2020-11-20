package clienttcp;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;

public class AboutProjectWindow extends javax.swing.JFrame implements InterfaceStyle {

    public AboutProjectWindow() {
        initComponents();
        setIcon();
        setStyle();
    }

    @Override
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/Icon.png")));
    }

    @Override
    public void setStyle() {
        title.setFont(new Font("Roboto", Font.BOLD, 18));
        title.setForeground(new Color(255, 255, 255));
        
        body.setFont(new Font("Roboto", 0, 16));
        body.setForeground(new Color(204, 204, 204));
        body.setText("<html>"
                + "<body>"
                + "Trabalho da disciplina de redes de "
                + "computadores I para SI proposto pelo "
                + "prof. <b>Celio Albuquerque</b> com objetivo de "
                + "desenvolver uma aplicação "
                + "cliente-servidor usando TCP.<br>"
                + "Esse é um simples grupo de bate-papo "
                + "sem armazenamento de mensagens "
                + "desenvolvido pelo aluno <b>Matheus "
                + "Antunes Vieira</b>.");
        link.setForeground(new Color(55, 161, 221));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanelGradient(new Color(87, 75, 116), new Color(42, 34, 53));
        title = new javax.swing.JLabel();
        link = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        body = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(312, 312));
        setUndecorated(true);

        title.setText("Sobre o projeto");

        link.setText("github.com/mathunes/chat-redei");
        link.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                linkMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clienttcp/assets/close.png")));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        body.setText("Body");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title)
                    .addComponent(body))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(link)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(title)))
                .addGap(18, 18, 18)
                .addComponent(body)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addComponent(link)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void linkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_linkMouseClicked
        //Abrindo página do github
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/Mathunes/chat-redei").toURI());
        } catch (Exception e) {
            //Janela de erro
            ErrorWindow ew = new ErrorWindow("ERRO", e.getMessage());
            ew.setVisible(true);
        }
    }//GEN-LAST:event_linkMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel link;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

}
