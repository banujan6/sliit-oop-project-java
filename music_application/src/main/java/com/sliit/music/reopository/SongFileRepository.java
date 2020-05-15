package com.sliit.music.reopository;

import com.sliit.music.model.SongFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongFileRepository extends JpaRepository<SongFile, Long> {

}
