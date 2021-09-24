package com.music.store.mx.application.service;

import java.util.List;
import java.util.Optional;
import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.dto.SongDto;

public interface MusicStoreService {


  List<AlbumDto> retrieveAlbums();
  
  List<AlbumDto> retrieveAlbum(Long albumId);

  List<SongDto> retrieveSongs();

  List<SongDto> retrieveSongs(Long albumId);
  
  Optional<SongDto> getSongByIdAndAlbumId(Long songId, Long albumId);


  public void createAlbum(AlbumDto albumDto);

  public void deleteAlbum(Long albumId);
  
  public void updateAlbum(AlbumDto albumDto);
  
  public void createSong(SongDto songDto);
  
  public void updateSong(SongDto songDto);
  
  public void deleteSong(Long songId);
  
  public void deleteSongByAlbumId(Long albumId);
  
  Boolean updateAlbums(String label);
  Boolean deleteSongId(Long songId, Long albumId);
}

