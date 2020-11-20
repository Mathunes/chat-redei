package clienttcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JTextPane;

//Thread para atualizar o chat
public class UpdateChat implements Runnable {
    
    JTextPane textPaneMessage;
    Socket clientSocket;
    ArrayList<String> clientMessages;
    int lengthMessages;
    
    public UpdateChat(JTextPane textPaneMessage, Socket clientSocket) {
        this.textPaneMessage = textPaneMessage;
        this.clientSocket = clientSocket;
        clientMessages = new ArrayList<String>();
        lengthMessages = 0;
    }
    
    //Método para imprimir as mensagens na tela
    private void showMessages(String message) {
        //Limpando a string recebida (veio de um array.toString())
        message = message.replace("[", "");
        message = message.replace("]", "");
        
        //Se o tamanho das novas mensagens forem maior do que a anterior, tem mensagem nova
        if (message.length() > lengthMessages) {
            lengthMessages = message.length();
            
            if (message.split(",").length > clientMessages.size()) {
            
                //Imprimindo apenas as novas mensagens
                for (int i = clientMessages.size(); i < message.split(",").length; i++) {
                    //Se for a primeira, não tem espaço (" ") a mais antes da mensagem (["oi", "ola"] -> "[oi, ola]")
                    if (i == 0) {
                        textPaneMessage.setText(textPaneMessage.getText() + message
                                .split(",")[i]
                                .replace("//", ","));
                    } else {
                        textPaneMessage.setText(textPaneMessage.getText() + '\n' + message
                                .split(",")[i]
                                .replaceFirst(" ", "\n")
                                .replace("//", ","));
                    }
                    
                    clientMessages.add(message
                            .split(",")[i]
                            .replace("//", ",")
                    );
                }       
            }
        }
    }
    
    public void run() {
        
        try {
            
            //Criando cadeia de saída com servidor
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //Criando cadeia de entrada com servidor
            BufferedReader inFromServer = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            
            while(true) {
                //Enviado mensagem com prefixo 3
                outToServer.writeBytes("3\n");
                
                showMessages(inFromServer.readLine());
                
                //Diminuindo velocidade do loop para não superaquecer meu processador I3
                Thread.sleep(250);
            }
            
        } catch(Exception e) {
            //Janela de erro
            ErrorWindow ew = new ErrorWindow("ERRO", e.getMessage());
            ew.setVisible(true);
            
        }
    }    
}
