package com.okta.springbootvue.SpringBootVueApplication;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Todo {
    
    @Id @GeneratedValue
    private Long id;

    private @NonNull String text;

    private Boolean completed = false;
    
}