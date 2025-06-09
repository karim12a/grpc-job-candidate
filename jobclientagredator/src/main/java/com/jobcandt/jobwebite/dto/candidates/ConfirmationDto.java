package com.jobcandt.jobwebite.dto.candidates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationDto {
    private  String confirmationId;
    private  String confirmationMessage;
}
