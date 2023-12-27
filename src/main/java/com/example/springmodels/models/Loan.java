package com.example.springmodels.models;

import com.example.springmodels.models.Copy;
import com.example.springmodels.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "loan_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    @Column(name = "return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
