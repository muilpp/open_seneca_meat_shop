package com.meatshop.model;

import android.content.Context;

import com.meatshop.BuildConfig;
import com.meatshop.R;

import java.util.Arrays;
import java.util.List;

public class StaticData {

    public static List<IntroItem> getIntroItemList(Context context) {
        IntroItem introItem1 = new IntroItem(context.getString(R.string.item_1_title), context.getString(R.string.item_1_description), BuildConfig.UNSPLASH_BASE_URL+"meat");
        IntroItem introItem2 = new IntroItem(context.getString(R.string.intro_2_title), context.getString(R.string.intro_2_description), BuildConfig.UNSPLASH_BASE_URL+"truck");
        IntroItem introItem3 = new IntroItem(context.getString(R.string.intro_3_title), context.getString(R.string.intro_3_description), BuildConfig.UNSPLASH_BASE_URL+"restaurant");
        IntroItem introItem4 = new IntroItem(context.getString(R.string.intro_4_title), context.getString(R.string.intro_4_description), BuildConfig.UNSPLASH_BASE_URL+"ham");

        return Arrays.asList(introItem1, introItem2, introItem3, introItem4);
    }

    public static List<ShopLocation> getShopLocationList() {
        ShopLocation shop1 = new ShopLocation("Sagrada Familia", 41.4036299,2.1721618, false, false, false, true);
        ShopLocation shop2 = new ShopLocation("Arc de Triomf", 41.3910524,2.1784509, false, false, true, true);
        ShopLocation shop3 = new ShopLocation("Plaça Catalunya", 41.3870154,2.1678531, false, true, false, true);
        ShopLocation shop4 = new ShopLocation("Glòries", 41.4022242,2.183341, true, false, false, true);
        ShopLocation shop5 = new ShopLocation("Castell de Montjuïc", 41.362959,2.1628696, true, true, false, false);
        ShopLocation shop6 = new ShopLocation("Parc Güell", 41.4144948,2.1505005, true, false, true, false);
        ShopLocation shop7 = new ShopLocation("Liceu", 41.3801969,2.1711008, true, true, true, false);
        ShopLocation shop8 = new ShopLocation("Port Olímpic", 41.3860518,2.1987874, false, true, true, false);
        ShopLocation shop9 = new ShopLocation("Poblenou", 41.403701,2.202642, false, false, true, false);
        ShopLocation shop10 = new ShopLocation("Gràcia", 41.3993005,2.1522323, true, true, true, true);

        return Arrays.asList(shop1, shop2, shop3, shop4, shop5, shop6, shop7, shop8, shop9, shop10);
    }
}
