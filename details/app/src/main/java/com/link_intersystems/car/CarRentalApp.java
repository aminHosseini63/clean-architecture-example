package com.link_intersystems.car;

import com.link_intersystems.car.booking.CarBookingConfig;
import com.link_intersystems.car.offers.CarOfferConfig;
import com.link_intersystems.car.offers.ui.CarOfferComponentConfig;
import com.link_intersystems.car.ui.MainComponentConfig;
import com.link_intersystems.car.ui.MainFrame;
import com.link_intersystems.plugins.ApplicationContext;

public class CarRentalApp {

    public static void main(String[] args) {
        CarRentalApp carRentalApp = new CarRentalApp();
        carRentalApp.run();
    }

    private void run() {
        ApplicationContext applicationContext = new ApplicationContext();
        CarOfferConfig carOfferUseCaseConfig = new CarOfferConfig();
        CarBookingConfig carBookingConfig = new CarBookingConfig();

        CarOfferComponentConfig carOfferComponentConfig = new CarOfferComponentConfig(carOfferUseCaseConfig, carBookingConfig);
        MainComponentConfig mainComponentConfig = new MainComponentConfig(carOfferComponentConfig);

        MainFrame mainFrame = mainComponentConfig.getMainComponent(applicationContext);
        mainFrame.show();
    }
}
