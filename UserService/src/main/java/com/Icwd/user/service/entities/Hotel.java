package com.Icwd.user.service.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Hotel {

	private Long id;
	private String name;
	private String location;
	private String about;


}