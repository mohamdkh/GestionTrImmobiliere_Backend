package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Entities.Discussion;
import com.example.GestionDesTransactionsImmobilieres.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Discussion/")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @PostMapping("Send")
    public void SendMail(@RequestBody  Discussion discussion){

       discussionService.SendMail(discussion);
    }
    @GetMapping("getAllMails")
    public List<Discussion> getAllMails(){

        return discussionService.getAllMessage();
    }
}
