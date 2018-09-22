package vn.hcmute.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "person",fetch = FetchType.LAZY)
    private Person person;
}
