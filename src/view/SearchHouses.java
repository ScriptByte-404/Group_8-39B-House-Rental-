package view;

import database.MysqlConnector;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchHouses extends javax.swing.JFrame {

    private String loggedInUsername = "User";
    private Connection conn;

    // ── Exact Figma colors ────────────────────────────────────────────────────
    private static final Color BG          = new Color(245, 245, 245);
    private static final Color WHITE       = Color.WHITE;
    private static final Color BLUE_NAV    = new Color(13,  71, 161);
    private static final Color TEAL_LOGO   = new Color(0,  176, 170);
    private static final Color BORDER_CLR  = new Color(218, 218, 218);
    private static final Color TEXT_DARK   = new Color(25,  25,  25);
    private static final Color TEXT_MED    = new Color(80,  80,  80);
    private static final Color TEXT_GREY   = new Color(150, 150, 150);
    private static final Color FILTER_BG   = new Color(255, 245, 245);
    private static final Color FILTER_BDR  = new Color(235, 210, 210);
    private static final Color PRICE_CLR   = new Color(13,  71, 161);
    private static final Color CARD_HOVER  = new Color(243, 247, 255);
    private static final Color RESET_HOVER = new Color(245, 245, 245);

    // ── House data ────────────────────────────────────────────────────────────
    private static final Object[][] ALL_HOUSES = {
        {"Modern Apartment", "Pokhara",   "Apartment", 55000},
        {"Luxury Villa",     "Lalitpur",  "Villa",     39990},
        {"Cozy Home",        "Birtamode", "House",     25000},
        {"Cozy Home",        "Birtamode", "House",     25000},
        {"Modern Apartment", "Pokhara",   "Apartment", 55000},
        {"Modern Apartment", "Pokhara",   "Apartment", 55000},
        {"Luxury Villa",     "Lalitpur",  "Villa",     39990},
        {"Luxury Villa",     "Lalitpur",  "Villa",     39990},
        {"Studio Flat",      "Kathmandu", "Flat",      18000},
        {"Family House",     "Bhaktapur", "House",     30000},
        {"Penthouse",        "Pokhara",   "Apartment", 75000},
        {"Garden House",     "Lalitpur",  "House",     22000},
    };

    private JPanel    cardsPanel;
    private JLabel    resultsLabel;
    private JComboBox<String> locationBox, typeBox, minPriceBox, maxPriceBox;

    public SearchHouses()                    { initComponents(); }
    public SearchHouses(String username)     { this.loggedInUsername = username; initComponents(); connectDB(); }

    private void connectDB() {
        try { conn = MysqlConnector.getConnection(); } catch (Exception e) { conn = null; }
    }

    // ══════════════════════════════════════════════════════════════════════════
    private void initComponents() {
        setTitle("GharSathi - Search Houses");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 700));

        JPanel root = new JPanel(null);
        root.setBackground(BG);
        setContentPane(root);

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) { layoutAll(root); }
        });
        layoutAll(root);
    }

    private void layoutAll(JPanel root) {
        root.removeAll();
        int W = Math.max(getWidth(), 1100);
        buildTopBar(root, W);
        buildNavBar(root, W);
        buildFilterPanel(root, W);
        buildResultsArea(root, W);
        root.revalidate();
        root.repaint();
    }

    // ── TOP BAR ───────────────────────────────────────────────────────────────
    private void buildTopBar(JPanel root, int W) {
        JPanel bar = new JPanel(null);
        bar.setBackground(WHITE);
        bar.setBounds(0, 0, W, 60);
        bar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_CLR));
        root.add(bar);

        ImageIcon rawIcon = new ImageIcon(getClass().getResource("rectangle 3.png"));
        Image scaled = rawIcon.getImage().getScaledInstance(40, 50, Image.SCALE_SMOOTH);
        JLabel logoImg = new JLabel(new ImageIcon(scaled));
        logoImg.setBounds(16, 10, 50, 55);
        bar.add(logoImg);

        JLabel logoTxt = new JLabel("<html><b>HOUSE RENTAL</b><br>SYSTEM</html>");
        logoTxt.setFont(new Font("Segoe UI", Font.BOLD, 11));
        logoTxt.setForeground(TEXT_DARK);
        logoTxt.setBounds(62, 12, 130, 40);
        bar.add(logoTxt);

        int searchX = 230, searchW = W - 560;
        JTextField search = new JTextField("Search by Location,house name......");
        search.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        search.setForeground(TEXT_GREY);
        search.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_CLR, 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 40)));
        search.setBounds(searchX, 15, searchW, 32);
        search.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (search.getText().startsWith("Search")) { search.setText(""); search.setForeground(TEXT_DARK); }
            }
            public void focusLost(FocusEvent e) {
                if (search.getText().isEmpty()) { search.setText("Search by Location,house name......"); search.setForeground(TEXT_GREY); }
            }
        });
        bar.add(search);

        JButton searchIcon = new JButton() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TEXT_GREY);
                g2.setStroke(new BasicStroke(2f));
                g2.drawOval(4, 4, 13, 13);
                g2.drawLine(14, 15, 20, 21);
            }
        };
        searchIcon.setOpaque(false); searchIcon.setContentAreaFilled(false);
        searchIcon.setBorderPainted(false); searchIcon.setFocusPainted(false);
        searchIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchIcon.setBounds(searchX + searchW - 34, 17, 26, 28);
        searchIcon.addActionListener(e -> {
            String q = search.getText(); if (q.startsWith("Search")) q = "";
            filterAndShow(q, "All", "All", 0, Integer.MAX_VALUE);
        });
        bar.add(searchIcon);

        JPanel avatarCircle = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(190, 190, 190));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(WHITE);
                g2.fillOval(9, 6, 14, 14);
                g2.fillArc(4, 22, 24, 18, 0, 180);
            }
        };
        avatarCircle.setOpaque(false);
        avatarCircle.setBounds(W - 220, 14, 32, 32);
        bar.add(avatarCircle);

        JLabel userLbl = new JLabel("Hello, " + loggedInUsername);
        userLbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userLbl.setForeground(TEXT_DARK);
        userLbl.setBounds(W - 184, 18, 150, 24);
        bar.add(userLbl);

        JButton hamburger = new JButton() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TEXT_DARK);
                g2.setStroke(new BasicStroke(2f));
                g2.drawLine(4, 7,  22, 7);
                g2.drawLine(4, 14, 22, 14);
                g2.drawLine(4, 21, 22, 21);
            }
        };
        hamburger.setOpaque(false); hamburger.setContentAreaFilled(false);
        hamburger.setBorderPainted(false); hamburger.setFocusPainted(false);
        hamburger.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hamburger.setBounds(W - 48, 16, 30, 28);
        bar.add(hamburger);
    }

    // ── NAV BAR ───────────────────────────────────────────────────────────────
    private void buildNavBar(JPanel root, int W) {
        JPanel nav = new JPanel(null);
        nav.setBackground(WHITE);
        nav.setBounds(0, 60, W, 56);
        nav.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_CLR));
        root.add(nav);

        String[] labels  = {"Dashboard", "Search Houses", "Bookings", "Saved Houses", "Profile"};
        boolean[] active = {false, true, false, false, false};
        int[] iconType   = {0, 1, 2, 3, 4};
        int startX = 26, gap = 10;
        int[] widths = {150, 170, 140, 145, 120};

        int x = startX;
        for (int i = 0; i < labels.length; i++) {
            final String label = labels[i];
            final boolean act  = active[i];
            final int icon     = iconType[i];
            final int bw       = widths[i];
            final int bi       = i;

            JButton btn = new JButton() {
                protected void paintComponent(Graphics g) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    if (act) {
                        g2.setColor(BLUE_NAV);
                        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                    }
                    Color fg = act ? WHITE : TEXT_DARK;
                    g2.setColor(fg);
                    drawNavIcon(g2, icon, 12, getHeight()/2 - 8, 16, 16, act);
                    g2.setFont(new Font("Segoe UI", act ? Font.BOLD : Font.PLAIN, 13));
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(label, 34, getHeight()/2 + fm.getAscent()/2 - 1);
                }
            };
            btn.setOpaque(false); btn.setContentAreaFilled(false);
            if (!act) btn.setBorder(BorderFactory.createLineBorder(BORDER_CLR, 1));
            else       btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setBounds(x, 10, bw, 36);
            btn.addActionListener(e -> handleNav(bi));
            nav.add(btn);
            x += bw + gap;
        }
    }

    private void drawNavIcon(Graphics2D g2, int type, int x, int y, int w, int h, boolean white) {
        g2.setStroke(new BasicStroke(1.5f));
        switch (type) {
            case 0:
                int[] px = {x, x+w/2, x+w}; int[] py = {y+h/2, y, y+h/2};
                g2.drawPolygon(px, py, 3);
                g2.drawRect(x+3, y+h/2, w-6, h/2);
                break;
            case 1:
                if (white) { g2.setColor(TEAL_LOGO); g2.fillOval(x, y, w, h); g2.setColor(WHITE); }
                g2.drawOval(x+3, y+3, w-8, h-8);
                g2.drawLine(x+w-5, y+h-5, x+w-1, y+h-1);
                break;
            case 2:
                g2.drawRoundRect(x, y+2, w, h-2, 3, 3);
                g2.drawLine(x+4, y, x+4, y+4);
                g2.drawLine(x+w-4, y, x+w-4, y+4);
                g2.drawLine(x, y+6, x+w, y+6);
                break;
            case 3:
                g2.drawRect(x+2, y, w-4, h);
                g2.drawLine(x+2, y+h, x+w/2, y+h-5);
                g2.drawLine(x+w-2, y+h, x+w/2, y+h-5);
                break;
            case 4:
                g2.drawOval(x+3, y, w-6, h/2);
                g2.drawArc(x, y+h/2, w, h/2+2, 0, 180);
                break;
        }
    }

    private void handleNav(int idx) {
        switch (idx) {
            case 0: new Dashboard(loggedInUsername).setVisible(true); this.dispose(); break;
            case 1: break;
            default: JOptionPane.showMessageDialog(this, "Coming soon!");
        }
    }

    // ── FILTER PANEL ──────────────────────────────────────────────────────────
    private void buildFilterPanel(JPanel root, int W) {
        JPanel fp = new JPanel(null);
        fp.setBackground(FILTER_BG);
        fp.setBounds(20, 128, W - 40, 112);
        fp.setBorder(BorderFactory.createLineBorder(FILTER_BDR, 1));
        root.add(fp);

        JLabel title = new JLabel("Search Filters");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setForeground(TEXT_DARK);
        title.setBounds(16, 8, 200, 20);
        fp.add(title);

        int lblY = 34, ddY = 54, ddH = 34;
        int[] ddX = {16, 214, 412, 610};
        int[] ddW = {186, 186, 186, 186};

        addFilterLabel(fp, "Location",      ddX[0], lblY);
        addFilterLabel(fp, "Property Type", ddX[1], lblY);
        addFilterLabel(fp, "Min Price",     ddX[2], lblY);
        addFilterLabel(fp, "Max Price",     ddX[3], lblY);

        locationBox = makeCombo(new String[]{"Select Location","Pokhara","Lalitpur","Kathmandu","Birtamode","Bhaktapur"});
        locationBox.setBounds(ddX[0], ddY, ddW[0], ddH);
        fp.add(locationBox);

        typeBox = makeCombo(new String[]{"Select Type","Apartment","Villa","House","Flat"});
        typeBox.setBounds(ddX[1], ddY, ddW[1], ddH);
        fp.add(typeBox);

        minPriceBox = makeCombo(new String[]{"Min Price","Rs 10,000","Rs 20,000","Rs 30,000","Rs 40,000","Rs 50,000"});
        minPriceBox.setBounds(ddX[2], ddY, ddW[2], ddH);
        fp.add(minPriceBox);

        maxPriceBox = makeCombo(new String[]{"Max Price","Rs 20,000","Rs 30,000","Rs 40,000","Rs 50,000","Rs 80,000"});
        maxPriceBox.setBounds(ddX[3], ddY, ddW[3], ddH);
        fp.add(maxPriceBox);

        JButton searchBtn = new JButton() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BLUE_NAV);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
                g2.setColor(WHITE);
                g2.setStroke(new BasicStroke(2f));
                g2.drawOval(8, 8, 14, 14);
                g2.drawLine(19, 19, 25, 25);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
                FontMetrics fm = g2.getFontMetrics();
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString("Search", 30, textY);
            }
        };
        searchBtn.setOpaque(false); searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false); searchBtn.setFocusPainted(false);
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBtn.setBounds(820, ddY, 118, ddH);
        searchBtn.addActionListener(e -> applyFilters());
        fp.add(searchBtn);

        JButton resetBtn = new JButton("Reset");
        resetBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resetBtn.setBackground(WHITE);
        resetBtn.setForeground(TEXT_DARK);
        resetBtn.setBorder(BorderFactory.createLineBorder(BORDER_CLR, 1));
        resetBtn.setFocusPainted(false);
        resetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetBtn.setBounds(948, ddY, 82, ddH);
        resetBtn.addActionListener(e -> {
            locationBox.setSelectedIndex(0); typeBox.setSelectedIndex(0);
            minPriceBox.setSelectedIndex(0); maxPriceBox.setSelectedIndex(0);
            showCards(ALL_HOUSES);
            resultsLabel.setText("Found " + ALL_HOUSES.length + " Properties");
        });
        fp.add(resetBtn);
    }

    private void addFilterLabel(JPanel p, String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(TEXT_DARK);
        lbl.setBounds(x, y, 180, 18);
        p.add(lbl);
    }

    private JComboBox<String> makeCombo(String[] items) {
        JComboBox<String> box = new JComboBox<>(items);
        box.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        box.setBackground(WHITE);
        box.setBorder(BorderFactory.createLineBorder(BORDER_CLR, 1));
        return box;
    }

    // ── RESULTS AREA ──────────────────────────────────────────────────────────
    private void buildResultsArea(JPanel root, int W) {
        resultsLabel = new JLabel("Found " + ALL_HOUSES.length + " Properties");
        resultsLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        resultsLabel.setForeground(TEXT_DARK);
        resultsLabel.setBounds(20, 250, 400, 26);
        root.add(resultsLabel);

        cardsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 14, 14));
        cardsPanel.setBackground(BG);

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setBounds(20, 282, W - 40, getHeight() - 318);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        root.add(scroll);

        showCards(ALL_HOUSES);
    }

    // ── FILTER LOGIC ──────────────────────────────────────────────────────────
    private void applyFilters() {
        String loc  = (String) locationBox.getSelectedItem();
        String type = (String) typeBox.getSelectedItem();
        String minS = (String) minPriceBox.getSelectedItem();
        String maxS = (String) maxPriceBox.getSelectedItem();
        int minV = 0, maxV = Integer.MAX_VALUE;
        try { if (!minS.equals("Min Price")) minV = Integer.parseInt(minS.replace("Rs ","").replace(",","")); } catch (Exception ignored) {}
        try { if (!maxS.equals("Max Price")) maxV = Integer.parseInt(maxS.replace("Rs ","").replace(",","")); } catch (Exception ignored) {}
        filterAndShow("",
            loc.equals("Select Location") ? "All" : loc,
            type.equals("Select Type")    ? "All" : type,
            minV, maxV);
    }

    private void filterAndShow(String kw, String loc, String type, int min, int max) {
        java.util.List<Object[]> res = new java.util.ArrayList<>();
        for (Object[] h : ALL_HOUSES) {
            String n=(String)h[0], l=(String)h[1], t=(String)h[2]; int p=(int)h[3];
            if ((kw.isEmpty() || n.toLowerCase().contains(kw.toLowerCase()) || l.toLowerCase().contains(kw.toLowerCase()))
                && (loc.equals("All")  || l.equalsIgnoreCase(loc))
                && (type.equals("All") || t.equalsIgnoreCase(type))
                && p >= min && p <= max) res.add(h);
        }
        Object[][] arr = res.toArray(new Object[0][]);
        showCards(arr);
        resultsLabel.setText("Found " + arr.length + " Properties");
    }

    // ── CARDS ─────────────────────────────────────────────────────────────────
    private void showCards(Object[][] houses) {
        cardsPanel.removeAll();
        if (houses.length == 0) {
            JLabel empty = new JLabel("No properties found. Try different filters.");
            empty.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            empty.setForeground(TEXT_GREY);
            cardsPanel.add(empty);
        } else {
            for (Object[] h : houses)
                cardsPanel.add(buildCard((String)h[0],(String)h[1],(String)h[2],(int)h[3]));
        }
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private JPanel buildCard(String name, String location, String type, int price) {
        JPanel card = new JPanel(null) {
            private boolean hovered = false;
            {
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
                    public void mouseExited (MouseEvent e) { hovered = false; repaint(); }
                    public void mouseClicked(MouseEvent e) {
                        int c = JOptionPane.showConfirmDialog(SearchHouses.this,
                            name + "\nLocation: " + location + "\nType: " + type
                            + "\nPrice: Rs " + String.format("%,d", price) + " / month"
                            + "\n\nClick OK to request booking.",
                            "Property Details", JOptionPane.OK_CANCEL_OPTION);
                        if (c == JOptionPane.OK_OPTION)
                            JOptionPane.showMessageDialog(SearchHouses.this, "Booking request sent for " + name + "!");
                    }
                });
            }
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hovered ? CARD_HOVER : WHITE);
                g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.setColor(hovered ? BLUE_NAV : BORDER_CLR);
                g2.setStroke(new BasicStroke(hovered ? 1.5f : 1f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
            }
        };
        card.setOpaque(false);
        card.setPreferredSize(new Dimension(220, 200));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel imgArea = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(230, 235, 240));
                g2.fillRect(0, 0, getWidth(), getHeight());
                int cx = getWidth()/2 - 22, cy = getHeight()/2 - 22, iw = 44, ih = 44;
                g2.setColor(TEXT_DARK);
                g2.setStroke(new BasicStroke(2f));
                g2.drawRoundRect(cx, cy, iw, ih, 4, 4);
                g2.fillOval(cx+7, cy+9, 10, 10);
                int[] mpx = {cx+4, cx+20, cx+iw-4};
                int[] mpy = {cy+ih-4, cy+ih/2+4, cy+ih-4};
                g2.fillPolygon(mpx, mpy, 3);
                int[] mpx2 = {cx+16, cx+28, cx+iw};
                int[] mpy2 = {cy+ih-4, cy+ih/2-2, cy+ih-4};
                g2.fillPolygon(mpx2, mpy2, 3);
            }
        };
        imgArea.setBounds(0, 0, 220, 110);
        card.add(imgArea);

        JLabel nameLbl = new JLabel(name);
        nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nameLbl.setForeground(TEXT_DARK);
        nameLbl.setBounds(10, 116, 200, 20);
        card.add(nameLbl);

        JPanel locRow = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TEXT_GREY);
                g2.fillOval(1, 1, 9, 9);
                g2.fillPolygon(new int[]{1,10,5}, new int[]{7,7,16}, 3);
                g2.setColor(WHITE); g2.fillOval(3, 3, 5, 5);
            }
        };
        locRow.setOpaque(false);
        locRow.setBounds(10, 138, 14, 17);
        card.add(locRow);

        JLabel locLbl = new JLabel(location);
        locLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        locLbl.setForeground(TEXT_GREY);
        locLbl.setBounds(26, 138, 180, 17);
        card.add(locLbl);

        JLabel priceLbl = new JLabel(String.format("Rs %,d per month", price));
        priceLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        priceLbl.setForeground(PRICE_CLR);
        priceLbl.setBounds(10, 160, 200, 18);
        card.add(priceLbl);

        return card;
    }

    // ── WRAP LAYOUT ───────────────────────────────────────────────────────────
    static class WrapLayout extends FlowLayout {
        WrapLayout(int align, int hgap, int vgap) { super(align, hgap, vgap); }
        public Dimension preferredLayoutSize(Container t) { return layout(t, true);  }
        public Dimension minimumLayoutSize(Container t)   { return layout(t, false); }
        private Dimension layout(Container target, boolean pref) {
            synchronized (target.getTreeLock()) {
                int tw = target.getSize().width; if (tw==0) tw = Integer.MAX_VALUE;
                Insets ins = target.getInsets();
                int maxW = tw - ins.left - ins.right - getHgap()*2;
                Dimension dim = new Dimension(0,0); int rw=0, rh=0;
                for (int i=0; i<target.getComponentCount(); i++) {
                    Component m = target.getComponent(i); if (!m.isVisible()) continue;
                    Dimension d = pref ? m.getPreferredSize() : m.getMinimumSize();
                    if (rw+d.width > maxW) { dim.width=Math.max(dim.width,rw); dim.height+=rh+getVgap(); rw=0; rh=0; }
                    rw+=d.width+getHgap(); rh=Math.max(rh,d.height);
                }
                dim.width=Math.max(dim.width,rw);
                dim.height+=rh+ins.top+ins.bottom+getVgap()*2;
                return dim;
            }
        }
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new SearchHouses("User").setVisible(true));
    }
}