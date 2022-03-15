package com.etstur.etsturtask.repository;

import com.etstur.etsturtask.repository.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {

}
