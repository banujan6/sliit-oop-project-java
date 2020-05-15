package com.sliit.music.service.user;

import com.sliit.music.dto.Response;
import com.sliit.music.model.Song;
import com.sliit.music.model.SongFile;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;


public interface SongService {

  Response addSongs(Song song, Long fileId);
  Response deleteSong(Long songId);
  Response uploadSong(MultipartFile file) throws IOException;
  String downloadFile(Long id);
  Response listAllSongs();


}
