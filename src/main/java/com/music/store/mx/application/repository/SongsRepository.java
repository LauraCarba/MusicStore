package com.music.store.mx.application.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.music.store.mx.model.Song;

public interface SongsRepository extends JpaRepository<Song, Long> {
  
  List<Song> findAll();
  List<Song> findByAlbumId(Long albumId);
  List<Song> findByAuthor(String author);
  
  @Query(value = "SELECT * FROM Cancion WHERE idCancion = ? && idAlbum = ? LIMIT 1", nativeQuery = true)
  Optional<Song> findByIdAndAlbumId(Long songId, Long albumId);

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM Cancion WHERE idCancion = ? && idAlbum = ?", nativeQuery = true)
  void deleteSong(Long songId, Long albumId);
}
