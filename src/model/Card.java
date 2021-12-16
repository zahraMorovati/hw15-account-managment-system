package model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Card {

    private int cvv2;
    private int cardNumber;


    public static final class CardBuilder {
        private Card card;

        private CardBuilder() {
            card = new Card();
        }

        public static CardBuilder aCard() {
            return new CardBuilder();
        }

        public CardBuilder setCvv2(int cvv2) {
            card.setCvv2(cvv2);
            return this;
        }

        public CardBuilder setCardNumber(int cardNumber) {
            card.setCardNumber(cardNumber);
            return this;
        }

        public CardBuilder but() {
            return aCard().setCvv2(card.getCvv2()).setCardNumber(card.getCardNumber());
        }

        public Card build() {
            return card;
        }
    }
}
