package com.sliit.music.service.user.impl;

import com.sliit.music.dto.Response;
import com.sliit.music.exceptions.ResourceNotFoundException;
import com.sliit.music.model.Album;
import com.sliit.music.model.Song;
import com.sliit.music.reopository.AlbumRepository;
import com.sliit.music.reopository.SongRepository;
import com.sliit.music.service.user.AlbumService;
import com.sliit.music.service.user.SongService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private SongRepository songRepository;

  @Autowired
  private SongService songService;

  @Override
  public Response saveAlbum(Album album) throws IOException {
    Album savedAlbum = albumRepository.save(album);
    for(Song song: savedAlbum.getSongs()) {
      Song existSong = songRepository.findById(song.getId()).orElseThrow(()-> new ResourceNotFoundException("Album", "id", song.getId()));
      existSong.setAlbum(album);
      songRepository.save(existSong);
    }
    return new Response("00", "album successfully saved!", savedAlbum);
  }

  @Override
  public Response updateAlbum(Album album, Long id) {
    Album oldAlbum = albumRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Album", "id", id));
  oldAlbum.setName(album.getName());
  oldAlbum.setReleasedOn(album.getReleasedOn());
  oldAlbum.setSongs(album.getSongs());
  oldAlbum.setSongs(album.getSongs());
  Album updatedAlbum = albumRepository.saveAndFlush(oldAlbum);
    for(Song song: updatedAlbum.getSongs()) {
      song.setAlbum(album);
      songRepository.save(song);
    }
    return new Response("oo", "successfully updated", updatedAlbum);
  }

  @Override
  public Response deleteAlbum(Long id) {
    Album album = albumRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Album", "id", id));
    albumRepository.delete(album);
    return new Response("00", "successfully deleted");
  }

  @Override
  public Response getAllAlbum() {
    List<Album> albums = albumRepository.findAll();
    return new Response("00", "", albums);
  }
}
