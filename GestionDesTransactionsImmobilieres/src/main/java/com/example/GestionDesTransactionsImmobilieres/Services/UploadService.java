package com.example.GestionDesTransactionsImmobilieres.Services;

import com.example.GestionDesTransactionsImmobilieres.Dao.Photo_bienRepository;
import com.example.GestionDesTransactionsImmobilieres.Dao.Piece_justifRepository;
import com.example.GestionDesTransactionsImmobilieres.Entities.Piece_justificative;
import com.example.GestionDesTransactionsImmobilieres.Entities.photo_bien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private File file;
    private  List<photo_bien> photosAnnonce;
    public int uplaodImage( MultipartFile file) {
        try{

            Piece_justificative img = new Piece_justificative(compressBytes(file.getBytes()), file.getOriginalFilename());
            img.setExtension(file.getContentType());
            Piece_justificative img1=ReposJustif.save(img);
            return img1.getId();
        }catch (Exception e){
            System.out.println( e.getMessage());
            return 0;
        }

    }
    public Piece_justificative getImage(int id) throws IOException {
        final Optional<Piece_justificative> retrievedImage = JustificateurRepos.findById(id);
        if(retrievedImage.isPresent()){
            String FullName =retrievedImage.get().getFullName();
            Piece_justificative img = new Piece_justificative(decompressBytes(retrievedImage.get().getImage()),
                    retrievedImage.get().getExtension(),retrievedImage.get().getFullName());
            return img;
        }
        else{
            return null;
        }
    }
    public List<photo_bien> getImagesBien(long id) throws IOException {
        photosAnnonce=new ArrayList<photo_bien>();
       Photo_bienRepos.findAll().forEach(img->{
           if(img.getId_annonce()==id){
               photosAnnonce.add(new photo_bien(decompressBytes(img.getImage()),img.getFullName(), (int) img.getId_annonce()));
           }
       });
       return photosAnnonce;


    }
    public String uplaodImageBien( MultipartFile file,long id) {
        try{
            photo_bien img = new photo_bien(compressBytes(file.getBytes()), file.getOriginalFilename(), (int) id);
            Photo_bienRepos.save(img);
        }catch (Exception e){
            return e.getMessage();
        }
        return "l'importation a été bien exécuté";
    }
    public List<photo_bien> GetListPhotos() {
    	List<photo_bien> photos=new ArrayList<photo_bien>();
    	photo_bien photo=new photo_bien();
    	 Photo_bienRepos.findAll().forEach(elem->{
    		photos.add(new photo_bien(decompressBytes(elem.getImage()), elem.getFullName(), elem.getId_annonce()));
    	});
    	 return photos;
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
