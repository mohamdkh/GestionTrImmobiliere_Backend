package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Entities.Piece_justificative;
import com.example.GestionDesTransactionsImmobilieres.Entities.photo_bien;
import com.example.GestionDesTransactionsImmobilieres.Services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("Upload/")
public class ImageUploadController {
    @Autowired
    private UploadService UploadService;
    @PostMapping("/uploadJustif")
    public int uplaodImageJustif(@RequestParam("imageFile") MultipartFile file) throws IOException {

        return  UploadService.uplaodImage(file);
    }
    @GetMapping(path = { "/getJustif" })
    public Piece_justificative getImageJustif(@RequestParam("id") int id) throws IOException {
        return UploadService.getImage(id);
    }
    @PostMapping("/uploadImageBien")
    public ResponseEntity<String> uplaodImageBien(@RequestParam("imageFile") MultipartFile file,@RequestParam("id_annonce") long id) throws IOException {
        System.out.println(id);
         return  ResponseEntity.status(HttpStatus.OK).body(UploadService.uplaodImageBien(file,id));
    }
    @GetMapping(path = { "/getImageBien" })
    public List<photo_bien> getImageBien(@RequestParam("idannonce") Long id) throws IOException {
        return UploadService.getImagesBien(id);
   }
    @GetMapping(path = { "/GetPhotoBien" })
    public List<photo_bien> GetPhotoBien(){
        return UploadService.GetListPhotos();
    }

}
