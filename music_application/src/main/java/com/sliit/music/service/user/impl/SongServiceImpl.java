package com.sliit.music.service.user.impl;

import com.sliit.music.dto.Response;
import com.sliit.music.exceptions.MusicStoreException;
import com.sliit.music.exceptions.ResourceNotFoundException;
import com.sliit.music.model.Song;
import com.sliit.music.model.SongFile;
import com.sliit.music.model.User;
import com.sliit.music.reopository.SongFileRepository;
import com.sliit.music.reopository.SongRepository;
import com.sliit.music.reopository.UserRepository;
import com.sliit.music.service.user.SongService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SongServiceImpl implements SongService {

  @Value("${upload_image.path}")
  private String PATH;

  @Autowired
  private SongFileRepository songFileRepository;

  @Autowired
  private SongRepository songRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Response addSongs(Song song, Long fileId) {
    SongFile songFile = songFileRepository.findById(fileId).orElseThrow(()-> new ResourceNotFoundException("song file", "id", fileId));

    if(songFile == null) {
      throw new MusicStoreException("song file is required");
    }
    song.setSongFile(songFile);
    Song savedSong = songRepository.save(song);
    return new Response("00", "song successfully saved", savedSong);
  }

  @Override
  public Response listAllSongs() {
    List<Song> songs = songRepository.findAll();
    return new Response("00", "listed songs", songs);
  }

  @Override
  public Response deleteSong(Long songId) {
    return null;
  }

  @Override
  public Response uploadSong(MultipartFile file) throws IOException {
    byte[] bytes = file.getBytes();
    Path path = Paths.get(PATH, file.getOriginalFilename());
    Files.write(path, bytes);
    SongFile songFile = new SongFile();
    songFile.setPath(file.getOriginalFilename());
    SongFile savedSongFile = songFileRepository.save(songFile);
    return new Response("00", "file successfully uploaded", savedSongFile);
  }

  @Override
  public String downloadFile(Long id) {
    SongFile songFile = songFileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Song File", "id", id));
    return songFile.getPath();
  }
}
