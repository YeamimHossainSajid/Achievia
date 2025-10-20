package com.example.achivia.auth.repository;

import com.example.achivia.auth.dto.response.CustomUserResponseDTO;
import com.example.achivia.auth.model.User;
import com.example.achivia.auth.model.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    @Query(value = """
            SELECT *
            FROM users u
            WHERE u.username = :username
            """, nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value = """
            SELECT u.id AS id, u.username AS username, u.email AS email
            FROM users u
            WHERE u.id = :id
            """, nativeQuery = true)
    CustomUserResponseDTO findUserByUserId(@Param("id") UUID id);

    @Query(value = """
            SELECT *
            FROM users u
            WHERE u.id = :id
            """, nativeQuery = true)
    Optional<User> findById(@Param("id") UUID id);

    @Query(value = """
            SELECT u.id AS id, u.username AS username, u.email AS email
            FROM users u
            WHERE u.username LIKE %:username%
            """, nativeQuery = true)
    CustomUserResponseDTO searchByUsername(@Param("username") String username);

    @Query(value = """
            SELECT *
            FROM roles r
            WHERE r.id IN :ids
            """, nativeQuery = true)
    Set<Role> findAllByIdIn(@Param("ids") Set<Long> ids);

    @Modifying
    @Query(value = """
            INSERT INTO users (id, username, email, password)
            VALUES (:id, :username, :email, :password)
            """, nativeQuery = true)
    void save(@Param("id") UUID id,
                  @Param("username") String username,
                  @Param("email") String email,
                  @Param("password") String password);

    @Modifying
    @Query(value = """
            UPDATE users
            SET username = :username,
                email = :email,
                password = :password
            WHERE id = :id
            """, nativeQuery = true)
    void updateUser(@Param("id") UUID id,
                    @Param("username") String username,
                    @Param("email") String email,
                    @Param("password") String password);

    @EntityGraph( attributePaths = { "roles" } )
    User findByUsernameOrEmail(String username, String email );

}
