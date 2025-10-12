package com.example.achivia.auth.repository;

import com.example.achivia.auth.dto.response.CustomUserResponseDTO;
import com.example.achivia.auth.model.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, Long > {

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    Optional<CustomUserResponseDTO> findUserProjectionById(@Param("userId") UUID userId);


    User findByUsername(String username);

    @EntityGraph( attributePaths = { "roles" } )
    User findByUsernameOrEmail(String username, String email );

    @Query("""
            SELECT u FROM User u where u.username=:username
            """)
    CustomUserResponseDTO searchByUsername(String username );

    boolean existsByEmail( String email );

    @EntityGraph( attributePaths = { "roles" } )
    @Query( """
                SELECT
                    user
                FROM
                    User user
                WHERE
                    user.id = :id
            """ )
    CustomUserResponseDTO findUserByUserId(@Param( "id" ) UUID id );

    User findById(@NotNull( message = "User id can't be null or empty." ) UUID uuid);
}
