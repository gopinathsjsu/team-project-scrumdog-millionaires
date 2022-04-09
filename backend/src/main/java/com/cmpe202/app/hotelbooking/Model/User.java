package com.cmpe202.app.hotelbooking.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance
@DiscriminatorColumn(name="user_type")
@Table(name = "user")
@JsonAutoDetect
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;

    @Column(name = "USER_EMAIL", unique=true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @NotEmpty(message = "*Please provide your first name name")
    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_MIDDLE_NAME")
    private String middleName;

    @NotEmpty(message = "*Please provide your last name")
    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @NotEmpty(message = "*Please provide a password")
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_CREATED_ON")
    @CreationTimestamp
    private Timestamp userCreatedOn;

    @Column(name = "USER_END_DATE")
    private Date userEndDate;

    @Column(name="USER_MODIFIED_ON",columnDefinition = "now()")
    @UpdateTimestamp
    private Timestamp  userModiedfOn;

    @Column(name="ACTIVE")
    private boolean active;

    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="ADDRESS_ID",referencedColumnName = "address_id")
    private Address address;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
