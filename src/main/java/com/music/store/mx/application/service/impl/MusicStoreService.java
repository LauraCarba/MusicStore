package com.music.store.mx.application.service.impl;

import java.util.List;
import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.application.dto.SongDto;

public interface MusicStoreService {
  
  
  List<AlbumDto>retrieveAlbums();
  List<SongDto>retrieveSongs();
  List<SongDto>retrieveSongs(Long albumId);
}
