package com.music.store.mx.application.mapper;

import com.music.store.mx.application.dto.SongDto;
import com.music.store.mx.model.Song;

public class SongMapper {
  private SongMapper() {
    super();
    
  }
  public static Song toModel(SongDto songDto) {
    return null;
    
  }
  public static SongDto toDto(Song song) {
    return SongDto.builder()
        .songId(song.getSongId())
        .title(song.getTitle())
        .albumId(song.getAlbumId())
        .length(song.getLength())
        .author(song.getAuthor())
        .build();
    
  }
}
