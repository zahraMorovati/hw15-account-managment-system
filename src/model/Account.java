package model;

import lombok.Data;
import model.enumation.AccountType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    private int id;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    private double balance;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList = new ArrayList<>(3);


    public static final class AccountBuilder {
        private Account account;

        private AccountBuilder() {
            account = new Account();
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder setId(int id) {
            account.setId(id);
            return this;
        }

        public AccountBuilder setAccountType(AccountType accountType) {
            account.setAccountType(accountType);
            return this;
        }

        public AccountBuilder setCreateDate(Date createDate) {
            account.setCreateDate(createDate);
            return this;
        }

        public AccountBuilder setModifyDate(Date modifyDate) {
            account.setModifyDate(modifyDate);
            return this;
        }

        public AccountBuilder setBalance(double balance) {
            account.setBalance(balance);
            return this;
        }

        public AccountBuilder setUser(User user) {
            account.setUser(user);
            return this;
        }

        public AccountBuilder setTransactionList(List<Transaction> transactionList) {
            account.setTransactionList(transactionList);
            return this;
        }

        public AccountBuilder but() {
            return anAccount().setId(account.getId()).setAccountType(account.getAccountType()).setCreateDate(account.getCreateDate()).setModifyDate(account.getModifyDate()).setBalance(account.getBalance()).setUser(account.getUser()).setTransactionList(account.getTransactionList());
        }

        public Account build() {
            return account;
        }
    }

    @Override
    public String toString() {
        return
                " account number =" + id +
                " accountType=" + accountType +
                " createDate=" + createDate +
                " modifyDate=" + modifyDate +
                " balance=" + balance;
    }
}
