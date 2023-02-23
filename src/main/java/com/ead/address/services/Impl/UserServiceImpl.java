package com.ead.address.services.Impl;


import com.ead.address.models.UserModel;
import com.ead.address.repositories.UserRepository;
import com.ead.address.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

   @Transactional
    @Override
    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }
}
