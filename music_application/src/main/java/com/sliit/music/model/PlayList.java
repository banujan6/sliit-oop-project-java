package com.sliit.music.model;

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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PlayList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
  private Long id;

  private String name;

  private String createdBy;

  @ManyToMany(fetch = FetchType.EAGER,
      cascade = {
          CascadeType.MERGE
      })
  @JoinTable(name = "playlist_song",
      joinColumns = { @JoinColumn(name = "playlist_id") },
      inverseJoinColumns = { @JoinColumn(name = "song_id") })
  private Set<Song> songSet = new HashSet<Song>();

  public PlayList() {
  }

  public PlayList(String name, String createdBy) {
    this.name = name;
    this.createdBy = createdBy;
  }
}
