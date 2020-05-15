package com.sliit.music.controller;

import com.sliit.music.dto.Response;
import com.sliit.music.model.Album;
import com.sliit.music.service.user.AlbumService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumController {
  @Autowired
  private AlbumService albumService;

  @PostMapping(path = "/album")
  public @ResponseBody
  ResponseEntity<Response> saveUser(@RequestBody Album album) throws IOException {
    return ResponseEntity.ok(albumService.saveAlbum(album));
  }

  @PutMapping(path = "/album/albumId/{albumId}")
  public @ResponseBody ResponseEntity<Response> updateUser(@RequestBody Album album,
      @PathVariable Long albumId) {
    return ResponseEntity.ok(albumService.updateAlbum(album,albumId));
  }

  @DeleteMapping(path = "/album/albumId/{albumId}")
  public @ResponseBody ResponseEntity<Response> deleteAlbum(@PathVariable Long albumId){
    return ResponseEntity.ok(albumService.deleteAlbum(albumId));
  }

  @GetMapping(path = "/albums")
  public @ResponseBody ResponseEntity<Response> listAlbums() {
    return ResponseEntity.ok(albumService.getAllAlbum());
  }

}
