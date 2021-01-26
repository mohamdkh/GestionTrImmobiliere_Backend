package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.DiscussionRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Discussion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {
    @Autowired
    DiscussionRepository discussionRepository;
    public void SendMail(Discussion discussion){
        discussion.setStatus("non lu");
        discussionRepository.save(discussion);
    }
    public List<Discussion> getAllMessage(String status){
        if(status.equals("all")){
            return discussionRepository.findAll();
        }
        return discussionRepository.getAllMessage(status.toLowerCase());
    }
    public Discussion GetMessageByID(Long id){
        Optional<Discussion> disc= discussionRepository.findById(id);
        if(disc.isPresent()){
            return discussionRepository.findById(id).get();
        }
        return null;
    }
    public boolean changeStatus( Long id) {
        Optional<Discussion> disc= discussionRepository.findById(id);
        if(disc.isPresent()){
            Discussion discussion=disc.get();
            discussion.setStatus("lu");
            discussionRepository.save(discussion);
            return true;
        }
        return false;
    }
}
