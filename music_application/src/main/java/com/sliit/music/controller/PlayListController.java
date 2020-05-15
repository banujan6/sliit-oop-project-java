package com.sliit.music.controller;

import com.sliit.music.dto.Response;
import com.sliit.music.model.PlayList;
import com.sliit.music.service.user.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayListController {
  @Autowired
  private PlayListService playListService;

  @PostMapping(path = "/playlist")
  public @ResponseBody
  ResponseEntity<Response> createPlayList(@RequestBody PlayList playList){
    return ResponseEntity.ok(playListService.createPlayList(playList));
  }

  @GetMapping(path = "/playlists")
  public @ResponseBody ResponseEntity<Response> getAllPlaylist() {
    return ResponseEntity.ok(playListService.getAllPlayList());
  }

  @DeleteMapping(path = "/playlist/playlist_id/{playlist_id}")
  public @ResponseBody ResponseEntity<Response> deletePlayList(@PathVariable Long playlist_id){
    return ResponseEntity.ok(playListService.deletePlayList(playlist_id));
  }

}
