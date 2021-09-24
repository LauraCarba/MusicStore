package com.music.store.mx.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Album")
@Getter
@Setter
public class Album {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="idAlbum", nullable = false, length = 11)
  private Long albumId;
  
  @Column(name="titulo", nullable = false, length = 45)
  private String title;
  
  @Column(name="lanzamiento", nullable = false)
  private Date launching;
  
  @Column(name="disquera", nullable = false, length = 45)
  private String label;
  
  @Column(name="cantante", nullable = false, length = 45)
  private String singer;
  
  @Column(name="genero", nullable = false, length = 45)
  private String gender;
  
  @Column(name="cobertura", nullable = false, length = 45)
  private String coverage;
  

}
