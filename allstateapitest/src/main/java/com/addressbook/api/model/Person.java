package com.addressbook.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author Ankush Thakur
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Please provide first Name")
    @NotEmpty(message = "Please provide first Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "first name must be alphanumeric and start with character")
    @Column(name = "firstName")
    private String firstName;

    @NotNull(message = "Please provide last Name")
    @NotEmpty(message = "Please provide last Name")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-]*$", message = "last name must be alphanumeric and start with character")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Please provide phone numbers")
    @NotEmpty(message = "Please provide phone numbers")
    @Column(name = "phoneNum")
    @ElementCollection(targetClass=String.class)
    private List<String> phoneNum;

    @NotNull(message = "Please provide address")
    @NotEmpty(message = "Please provide address")
    @Column(name = "addresses")
    @ElementCollection(targetClass=String.class)
   private List<String> addresses;
}
