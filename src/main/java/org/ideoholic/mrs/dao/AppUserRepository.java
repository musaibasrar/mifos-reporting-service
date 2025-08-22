package org.ideoholic.mrs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.ideoholic.mrs.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u WHERE u.isSelfServiceUser = false")
    List<AppUser> findNonSelfServiceUsers();
}
