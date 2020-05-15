package com.sliit.music.service.user.impl;

import com.sliit.music.dto.Response;
import com.sliit.music.model.PlayList;
import com.sliit.music.model.Song;
import com.sliit.music.reopository.PlayListRepository;
import com.sliit.music.service.user.PlayListService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListServiceImpl implements PlayListService {

  @Autowired
  private PlayListRepository playListRepository;

  @Override
  public Response createPlayList(PlayList playList) {
    PlayList savedPlayList = playListRepository.saveAndFlush(playList);
    return new Response("00", "successfully saved", savedPlayList);
  }

  @Override
  public Response addSongsToPlayList(ArrayList<Song> songList, Long playListId) {
    return null;
  }

  @Override
  public Response getAllPlayList() {
    List<PlayList> playLists = playListRepository.findAll();
    return new Response("00", "successfully listed", playLists);
  }

  @Override
  public Response viewPlayList() {
    return null;
  }

  @Override
  public Response deletePlayList(Long playListId) {
    playListRepository.deleteById(playListId);
    return new Response("00", "successfully deleted");
  }
}
