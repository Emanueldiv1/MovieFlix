package com.movieflix.movie.entity;

import com.movieflix.category.entity.Category;
import com.movieflix.streaming.entity.Streaming;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "realease_date")
    private LocalDateTime releaseDate;

    @CreationTimestamp // preenchimento automatico
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp // preenchimento automatico
    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    private Double rating;

    @ManyToMany
    @JoinTable(name = "movie_category",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )

    private List<Streaming> streamings;


}
