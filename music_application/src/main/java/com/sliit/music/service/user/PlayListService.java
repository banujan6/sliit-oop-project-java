package com.sliit.music.service.user;

import com.sliit.music.dto.Response;
import com.sliit.music.model.PlayList;
import com.sliit.music.model.Song;
import java.util.ArrayList;
import javax.validation.constraints.Size.List;

public interface PlayListService {
  Response createPlayList(PlayList playList);
  Response addSongsToPlayList(ArrayList<Song> songList, Long playListId);
  Response getAllPlayList();
  Response viewPlayList();
  Response deletePlayList(Long playListId);
}
