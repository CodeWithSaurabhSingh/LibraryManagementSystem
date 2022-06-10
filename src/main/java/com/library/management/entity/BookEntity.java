package com.library.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookid")
	private Long id;

	@Column(name = "bookname", nullable = false)
	private String bookName;

	@Column(name = "authname", nullable = false)
	private String autherName;

	@Column(name = "status")
	private Boolean status = true;

	 @ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userid")
	private UserEntity borrower;

	@Column(name = "borrowdate")
	private LocalDateTime borrowDate;

}
