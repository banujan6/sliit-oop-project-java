package com.sliit.music.service.user;

import com.sliit.music.dto.Response;
import com.sliit.music.model.Album;
import java.io.IOException;
import java.util.List;

public interface AlbumService {
  Response saveAlbum(Album album) throws IOException;
  Response updateAlbum(Album album, Long id);
  Response deleteAlbum(Long id);
  Response getAllAlbum();

}
