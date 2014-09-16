package org.xine.client;

/**
 * The Class StockPrice.
 */
public class StockPrice {

    /** The symbol. */
    private String symbol;

    /** The price. */
    private double price;

    /** The change. */
    private double change;

    public StockPrice(final String symbol, final double price, final double change) {
        super();
        setSymbol(symbol);
        setPrice(price);
        setChange(change);
    }

    public StockPrice() {
        super();
    }

    public double getChangePercent() {
        return 100.0 * this.change / this.price;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getChange() {
        return this.change;
    }

    public void setChange(final double change) {
        this.change = change;
    }
}
