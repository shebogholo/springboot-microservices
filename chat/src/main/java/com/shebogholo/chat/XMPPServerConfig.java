//package com.shebogholo.chat;
//
//import org.jivesoftware.smack.AbstractXMPPConnection;
//import org.jivesoftware.smack.MessageListener;
//import org.jivesoftware.smack.chat2.Chat;
//import org.jivesoftware.smack.chat2.ChatManager;
//import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
//import org.jivesoftware.smack.filter.MessageTypeFilter;
//import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.packet.Presence;
//import org.jivesoftware.smack.tcp.XMPPTCPConnection;
//import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
//import org.jxmpp.jid.EntityBareJid;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class XMPPServerConfig implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("******************** XMPPService Server Config ********************");
//        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
////                .setUsernameAndPassword("juma","juma")
//                .setXmppDomain("shebogholo.com")
//                .setHost("shebogholo.com")
//                .build();
//
//        AbstractXMPPConnection connection = new XMPPTCPConnection(config);
//        connection.connect();
//        System.out.println("Connected: " + connection.isConnected());
//
//        // connection.login("juma", "juma");
////        connection.login();
//
////        ChatManager chatManager = ChatManager.getInstanceFor(connection);
////        chatManager.addIncomingListener((entityBareJid, message, chat) -> {
////            System.out.println(message.getFrom().asBareJid()+ " said: " + message.getBody());
////        });
////
////        chatManager.addOutgoingListener((entityBareJid, message, chat) -> {
////            System.out.println("I said: " + message.getBody());
////        });
//
//    }
//}
