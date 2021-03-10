package com.boris.list_users_add_update_delete_pagging_sorting.service;

import com.boris.list_users_add_update_delete_pagging_sorting.dao.UserRepository;
import com.boris.list_users_add_update_delete_pagging_sorting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {

        Optional<User> result = userRepository.findById(theId);

        User theUser = null;

        if(result.isPresent()){
            theUser = result.get();
        } else {
            throw new RuntimeException("User id not found " + theId);
        }

        return theUser;
    }

    @Override
    public void saveUser(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
        Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1 , pageSize, sort);
        return this.userRepository.findAll(pageable);
    }
}
