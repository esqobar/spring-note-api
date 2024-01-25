package com.collins.backend.repositories;

import com.collins.backend.entities.Note;
import com.collins.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findByTitle(String title);

    List<Note> findByUserEmail(String email);
    void deleteByUser(User user);

    long count();
    @Query("SELECT COUNT(n) from Note n WHERE n.user.email = :email")
    long countByEmail(@Param("email") String email);
}
