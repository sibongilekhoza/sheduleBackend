package com.sheduleBackend.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {
}
