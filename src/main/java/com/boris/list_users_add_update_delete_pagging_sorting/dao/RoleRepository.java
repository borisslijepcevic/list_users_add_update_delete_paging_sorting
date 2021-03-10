package com.boris.list_users_add_update_delete_pagging_sorting.dao;

import com.boris.list_users_add_update_delete_pagging_sorting.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
