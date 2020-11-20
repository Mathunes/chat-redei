package servertcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JTextPane;

public class HandleMessage implements Runnable {
    //Socket para receber a conexão criada anteriormente
    private Socket socketConnection;
    //Lista de clientes recebida como referência para atualizar se o cliente for aceito conforme a validação
    private ArrayList<String> clientList;
    //Lista com as mensagens dos clientes
    static ArrayList<String> clientMessages;
    
    JTextPane output;
    
    public HandleMessage(Socket socketConnection, ArrayList<String> clientList, ArrayList<String> clientMessages, JTextPane output) {
        this.socketConnection = socketConnection;
        this.clientList = clientList;
        this.clientMessages = clientMessages;
        this.output = output;
    }
    
    //Método para adicionar o nome do cliente na lista, apenas se não houver outro igual
    synchronized private boolean addClient(String username) {
        if (clientList.contains(username))
            return false;
        clientList.add(username);
        return true;
    }
    
    //Método para salvar as mensagens dos clientes
    synchronized private void saveMessage(String message) {
        clientMessages.add(message);
    }
    
    //Método para remover o nome do cliente da lista, quando a janela for fechada
    synchronized private void removeClient(String username) {
        clientList.remove(clientList.indexOf(username));
        output.setText(output.getText() + getDateTime() + "Cliente desconectado: " + 
                socketConnection.getInetAddress() + ":" + socketConnection.getPort() + "\n");
    }
    
    public void run() {
        
        String message;
        
        try {
            //Criando cadeia de entrada com o cliente
            Scanner clientMessage = new Scanner(socketConnection.getInputStream());
            //Criando cadeia de saída com o cliente
            DataOutputStream outToClient = new DataOutputStream(socketConnection.getOutputStream());
            //Recebendo várias mensagens
            while(clientMessage.hasNextLine()) {
                
                message = clientMessage.nextLine();
                
                //Se for 1, o cliente quer fazer login
                if (message.charAt(0) == '1')
                    if (addClient(message.replaceFirst("1", "")))
                        outToClient.writeBytes("true\n");
                    else
                        outToClient.writeBytes("false\n");
                
                //Se for 2, o cliente quer receber os clientes ativos no momento
                else if (message.charAt(0) == '2')
                    saveMessage(message.replaceFirst("2", ""));
                
                //Se for 3, o cliente quer receber as mensagens
                else if (message.charAt(0) == '3')
                    outToClient.writeBytes(clientMessages.toString() + '\n');
                
                //Se for 4, o cliente quer sair da conversa
                else if (message.charAt(0) == '4')
                    removeClient(message.replaceFirst("4", ""));
            }
            
        } catch (Exception e) {
        
            output.setText(output.getText() +  getDateTime() +  e.getMessage() + "\n");
            
        }    
    }
    
    private String getDateTime() {
        Date dataHoraAtual = new Date();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);
        String hour = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
        
        return date + " " + hour + " ";
    }
    
}
