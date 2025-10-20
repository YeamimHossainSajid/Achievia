package com.example.achivia.auth.repository;

import com.example.achivia.auth.dto.response.CustomUserResponseDTO;
import com.example.achivia.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {


    @Query(value = "SELECT * FROM _user WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmailNative(@Param("email") String email);


    @Query(value = "SELECT * FROM _user WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsernameNative(@Param("username") String username);


    @Query(value = "SELECT * FROM _user WHERE slug = :slug", nativeQuery = true)
    Optional<User> findBySlugNative(@Param("slug") String slug);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM _user WHERE email = :email", nativeQuery = true)
    boolean existsByEmailNative(@Param("email") String email);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM _user WHERE username = :username", nativeQuery = true)
    boolean existsByUsernameNative(@Param("username") String username);


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM _user WHERE slug = :slug", nativeQuery = true)
    boolean existsBySlugNative(@Param("slug") String slug);


    @Query(value = "SELECT id AS id, username AS username, email AS email FROM _user WHERE username LIKE %:username%", nativeQuery = true)
    CustomUserResponseDTO searchByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM _user WHERE id = :id", nativeQuery = true)
    Optional<User> findUserById(@Param("id") UUID id);

    @Query(value = "SELECT id AS id, username AS username, email AS email FROM _user WHERE id = :id", nativeQuery = true)
    CustomUserResponseDTO findUserByUserId(@Param("id") UUID id);

    @Query(value = "SELECT * FROM _user WHERE id IN :ids", nativeQuery = true)
    Set<User> findAllByIdIn(@Param("ids") Set<UUID> ids);

    @Query(value = "SELECT * FROM _user WHERE username = :username OR email = :email", nativeQuery = true)
    User findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);


}
