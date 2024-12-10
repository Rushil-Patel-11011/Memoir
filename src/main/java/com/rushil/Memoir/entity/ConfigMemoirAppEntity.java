package com.rushil.Memoir.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="config_memoir_app")
@Data
@NoArgsConstructor(force = true)
public class ConfigMemoirAppEntity {

    private String key;
    private String value;
}
