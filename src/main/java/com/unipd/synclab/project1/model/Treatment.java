// package com.unipd.synclab.project1.model;

// import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class Treatment {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//     private String name;
//     private Integer duration;
//     private Integer price;
    
//     @OneToMany
//     private List<TreatmentReservation> reservation;
// }
