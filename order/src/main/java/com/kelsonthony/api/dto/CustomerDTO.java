package com.kelsonthony.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Long age;
}
