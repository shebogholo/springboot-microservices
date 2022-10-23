package com.shebogholo.chat;


import com.shebogholo.chat.utils.RegisterRequest;
import com.shebogholo.chat.utils.Request;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class XMPPService {
    private ChatManager chatManager;
    private AccountManager accountManager;
    private AbstractXMPPConnection connection;

    private IncomingChatMessageListener incomingChatMessageListener;
    private OutgoingChatMessageListener outgoingChatMessageListener;

    @Autowired
    public XMPPService() {
        try{
            // create connection
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("shebogholo.com")
                    .setHost("shebogholo.com")
                    .build();
            connection = new XMPPTCPConnection(config);
            // connect
            connection.connect();
            chatManager = ChatManager.getInstanceFor(connection);
            accountManager = AccountManager.getInstance(connection);

            // add listeners
            chatManager.addIncomingListener((entityBareJid, message, chat) -> {
                System.out.println("========================================");
                System.out.println("Received message: (" + message.getBody() +") from: " + entityBareJid.getLocalpart());
                System.out.println("----------------------------------------");
            });

            if (connection.isConnected()) {
                if (!connection.isAuthenticated()) {
                    System.out.println("Authenticating...");
                    connection.login("juma", "juma");
                    System.out.println("Authenticated!");
                }
            }

            // send presence
            connection.sendStanza(new Presence(Presence.Type.available));

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean isAuthenticated(){
        return connection.isAuthenticated();
    }

    public boolean isConnected(){
        return connection.isConnected();
    }

    public void login(String username, String password){
        try {
            connection.login(username, password);
        } catch (XMPPException | SmackException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(RegisterRequest registerRequest){
        try {
            Localpart localpart = Localpart.from(registerRequest.username());
            System.out.println("Registering user: " + localpart);
            accountManager.createAccount(localpart, registerRequest.password());
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException | InterruptedException e) {
            e.printStackTrace();
        } catch (XmppStringprepException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Request request){
        System.out.println("Sending message to: " + request.messageTo());
        try {
            Jid jid = JidCreate.from(request.messageTo()+"@shebogholo.com");
            Chat chat = chatManager.chatWith((EntityBareJid) jid);
            chat.send(request.message());
        } catch (InterruptedException | XmppStringprepException | SmackException.NotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void logout(){
        try {
            connection.sendStanza(new Presence(Presence.Type.available));
            connection.disconnect();
        } catch (SmackException.NotConnectedException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect(){
        try {
            // create connection
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setXmppDomain("shebogholo.com")
                    .setHost("shebogholo.com")
                    .build();
            connection = new XMPPTCPConnection(config);
            // connect
            connection.connect();
            chatManager = ChatManager.getInstanceFor(connection);
            accountManager = AccountManager.getInstance(connection);

            // add listeners
            chatManager.addIncomingListener((entityBareJid, message, chat) -> {
                System.out.println("========================================");
                System.out.println("Received message: (" + message.getBody() +") from: " + entityBareJid.getLocalpart());
                System.out.println("----------------------------------------");
            });
        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}