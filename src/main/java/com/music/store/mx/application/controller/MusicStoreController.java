package com.music.store.mx.application.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.dto.SongDto;
import com.music.store.mx.application.repository.AlbumRespository;
import com.music.store.mx.application.repository.SongsRepository;
import com.music.store.mx.application.service.MusicStoreService;
import com.music.store.mx.model.Album;
import com.music.store.mx.model.Song;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MusicStoreController {

  @Autowired
  private AlbumRespository albumRepository;
  private SongsRepository songsRepository;
  private MusicStoreService musicStoreService;

  public MusicStoreController(MusicStoreService musicStoreService) {
    this.musicStoreService = musicStoreService;
  }

  @GetMapping(value = "/api/v1/albums", produces = "application/json")
  public ResponseEntity<List<AlbumDto>> getAlbums() {
    return new ResponseEntity<>(musicStoreService.retrieveAlbums(), HttpStatus.OK);

  }

  @GetMapping(value = "/api/v1/songs", produces = "application/json")
  public ResponseEntity<List<SongDto>> getSongs() {
    return new ResponseEntity<>(musicStoreService.retrieveSongs(), HttpStatus.OK);
  }

  @GetMapping(value = "/api/v1/album/{albumId}/songs", produces = "application/json")
  public ResponseEntity<List<SongDto>> getSongs(@PathVariable(name = "albumId") Long albumId) {
    return new ResponseEntity<>(musicStoreService.retrieveSongs(albumId), HttpStatus.OK);
  }


  @GetMapping(value = "/api/v1/albumByAlbumId/{albumId}", produces = "application/json")
  public ResponseEntity<List<AlbumDto>> getAlbumById(@PathVariable(name = "albumId") Long albumId) {
    return new ResponseEntity<>(musicStoreService.retrieveAlbum(albumId), HttpStatus.OK);
  }



  @GetMapping(value = "/api/v1/mensaje")
  public String getMessage() {
    return "HolaMundo!!";
  }

  @PostMapping(value = "/api/v1/save")
  public ResponseEntity<Album> createAlbum(@RequestBody AlbumDto albumdto) {
    
    this.musicStoreService.createAlbum(albumdto);

    return null;
  }

  @DeleteMapping(value = "/api/v1/delete/{albumId}")
  public ResponseEntity<Void> deleteAlbum(@PathVariable("albumId") Long albumId) {

    log.info("Delete album: " + albumId);

    musicStoreService.deleteAlbum(albumId);
    return ResponseEntity.ok(null);
  }

  @PutMapping(value = "/api/v1/update/{albumId}")
  public ResponseEntity<Album> updateAlbum(@RequestBody AlbumDto albumDto) {
    this.musicStoreService.updateAlbum(albumDto);
    return null;
  }

  @PutMapping(value = "/api/v1/update/albums")
  public ResponseEntity updateAlbums(@RequestBody AlbumDto albumDto) {
    if (musicStoreService.updateAlbums(albumDto.getLabel())) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(value = "/api/v1/saveSong")
  public ResponseEntity<Song> createSong(@RequestBody SongDto songdto) {

    this.musicStoreService.createSong(songdto);

    return null;
  }



  @PutMapping(value = "/api/v1/updateSong/{songId}")
  public ResponseEntity<Song> updateSong(@RequestBody SongDto songDto) {
    this.musicStoreService.updateSong(songDto);
    return null;
  }

  @DeleteMapping(value = "/api/v1/deleteSong/{songId}")
  public ResponseEntity<Void> deleteSong(@PathVariable("songId") Long songId) {



    musicStoreService.deleteSong(songId);
    return ResponseEntity.ok(null);
  }

  @DeleteMapping(value = "/api/v1/album/{albumId}/songs")
  public ResponseEntity<Void> deleteSongByAlbumId(@PathVariable("albumId") Long albumId) {



    musicStoreService.deleteSongByAlbumId(albumId);
    return ResponseEntity.ok(null);
  }

  // Cancion poor id de album
  @GetMapping(value = "/api/v1/albums/{albumId}/songs/{SongId}", produces = "application/json")
  public ResponseEntity<SongDto> getSongById(@PathVariable("albumId") Long albumId,
      @PathVariable("SongId") Long songId) {
    return musicStoreService.getSongByIdAndAlbumId(songId, albumId)
        .map(song -> new ResponseEntity<>(song, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

  // Eliminar cancion poor id de album y id de cancion
  @DeleteMapping(value = "/api/v1/albums/{albumId}/songs/{songId}")
  public ResponseEntity deleteSong(@PathVariable("albumId") Long albumId, @PathVariable("songId") Long songId) {
    if (musicStoreService.deleteSongId(songId, albumId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
