package com.meatshop.model;

public class ShopLocation {
    private String title;
    private double lat, lon;
    private boolean sellsMeat, is24h, familyFriendly, takeAway;

    public ShopLocation(String title, double lat, double lon, boolean sellsMeat, boolean is24h, boolean familyFriendly, boolean takeAway) {
        this.title = title;
        this.lat = lat;
        this.lon = lon;
        this.sellsMeat = sellsMeat;
        this.is24h = is24h;
        this.familyFriendly = familyFriendly;
        this.takeAway = takeAway;

    }

    public String getTitle() {
        return title;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopLocation that = (ShopLocation) o;

        if (that.sellsMeat && !sellsMeat) return false;
        if (that.is24h && !is24h) return false;
        if (that.familyFriendly && !familyFriendly) return false;
        if (that.takeAway && !takeAway) return false;
        return  true;
    }

    @Override
    public int hashCode() {
        int result = (sellsMeat ? 1 : 0);
        result = 31 * result + (is24h ? 1 : 0);
        result = 31 * result + (familyFriendly ? 1 : 0);
        result = 31 * result + (takeAway ? 1 : 0);
        return result;
    }
}