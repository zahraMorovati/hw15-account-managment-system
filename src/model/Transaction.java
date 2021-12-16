package model;

import lombok.Data;
import model.enumation.TransactionType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne
    private User user;
    @ManyToOne
    private Account account;


    public static final class TransactionBuilder {
        private Transaction transaction;

        private TransactionBuilder() {
            transaction = new Transaction();
        }

        public static TransactionBuilder aTransaction() {
            return new TransactionBuilder();
        }

        public TransactionBuilder setId(int id) {
            transaction.setId(id);
            return this;
        }

        public TransactionBuilder setModifyDate(Date modifyDate) {
            transaction.setModifyDate(modifyDate);
            return this;
        }

        public TransactionBuilder setTransactionType(TransactionType transactionType) {
            transaction.setTransactionType(transactionType);
            return this;
        }

        public TransactionBuilder setUser(User user) {
            transaction.setUser(user);
            return this;
        }

        public TransactionBuilder setAccount(Account account) {
            transaction.setAccount(account);
            return this;
        }

        public TransactionBuilder but() {
            return aTransaction().setId(transaction.getId()).setModifyDate(transaction.getModifyDate()).setTransactionType(transaction.getTransactionType()).setUser(transaction.getUser()).setAccount(transaction.getAccount());
        }

        public Transaction build() {
            return transaction;
        }
    }

    @Override
    public String toString() {
        return
                " id=" + id +"\n" +
                " modifyDate=" + modifyDate +"\n" +
                " transactionType=" + transactionType ;
    }
}
