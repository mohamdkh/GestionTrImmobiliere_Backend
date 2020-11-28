package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Dao.CommuneRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.communes;
import com.example.GestionDesTransactionsImmobilieres.Services.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("DataCommune/")
public class CommuneController {
    @Autowired
    private CommuneService serviceCommune;
    @GetMapping(path = { "/GetData/{id}" })
    public List<String> getImageJustif(@PathVariable("id") double id) throws IOException {
        return serviceCommune.getDataCommune(id);
    }
}
