package com.sheduleBackend.Repositories;

import com.sheduleBackend.Enums.Role;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.Subject;
import com.sheduleBackend.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   public User findByEmail(String email);
   public Boolean existsByEmail(String email);

   public Optional<Staff> findByStaffNo(long staffNo);

    public List<Staff> findAllByUserRole(Role role);



    @Query("SELECT COUNT(u) FROM User u WHERE TYPE(u) = Staff")
    long countStaff();


}
