package com.example.bookstoreapp.dto;

import com.example.bookstoreapp.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusDto {

    @NotNull(message = "Status is required")
    private Status status;
}
