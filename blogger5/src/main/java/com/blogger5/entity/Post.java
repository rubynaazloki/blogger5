package com.blogger5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "description",nullable = false)
    String description;
    @Column(name = "content", nullable = false)
    String content;

}
