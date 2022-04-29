package com.shopservice.riasneakers.repository;

import com.shopservice.riasneakers.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,String> {

}
