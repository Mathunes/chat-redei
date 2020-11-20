package clienttcp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginWindow extends javax.swing.JFrame implements InterfaceStyle {

    private Socket clientSocket;
    
    public LoginWindow() {
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
        //Removendo bordas do input
        CompoundBorder innerCompound = null;
        CompoundBorder outerCompound = null;
        innerCompound = new CompoundBorder(new EmptyBorder(0, 8, 0, 8), new EmptyBorder(0,0,0,0));
        outerCompound = new CompoundBorder(new LineBorder(new Color(255, 255, 255), 1), innerCompound);
        inputUsername.setBorder(outerCompound);
        
        inputUsername.setFont(new Font("Roboto", Font.BOLD, 14));
        
        btnLogin.setBackground(new Color(87, 105, 116));
        btnLogin.setBorder(null);
        btnLogin.setFocusPainted(false);
        
        //Ouvindo evento do mouse para dar efeito no botão
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnLogin.setBackground(new Color(79, 94, 103));
            }
            
            public void mouseExited(MouseEvent evt) {
                btnLogin.setBackground(new Color(87, 105, 116));
            }
            
        });
        
        btnLogin.setFont(new Font("Roboto", Font.BOLD, 16));
        btnLogin.setForeground(new Color(250, 250, 250));
        
        jLabel1.setFont(new Font("Roboto", Font.TYPE1_FONT, 16));
        jLabel1.setForeground(new Color(198, 179, 179));
        
        aboutProject.setFont(new Font("Roboto", 0, 14));
        aboutProject.setForeground(new Color(198, 179, 179));
    }
    
    //Método para conectar cliente ao servidor
    private boolean connectServer(String username) {
        try {
            clientSocket = new Socket("localhost", 8080);
            
            //Criando cadeia de saída com servidor
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            
            //Criando cadeia de entrada com servidor
            BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

            String message = username;
            
            //Enviado mensagem com prefixo 1
            outToServer.writeBytes('1' + message + '\n');

            message = inFromServer.readLine();
            
            //Se for false, o nome já está na lista
            if (!Boolean.parseBoolean(message)) {
                
                inputUsername.setText("");
                
                //Janela de erro
                ErrorWindow ew = new ErrorWindow(
                        "Ops...", "Nome de usuário <b>" + username 
                        + "</b> já está uso, por favor informe outro."
                );
                ew.setVisible(true);
            }
            
            return Boolean.parseBoolean(message);
            
        } catch(Exception e) {
            //Janela de erro
            ErrorWindow ew = new ErrorWindow("ERRO", e.getMessage());
            ew.setVisible(true);
            
        }
        
        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanelGradient(new Color(53, 33, 80), new Color(0, 0, 0));
        jLabel1 = new javax.swing.JLabel();
        inputUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        closeLogin = new javax.swing.JLabel();
        aboutProject = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        jLabel1.setText("Informe seu nome");

        btnLogin.setBackground(new java.awt.Color(225, 225, 225));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogin.setText("ENTRAR");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clienttcp/assets/img-group.png")));

        closeLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clienttcp/assets/close.png")));
        closeLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLoginMouseClicked(evt);
            }
        });

        aboutProject.setText("Sobre o projeto");
        aboutProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutProjectMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(closeLogin)
                .addGap(26, 26, 26))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(aboutProject))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(closeLogin)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(inputUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aboutProject)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    //Ouvindo clique do botão
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        //Se o texto não for vazio conectar ao servidor
        if (!inputUsername.getText().equals("")) {
            
            //Se o nome está disponível fechar janela atual e abrir página principal
            if(connectServer(inputUsername.getText())) {
                dispose();
                
                ChatGroupWindow frame = new ChatGroupWindow(inputUsername.getText(), clientSocket);
                
                //frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 25, 25));
                
                frame.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void closeLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLoginMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeLoginMouseClicked

    private void aboutProjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutProjectMouseClicked
        AboutProjectWindow apw = new AboutProjectWindow();
        
        apw.setVisible(true);
    }//GEN-LAST:event_aboutProjectMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutProject;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel closeLogin;
    private javax.swing.JTextField inputUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
