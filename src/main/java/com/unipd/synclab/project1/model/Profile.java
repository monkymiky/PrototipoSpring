// package com.unipd.synclab.project1.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;

// import jakarta.persistence.OneToOne;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class Profile {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Integer id;
//     private String allergie;
//     private String note;

//     @OneToOne(optional = false)
//     @JoinColumn(name = "CLIENT_ID", unique = true)
//     private Client client;
// }
