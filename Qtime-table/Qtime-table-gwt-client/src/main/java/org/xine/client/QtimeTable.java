package org.xine.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("synthetic-access")
public class QtimeTable implements EntryPoint {
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    @SuppressWarnings("unused")
    private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. Please check your network connection and try again.";

    /** The Constant REFRESH_INTERVAL IN MS. */
    private static final int REFRESH_INTERVAL = 5000;

    /** The main panel. */
    private final VerticalPanel mainPanel = new VerticalPanel();

    /** The stocks flex table. */
    private final FlexTable stocksFlexTable = new FlexTable();

    /** The add panel. */
    private final HorizontalPanel addPanel = new HorizontalPanel();

    /** The new symbol text box. */
    private final TextBox newSymbolTextBox = new TextBox();

    /** The add stock button. */
    private final Button addStockButton = new Button("Add");

    /** The last updated label. */
    private final Label lastUpdatedLabel = new Label();

    /**
     * Create a remote service proxy to talk to the server-side Greeting service.
     */
    // private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    /** The stocks. */
    private final ArrayList<String> stocks = new ArrayList<>();

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        this.stocksFlexTable.setText(0, 0, "Symbol");
        this.stocksFlexTable.setText(0, 1, "Price");
        this.stocksFlexTable.setText(0, 2, "Change");
        this.stocksFlexTable.setText(0, 3, "Remove");
        // Add styles to elements in the stock list table.
        this.stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
        this.stocksFlexTable.addStyleName("watchList");
        this.stocksFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
        this.stocksFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
        this.stocksFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
        this.stocksFlexTable.setCellPadding(6);

        // Assemble Add Stock panel.
        this.addPanel.add(this.newSymbolTextBox);
        this.addPanel.add(this.addStockButton);
        this.addPanel.addStyleName("addPanel");

        // Assemble Main panel.
        this.mainPanel.add(this.stocksFlexTable);
        this.mainPanel.add(this.addPanel);
        this.mainPanel.add(this.lastUpdatedLabel);

        // Associate the Main panel with the HTML host page.
        RootPanel.get("stockList").add(this.mainPanel);

        // Move cursor focus to the input box.
        this.newSymbolTextBox.setFocus(true);

        // Listen for mouse events on the Add button.
        this.addStockButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                addStock();
            }

        });

        // Listen for keyboard events in the input box.
        this.newSymbolTextBox.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(final KeyDownEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    addStock();
                }
            }
        });

        // refresh list automatically
        final Timer timer = new Timer() {
            @Override
            public void run() {
                refreshWatchList();
            }
        };
        timer.scheduleRepeating(REFRESH_INTERVAL);

    }

    /**
     * Refresh watch list.
     * Generate random stock prices.
     */
    private void refreshWatchList() {
        final double maxPrice = 100.0;
        final double maxPriceChange = 0.02;

        final StockPrice[] prices = new StockPrice[this.stocks.size()];
        for (int i = 0; i < this.stocks.size(); i++) {
            final double p = Random.nextDouble() * maxPrice;
            final double c = p * maxPriceChange * (Random.nextDouble() * 2.0 - 1.0);
            prices[i] = new StockPrice(this.stocks.get(i), p, c);
        }

        updateTable(prices);
    }

    private void updateTable(final StockPrice[] prices) {
        if (prices == null) {
            return;
        }

        for (final StockPrice stockPrice : prices) {
            updateTable(stockPrice);
        }

        // Display timestamp showing last refresh.
        this.lastUpdatedLabel.setText("Last update : " + DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(new Date()));
    }

    private void updateTable(final StockPrice price) {
        // do some thing just if the stock is on stock table
        if (this.stocks.contains(price.getSymbol())) {
            final int row = this.stocks.indexOf(price.getSymbol()) + 1;

            final String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
            final NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
            final String changeText = changeFormat.format(price.getChange());
            final String changePercentText = changeFormat.format(price.getChangePercent());

            // populate the price and change fields with new data.
            this.stocksFlexTable.setText(row, 1, priceText);

            final Label changeWidget = (Label) this.stocksFlexTable.getWidget(row, 2);
            changeWidget.setText(changeText + " (" + changePercentText + "%)");

            // this.stocksFlexTable.setText(row, 2, changeText + "(" + changePercentText + "%)");

            // Change the color of text in the Change field based on its value.
            String changeStyleName = "noChange";
            if (price.getChangePercent() < -0.1f) {
                changeStyleName = "negativeChange";
            } else if (price.getChangePercent() > 0.1f) {
                changeStyleName = "positiveChange";
            }

            changeWidget.setStyleName(changeStyleName);

        }

    }

    /**
     * Adds the stock.
     */
    private void addStock() {
        final String symbol = this.newSymbolTextBox.getText().toUpperCase().trim();
        this.newSymbolTextBox.setFocus(true);

        // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
        if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
            Window.alert("'" + symbol + "' is not a valid symbol.");
            this.newSymbolTextBox.selectAll();
            return;
        }

        this.newSymbolTextBox.setText("");

        // Don't add the stock if it's already in the table.
        if (this.stocks.contains(symbol)) {
            return;
        }

        // Add the stock to the table.
        final int row = this.stocksFlexTable.getRowCount();
        this.stocks.add(symbol);
        this.stocksFlexTable.setText(row, 0, symbol);
        this.stocksFlexTable.setWidget(row, 2, new Label());
        this.stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
        this.stocksFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
        this.stocksFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");

        // Add a button to remove this stock from the table.
        final Button removeStockButton = new Button("x");
        removeStockButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent event) {
                final int removeIndex = QtimeTable.this.stocks.indexOf(symbol);
                QtimeTable.this.stocks.remove(removeIndex);
                QtimeTable.this.stocksFlexTable.removeRow(removeIndex + 1);
            }
        });
        removeStockButton.addStyleDependentName("remove");

        // final Button removeStockButton = new Button("x", new ClickHandler() {
        // @Override
        // public void onClick(final ClickEvent event) {
        // final int removeIndex = QtimeTable.this.stocks.indexOf(symbol);
        // QtimeTable.this.stocks.remove(removeIndex);
        // QtimeTable.this.stocksFlexTable.removeRow(removeIndex + 1);
        // }
        // });
        this.stocksFlexTable.setWidget(row, 3, removeStockButton);

        // Get the stock price.
        refreshWatchList();

    }
}
