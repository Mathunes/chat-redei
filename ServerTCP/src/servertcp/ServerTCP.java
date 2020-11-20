package servertcp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

public class ServerTCP extends javax.swing.JFrame implements InterfaceStyle {

    //Lista com nome dos clientes conectados segundo a validação
    static ArrayList<String> clientList;
    //Lista com as mensagens dos clientes
    static ArrayList<String> clientMessages;

    private boolean activeServer;
    
    static private int port;

    public ServerTCP() {
        super("Servidor");
        initComponents();
        setStyle();
        setIcon();
    }

    private void startServer() {

        clientList = new ArrayList<String>();
        clientMessages = new ArrayList<String>();

        try {
            //Criando socket de apresentação
            ServerSocket server = new ServerSocket(port);
            output.setText(getDateTime() + "Servidor iniciado na porta: " + server.getLocalPort() + "\n");

            activeServer = true;

            new Thread() {

                public void run() {
                    try {
                        while (activeServer) {
                            //Esperando contato do cliente
                            Socket connectionSocket = server.accept();
                            
                            //Imprimindo informações do cliente
                            output.setText(output.getText() + getDateTime() + "Cliente conectado: "
                                    + connectionSocket.getInetAddress() + ":" + connectionSocket.getPort() + "\n");

                            //Criando instância da Thread que irá tratar as mensagem recebidas do cliente
                            HandleMessage clientMessage = new HandleMessage(
                                    connectionSocket, clientList, clientMessages, output);

                            Thread clientThread = new Thread(clientMessage);

                            clientThread.start();

                        }
                    } catch (Exception e) {
                        output.setText(output.getText() + getDateTime() + e.getMessage() + "\n");
                    }

                }

            }.start();

        } catch (Exception e) {
            output.setText(output.getText() + getDateTime() + e.getMessage() + "\n");
        }

    }

    @Override
    public void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/IconServer.png")));
    }

    @Override
    public void setStyle() {

        output.setSize(getWidth(), getHeight());

        //Removendo borda do input
        CompoundBorder innerCompound = null;
        CompoundBorder outerCompound = null;
        innerCompound = new CompoundBorder(new EmptyBorder(16, 16, 0, 16), new EmptyBorder(0, 0, 0, 0));
        outerCompound = new CompoundBorder(new LineBorder(new Color(255, 255, 255), 0), innerCompound);
        output.setBorder(outerCompound);

        output.setForeground(new Color(0, 255, 0));

        jScrollPane1.setViewportBorder(null);
        jScrollPane1.setBorder(null);

        startServer.setBackground(new Color(87, 105, 116));
        startServer.setBorder(null);
        startServer.setFocusPainted(false);

        //Ouvindo evento do mouse para dar efeito no botão
        startServer.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent evt) {
                if (!activeServer) {
                    startServer.setBackground(new Color(79, 94, 103));
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (!activeServer) {
                    startServer.setBackground(new Color(87, 105, 116));
                }
            }

        });

        startServer.setFont(new Font("Roboto", Font.BOLD, 16));
        startServer.setForeground(new Color(250, 250, 250));

        //Impedindo área de chat de ser editada
        output.setEditable(false);

        statusServer.setFont(new Font("Roboto", 0, 20));
        statusServer.setForeground(new Color(255, 255, 255));

        //Rolar automaticamente para ver a parte inferior da jTextPane
        DefaultCaret caret = (DefaultCaret) output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    private String getDateTime() {
        Date dataHoraAtual = new Date();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);
        String hour = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        return date + " " + hour + " ";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanelGradient(new Color(53, 33, 80), new Color(0, 0, 0));
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextPane();
        statusServer = new javax.swing.JLabel();
        startServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        output.setBackground(new java.awt.Color(0, 0, 0));
        output.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        output.setMaximumSize(new java.awt.Dimension(575, 488));
        output.setMinimumSize(new java.awt.Dimension(575, 488));
        output.setPreferredSize(new java.awt.Dimension(575, 488));
        jScrollPane1.setViewportView(output);

        statusServer.setText("Servidor desativado");

        startServer.setText("INICIAR");
        startServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(statusServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(startServer, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startServer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusServer))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
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
    }// </editor-fold>//GEN-END:initComponents

    private void startServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerActionPerformed
        if (!activeServer) {
            startServer();
            statusServer.setText(statusServer.getText().replaceFirst("desativado", "ativado"));
            startServer.setEnabled(false);
        }
    }//GEN-LAST:event_startServerActionPerformed
     
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (args.length > 0)
                    try {
                        port = Integer.parseInt(args[0]);
                    } catch (Exception e) {
                        port = 8080;
                    }
                else
                    port = 8080;
                new ServerTCP().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane output;
    private javax.swing.JButton startServer;
    private javax.swing.JLabel statusServer;
    // End of variables declaration//GEN-END:variables

}
