package com.ps.flowwork_backend.authentication_service.model.mapper;

import com.ps.flowwork_backend.authentication_service.model.dto.UserRequestDTO;
import com.ps.flowwork_backend.authentication_service.model.dto.UserResponseDTO;
import com.ps.flowwork_backend.authentication_service.model.entity.Users;

public class UserDTOMapper {

    public static UserResponseDTO toDTO(Users user){
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .profileImgURL(user.getProfileImgURL())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static Users toEntity(UserRequestDTO requestDTO){
        return Users.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .build();
    }
}
