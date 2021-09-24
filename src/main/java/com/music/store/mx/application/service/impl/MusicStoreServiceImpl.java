package com.music.store.mx.application.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;
import com.music.store.mx.application.service.MusicStoreService;
import com.music.store.mx.model.Album;
import com.music.store.mx.model.Song;
import lombok.extern.slf4j.Slf4j;
import com.music.store.mx.application.controller.MusicStoreInputException;
import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.mapper.AlbumMapper;
import com.music.store.mx.application.dto.SongDto;
import com.music.store.mx.application.mapper.SongMapper;
import com.music.store.mx.application.repository.AlbumRespository;
import com.music.store.mx.application.repository.SongsRepository;


@Service
@Slf4j
public class MusicStoreServiceImpl implements MusicStoreService {

  private AlbumRespository albumRespository;
  private SongsRepository songsRepository;



  public MusicStoreServiceImpl(AlbumRespository albumRespository, SongsRepository songsRepository) {

    this.albumRespository = albumRespository;
    this.songsRepository = songsRepository;

  }

  @Override
  public List<AlbumDto> retrieveAlbums() {
    return this.albumRespository.findAll().stream().map(AlbumMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<SongDto> retrieveSongs() {
    return this.songsRepository.findAll().stream().map(SongMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<SongDto> retrieveSongs(Long albumId) {

    return this.songsRepository.findByAlbumId(albumId).stream().map(SongMapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public List<AlbumDto> retrieveAlbum(Long albumId) {

    return this.albumRespository.findByAlbumId(albumId).stream().map(AlbumMapper::toDto)
        .collect(Collectors.toList());
  }

  
  @Override
  public void createAlbum(AlbumDto albumDto) {


    Album album = new Album();

    album.setTitle(albumDto.getTitle());
    album.setLaunching(albumDto.getLaunching());
    album.setLabel(albumDto.getLabel());
    album.setSinger(albumDto.getSinger());
    album.setGender(albumDto.getGender());
    album.setCoverage(albumDto.getCoverage());

    

    albumRespository.save(album);
  }

  @Override
  public void deleteAlbum(Long albumId) {


    log.info("Service :: deleteAlbum : ", albumId);
    albumRespository.deleteById(albumId);



  }

  @Override
  public void updateAlbum(AlbumDto albumDto) {

    java.util.Optional<Album> optionalAlbum = albumRespository.findById(albumDto.getAlbumId());
    if (optionalAlbum.isPresent()) {
      Album updateAlbum = optionalAlbum.get();
      updateAlbum.setTitle(albumDto.getTitle());
      updateAlbum.setLaunching(albumDto.getLaunching());
      updateAlbum.setLabel(albumDto.getLabel());
      updateAlbum.setSinger(albumDto.getSinger());
      updateAlbum.setGender(albumDto.getGender());
      updateAlbum.setCoverage(albumDto.getCoverage());

      albumRespository.save(updateAlbum);
    }

  }
  @Override
  public Boolean updateAlbums(String label) {
 
   if (this.retrieveAlbums()!=null) {
     this.albumRespository.updateAlbumsCompany(label);
     return true;
   }else {
     return false;
   }
      
    
  }

  
  
  @Override
  public void createSong(SongDto songDto) {
    Song song = new Song();

    song.setTitle(songDto.getTitle());
    song.setAlbumId(songDto.getAlbumId());
    song.setAuthor(songDto.getAuthor());
    song.setLength(songDto.getLength());



    songsRepository.save(song);
  }

  @Override
  public void updateSong(SongDto songDto) {

    java.util.Optional<Song> optionalSong = songsRepository.findById(songDto.getSongId());
    if (optionalSong.isPresent()) {
      Song updateSong = optionalSong.get();
      updateSong.setTitle(songDto.getTitle());
      updateSong.setAlbumId(songDto.getAlbumId());
      updateSong.setAuthor(songDto.getAuthor());
      updateSong.setLength(songDto.getLength());

      songsRepository.save(updateSong);
    }
  }

  @Override
  public void deleteSong(Long songId) {

    songsRepository.deleteById(songId);
  }
//elimina cancion por id de album y por id de cancion
  @Override
  public void deleteSongByAlbumId(Long albumId) {
    songsRepository.deleteAll(songsRepository.findByAlbumId(albumId));
   
  }

  @Override
  public Optional<SongDto> getSongByIdAndAlbumId(Long songId, Long albumId) {
    
    return this.songsRepository.findByIdAndAlbumId(songId, albumId).map(SongMapper::toDto);
  }

  @Override
  public Boolean deleteSongId(Long songId, Long albumId) {
  if(this.getSongByIdAndAlbumId(songId, albumId).isPresent()) {
    this.songsRepository.deleteSong(songId, albumId);
    return true;
    
  }else {
    return false;
  }
  }


    
  }
  
  




