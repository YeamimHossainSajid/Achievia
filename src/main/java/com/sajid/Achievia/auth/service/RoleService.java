package com.sajid.Achievia.auth.service;

import com.sajid.Achievia.auth.dto.response.CustomRoleResponseDTO;

public interface RoleService {

    public CustomRoleResponseDTO readOne(Long id );
    public String delete( Long id );

}
