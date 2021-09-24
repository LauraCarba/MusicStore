package com.music.store.mx.application.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.music.store.mx.model.Album;
import com.music.store.mx.model.Song;


public interface AlbumRespository extends JpaRepository<Album, Long> {
  
  List<Album> findAll();
  List<Album> findByAlbumId(Long albumId);
  //List<Album> findById(Long albumId);
 @Transactional
 @Modifying
 @Query(value="UPDATE Album set disquera = ?", nativeQuery =true)
 void updateAlbumsCompany(String label);

}
