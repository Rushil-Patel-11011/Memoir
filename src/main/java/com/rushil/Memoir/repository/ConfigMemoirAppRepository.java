package com.rushil.Memoir.repository;

import com.rushil.Memoir.entity.ConfigMemoirAppEntity;
import com.rushil.Memoir.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigMemoirAppRepository extends MongoRepository <ConfigMemoirAppEntity, ObjectId>{

}
