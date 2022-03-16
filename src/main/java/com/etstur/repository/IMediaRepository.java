package com.etstur.repository;

import com.etstur.repository.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMediaRepository extends JpaRepository<Media, Long> {
}
