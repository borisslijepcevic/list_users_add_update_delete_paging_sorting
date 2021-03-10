package com.boris.list_users_add_update_delete_pagging_sorting.service;



import com.boris.list_users_add_update_delete_pagging_sorting.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

     public List<User> findAll();

     public User findById(int theId);

     public void saveUser(User theUser);

     public void deleteById(int theId);

     Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
