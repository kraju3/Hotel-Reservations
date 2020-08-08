package com.kiran.raju.learning.spring.data.repository;


import com.kiran.raju.learning.spring.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {


}
