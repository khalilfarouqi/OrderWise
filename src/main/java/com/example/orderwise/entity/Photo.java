package com.example.orderwise.entity;

import com.example.orderwise.common.dto.PhotoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="photos")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
}
