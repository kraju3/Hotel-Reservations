package com.kiran.raju.learning.spring.data.repository;

import com.kiran.raju.learning.spring.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface GuestRepository extends CrudRepository<Guest,Long> {

}
