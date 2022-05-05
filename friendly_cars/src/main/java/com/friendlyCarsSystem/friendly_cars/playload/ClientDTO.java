package com.friendlyCarsSystem.friendly_cars.playload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClientDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String userName;
    private String password;
}
