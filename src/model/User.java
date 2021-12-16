package model;

import lombok.Data;
import model.enumation.TypeUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.usertype.UserType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private int nationalCode;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="user_ID")
    private List<Account> accountList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private TypeUser userType;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date userEnteredDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date userLastUpdatedDate;
    @Embedded
    private Card card;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactionList = new ArrayList<>();

    public static final class UserBuilder {
        private User user;

        private UserBuilder() {
            user = new User();
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder setId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder setName(String name) {
            user.setName(name);
            return this;
        }

        public UserBuilder setFamily(String family) {
            user.setFamily(family);
            return this;
        }

        public UserBuilder setNationalCode(int nationalCode) {
            user.setNationalCode(nationalCode);
            return this;
        }

        public UserBuilder setAccountList(List<Account> accountList) {
            user.setAccountList(accountList);
            return this;
        }

        public UserBuilder setUserType(TypeUser userType) {
            user.setUserType(userType);
            return this;
        }

        public UserBuilder setUserEnteredDate(Date userEnteredDate) {
            user.setUserEnteredDate(userEnteredDate);
            return this;
        }

        public UserBuilder setUserLastUpdatedDate(Date userLastUpdatedDate) {
            user.setUserLastUpdatedDate(userLastUpdatedDate);
            return this;
        }

        public UserBuilder setCard(Card card) {
            user.setCard(card);
            return this;
        }

        public UserBuilder setTransactionList(List<Transaction> transactionList) {
            user.setTransactionList(transactionList);
            return this;
        }

        public UserBuilder but() {
            return anUser().setId(user.getId()).setName(user.getName()).setFamily(user.getFamily()).setNationalCode(user.getNationalCode()).setAccountList(user.getAccountList()).setUserType(user.getUserType()).setUserEnteredDate(user.getUserEnteredDate()).setUserLastUpdatedDate(user.getUserLastUpdatedDate()).setCard(user.getCard()).setTransactionList(user.getTransactionList());
        }

        public User build() {
            return user;
        }
    }

    @Override
    public String toString() {
        return
                " id=" + id +"\n" +
                " name='" + name + "\n" +
                " family='" + family + "\n" +
                " nationalCode=" + nationalCode +"\n" +
                " userType=" + userType +"\n" +
                " userEnteredDate=" + userEnteredDate +"\n" +
                " userLastUpdatedDate=" + userLastUpdatedDate +"\n" +
                " card number=" + card.getCardNumber() +"cvv2= "+card.getCvv2();
    }
}
