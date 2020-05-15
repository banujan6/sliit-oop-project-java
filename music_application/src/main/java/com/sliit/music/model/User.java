package com.sliit.music.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate Id...
  private Long id;

  private String name;

  @Column(name = "email", length = 50, unique = true)
  private String email;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  private String role; // admin, user, artist

  @ManyToMany(fetch = FetchType.LAZY,
      mappedBy = "user")
  @Getter(onMethod = @__( @JsonIgnore))
  @Setter
  private Set<Song> songSet = new HashSet<Song>();

  public User() {
  }

  public User(String name, String email, String password, String role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
