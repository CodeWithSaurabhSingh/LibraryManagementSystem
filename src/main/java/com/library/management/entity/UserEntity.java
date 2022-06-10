package com.library.management.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "userid")
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	
	@Column(name = "emailid" ,nullable = false)
	private String userEmailId;
	
	@Column(name = "contactno" , nullable = false)
	private Long userContactNo;
	
	@Column(name = "libcardno")
	private String libCardNo;
	
	@OneToMany(mappedBy = "borrower",cascade = CascadeType.ALL)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<BookEntity> books;

	
}
