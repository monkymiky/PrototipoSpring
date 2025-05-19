// package com.unipd.synclab.project1.model;

// import java.time.LocalDateTime;
// import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
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
// public class Reservation {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Integer id;
//     private LocalDateTime date;
//     @ManyToOne
//     @JoinColumn
//     private Client client;
//     @ManyToOne
//     @JoinColumn
//     private Worker worker;
//     @OneToMany
//     private List<TreatmentReservation> treatment;
// }
