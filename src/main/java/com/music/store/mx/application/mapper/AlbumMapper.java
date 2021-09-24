package com.music.store.mx.application.mapper;

import com.music.store.mx.application.dto.AlbumDto;
import com.music.store.mx.model.Album;


public class AlbumMapper {
  private AlbumMapper() {
    super();
  }
  
  public static Album toModel(AlbumDto albumDto) {
    return null;
  }
  public static AlbumDto toDto(Album album) {
    
    return AlbumDto.builder()
    .albumId(album.getAlbumId())
    .title(album.getTitle())
    .launching(album.getLaunching())
    .label(album.getLabel())
    .singer(album.getSinger())
    .gender(album.getGender())
    .coverage(album.getCoverage())
    .build();
    
  }
}
