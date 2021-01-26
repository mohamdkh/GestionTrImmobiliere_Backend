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
    @GetMapping("getMails")
    public List<Discussion> getAllMails(@RequestParam("status") String status){

        return discussionService.getAllMessage(status);
    }
    @GetMapping("getMailByID")
    public Discussion getAllMails(@RequestParam("id") Long id){

        return discussionService.GetMessageByID(id);
    }
    @GetMapping("/changeStatus")
    public boolean changeStatus(@RequestParam("id") Long id){
        return discussionService.changeStatus(id);
    }
}
