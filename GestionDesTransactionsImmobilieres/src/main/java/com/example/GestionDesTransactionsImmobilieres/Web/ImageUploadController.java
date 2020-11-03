package com.example.GestionDesTransactionsImmobilieres.Web;

import com.example.GestionDesTransactionsImmobilieres.Dao.Piece_justifRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Piece_justificative;
import com.example.GestionDesTransactionsImmobilieres.Services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GestionDesTransactionsImmobilieres.Services.UploadService;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("Upload/")
public class ImageUploadController {
    @Autowired
    private UploadService UploadService;
    @PostMapping("/uploadJustif")
    public ResponseEntity<String> uplaodImageJustif(@RequestParam("imageFile") MultipartFile file) throws IOException {
        return  ResponseEntity.status(HttpStatus.OK).body(UploadService.uplaodImage(file));
    }
    @GetMapping(path = { "/getJustif" })
    public String getImageJustif(@RequestParam("id") int id,@RequestParam("path") String path) throws IOException {
        return UploadService.getImage(id,path);
    }
    @PostMapping("/uploadImageBien")
    public ResponseEntity<String> uplaodImageBien(@RequestParam("imageFile") MultipartFile file) throws IOException {
        return  ResponseEntity.status(HttpStatus.OK).body(UploadService.uplaodImageBien(file));
    }
    @GetMapping(path = { "/getImageBien" })
    public String getImageBien(@RequestParam("id") Long id,@RequestParam("path") String path) throws IOException {
        return UploadService.getImageBien(id,path);
    }

}
