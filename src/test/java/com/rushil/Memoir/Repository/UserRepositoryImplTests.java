package com.rushil.Memoir.Repository;

import com.rushil.Memoir.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;
    @Test
    public void testSaveNewUser(){
        Assert.notNull(userRepository.getUserForSA());
    }
}
