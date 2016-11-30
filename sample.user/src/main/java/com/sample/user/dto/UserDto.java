package com.sample.user.dto;

import com.sample.permission.dto.RoleDto;
import com.sample.user.model.Profile;
import com.sample.user.model.enums.UserType;

import java.util.Set;

/**
 * Created by andongxu on 16-11-18.
 */
public class UserDto {

    private Long id;

    private String name;

    private String username;

    private UserType userType;

    private ProfileDto profileDto;

    private AuthorityDto authorityDto;

    private Set<RoleDto> roleDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
    }

    public AuthorityDto getAuthorityDto() {
        return authorityDto;
    }

    public void setAuthorityDto(AuthorityDto authorityDto) {
        this.authorityDto = authorityDto;
    }

    public Set<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(Set<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }
}
