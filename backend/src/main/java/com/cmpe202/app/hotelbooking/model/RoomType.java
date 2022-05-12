package com.cmpe202.app.hotelbooking.model;

import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROOM_TYPE", catalog = "hotel_app")
@ToString
@JsonAutoDetect
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "$$_hibernate_interceptor" })
public class RoomType {


	@Id
	@Column(name = "type_id")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Column(name = "room_type")
	private String roomType;

	@Column(name = "description")
	private String description;

	@Column(name = "room_capacity")
	private int roomCapacity;

	@OneToMany(mappedBy = "roomType", cascade = CascadeType.MERGE, orphanRemoval = true)
	@JsonIgnoreProperties({"roomType"})
	private List<Room> rooms;
}
