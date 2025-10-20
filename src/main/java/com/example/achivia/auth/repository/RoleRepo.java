package com.example.achivia.auth.repository;

import com.example.achivia.auth.dto.response.CustomRoleResponseDTO;
import com.example.achivia.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {


    @Query(value = """
            SELECT r.id, r.role_type AS roleType
            FROM roles r
            WHERE r.id = :id
            """, nativeQuery = true)
    CustomRoleResponseDTO findRoleById(@Param("id") Long id);

    @Query(value = """
            SELECT *
            FROM roles r
            WHERE r.id = :id
            """, nativeQuery = true)
    Optional<Role> findById(@Param("id") Long id);


    @Query(value = """
            SELECT *
            FROM roles r
            WHERE r.role_type = :roleType
            """, nativeQuery = true)
    Role findByRoleType(@Param("roleType") String roleType);


    @Query(value = """
            SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
            FROM roles r
            WHERE r.role_type = :roleType
            """, nativeQuery = true)
    boolean existsByRoleType(@Param("roleType") String roleType);

    @Modifying
    @Query(value = """
            INSERT INTO roles (role_type)
            VALUES (:roleType)
            """, nativeQuery = true)
    void save(@Param("roleType") String roleType);

    @Modifying
    @Query(value = """
            DELETE FROM roles
            WHERE id = :id
            """, nativeQuery = true)
    void delete(@Param("id") Long id);

    @Query(value = """
        SELECT *
        FROM roles r
        WHERE r.id IN :ids
        """, nativeQuery = true)
    Set<Role> findAllByIdIn(@Param("ids") Set<Long> ids);

}

