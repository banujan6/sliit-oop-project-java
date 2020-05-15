package com.sliit.music.controller;

import com.sliit.music.dto.Response;
import com.sliit.music.model.Song;
import com.sliit.music.service.user.SongService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SongController {
  @Autowired
  private SongService songService;

  @PostMapping(path = "/upload")
  public @ResponseBody
  ResponseEntity<Response> uploadSong(@RequestParam("file") MultipartFile file) throws IOException {
    return ResponseEntity.ok(songService.uploadSong(file));
  }

  @GetMapping("/download/fileId/{fileId}")
  public void downloadFileFromLocal(@PathVariable Long fileId, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    File file = new File(songService.downloadFile(fileId));
    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
    if (mimeType == null) {
      mimeType = "application/octet-stream";
    }
    response.setContentType(mimeType);
    response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
    response.setContentLength((int) file.length());
    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
    FileCopyUtils.copy(inputStream, response.getOutputStream());
  }

  @PostMapping(path = "/song/fileId/{fileId}")
  public @ResponseBody ResponseEntity<Response> addSong(@RequestBody Song song,
      @PathVariable Long fileId) {
    return ResponseEntity.ok(songService.addSongs(song, fileId));
  }

  @GetMapping(path = "/songs")
  public @ResponseBody ResponseEntity<Response> getAllSongs() {
    return ResponseEntity.ok(songService.listAllSongs());
  }
}
