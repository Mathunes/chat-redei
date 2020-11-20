package clienttcp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class ChatGroupWindow extends javax.swing.JFrame implements InterfaceStyle {

    private String username;
    private Socket clientSocket;

    public ChatGroupWindow(String username, Socket clientSocket) {
        super("Chat");
        initComponents();
        setIcon();
        setStyle();

        this.username = username;
        this.clientSocket = clientSocket;

        //"Username conectado"
        jLabel2.setText(username + jLabel2.getText());

        //Chamando método para atualizar o chat
        updateChat(textPaneMessage);

        //Ouvindo fechamento da janela
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                logoff();
            }
        }
        );

    }

    @Override
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/Icon.png")));
    }

    @Override
    public void setStyle() {

        jPanel2.setSize(getWidth(), getHeight());

        //Removendo borda do input
        CompoundBorder innerCompound = null;
        CompoundBorder outerCompound = null;
        innerCompound = new CompoundBorder(new EmptyBorder(0, 16, 0, 16), new EmptyBorder(0, 0, 0, 0));
        outerCompound = new CompoundBorder(new LineBorder(new Color(255, 255, 255), 0), innerCompound);
        inputMessage.setBorder(outerCompound);

        inputMessage.setFont(new Font("Roboto", 0, 14));

        innerCompound = new CompoundBorder(new EmptyBorder(16, 16, 0, 16), new EmptyBorder(0, 0, 0, 0));
        outerCompound = new CompoundBorder(new LineBorder(new Color(255, 255, 255), 0), innerCompound);
        textPaneMessage.setBorder(outerCompound);

        textPaneMessage.setBackground(new Color(63, 43, 64));
        textPaneMessage.setFont(new Font("Roboto", 0, 16));
        textPaneMessage.setForeground(new Color(255, 255, 255));

        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(null);

        btnSendMessage.setBackground(new Color(87, 105, 116));
        btnSendMessage.setBorder(null);
        btnSendMessage.setFocusPainted(false);

        //Ouvindo evento do mouse para dar efeito no botão
        btnSendMessage.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnSendMessage.setBackground(new Color(79, 94, 103));
            }

            public void mouseExited(MouseEvent evt) {
                btnSendMessage.setBackground(new Color(87, 105, 116));
            }

        });

        btnSendMessage.setFont(new Font("Roboto", Font.BOLD, 16));
        btnSendMessage.setForeground(new Color(250, 250, 250));

        //Impedindo área de chat de ser editada
        textPaneMessage.setEditable(false);

        jLabel2.setFont(new Font("Roboto", 0, 20));
        jLabel2.setForeground(new Color(255, 255, 255));

        //Rolar automaticamente para ver a parte inferior da jTextPane
        DefaultCaret caret = (DefaultCaret) textPaneMessage.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

    }

    private void logoff() {
        try {
            //Criando cadeia de saída com servidor
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviado mensagem com prefixo 4
            outToServer.writeBytes("4" + username + "\n");

        } catch (Exception e) {
            //Janela de erro
            ErrorWindow ew = new ErrorWindow("ERRO", e.getMessage());
            ew.setVisible(true);

        }

    }

    //Método chamando thread que atualiza o chat
    private void updateChat(JTextPane textPaneMessage) {
        UpdateChat uc = new UpdateChat(textPaneMessage, clientSocket);

        Thread updateChatThread = new Thread(uc);

        updateChatThread.start();
    }

    //Método para enviar messagens para o servidor
    private void sendMessageToServer(String message) {
        try {
            //Criando cadeia de saída com servidor
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Enviado mensagem com prefixo 2
            outToServer.writeBytes("2" + username.toUpperCase() + ": " + message + "\n");

        } catch (Exception e) {
            //Janela de erro
            ErrorWindow ew = new ErrorWindow("ERRO", e.getMessage());
            ew.setVisible(true);

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new JPanelGradient(new Color(53, 33, 80), new Color(0, 0, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneMessage = new javax.swing.JTextPane();
        inputMessage = new javax.swing.JTextField();
        btnSendMessage = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 615));
        setResizable(false);

        textPaneMessage.setContentType("text/text"); // NOI18N
        textPaneMessage.setFocusable(false);
        jScrollPane1.setViewportView(textPaneMessage);

        inputMessage.setMinimumSize(new java.awt.Dimension(6, 23));
        inputMessage.setPreferredSize(new java.awt.Dimension(6, 23));

        btnSendMessage.setText("Enviar");
        btnSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMessageActionPerformed(evt);
            }
        });

        jLabel2.setText(" conectado");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(inputMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(33, 33, 33)
                            .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMessageActionPerformed
        //Se o texto não for vazio enviar mensagem
        if (!inputMessage.getText().equals("")) {
            sendMessageToServer(inputMessage.getText().replaceAll(",", "//"));

            //Limpando input
            inputMessage.setText("");
        }
    }//GEN-LAST:event_btnSendMessageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendMessage;
    private javax.swing.JTextField inputMessage;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane textPaneMessage;
    // End of variables declaration//GEN-END:variables

}
