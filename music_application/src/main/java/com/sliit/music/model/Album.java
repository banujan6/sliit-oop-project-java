package com.sliit.music.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
    private Long id;

    private String name;

    private Date releasedOn;

    @OneToMany(cascade = {
        CascadeType.MERGE
    }, mappedBy = "album")
    private Set<Song> songs = new HashSet<Song>();

    public Album() {
    }
}

