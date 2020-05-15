package com.sliit.music.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Entity
public class Song {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
  private Long id;

  private String name;
  private String genre;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "album_id")
  @Getter(onMethod = @__( @JsonIgnore))
  @Setter
  private Album album;


  @ManyToMany(fetch = FetchType.LAZY,
      mappedBy = "songSet")
  @Getter(onMethod = @__( @JsonIgnore))
  @Setter
  private Set<PlayList> playListSet = new HashSet<PlayList>();

  @OneToOne(fetch = FetchType.LAZY)
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @JoinColumn(name = "song_file_id")
  private SongFile songFile;


  @ManyToMany(fetch = FetchType.EAGER,
      cascade = {
          CascadeType.MERGE
      })
  @JoinTable(name = "artist_song",
      joinColumns = { @JoinColumn(name = "song_id") },
      inverseJoinColumns = { @JoinColumn(name = "artist_id") })
  private Set<User> user = new HashSet<User>();

  public Song() {
  }

  public Song(String name, String genre) {
    this.name = name;
    this.genre = genre;
  }
}

