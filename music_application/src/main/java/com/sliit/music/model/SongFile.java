package com.sliit.music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SongFile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
  private Long id;

  private String path;

  @OneToOne(fetch = FetchType.LAZY,
      cascade =  CascadeType.ALL,
      mappedBy = "songFile")
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @Getter(onMethod = @__( @JsonIgnore))
  @Setter
  private Song song;

  public SongFile() {
  }

  public SongFile(String path) {
    this.path = path;
  }
}
