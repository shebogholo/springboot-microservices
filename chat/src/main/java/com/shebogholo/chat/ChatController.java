package com.shebogholo.chat;

import com.shebogholo.chat.utils.Message;
import com.shebogholo.chat.utils.Request;
import org.jxmpp.jid.*;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.jid.parts.Domainpart;
import org.jxmpp.jid.parts.Localpart;
import org.jxmpp.jid.parts.Resourcepart;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat")
public class ChatController {
    private final XMPPService xmppService;

    @Autowired
    public ChatController(XMPPService xmppService) {
        this.xmppService = xmppService;
    }

    @GetMapping("/")
    public Message index() {
        return Message.builder().message("Chat API is working!").build();
    }

    // send message
    @PostMapping("/send")
    public void sendMessage(@RequestBody Request request) {
        if (xmppService.isAuthenticated()) {
            xmppService.sendMessage(request);
        }else{
            xmppService.login("juma", "juma");
            xmppService.sendMessage(request);
        }
    }

    // logout
    @GetMapping("/logout")
    public void logout() {
        xmppService.logout();
    }
}
