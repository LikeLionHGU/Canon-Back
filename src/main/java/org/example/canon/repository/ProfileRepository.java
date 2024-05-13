package org.example.canon.repository;

import org.example.canon.entity.Profile;
import org.example.canon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile  findByUser(User user);
}
