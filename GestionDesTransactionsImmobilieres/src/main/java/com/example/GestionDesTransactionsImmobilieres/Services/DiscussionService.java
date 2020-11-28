package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.DiscussionRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionService {
    @Autowired
    DiscussionRepository discussionRepository;
    public void SendMail(Discussion discussion){
        discussion.setStatus("non lu");
        discussionRepository.save(discussion);
    }
    public List<Discussion> getAllMessage(){
        return discussionRepository.getAllMessage();
    }
}
