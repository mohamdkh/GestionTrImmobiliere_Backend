package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.Photo_bienRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.Piece_justifRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Piece_justificative;
import com.example.GestionDesTransactionsImmobilieres.Entities.photo_bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class UploadService {
    @Autowired
    private Photo_bienRepository Photo_bienRepos;
    @Autowired
    private Piece_justifRepository JustificateurRepos;
    @Autowired
    private Piece_justifRepository ReposJustif;
    private String FullPath;
    public int uplaodImage( MultipartFile file) {
        try{

            Piece_justificative img = new Piece_justificative(compressBytes(file.getBytes()), file.getOriginalFilename());
            Piece_justificative img1=ReposJustif.save(img);
            return img1.getId();
        }catch (Exception e){
            System.out.println( e.getMessage());
            return 0;
        }

    }
    public String getImage(int id,String path) throws IOException {
        FullPath="";
        final Optional<Piece_justificative> retrievedImage = JustificateurRepos.findById(id);
        String FullName =retrievedImage.get().getFullName();
        // Piece_justificative img = new Piece_justificative(decompressBytes(retrievedImage.get().getImage()));
        try (FileOutputStream fileOuputStream = new FileOutputStream(path+"/"+FullName)){
            fileOuputStream.write(decompressBytes(retrievedImage.get().getImage()));
            return FullName;
        }catch (Exception e){
            return "error :" + e.getMessage();
        }

    }
    public String uplaodImageBien( MultipartFile file) {
        try{
            photo_bien img = new photo_bien(compressBytes(file.getBytes()), file.getOriginalFilename());
            Photo_bienRepos.save(img);
        }catch (Exception e){
            return e.getMessage();
        }
        return "l'importation a été bien exécuté";
    }
    public String getImageBien(Long id,String path) throws IOException {
        FullPath="";
        final Optional<photo_bien> retrievedImage = Photo_bienRepos.findById(id);
        String FullName =retrievedImage.get().getFullName();
        // Piece_justificative img = new Piece_justificative(decompressBytes(retrievedImage.get().getImage()));
        try (FileOutputStream fileOuputStream = new FileOutputStream(path+"/"+FullName)){
            fileOuputStream.write(decompressBytes(retrievedImage.get().getImage()));
            return path+"/"+FullName;
        }catch (Exception e){
            return "error :" + e.getMessage();
        }

    }
    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

}
