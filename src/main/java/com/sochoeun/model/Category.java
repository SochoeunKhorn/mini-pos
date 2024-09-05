package com.sochoeun.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private String categoryCode;
    private String imagePath;
    @Column(name = "is_deleted",columnDefinition = "boolean default false")
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Category> children;

    /* == response == */
    /* response parent , response-sub-category */
    /*
    *   {
    *       id:1
    *       name:Coffee
    *       code:001
    *       image:http://image-conffe
    *       children:
    *           [
    *               {
    *                   id:2
    *                   name:Hot
    *                   code:00101
    *                   image:http://image-coffe-hot
    *                   parent:1
    *               },
                    {
     *                   id:3
     *                   name:Ice
     *                   code:00102
     *                   image:http://image-coffe-ice
     *                   parent:1
     *               }
    *           ]
    *   }
    * */
}

