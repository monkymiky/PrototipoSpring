
// package com.unipd.synclab.project1.model;

// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.MapsId;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// public class TreatmentReservation {
//     @EmbeddedId
//     private TreatmentReservationId id;

//     @ManyToOne
//     @MapsId("reservation")
//     @JoinColumn(name = "reservation", referencedColumnName = "id")
//     private Reservation reservation;

//     @ManyToOne
//     @MapsId("treatment")
//     @JoinColumn(name = "treatment", referencedColumnName = "id")
//     private Treatment treatment;
// }
