// package com.unipd.synclab.project1.model;

// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
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
// public class Worker {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Integer id;
//     private String name;
//     @OneToMany(cascade = CascadeType.ALL)
//     private List<Reservation> reservations;
//     @ManyToMany
//     @JoinTable(name = "WORKER_SPEC")
//     private List<Specialization> specializations;

// }
